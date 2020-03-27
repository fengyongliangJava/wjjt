package com.bootdo.elec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.elec.dao.ElecUpLoadDao;
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.service.ElecUpLoadService;




@Service
public class ElecUpLoadServiceImpl implements ElecUpLoadService {
	
	
	@Autowired
	private ElecUpLoadDao elecUpLoadDao;



	public ElecDO getElecByUserId(ElecDO elec) {
		return elecUpLoadDao.getElecByUserId(elec);
	}


	public void createElecNextMonth(ElecDO elec) {
		elecUpLoadDao.createElecNextMonth(elec);
	}
}
