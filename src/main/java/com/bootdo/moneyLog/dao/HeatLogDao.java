package com.bootdo.moneyLog.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.moneyLog.domain.HeatLogDO;
import com.bootdo.moneyLog.vo.HeatLogVo;


@Mapper
public interface HeatLogDao {


	HeatLogVo get(Long id);
	
	List<HeatLogVo> list(Map<String,Object> map);
	
    List<HeatLogVo> exprotHeatLogDD(HeatLogVo heatLog);
    
    List<HeatLogVo> exprotHeatLogMM(HeatLogVo heatLog);
    
    List<HeatLogVo> exprotHeatLogYY(HeatLogVo heatLog);
    
	int count(Map<String,Object> map);

	int save(HeatLogDO heatLog);
	
	int update(HeatLogDO heatLog);
	
	int remove(Long id);

	int batchRemove(Long[] ids);
	
//日统计 start		
	
	int getHeatCountD(HeatLogVo heatLog );

    BigDecimal getHeatSumD(HeatLogVo heatLog);
    
//日统计 end	  
    
    
//日统计表格 start
	
  	int MHeatLogCountA(HeatLogVo heatLog);
  	BigDecimal MHeatLogSumA(HeatLogVo heatLog);
  	int MHeatLogCountC(HeatLogVo heatLog);
  	BigDecimal MHeatLogSumC(HeatLogVo heatLog);
    
  	int SHeatLogCountA(HeatLogVo heatLog);
  	BigDecimal SHeatLogSumA(HeatLogVo heatLog);
  	int SHeatLogCountC(HeatLogVo heatLog);
  	BigDecimal SHeatLogSumC(HeatLogVo heatLog);
    
//日统计表格 end
    
    
    
//月统计表格 start
    
  	int CountMHeatLog(HeatLogVo heatLog);
    BigDecimal SumMHeatLog(HeatLogVo heatLog);
    
  	int CountSHeatLog(HeatLogVo heatLog);
  	BigDecimal SumSHeatLog(HeatLogVo heatLog);
    
  	int CountGMHeatLog(HeatLogVo heatLog);
  	BigDecimal SumGMHeatLog(HeatLogVo heatLog);
    
  	int CountGSHeatLog(HeatLogVo heatLog);
  	BigDecimal SumGSHeatLog(HeatLogVo heatLog);
    
 //月统计表格 end
    
	
 //年统计 start	
  	
	  int Count1HeatLog(HeatLogVo heatLog);
	
	  BigDecimal Sum1HeatLog(HeatLogVo heatLog);
	  
	  int Count2HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum2HeatLog(HeatLogVo heatLog);

	  int Count3HeatLog(HeatLogVo heatLog);
	
	  BigDecimal Sum3HeatLog(HeatLogVo heatLog);
	
	  int Count4HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum4HeatLog(HeatLogVo heatLog);
	  
	  int Count5HeatLog(HeatLogVo heatLog);
	
	  BigDecimal Sum5HeatLog(HeatLogVo heatLog);
	  
	  int Count6HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum6HeatLog(HeatLogVo heatLog);
	  
	  int Count7HeatLog(HeatLogVo heatLog);
	
	  BigDecimal Sum7HeatLog(HeatLogVo heatLog);
	  
	  int Count8HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum8HeatLog(HeatLogVo heatLog);
	  
	  int Count9HeatLog(HeatLogVo heatLog);
	
	  BigDecimal Sum9HeatLog(HeatLogVo heatLog);
	  
	  int Count10HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum10HeatLog(HeatLogVo heatLog);
	  
	  int Count11HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum11HeatLog(HeatLogVo heatLog);
	  
	  int Count12HeatLog(HeatLogVo heatLog);
  	
	  BigDecimal Sum12HeatLog(HeatLogVo heatLog);
//年统计 end		  
  
	
	

}
