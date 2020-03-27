package com.bootdo.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodeEnum {
	FAILED(1, "系统出了点小差"), 
	SUCCESS(0, "成功"),
	PARAMS_INVALID(2, "参数校验失败");

	private int code;
	private String msg;

	private ErrorCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	private static Map<Integer, ErrorCodeEnum> map = new HashMap<Integer, ErrorCodeEnum>();
	static {
		for (ErrorCodeEnum item : ErrorCodeEnum.values()) {
			map.put(item.code, item);
		}
	}
	
	public static ErrorCodeEnum get(int code) {
		return map.get(code);
	}
	
}
