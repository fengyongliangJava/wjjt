package com.bootdo.heat.vo;

import java.io.Serializable;

import com.bootdo.heat.domain.HeatDO;




public class HeatVo extends  HeatDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userOrgName;

	private String  queryType;
	
	
	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
}
