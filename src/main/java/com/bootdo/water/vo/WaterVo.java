package com.bootdo.water.vo;

import java.io.Serializable;

import com.bootdo.water.domain.WaterDO;



public class WaterVo extends  WaterDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户归属
	private String userOrgName;

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	

	
	
	
}
