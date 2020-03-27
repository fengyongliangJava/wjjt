package com.bootdo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReportFileEnum {
	
	ReportFile0(0, "ReportFile0.xls", "汇总表.xls"), 
	
	OTHER(99, "default", "");

	private int type;
	private String dir;
	private String name;

	private ReportFileEnum(int type, String dir, String name) {
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

	private static Map<Integer, ReportFileEnum> map = new HashMap<Integer, ReportFileEnum>();
	static {
		for (ReportFileEnum item : ReportFileEnum.values()) {
			map.put(item.type, item);
		}
	}

	public static ReportFileEnum get(int type) {
		return map.get(type);
	}
}
