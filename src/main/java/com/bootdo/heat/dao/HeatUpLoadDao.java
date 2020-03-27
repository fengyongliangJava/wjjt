package com.bootdo.heat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.heat.domain.HeatDO;


@Mapper
public interface HeatUpLoadDao {

	
	// 查询heat表
	List<HeatDO> getAllHeatList(HeatDO heat);
	//更新heat 表
    int	updateHeat(HeatDO heat);

    //根据日期查询出来相应的数据
	List<HeatDO> getAllHeatById(HeatDO heat);

	// 查询用户编号相同的数据
	int getEqualUserId(HeatDO heat);
	
}
