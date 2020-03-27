package com.bootdo.print.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class PrintDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private Long id;

	private String userId;

	private String userName;

	private String userType;

	private String userOrg;

	private BigDecimal start;

	private BigDecimal end;

	private Integer hu;

	private BigDecimal elecPrice;

	private BigDecimal elecAmount;

	private BigDecimal elecCost;

	private BigDecimal elecSum;

	private BigDecimal elecMoney;

	private BigDecimal heatPrice;

	private BigDecimal heatArea;

	private BigDecimal heatCost;
	
	private BigDecimal heatSum;

	private BigDecimal heatMoney;

	private BigDecimal waterPrice;

	private BigDecimal waterCost;

	private BigDecimal waterSum;

	private BigDecimal waterMoney;

	private Date printDate;
	
	private BigDecimal printMoney;


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

	public void setElecPrice(BigDecimal elecPrice) {
		this.elecPrice = elecPrice;
	}

	public BigDecimal getElecPrice() {
		return elecPrice;
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

	public void setElecMoney(BigDecimal elecMoney) {
		this.elecMoney = elecMoney;
	}

	public BigDecimal getElecMoney() {
		return elecMoney;
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

	public void setHeatMoney(BigDecimal heatMoney) {
		this.heatMoney = heatMoney;
	}

	public BigDecimal getHeatMoney() {
		return heatMoney;
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

	public void setWaterMoney(BigDecimal waterMoney) {
		this.waterMoney = waterMoney;
	}

	public BigDecimal getWaterMoney() {
		return waterMoney;
	}

	public void setPrintDate(Date printDate) {
		this.printDate = printDate;
	}
	
	public Date getPrintDate() {
		return printDate;
	}

	public BigDecimal getPrintMoney() {
		return printMoney;
	}

	public void setPrintMoney(BigDecimal printMoney) {
		this.printMoney = printMoney;
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
