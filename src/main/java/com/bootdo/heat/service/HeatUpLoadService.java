package com.bootdo.heat.service;

import java.util.List;

import com.bootdo.heat.domain.HeatDO;


public interface HeatUpLoadService {
	
	List<HeatDO> getAllHeatList(HeatDO heat);
	
    int	updateHeat(HeatDO heat);

    //根据日期,用户归属地查询出来相应的数据
    List<HeatDO> getAllHeatById(HeatDO heat);

    // 查询用户编号相同的数据
    int getEqualUserId(HeatDO heat);
}
