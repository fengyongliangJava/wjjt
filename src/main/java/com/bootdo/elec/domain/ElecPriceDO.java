package com.bootdo.elec.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class ElecPriceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String elecOrg;
    private BigDecimal mElec;
    private BigDecimal sElec;
    private BigDecimal g1Elec;
    private BigDecimal g2Elec;
    private BigDecimal g3Elec;
    private BigDecimal wmElec;
    private BigDecimal wsElec;
    private BigDecimal wgElec;
    private BigDecimal mmElec;
    private BigDecimal msElec;
    private BigDecimal zElec;
	
	private String createTime;
	private Long createBy;
	private Date updateTime;
	private Long updateBy;
	private String remark;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getElecOrg() {
		return elecOrg;
	}
	public void setElecOrg(String elecOrg) {
		this.elecOrg = elecOrg;
	}
	

	public BigDecimal getmElec() {
		return mElec;
	}
	public void setmElec(BigDecimal mElec) {
		this.mElec = mElec;
	}
	public BigDecimal getsElec() {
		return sElec;
	}
	public void setsElec(BigDecimal sElec) {
		this.sElec = sElec;
	}
	public BigDecimal getG1Elec() {
		return g1Elec;
	}
	public void setG1Elec(BigDecimal g1Elec) {
		this.g1Elec = g1Elec;
	}
	public BigDecimal getG2Elec() {
		return g2Elec;
	}
	public void setG2Elec(BigDecimal g2Elec) {
		this.g2Elec = g2Elec;
	}
	public BigDecimal getG3Elec() {
		return g3Elec;
	}
	public void setG3Elec(BigDecimal g3Elec) {
		this.g3Elec = g3Elec;
	}
	public BigDecimal getWmElec() {
		return wmElec;
	}
	public void setWmElec(BigDecimal wmElec) {
		this.wmElec = wmElec;
	}
	public BigDecimal getWsElec() {
		return wsElec;
	}
	public void setWsElec(BigDecimal wsElec) {
		this.wsElec = wsElec;
	}
	public BigDecimal getWgElec() {
		return wgElec;
	}
	public void setWgElec(BigDecimal wgElec) {
		this.wgElec = wgElec;
	}
	public BigDecimal getMmElec() {
		return mmElec;
	}
	public void setMmElec(BigDecimal mmElec) {
		this.mmElec = mmElec;
	}
	public BigDecimal getMsElec() {
		return msElec;
	}
	public void setMsElec(BigDecimal msElec) {
		this.msElec = msElec;
	}
	public BigDecimal getzElec() {
		return zElec;
	}
	public void setzElec(BigDecimal zElec) {
		this.zElec = zElec;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
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
	
	@Override
	public String toString() {
		return "ElecPriceDO [id=" + id + ", elecOrg=" + elecOrg + ", mElec=" + mElec + ", sElec=" + sElec + ", g1Elec="
				+ g1Elec + ", g2Elec=" + g2Elec + ", g3Elec=" + g3Elec + ", wmElec=" + wmElec + ", wsElec=" + wsElec
				+ ", wgElec=" + wgElec + ", mmElec=" + mmElec + ", msElec=" + msElec + ", zElec=" + zElec
				+ ", createTime=" + createTime + ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy="
				+ updateBy + ", remark=" + remark + "]";
	}
	
	
	
	
		
}
