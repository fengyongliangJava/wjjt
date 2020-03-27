package com.bootdo.elec.service;

import com.bootdo.elec.domain.ElecDO;


public interface ElecUpLoadService {
	
	

	public ElecDO getElecByUserId(ElecDO elec);
	
	
	public void createElecNextMonth(ElecDO elec);
	
}
