package com.bootdo.moneyList.service.impl;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.moneyList.dao.MoneyWZDao;
import com.bootdo.moneyList.service.MoneyWZService;
import com.bootdo.moneyLog.vo.ElecLogVo;


@Service
public class MoneyWZServiceImpl implements MoneyWZService {

	@Autowired
	private MoneyWZDao MoneyWZDao;

	
	
	public ElecVo get(Long id) {
		return MoneyWZDao.get(id);
	}
	
	public List<ElecVo> list(Map<String, Object> map) {
		return MoneyWZDao.list(map);
	}

	public List<ElecVo> WZElecList(Long[] ids) {
		return MoneyWZDao.WZElecList(ids);
	}
	
	public List<ElecVo> exprotWZPre(ElecVo elec) {
		return MoneyWZDao.exprotWZPre(elec);
	}

	public List<ElecVo> exprotWZOwe(ElecVo elec) {
		return MoneyWZDao.exprotWZOwe(elec);
	}

	public List<ElecLogVo> exprotWZLog(ElecLogVo elecLog) {
		return MoneyWZDao.exprotWZLog(elecLog);
	}

	public int count(Map<String, Object> map) {
		return MoneyWZDao.count(map);
	}

	public int save(ElecDO elec) {
		return MoneyWZDao.save(elec);
	}

	public int update(ElecDO elec) {
		return MoneyWZDao.update(elec);
	}

	public int updateState(ElecDO elec) {
		return MoneyWZDao.updateState(elec);
	}

	public int remove(Long id) {
		return MoneyWZDao.remove(id);
	}

	public int batchRemove(Long[] ids) {
		return MoneyWZDao.batchRemove(ids);
	}



	


}
