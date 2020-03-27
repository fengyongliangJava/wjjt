package com.bootdo.moneyLog.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.moneyLog.vo.ElecLogVo;





@Mapper
public interface ElecLogDao {
	
	ElecLogVo get(Long id);
	
	List<ElecLogVo> list(Map<String,Object> map);
    
    List<ElecLogVo> exprotElecLogDD(ElecLogVo elecLog);
    
    List<ElecLogVo> exprotElecLogMM(ElecLogVo elecLog);
    
    List<ElecLogVo> exprotElecLogYY(ElecLogVo elecLog);
	
	int count(Map<String,Object> map);

	int save(ElecLogDO elecLog);
	
	int update(ElecLogDO elecLog);
	
	int remove(Long id);

	int batchRemove(Long[] ids);
	
	
	
	
	
//日统计 start
		
	int getElecCountD(ElecLogVo elecLog);

    BigDecimal getElecSumD(ElecLogVo elecLog);

//日统计 end   
    
    
//日统计表格 start
	
  	int  MElecLogCountA(ElecLogVo elecLog);
  	BigDecimal  MElecLogSumA(ElecLogVo elecLog);
  	int  MElecLogCountC(ElecLogVo elecLog);
  	BigDecimal  MElecLogSumC(ElecLogVo elecLog);
    
  	int  SElecLogCountA(ElecLogVo elecLog);
  	BigDecimal  SElecLogSumA(ElecLogVo elecLog);
  	int  SElecLogCountC(ElecLogVo elecLog);
  	BigDecimal SElecLogSumC(ElecLogVo elecLog);
  	
  	
  	int GElecLogCountA(ElecLogVo elecLog);
  	BigDecimal GElecLogSumA(ElecLogVo elecLog);
  	int GElecLogCountC(ElecLogVo elecLog);
  	BigDecimal GElecLogSumC(ElecLogVo elecLog);
  	
  	
  	int YXGElecLogCountA(ElecLogVo elecLog);
  	BigDecimal YXGElecLogSumA(ElecLogVo elecLog);
  	int YXGElecLogCountC(ElecLogVo elecLog);
  	BigDecimal YXGElecLogSumC(ElecLogVo elecLog);
    
  	
  	int  WElecLogCountA(ElecLogVo elecLog);
  	BigDecimal  WElecLogSumA(ElecLogVo elecLog);
  	int  WElecLogCountC(ElecLogVo elecLog);
  	BigDecimal  WElecLogSumC(ElecLogVo elecLog);
    
    
  	int TElecLogCountA(ElecLogVo elecLog);
  	BigDecimal TElecLogSumA(ElecLogVo elecLog);
  	int TElecLogCountC(ElecLogVo elecLog);
  	BigDecimal TElecLogSumC(ElecLogVo elecLog);
    

//日统计表格 end   
    
    
    
//月统计 start	
	
      int CountMElecLog(ElecLogVo elecLog);
  	
  	  BigDecimal SumMElecLog(ElecLogVo elecLog);

  	  int CountSElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumSElecLog(ElecLogVo elecLog); 	

  	  int CountG1ElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumG1ElecLog(ElecLogVo elecLog); 	
  	  
  	  int CountG2ElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumG2ElecLog(ElecLogVo elecLog); 	
  	  
  	  int CountG3ElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumG3ElecLog(ElecLogVo elecLog); 	
  	  
  	  int CountWMElecLog(ElecLogVo elecLog);
    	
  	  BigDecimal SumWMElecLog(ElecLogVo elecLog);

  	  int CountWSElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumWSElecLog(ElecLogVo elecLog); 	

  	  int CountWGElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumWGElecLog(ElecLogVo elecLog); 

  	  int CountMMElecLog(ElecLogVo elecLog);
    	
  	  BigDecimal SumMMElecLog(ElecLogVo elecLog);

  	  int CountMSElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumMSElecLog(ElecLogVo elecLog); 	
  	  
  	  int CountZElecLog(ElecLogVo elecLog);   

  	  BigDecimal SumZElecLog(ElecLogVo elecLog); 	
  	
  //月统计 end			

  	  
  	  
  	  
  	  
    
  //年统计 start	
  	  int Count1ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum1ElecLog(ElecLogVo elecLog);
  	  
  	  int Count2ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum2ElecLog(ElecLogVo elecLog);

  	  int Count3ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum3ElecLog(ElecLogVo elecLog);
  	
  	  int Count4ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum4ElecLog(ElecLogVo elecLog);
  	  
  	  int Count5ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum5ElecLog(ElecLogVo elecLog);
  	  
  	  int Count6ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum6ElecLog(ElecLogVo elecLog);
  	  
  	  int Count7ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum7ElecLog(ElecLogVo elecLog);
  	  
  	  int Count8ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum8ElecLog(ElecLogVo elecLog);
  	  
  	  int Count9ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum9ElecLog(ElecLogVo elecLog);
  	  
  	  int Count10ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum10ElecLog(ElecLogVo elecLog);
  	  
  	  int Count11ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum11ElecLog(ElecLogVo elecLog);
  	  
  	  int Count12ElecLog(ElecLogVo elecLog);
  	  BigDecimal Sum12ElecLog(ElecLogVo elecLog);
 //年统计 end		  
    
    

    
}
