package com.bootdo.elec.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class ElecDO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String userId;
	private String userName;
	private String userType;
	private String userOrg;
	private String userTell;
	private String userState;
	private String wagesId;
	private String elecType;
	private BigDecimal elecPrice;
	private BigDecimal start;
	private BigDecimal end;
	private Integer hu;
	private BigDecimal elecAmount;
	private BigDecimal elecCost;
	private BigDecimal elecSum;
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

	public void setElecType(String elecType) {
		this.elecType = elecType;
	}

	public String getElecType() {
		return elecType;
	}

	public void setElecPrice(BigDecimal elecPrice) {
		this.elecPrice = elecPrice;
	}

	public BigDecimal getElecPrice() {
		return elecPrice;
	}


	public BigDecimal getStart() {
		return start;
	}

	public void setStart(BigDecimal start) {
		this.start = start;
	}

	public BigDecimal getEnd() {
		return end;
	}

	public void setEnd(BigDecimal end) {
		this.end = end;
	}

	public void setHu(Integer hu) {
		this.hu = hu;
	}

	public Integer getHu() {
		return hu;
	}

	public void setElecAmount(BigDecimal elecAmount) {
		this.elecAmount = elecAmount;
	}

	public BigDecimal getElecAmount() {
		return elecAmount;
	}

	public void setElecCost(BigDecimal elecCost) {
		this.elecCost = elecCost;
	}

	public BigDecimal getElecCost() {
		return elecCost;
	}

	public void setElecSum(BigDecimal elecSum) {
		this.elecSum = elecSum;
	}

	public BigDecimal getElecSum() {
		return elecSum;
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
