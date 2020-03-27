package com.bootdo.heat.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class HeatPriceDO implements Serializable {
	
    	private static final long serialVersionUID = 1L;
	
	    private Long id;

	    private String heatOrg;

	    private BigDecimal mHeat;

	    private BigDecimal sHeat;

	    private BigDecimal gmHeat;

	    private BigDecimal gsHeat;

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

		public String getHeatOrg() {
			return heatOrg;
		}

		public void setHeatOrg(String heatOrg) {
			this.heatOrg = heatOrg;
		}

		public BigDecimal getmHeat() {
			return mHeat;
		}

		public void setmHeat(BigDecimal mHeat) {
			this.mHeat = mHeat;
		}

		public BigDecimal getsHeat() {
			return sHeat;
		}

		public void setsHeat(BigDecimal sHeat) {
			this.sHeat = sHeat;
		}

		public BigDecimal getGmHeat() {
			return gmHeat;
		}

		public void setGmHeat(BigDecimal gmHeat) {
			this.gmHeat = gmHeat;
		}

		public BigDecimal getGsHeat() {
			return gsHeat;
		}

		public void setGsHeat(BigDecimal gsHeat) {
			this.gsHeat = gsHeat;
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

	
        

	
}
