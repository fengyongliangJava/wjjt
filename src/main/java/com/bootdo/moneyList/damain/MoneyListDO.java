package com.bootdo.moneyList.damain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class MoneyListDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String money;

	private String id;
	//是否存在数据
	private String number;
	//用户编号
	private String userId;
	//用户名
	private String userName;
	//缴费类型
	private String userType;
	//用户归属
	private String userOrg;
	
	private String userOrgName;
	//联系方式
	private String userTell;
	//用户状态: 0代表未使用 1代表使用中
	private String userState;
	//电量类型：0代表商业电; 1代表民用电; 2代表工业盈利; 3代表工业非盈利; 4代表特殊电费1 5代表特殊电费2; 6代表特殊电费3; 7代表特殊电费4;
	private String moneyType;
	//取暖单价
	private BigDecimal userPrice;
	//取暖面积
	private BigDecimal userArea;
	//暖费
	private BigDecimal userCost;
	//暖费余额
	private BigDecimal userSum;
	
	private Date moneyDate;
	
	//创建时间
	private String createTime;

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserOrgName() {
		return userOrgName;
	}

	public void setUserOrgName(String userOrgName) {
		this.userOrgName = userOrgName;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getCreateTime() {

		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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



	public String getUserTell() {
		return userTell;
	}

	public void setUserTell(String userTell) {
		this.userTell = userTell;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public BigDecimal getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(BigDecimal userPrice) {
		this.userPrice = userPrice;
	}

	public BigDecimal getUserArea() {
		return userArea;
	}

	public void setUserArea(BigDecimal userArea) {
		this.userArea = userArea;
	}

	public BigDecimal getUserCost() {
		return userCost;
	}

	public void setUserCost(BigDecimal userCost) {
		this.userCost = userCost;
	}

	public BigDecimal getUserSum() {
		return userSum;
	}

	public void setUserSum(BigDecimal userSum) {
		this.userSum = userSum;
	}

	public String getNumber() {return number;}

	public void setNumber(String number) {this.number = number;}

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
	
	
	
	
	
}
