package com.bootdo.temple.util;

import java.util.UUID;

/**
 * 公共方法
 * @author N
 *
 */
public class CommonUtil {
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
