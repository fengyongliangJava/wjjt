package com.bootdo.water.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class WaterPriceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String waterOrg;
	
    private BigDecimal mWater;

    private BigDecimal s1Water;

    private BigDecimal s2Water;

    private BigDecimal s3Water;
	
	private String createTime;
	
	private Long createBy;
	
	private Date updateTime;
	
	private Long updateBy;
	
	private String remark;


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setWaterOrg(String waterOrg) {
		this.waterOrg = waterOrg;
	}

	public String getWaterOrg() {
		return waterOrg;
	}
	

	public BigDecimal getmWater() {
		return mWater;
	}

	public void setmWater(BigDecimal mWater) {
		this.mWater = mWater;
	}

	public BigDecimal getS1Water() {
		return s1Water;
	}

	public void setS1Water(BigDecimal s1Water) {
		this.s1Water = s1Water;
	}

	public BigDecimal getS2Water() {
		return s2Water;
	}

	public void setS2Water(BigDecimal s2Water) {
		this.s2Water = s2Water;
	}

	public BigDecimal getS3Water() {
		return s3Water;
	}

	public void setS3Water(BigDecimal s3Water) {
		this.s3Water = s3Water;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
}
