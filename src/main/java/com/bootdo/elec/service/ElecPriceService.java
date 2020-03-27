package com.bootdo.elec.service;

import java.util.List;
import java.util.Map;

import com.bootdo.elec.domain.ElecPriceDO;
import com.bootdo.elec.vo.ElecPriceVo;


public interface ElecPriceService {
	

	ElecPriceVo get(Long id);
	
	List<ElecPriceVo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ElecPriceDO elecPrice);
	
	int update(ElecPriceDO elecPrice);
	
	int updateMElec(ElecPriceDO elecPrice);
	
	int updateSElec(ElecPriceDO elecPrice);
	
	int updateG1Elec(ElecPriceDO elecPrice);
	
	int updateG2Elec(ElecPriceDO elecPrice);
	
	int updateG3Elec(ElecPriceDO elecPrice);
	
	int updateWMElec(ElecPriceDO elecPrice);
	
	int updateWSElec(ElecPriceDO elecPrice);
	
	int updateWGElec(ElecPriceDO elecPrice);
	
	int updateMMElec(ElecPriceDO elecPrice);
	
	int updateMSElec(ElecPriceDO elecPrice);
	
	int updateZElec(ElecPriceDO elecPrice);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
