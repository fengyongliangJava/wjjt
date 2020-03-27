package com.bootdo.moneyLog.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class ElecLogDO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String userId;
	
	private String userName;
	
	private String userType;
	
	private String elecType;
	
	private String userOrg;
	
	private BigDecimal elecMoney;
	
	private Date moneyDate;   
	
	private String createTime;
	
	private String createBy;

	private Date updateTime;
	
	private String updateBy;
	
	private String remark;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getElecType() {
		return elecType;
	}

	public void setElecType(String elecType) {
		this.elecType = elecType;
	}
	
	public String getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}


	public Date getMoneyDate() {
		return moneyDate;
	}

	public void setMoneyDate(Date moneyDate) {
		this.moneyDate = moneyDate;
	}

	
	public BigDecimal getElecMoney() {
		return elecMoney;
	}

	public void setElecMoney(BigDecimal elecMoney) {
		this.elecMoney = elecMoney;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
}
