package com.bootdo.heat.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bootdo.heat.domain.HeatDO;
import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.vo.HeatVo;


public interface HeatService {
	
	HeatVo get(Integer id);
	
	List<HeatVo> list(Map<String, Object> map);
	
	List<HeatVo> exprotHeat(HeatVo heat);
	
	List<HeatVo> exprotHeatPre(HeatVo heat);
	
	List<HeatVo> exprotHeatOwe(HeatVo heat);
	
	List<HeatVo> exprotHeatPreYY(HeatVo heat);
	
	List<HeatVo> exprotHeatOweYY(HeatVo heat);
	
	int count(Map<String, Object> map);
	
	int save(HeatDO heat);
	
	int update(HeatDO heat);
	
	int updateState(HeatDO heat);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	
//欠费统计	
	int HeatCountOwe(HeatVo heat);
    BigDecimal HeatSumOwe(HeatVo heat);
//欠费统计   
    
    
 //月统计表格 start    
    BigDecimal HeatAreaMM(HeatVo heat);
    
    int PreHeatCountMMOld (HeatVo heat);
    BigDecimal PreHeatSumMMOld (HeatVo heat);
	int OweHeatCountMMOld (HeatVo heat);
	BigDecimal OweHeatSumMMOld (HeatVo heat);

	int HeatCostMMCount (HeatVo heat);
	BigDecimal HeatCostMMSum (HeatVo heat);
	
	int HeatLogMMCount (HeatVo heat);
	BigDecimal HeatLogMMSum (HeatVo heat);
	
	int PreHeatCountMMNew (HeatVo heat);
	BigDecimal PreHeatSumMMNew (HeatVo heat);
	int OweHeatCountMMNew (HeatVo heat);
	BigDecimal OweHeatSumMMNew (HeatVo heat);

   //月统计表格 end      
    
      
   // 年统计表格 start  

  	List<HeatPriceDO> HeatPrice();
	BigDecimal HeatAreaYY(HeatVo heat);
	
  	int PreHeatCountYYOld (HeatVo heat);
  	BigDecimal PreHeatSumYYOld (HeatVo heat);
  	int OweHeatCountYYOld (HeatVo heat);
  	BigDecimal OweHeatSumYYOld (HeatVo heat);

  	int HeatCostYYCount (HeatVo heat);
  	BigDecimal HeatCostYYSum (HeatVo heat);
  	
  	int HeatLogYYCount (HeatVo heat);
  	BigDecimal HeatLogYYSum (HeatVo heat);
  	
  	int PreHeatCountYYNew (HeatVo heat);
  	BigDecimal PreHeatSumYYNew (HeatVo heat);
  	int OweHeatCountYYNew (HeatVo heat);
  	BigDecimal OweHeatSumYYNew (HeatVo heat);
  	
   // 年统计表格 end     
    
    
    
    
    
}
