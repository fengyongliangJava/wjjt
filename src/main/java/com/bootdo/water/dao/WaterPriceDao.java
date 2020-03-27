package com.bootdo.water.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.water.domain.WaterPriceDO;
import com.bootdo.water.vo.WaterPriceVo;


@Mapper
public interface WaterPriceDao {

	WaterPriceVo get(Long id);
	
	List<WaterPriceVo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(WaterPriceDO waterPrice);
	
	int update(WaterPriceDO waterPrice);
	
	int updateMWater(WaterPriceDO waterPrice);
	
	int updateS1Water(WaterPriceDO waterPrice);
	
	int updateS2Water(WaterPriceDO waterPrice);
	
	int updateS3Water(WaterPriceDO waterPrice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
