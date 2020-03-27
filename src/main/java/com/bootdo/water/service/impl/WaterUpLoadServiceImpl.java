package com.bootdo.water.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.water.dao.WaterUpLoadDao;
import com.bootdo.water.domain.WaterDO;
import com.bootdo.water.service.WaterUpLoadService;



@Service
public class WaterUpLoadServiceImpl implements WaterUpLoadService {
	
	@Autowired
	private WaterUpLoadDao waterUpLoadDao;

	public List<WaterDO> getAllWaterList(WaterDO water) {
		return waterUpLoadDao.getAllWaterList(water);
	}

	public int updateWater(WaterDO water) {
		return waterUpLoadDao.updateWater(water);
	}

	//暖费生成
	//根据暖费查询相应的信息
	public List<WaterDO> getAllWaterById(WaterDO water) {
		return waterUpLoadDao.getAllWaterById(water);
	}

	// 查询用户编号相同的数据
	public int  getEqualUserId(WaterDO water){
		return waterUpLoadDao.getEqualUserId(water);
	}
}
