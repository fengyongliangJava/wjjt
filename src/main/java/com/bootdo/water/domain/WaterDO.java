package com.bootdo.water.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



public class WaterDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private Long id;

	private String userId;

	private String userName;

	private String userType;

	private String userOrg;

	private String userTell;

	private String userState;

	private String wagesId;

	private String waterType;

	private BigDecimal waterPrice;
	
	private BigDecimal waterCost;
	
	private BigDecimal waterSum;
	
	private String createTime;

	private String createBy;

	private Date updateTime;

	private String updateBy;

	private String remark;


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}

	public String getUserOrg() {
		return userOrg;
	}

	public void setUserTell(String userTell) {
		this.userTell = userTell;
	}

	public String getUserTell() {
		return userTell;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getUserState() {
		return userState;
	}

	public void setWagesId(String wagesId) {
		this.wagesId = wagesId;
	}

	public String getWagesId() {
		return wagesId;
	}

	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}

	public String getWaterType() {
		return waterType;
	}

	public void setWaterPrice(BigDecimal waterPrice) {
		this.waterPrice = waterPrice;
	}

	public BigDecimal getWaterPrice() {
		return waterPrice;
	}
	
	public BigDecimal getWaterCost() {
		return waterCost;
	}

	public void setWaterCost(BigDecimal waterCost) {
		this.waterCost = waterCost;
	}

	public void setWaterSum(BigDecimal waterSum) {
		this.waterSum = waterSum;
	}

	public BigDecimal getWaterSum() {
		return waterSum;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return remark;
	}
	

}
