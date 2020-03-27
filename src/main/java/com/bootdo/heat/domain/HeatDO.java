package com.bootdo.heat.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class HeatDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String userId;
	private String userName;
	private String userType;
	private String userOrg;
	private String userTell;
	private String userState;
	private String wagesId;
	private String heatType;
	private BigDecimal heatPrice;
	private BigDecimal heatArea;
	private BigDecimal heatCost;
	private BigDecimal heatSum;
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
	
	
	
	public void setHeatType(String heatType) {
		this.heatType = heatType;
	}
	public String getHeatType() {
		return heatType;
	}

	
	
	
	public void setHeatPrice(BigDecimal heatPrice) {
		this.heatPrice = heatPrice;
	}

	public BigDecimal getHeatPrice() {
		return heatPrice;
	}

	
	
	public void setHeatArea(BigDecimal heatArea) {
		this.heatArea = heatArea;
	}
	public BigDecimal getHeatArea() {
		return heatArea;
	}

	
	public void setHeatCost(BigDecimal heatCost) {
		this.heatCost = heatCost;
	}
	public BigDecimal getHeatCost() {
		return heatCost;
	}

	
	
	public void setHeatSum(BigDecimal heatSum) {
		this.heatSum = heatSum;
	}
	public BigDecimal getHeatSum() {
		return heatSum;
	}

	
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateTime() {
		return createTime;
	}

	
	
	public void setCreateBy(String string) {
		this.createBy = string;
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
