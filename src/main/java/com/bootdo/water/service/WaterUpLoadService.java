package com.bootdo.water.service;

import java.util.List;

import com.bootdo.water.domain.WaterDO;


public interface WaterUpLoadService {
	
	List<WaterDO> getAllWaterList(WaterDO water);
	
    int	updateWater(WaterDO water);


    List<WaterDO> getAllWaterById(WaterDO water);

    int getEqualUserId(WaterDO water);
}
