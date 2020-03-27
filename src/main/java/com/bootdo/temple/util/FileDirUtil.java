package com.bootdo.temple.util;

import java.io.File;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootdo.common.enums.FileServiceTypeEnum;
import com.bootdo.common.prop.FileProp;

/**
 * 文件路径处理工具类 根据不同文件业务类型，生成目录
 * 
 * @author N
 * 
 */
@Component
public class FileDirUtil {
	private Logger log = LoggerFactory.getLogger(FileDirUtil.class);
	private final String YYYY = "yyyy";
	private final String MM = "MM";
	private final String MMDD = "MMdd";
	private final String DD = "dd";
	private final String HH = "HH";
	private final String MINUTS = "mm";
	
	@Autowired
	private FileProp fileProp;
	
	/**
	 * 
	 * @param serviceType 文件业务类型
	 * @return 文件的相对路径
	 */
	public String getSavePath(int serviceType) {
		switch (serviceType) {
		case 100: {
			return getTemplatePath();
		}
		case 201: {			//FileServiceTypeEnum.FILED_DOC
			return getYYYY_MMDD_HH(FileServiceTypeEnum.FILED_DOC.getDir());
		}
		default:
			return getDefaultPath();
		}
	}
	
	public String getSavePath(String prefix) {
		return mkdir(prefix, new String[0]);
	}
	
	/**
	 * 创建目录
	 * @param dirs
	 * @return 相对路径
	 */
	private String mkdir(String root, String[] dirs) {
		Date now = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append(fileProp.getBasePath());
		sb.append(root).append("/");
		for (String dir : dirs) {
			sb.append(DateFormatUtils.format(now, dir)).append("/");
		}
		
		File file = new File(sb.toString());
		if(!file.exists()){
			if (!file.mkdirs()) {
				log.error("创建目录失败  {}", sb.toString());
				throw new RuntimeException("创建目录失败");
			}
		}
		return sb.toString().substring(fileProp.getBasePath().length());
	}
	
	
	
	/**
	 * 默认路径
	 * 按 yyyy
	 * MM
	 * dd
	 * mm
	 * @return
	 */
	private String getDefaultPath() {
		return mkdir("default", new String[]{this.YYYY, this.MM, this.DD, this.MINUTS});
	}
	
	/**
	 * 模版文件路径
	 * @return
	 */
	private String getTemplatePath() {
		return mkdir(FileServiceTypeEnum.TEMPLATE.getDir(), new String[0]);
	}
	
	private String getYYYY_MMDD_HH(String dirName) {
		return mkdir(dirName, new String[]{this.YYYY, this.MMDD, this.HH});
	}

}
