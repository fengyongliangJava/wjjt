package com.bootdo.heat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.vo.HeatPriceVo;


@Mapper
public interface HeatPriceDao {

	HeatPriceVo get(Long id);
	
	List<HeatPriceVo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(HeatPriceDO heatPrice);
	
	int update(HeatPriceDO heatPrice);
	
	int updateMHeat(HeatPriceDO heatPrice);
	
	int updateSHeat(HeatPriceDO heatPrice);
	
	int updateGMHeat(HeatPriceDO heatPrice);
	
	int updateGSHeat(HeatPriceDO heatPrice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
