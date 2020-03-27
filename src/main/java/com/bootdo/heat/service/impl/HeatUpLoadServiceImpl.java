package com.bootdo.heat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.heat.dao.HeatUpLoadDao;
import com.bootdo.heat.domain.HeatDO;
import com.bootdo.heat.service.HeatUpLoadService;



@Service
public class HeatUpLoadServiceImpl implements HeatUpLoadService {
	@Autowired
	private HeatUpLoadDao heatUpLoadDao;

	public List<HeatDO> getAllHeatList(HeatDO heat) {
		return heatUpLoadDao.getAllHeatList(heat);
	}

	public int updateHeat(HeatDO heat) {
		return heatUpLoadDao.updateHeat(heat);
	}

	//暖费生成
	//根据暖费查询相应的信息
	public List<HeatDO> getAllHeatById(HeatDO heat) {
		return heatUpLoadDao.getAllHeatById(heat);
	}

	// 查询用户编号相同的数据
	public int  getEqualUserId(HeatDO heat){
		return heatUpLoadDao.getEqualUserId(heat);
	}
}
