package com.bootdo.elec.dao;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.elec.domain.ElecDO;



@Mapper
public interface ElecUpLoadDao {


	public ElecDO getElecByUserId(ElecDO elec);
	

	
	public void createElecNextMonth(ElecDO elec);
	
	
	
	
}
