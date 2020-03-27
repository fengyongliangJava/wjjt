package com.bootdo.heat.vo;

import java.io.Serializable;

import com.bootdo.heat.domain.HeatPriceDO;




public class HeatPriceVo extends  HeatPriceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//暖价归属
	private String heatOrgName;

	public String getHeatOrgName() {
		return heatOrgName;
	}

	public void setHeatOrgName(String heatOrgName) {
		this.heatOrgName = heatOrgName;
	}

	
	
	
}
