package com.bootdo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum FileServiceTypeEnum {
	/**
	 * 10*为模版文件
	 */
	TEMPLATE(100, "template", "模版"), 
	PICTURE(101, "picture", "图像"), 
	SCIENTIFIC(102, "scientific", "科研成果"), 
	UPLOAD(103, "upload", "上传资料"), 
	FILED_DOC(201, "filedDoc", "归档报告"), 
	OTHER(0, "default", "");

	private int type;
	private String dir;
	private String name;

	private FileServiceTypeEnum(int type, String dir, String name) {
		this.type = type;
		this.dir = dir;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public String getDir() {
		return dir;
	}

	public String getName() {
		return name;
	}

	private static Map<Integer, FileServiceTypeEnum> map = new HashMap<Integer, FileServiceTypeEnum>();
	static {
		for (FileServiceTypeEnum item : FileServiceTypeEnum.values()) {
			map.put(item.type, item);
		}
	}

	public static FileServiceTypeEnum get(int type) {
		return map.get(type);
	}
}
