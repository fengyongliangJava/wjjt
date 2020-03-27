package com.bootdo.moneyLog.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.moneyLog.domain.WaterLogDO;
import com.bootdo.moneyLog.vo.WaterLogVo;


@Mapper
public interface WaterLogDao {


	WaterLogVo get(Long id);
	
	List<WaterLogVo> list(Map<String,Object> map);
	
    List<WaterLogVo> exprotWaterLogDD(WaterLogVo waterLog);
	
    List<WaterLogVo> exprotWaterLogMM(WaterLogVo waterLog);
    
    List<WaterLogVo> exprotWaterLogYY(WaterLogVo waterLog);
    
	int count(Map<String,Object> map);

	int save(WaterLogDO waterLog);
	
	int update(WaterLogDO waterLog);
	
	int remove(Long id);

	int batchRemove(Long[] ids);
	
 //日统计 start		
	
	int getWaterCountD(WaterLogVo waterLog);

	BigDecimal getWaterSumD(WaterLogVo waterLog);
    
 //日统计 end	   
    
    
//日统计表格 start		
	
   int WaterLogCountA(WaterLogVo waterLog);
   BigDecimal WaterLogSumA(WaterLogVo waterLog);
   int WaterLogCountC(WaterLogVo waterLog);
   BigDecimal WaterLogSumC(WaterLogVo waterLog);
   
   
   int MWaterLogCountA(WaterLogVo waterLog);
   BigDecimal MWaterLogSumA(WaterLogVo waterLog);
   int MWaterLogCountC(WaterLogVo waterLog);
   BigDecimal MWaterLogSumC(WaterLogVo waterLog);
   
   
   int SWaterLogCountA(WaterLogVo waterLog);
   BigDecimal SWaterLogSumA(WaterLogVo waterLog);
   int SWaterLogCountC(WaterLogVo waterLog);
   BigDecimal SWaterLogSumC(WaterLogVo waterLog);
   
   
//日统计表格 end	
    
   
   
//月统计表格 start
   
   int CountMWaterLog(WaterLogVo waterLog);
   BigDecimal SumMWaterLog(WaterLogVo waterLog);
   
   int CountS1WaterLog(WaterLogVo waterLog);
   BigDecimal  SumS1WaterLog(WaterLogVo waterLog);
   
   int CountS2WaterLog(WaterLogVo waterLog);
   BigDecimal  SumS2WaterLog(WaterLogVo waterLog);
   
   int CountS3WaterLog(WaterLogVo waterLog);
   BigDecimal  SumS3WaterLog(WaterLogVo waterLog);
   
//月统计表格 end
   
   
    
	  
//年统计 start	
	   	
 	  int Count1WaterLog(WaterLogVo waterLog);
 	
 	  BigDecimal Sum1WaterLog(WaterLogVo waterLog);
 	  
 	  int Count2WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum2WaterLog(WaterLogVo waterLog);

 	  int Count3WaterLog(WaterLogVo waterLog);
 	
 	  BigDecimal Sum3WaterLog(WaterLogVo waterLog);
 	
 	  int Count4WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum4WaterLog(WaterLogVo waterLog);
 	  
 	  int Count5WaterLog(WaterLogVo waterLog);
 	
 	  BigDecimal Sum5WaterLog(WaterLogVo waterLog);
 	  
 	  int Count6WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum6WaterLog(WaterLogVo waterLog);
 	  
 	  int Count7WaterLog(WaterLogVo waterLog);
 	
 	  BigDecimal Sum7WaterLog(WaterLogVo waterLog);
 	  
 	  int Count8WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum8WaterLog(WaterLogVo waterLog);
 	  
 	  int Count9WaterLog(WaterLogVo waterLog);
 	
 	  BigDecimal Sum9WaterLog(WaterLogVo waterLog);
 	  
 	  int Count10WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum10WaterLog(WaterLogVo waterLog);
 	  
 	  int Count11WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum11WaterLog(WaterLogVo waterLog);
 	  
 	  int Count12WaterLog(WaterLogVo waterLog);
   	
 	  BigDecimal Sum12WaterLog(WaterLogVo waterLog);
 	  
//年统计 end		  
	   
	 	
	  
    
	  

 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  
 	  

}
