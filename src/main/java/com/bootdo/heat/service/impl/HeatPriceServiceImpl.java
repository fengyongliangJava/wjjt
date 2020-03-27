package com.bootdo.heat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.heat.dao.HeatPriceDao;
import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.service.HeatPriceService;
import com.bootdo.heat.vo.HeatPriceVo;



@Service
public class HeatPriceServiceImpl implements HeatPriceService {
	@Autowired
	private HeatPriceDao heatPriceDao;
	
	public HeatPriceVo get(Long id){
		return heatPriceDao.get(id);
	}
	
	public List<HeatPriceVo> list(Map<String, Object> map){
		return heatPriceDao.list(map);
	}
	
	public int count(Map<String, Object> map){
		return heatPriceDao.count(map);
	}
	
	public int save(HeatPriceDO heatPrice){
		return heatPriceDao.save(heatPrice);
	}
	
	public int update(HeatPriceDO heatPrice){
		return heatPriceDao.update(heatPrice);
	}
	

	public int updateMHeat(HeatPriceDO heatPrice) {
		return heatPriceDao.updateMHeat(heatPrice);
	}

	public int updateSHeat(HeatPriceDO heatPrice) {
		return heatPriceDao.updateSHeat(heatPrice);
	}

	public int updateGMHeat(HeatPriceDO heatPrice) {
		return heatPriceDao.updateGMHeat(heatPrice);
	}

	public int updateGSHeat(HeatPriceDO heatPrice) {
		return heatPriceDao.updateGSHeat(heatPrice);
	}
	
	
	public int remove(Long id){
		return heatPriceDao.remove(id);
	}
	
	public int batchRemove(Long[] ids){
		return heatPriceDao.batchRemove(ids);
	}

	
}
