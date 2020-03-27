package com.bootdo.temple.util;

/**
 * 保存文件返回结果
 * 
 * @author N
 * 
 */
public class SaveFileResult {
	/**
	 * 完整路径 = basePath/temp + relativePath + fileName
	 */
	private String fullPath;
	/**
	 * 相对路径 不包括根目录，和文件名
	 */
	private String relativePath;
	/**
	 * 文件名
	 */
	private String fileName;

	public SaveFileResult() {

	}
	
	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
