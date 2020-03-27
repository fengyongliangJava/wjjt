package com.bootdo.moneyList.service;

import java.util.List;
import java.util.Map;

import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.moneyLog.vo.ElecLogVo;

public interface MoneyWZService {


	ElecVo get(Long id);
	
	List<ElecVo> list(Map<String,Object> map);
	
	List<ElecVo> WZElecList (Long[] ids);
	
	List<ElecVo> exprotWZPre(ElecVo elec);
	
	List<ElecVo> exprotWZOwe(ElecVo elec);
	
	List<ElecLogVo> exprotWZLog(ElecLogVo elecLog);
	
	int count(Map<String,Object> map);

	int save(ElecDO elec);
	
	int update(ElecDO elec);
	
	int updateState(ElecDO elec);
	
	int remove(Long id);

	int batchRemove(Long[] ids);
	
	
}
