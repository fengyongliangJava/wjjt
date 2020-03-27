package com.bootdo.water.vo;

import java.io.Serializable;

import com.bootdo.water.domain.WaterPriceDO;


public class WaterPriceVo extends  WaterPriceDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//水价归属
	private String waterOrgName;

	public String getWaterOrgName() {
		return waterOrgName;
	}

	public void setWaterOrgName(String waterOrgName) {
		this.waterOrgName = waterOrgName;
	}

	
	
	
}
