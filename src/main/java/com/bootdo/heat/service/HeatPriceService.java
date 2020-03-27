package com.bootdo.heat.service;

import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.vo.HeatPriceVo;

import java.util.List;
import java.util.Map;


public interface HeatPriceService {
	
	HeatPriceVo get(Long id);
	
	List<HeatPriceVo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(HeatPriceDO heatPrice);
	
	int update(HeatPriceDO heatPrice);
	
	int updateMHeat(HeatPriceDO heatPrice);
	
	int updateSHeat(HeatPriceDO heatPrice);
	
	int updateGMHeat(HeatPriceDO heatPrice);
	
	int updateGSHeat(HeatPriceDO heatPrice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
