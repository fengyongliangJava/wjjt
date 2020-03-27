package com.bootdo.elec.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.elec.dao.ElecPriceDao;
import com.bootdo.elec.domain.ElecPriceDO;
import com.bootdo.elec.service.ElecPriceService;
import com.bootdo.elec.vo.ElecPriceVo;



@Service
public class ElecPriceServiceImpl implements ElecPriceService {
	@Autowired
	private ElecPriceDao elecPriceDao;
	
	public ElecPriceVo get(Long id){
		return elecPriceDao.get(id);
	}
	
	public List<ElecPriceVo> list(Map<String, Object> map){
		return elecPriceDao.list(map);
	}
	
	public int count(Map<String, Object> map){
		return elecPriceDao.count(map);
	}
	
	public int save(ElecPriceDO elecPrice){
		return elecPriceDao.save(elecPrice);
	}
	
	public int update(ElecPriceDO elecPrice){
		return elecPriceDao.update(elecPrice);
	}
	

	public int updateMElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateMElec(elecPrice);
	}

	public int updateSElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateSElec(elecPrice);
	}

	public int updateG1Elec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateG1Elec(elecPrice);
	}

	public int updateG2Elec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateG2Elec(elecPrice);
	}

	public int updateG3Elec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateG3Elec(elecPrice);
	}

	public int updateWMElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateWMElec(elecPrice);
	}

	public int updateWSElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateWSElec(elecPrice);
	}

	public int updateWGElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateWGElec(elecPrice);
	}
	public int updateMMElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateMMElec(elecPrice);
	}
	
	public int updateMSElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateMSElec(elecPrice);
	}

	public int updateZElec(ElecPriceDO elecPrice) {
		return elecPriceDao.updateZElec(elecPrice);
	}

	public int remove(Long id){
		return elecPriceDao.remove(id);
	}
	
	public int batchRemove(Long[] ids){
		return elecPriceDao.batchRemove(ids);
	}



	
}
