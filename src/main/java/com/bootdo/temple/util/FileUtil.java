package com.bootdo.temple.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.enums.FileServiceTypeEnum;
import com.bootdo.common.prop.FileProp;

@Component
public class FileUtil {
	private final String BASE_TEMP_PATH = "temp/";
	
	@Autowired
	private FileDirUtil fileDirUtil;
	@Autowired
	private FileProp fileProp;

	@PostConstruct
	public void init() {
		//创建临时目录
		File file = new File(fileProp.getBasePath() + BASE_TEMP_PATH);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 获取文件名后缀
	 * 
	 * @param file
	 * @return
	 */
	public String getFileSuffix(File file) {
		return getFileSuffix(file.getName());
	}
	public String getFileSuffix(String fileName) {
		int idx = fileName.lastIndexOf(".");
		if (idx == -1) {
			return "";
		}
		return fileName.substring(idx + 1);
	}

	/**
	 * 给文件重命名
	 * 
	 * serviceType_uuid.suffix
	 * 
	 * @param file
	 * @param serviceType
	 * @return
	 */
	@SuppressWarnings("unused")
	private String rename(File file, int serviceType) {
		StringBuilder sb = new StringBuilder();
		sb.append(serviceType).append("_").append(CommonUtil.getUUID())
				.append(".").append(getFileSuffix(file));
		return sb.toString();
	}
	
	private String rename(String fileName, int serviceType) {
		StringBuilder sb = new StringBuilder();
		sb.append(serviceType).append("_").append(CommonUtil.getUUID())
		.append(".").append(getFileSuffix(fileName));
		return sb.toString();
	}

	
	/**
	 * 保存临时文件
	 * 
	 * @param src
	 * @param serviceType
	 * @return
	 */
	public SaveFileResult saveTempFile(MultipartFile msrc, int serviceType) throws IOException{
		String newFileName = rename(msrc.getOriginalFilename(), serviceType);
		String savePath = fileProp.getBasePath() + BASE_TEMP_PATH + newFileName;
		
		File src = new File(savePath);
		msrc.transferTo(src);
		
		SaveFileResult result = new SaveFileResult();
		result.setFileName(newFileName);
		result.setFullPath(savePath);
		result.setRelativePath(BASE_TEMP_PATH);
		return result;
	}
	
	/**
	 * 保存文件
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException 
	 */
	public SaveFileResult saveFile(String filePath) throws FileNotFoundException, IOException {
		SaveFileResult result = new SaveFileResult();
		if (filePath.startsWith(BASE_TEMP_PATH)) {
			//临时文件转正式目录
			File file = new File(fileProp.getBasePath() + filePath);
			if (!file.exists() || !file.isFile()) {
				throw new FileNotFoundException("文件没找到" + filePath);
			}
			
			//解析文件的业务类型
			int serviceType;
			String fileName = file.getName();
			int idx = fileName.indexOf("_");
			if (idx == -1) {
				serviceType = 9999;			//会选择默认存储路径
			} else {
				serviceType = Integer.parseInt(fileName.substring(0, idx));
			}
			String newFilePath = fileDirUtil.getSavePath(serviceType);
			String fullPath = fileProp.getBasePath() + newFilePath + fileName;
			FileUtils.copyFile(file, new File(fullPath));
			file.delete();			//把临时文件删除
			
			result.setFileName(fileName);
			result.setFullPath(fullPath);
			result.setRelativePath(newFilePath);
		} else {
			//判断是否已经存在，存在就不需要再保存
			File file = new File(fileProp.getBasePath() + filePath);
			if (!file.exists() || !file.isFile()) {
				throw new FileNotFoundException("文件没找到" + filePath);
			}
			
			result.setFileName(file.getName());
			result.setFullPath(fileProp.getBasePath() + filePath);
			result.setRelativePath(filePath.substring(0, filePath.length() - result.getFileName().length()));
		}
		
		return result;
	}
	
	
	/**
	 * 保存文件
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException 
	 */
	public SaveFileResult saveFile(String filePath, MultipartFile mf) throws FileNotFoundException, IOException {
		SaveFileResult result = new SaveFileResult();
		if (filePath.startsWith(BASE_TEMP_PATH)) {
			//临时文件转正式目录
			File file = new File(fileProp.getBasePath() + filePath);
			if (!file.exists() || !file.isFile()) {
				throw new FileNotFoundException("文件没找到" + filePath);
			}
			
			//解析文件的业务类型
			int serviceType;
			String fileName = file.getName();
			int idx = fileName.indexOf("_");
			if (idx == -1) {
				serviceType = 9999;			//会选择默认存储路径
			} else {
				serviceType = Integer.parseInt(fileName.substring(0, idx));
			}
			String newFilePath = fileDirUtil.getSavePath(serviceType);
			String fullPath = fileProp.getBasePath() + newFilePath + fileName;
			FileUtils.copyFile(file, new File(fullPath));
			file.delete();			//把临时文件删除
			
			result.setFileName(fileName);
			result.setFullPath(fullPath);
			result.setRelativePath(newFilePath);
		} else {
			//判断是否已经存在，存在就不需要再保存
			File src = new File(fileProp.getBasePath() + filePath);
			mf.transferTo(src);
			File file = new File(fileProp.getBasePath() + filePath);
			if (!file.exists() || !file.isFile()) {
				throw new FileNotFoundException("文件没找到" + filePath);
			}
			
			result.setFileName(file.getName());
			result.setFullPath(fileProp.getBasePath() + filePath);
			result.setRelativePath(filePath.substring(0, filePath.length() - result.getFileName().length()));
		}
		
		return result;
	}
	
	public String getFullFilePath(String relativePath, String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append(fileProp.getBasePath()).append(relativePath).append(fileName);
		return sb.toString();
	}
	
	/**
	 * 获取归档文件的保存绝对路径（包括文件名）
	 * @param templateName 模版名称（中文）
	 * @param templateVersion
	 * @param stuNo
	 * @param fileSuffix
	 * @param rxnf 入学年份  yyyy
	 * @return 绝对路径
	 */
	public SaveFileResult getFiledDocSavePath(String templateName, String templateVersion, 
			String stuNo, String fileSuffix, String rxnf) {
		SaveFileResult result = new SaveFileResult();
		String path = fileDirUtil.getSavePath(rxnf + "/" + stuNo);
		result.setRelativePath(path);
		StringBuilder sb = new StringBuilder();
		sb.append(templateName)
//			.append("_")
//			.append(templateVersion)
			.append("_")
			.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"))
			.append(".")
			.append(fileSuffix);
		
		result.setFileName(sb.toString());
		result.setFullPath(fileProp.getBasePath() + path + result.getFileName());
		return result;
	}
	/**
	 * 获取归档文件的保存绝对路径（包括文件名）
	 * @param templateCode
	 * @param templateName
	 * @param templateVersion
	 * @param fileSuffix
	 * @return 绝对路径
	 */
	public SaveFileResult getFiledDocSavePath(String templateCode, String templateName, 
			String templateVersion,  String fileSuffix) {
		SaveFileResult result = new SaveFileResult();
		String path = fileDirUtil.getSavePath(FileServiceTypeEnum.FILED_DOC.getDir() + "/" + templateCode);
		result.setRelativePath(path);
		StringBuilder sb = new StringBuilder();
		sb.append(templateName)
//		.append("_")
//		.append(templateVersion)
		.append("_")
		.append(DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"))
		.append(".")
		.append(fileSuffix);
		
		result.setFileName(sb.toString());
		result.setFullPath(fileProp.getBasePath() + path + result.getFileName());
		return result;
	}
	
	/**
	 * 获取临时文件路径
	 * @param suffix 文件后缀
	 * @return
	 */
	public String getTempFile(String suffix) {
		return fileProp.getTempPath() + fileDirUtil.getSavePath(-1) + CommonUtil.getUUID() + "." + suffix;
	}
}
