package com.bootdo.elec.vo;

import java.io.Serializable;

import com.bootdo.elec.domain.ElecPriceDO;




public class ElecPriceVo extends  ElecPriceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String elecOrgName;

	public String getElecOrgName() {
		return elecOrgName;
	}

	public void setElecOrgName(String elecOrgName) {
		this.elecOrgName = elecOrgName;
	}
	
	
	
}
