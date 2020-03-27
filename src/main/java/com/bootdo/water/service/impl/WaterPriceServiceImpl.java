package com.bootdo.water.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.water.dao.WaterPriceDao;
import com.bootdo.water.domain.WaterPriceDO;
import com.bootdo.water.service.WaterPriceService;
import com.bootdo.water.vo.WaterPriceVo;



@Service
public class WaterPriceServiceImpl implements WaterPriceService {
	
	@Autowired
	private WaterPriceDao waterPriceDao;
	
	public WaterPriceVo get(Long id){
		return waterPriceDao.get(id);
	}
	
	public List<WaterPriceVo> list(Map<String, Object> map){
		return waterPriceDao.list(map);
	}
	
	public int count(Map<String, Object> map){
		return waterPriceDao.count(map);
	}
	
	public int save(WaterPriceDO waterPrice){
		return waterPriceDao.save(waterPrice);
	}
	
	public int update(WaterPriceDO waterPrice){
		return waterPriceDao.update(waterPrice);
	}
	
	public int updateMWater(WaterPriceDO waterPrice) {
		return waterPriceDao.updateMWater(waterPrice);
	}

	public int updateS1Water(WaterPriceDO waterPrice) {
		return waterPriceDao.updateS1Water(waterPrice);
	}

	public int updateS2Water(WaterPriceDO waterPrice) {
		return waterPriceDao.updateS2Water(waterPrice);
	}

	public int updateS3Water(WaterPriceDO waterPrice) {
		return waterPriceDao.updateS3Water(waterPrice);
	}
	

	
	public int remove(Long id){
		return waterPriceDao.remove(id);
	}
	
	public int batchRemove(Long[] ids){
		return waterPriceDao.batchRemove(ids);
	}

	
	
}
