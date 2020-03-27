package com.bootdo.heat.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author daixn
 * @email 1076143369@qq.com
 * @date 2019-02-21 21:59:35
 */
public class HeatCostDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//暖费单Id
	private Long heatId;
	//用户编号
	private String userId;
	//用户名
	private String userName;
	//用户类型: A 现金缴费; B 工资代扣
	private String userType;
	//用户归属
	private Long userOrg;
	//暖缴费的金额
	private BigDecimal heatMoney;
	//暖缴费的日期
	private Date heatDate;
	//创建时间
	private Date createTime;
	//创建人
	private Long createBy;
	//更新时间
	private Date updateTime;
	//创建人
	private Long updateBy;
	//备注
	private String remark;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：暖费单Id
	 */
	public void setHeatId(Long heatId) {
		this.heatId = heatId;
	}
	/**
	 * 获取：暖费单Id
	 */
	public Long getHeatId() {
		return heatId;
	}
	/**
	 * 设置：用户编号
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户编号
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：用户名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：用户类型: A 现金缴费; B 工资代扣
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型: A 现金缴费; B 工资代扣
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置：用户归属
	 */
	public void setUserOrg(Long userOrg) {
		this.userOrg = userOrg;
	}
	/**
	 * 获取：用户归属
	 */
	public Long getUserOrg() {
		return userOrg;
	}
	/**
	 * 设置：暖缴费的金额
	 */
	public void setHeatMoney(BigDecimal heatMoney) {
		this.heatMoney = heatMoney;
	}
	/**
	 * 获取：暖缴费的金额
	 */
	public BigDecimal getHeatMoney() {
		return heatMoney;
	}
	/**
	 * 设置：暖缴费的日期
	 */
	public void setHeatDate(Date heatDate) {
		this.heatDate = heatDate;
	}
	/**
	 * 获取：暖缴费的日期
	 */
	public Date getHeatDate() {
		return heatDate;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：创建人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
}
