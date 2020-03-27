package com.bootdo.water.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.water.domain.WaterDO;


@Mapper
public interface WaterUpLoadDao {

	
	List<WaterDO> getAllWaterList(WaterDO water);

    int	updateWater(WaterDO water);

	List<WaterDO> getAllWaterById(WaterDO water);


	int getEqualUserId(WaterDO water);
	
}
