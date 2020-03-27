package com.bootdo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum GenderEnum {
	MALE(1, "男"), 
	FEMALE(2, "女");

	private int code;
	private String desc;

	private GenderEnum(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	private static Map<Integer, GenderEnum> map = new HashMap<Integer, GenderEnum>();
	static {
		for (GenderEnum item : GenderEnum.values()) {
			map.put(item.code, item);
		}
	}
	
	public static GenderEnum get(int code) {
		return map.get(code);
	}
	
}
