package com.bootdo.temple.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bootdo.temple.util.FileUtil;

@Controller
@RequestMapping("/file")
public class TempletController {
	private Logger log = LoggerFactory.getLogger(TempletController.class);
	
	@Autowired
	private FileUtil fileUtil;
	@Value("${huhste.file.basePath}")
	private String uploadRootUri;

	
	/**
	 * 下载文件(导入模板)
	 * @param id
	 * @param httpResp
	 */
	@GetMapping("/downImport/{importType}")
	public void downImport(HttpServletResponse httpResp, @PathVariable String importType) {
		if(importType == null || "".endsWith(importType)){
			handleError(httpResp, "下载参数为空");
			return;
		}
		
		String fileName = "";
		if("elecUpload".equals(importType)){
			fileName = "电字上传模板.xlsx";
		}else if("elecUser".equals(importType)){
			fileName = "电费上传模板.xlsx";
		}else if("waterUser".equals(importType)){
			fileName = "水费上传模板.xlsx";
		}else if("heatUser".equals(importType)){
			fileName = "暖费上传模板.xlsx";
		}else if("userInto".equals(importType)){
			fileName = "HR工资代扣模板.xlsx";
		}else if("elecLog".equals(importType)){
			fileName = "电缴费记录上传模板.xlsx";
		}else if("heatLog".equals(importType)){
			fileName = "暖缴费记录上传模板.xlsx";
		}else if("waterLog".equals(importType)){
			fileName = "电缴费记录上传模板.xlsx";
		}



		
		
		String fullPath = fileUtil.getFullFilePath("template/", fileName);
		File file = new File(fullPath);
		if (!file.exists() || !file.isFile()) {
			log.error("文件不存在 --> {}", fullPath);
			handleError(httpResp, "文件不存在");
			return;
		}
		
		InputStream is = null;
		try{
			is = new FileInputStream(file);
			httpResp.addHeader("Content-Type", "application/octet-stream; charset=UTF-8"); 
			httpResp.addHeader("Content-Disposition", "attachment; filename*=UTF-8''" + URLEncoder.encode(fileName.replaceAll(" ", "_"), "UTF-8")); 
//			httpResp.addHeader("Content-Disposition", "attachment; filename=" + new String(getShowName(fileDo).replaceAll(" ", "_").getBytes("UTF-8"), "ISO8859-1")); 
			httpResp.addHeader("Content-Length", file.length() + ""); 
			
			IOUtils.copy(is, httpResp.getOutputStream());
			
		}catch(Exception e) {
			log.error("{}", e);
		} finally {
			IOUtils.closeQuietly(is);
		}
		
	}
	
	/**
	 * 下载异常处理
	 * @param httpResp
	 * @param msg
	 */
	private void handleError(HttpServletResponse httpResp, String msg) {
		try {
			httpResp.addHeader("Content-Type", "text/html; charset=UTF-8"); 
			IOUtils.write(msg.getBytes(), httpResp.getOutputStream());
		} catch(Exception e) {
			log.error("{}", e);
		}
	}
	
}