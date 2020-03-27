package com.bootdo.moneyLog.vo;

import java.io.Serializable;

import com.bootdo.moneyLog.domain.HeatLogDO;



public class HeatLogVo extends  HeatLogDO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String userOrgName;
	
    private String endDate;
    
    private String beginDate;
	

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}


	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
