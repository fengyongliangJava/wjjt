package com.bootdo.elec.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.domain.ElecPriceDO;
import com.bootdo.elec.vo.ElecVo;

public interface ElecService {
	
	ElecVo get(Long id);
	
	List<ElecVo> list(Map<String, Object> map);
	
	List<ElecVo> exprotElec(ElecVo elec);
	
	List<ElecVo> exprotElecPre(ElecVo elec);
	
	List<ElecVo> exprotElecOwe(ElecVo elec);
	
	List<ElecVo> exprotElecPreYY(ElecVo elec);
	
	List<ElecVo> exprotElecOweYY(ElecVo elec);
	
	int count(Map<String, Object> map);
	
	int save(ElecDO elec);
	
	int update(ElecDO elec);
	
	int updateState(ElecDO elec);

	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	
//欠费统计    start	
	int ElecCountOwe(ElecVo elec);
	BigDecimal ElecSumOwe(ElecVo elec);
//欠费统计   end		
	
	
//月统计表格    start

	int ElecAmountMM(ElecVo elec);
	
	int PreElecCountMMOld (ElecVo elec);
	BigDecimal PreElecSumMMOld (ElecVo elec);
	int OweElecCountMMOld (ElecVo elec);
	BigDecimal OweElecSumMMOld (ElecVo elec);

	int ElecCostMMCount (ElecVo elec);
	BigDecimal ElecCostMMSum (ElecVo elec);
	
	int ElecLogMMCount (ElecVo elec);
	BigDecimal ElecLogMMSum (ElecVo elec);
	
	int PreElecCountMMNew (ElecVo elec);
	BigDecimal PreElecSumMMNew (ElecVo elec);
	int OweElecCountMMNew (ElecVo elec);
	BigDecimal OweElecSumMMNew (ElecVo elec);
	
	
	
//月统计表格    end    
    
	


 	  
// 年统计表格 start  

	List<ElecPriceDO> ElecPrice();
	int ElecAmountYY(ElecVo elec);
	
	int PreElecCountYYOld (ElecVo elec);
	BigDecimal PreElecSumYYOld (ElecVo elec);
	int OweElecCountYYOld (ElecVo elec);
	BigDecimal OweElecSumYYOld (ElecVo elec);

	int ElecCostYYCount (ElecVo elec);
	BigDecimal ElecCostYYSum (ElecVo elec);
	
	int ElecLogYYCount (ElecVo elec);
	BigDecimal ElecLogYYSum (ElecVo elec);
	
	int PreElecCountYYNew (ElecVo elec);
	BigDecimal PreElecSumYYNew (ElecVo elec);
	int OweElecCountYYNew (ElecVo elec);
	BigDecimal OweElecSumYYNew (ElecVo elec);
   	  
 // 年统计表格 end 
    
    
}
