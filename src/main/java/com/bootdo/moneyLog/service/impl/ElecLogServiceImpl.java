package com.bootdo.moneyLog.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.moneyLog.dao.ElecLogDao;
import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;



@Service
public class ElecLogServiceImpl implements ElecLogService {
	
	@Autowired
	private ElecLogDao elecLogDao;
	
	public ElecLogVo get(Long id) {
		return elecLogDao.get(id);
	}

	public List<ElecLogVo> list(Map<String, Object> map) {
		return elecLogDao.list(map);
	}
	
	
	public List<ElecLogVo> exprotElecLogDD(ElecLogVo elecLog) {
		return elecLogDao.exprotElecLogDD(elecLog);
	}
	
	
	public List<ElecLogVo> exprotElecLogMM(ElecLogVo elecLog) {
		return elecLogDao.exprotElecLogMM(elecLog);
	}
	
	public List<ElecLogVo> exprotElecLogYY(ElecLogVo elecLog) {
		return elecLogDao.exprotElecLogYY(elecLog);
	}
	
	public int count(Map<String, Object> map) {
		return elecLogDao.count(map);
	}

	public int  save(ElecLogDO elecLog) {
		return elecLogDao.save(elecLog);
	}

	public int  update(ElecLogDO elecLog) {
		return elecLogDao.update(elecLog);
	}

	public int  remove(Long id) {
		return elecLogDao.remove(id);
	}

	public int  batchRemove(Long[] ids) {
		return elecLogDao.batchRemove(ids);
	}



//日统计 start
	
	public int getElecCountD(ElecLogVo elecLog) {
		return elecLogDao.getElecCountD(elecLog);
	}
	public BigDecimal getElecSumD(ElecLogVo elecLog) {
		return elecLogDao.getElecSumD(elecLog);
	}
	
//日统计 end
	
	
	
	
//日统计表格   start	
	
	public int MElecLogCountA(ElecLogVo elecLog) {
		return elecLogDao.MElecLogCountA(elecLog);
	}
	public BigDecimal MElecLogSumA(ElecLogVo elecLog) {
		return elecLogDao.MElecLogSumA(elecLog);
	}
	public int MElecLogCountC(ElecLogVo elecLog) {
		return elecLogDao.MElecLogCountC(elecLog);
	}
	public BigDecimal MElecLogSumC(ElecLogVo elecLog) {
		return elecLogDao.MElecLogSumC(elecLog);
	}


	public int SElecLogCountA(ElecLogVo elecLog) {
		return elecLogDao.SElecLogCountA(elecLog);
	}
	public BigDecimal SElecLogSumA(ElecLogVo elecLog) {
		return elecLogDao.SElecLogSumA(elecLog);
	}
	public int SElecLogCountC(ElecLogVo elecLog) {
		return elecLogDao.SElecLogCountC(elecLog);
	}
	public BigDecimal SElecLogSumC(ElecLogVo elecLog) {
		return elecLogDao.SElecLogSumC(elecLog);
	}

	
	
	public int GElecLogCountA(ElecLogVo elecLog) {
		return elecLogDao.GElecLogCountA(elecLog);
	}
	public BigDecimal GElecLogSumA(ElecLogVo elecLog) {
		return elecLogDao.GElecLogSumA(elecLog);
	}
	public int GElecLogCountC(ElecLogVo elecLog) {
		return elecLogDao.GElecLogCountC(elecLog);
	}
	public BigDecimal GElecLogSumC(ElecLogVo elecLog) {
		return elecLogDao.GElecLogSumC(elecLog);
	}
	
	
	
	public int YXGElecLogCountA(ElecLogVo elecLog) {
		return elecLogDao.YXGElecLogCountA(elecLog);
	}
	public BigDecimal YXGElecLogSumA(ElecLogVo elecLog) {
		return elecLogDao.YXGElecLogSumA(elecLog);
	}
	public int YXGElecLogCountC(ElecLogVo elecLog) {
		return elecLogDao.YXGElecLogCountC(elecLog);
	}
	public BigDecimal YXGElecLogSumC(ElecLogVo elecLog) {
		return elecLogDao.YXGElecLogSumC(elecLog);
	}
	
	
	
	
	

	public int WElecLogCountA(ElecLogVo elecLog) {
		return elecLogDao.WElecLogCountA(elecLog);
	}
	public BigDecimal WElecLogSumA(ElecLogVo elecLog) {
		return elecLogDao.WElecLogSumA(elecLog);
	}
	public int WElecLogCountC(ElecLogVo elecLog) {
		return elecLogDao.WElecLogCountC(elecLog);
	}
	public BigDecimal WElecLogSumC(ElecLogVo elecLog) {
		return elecLogDao.WElecLogSumC(elecLog);
	}




	public int TElecLogCountA(ElecLogVo elecLog) {
		return elecLogDao.TElecLogCountA(elecLog);
	}
	public BigDecimal TElecLogSumA(ElecLogVo elecLog) {
		return elecLogDao.TElecLogSumA(elecLog);
	}
	public int TElecLogCountC(ElecLogVo elecLog) {
		return elecLogDao.TElecLogCountC(elecLog);
	}
	public BigDecimal TElecLogSumC(ElecLogVo elecLog) {
		return elecLogDao.TElecLogSumC(elecLog);
	}
	

//日统计表格  end		
	
	


//月统计 start		
	
	public int CountMElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountMElecLog(elecLog);
	}
	public BigDecimal SumMElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumMElecLog(elecLog);
	}
	public int CountSElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountSElecLog(elecLog);
	}
	public BigDecimal SumSElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumSElecLog(elecLog);
	}
	public int CountG1ElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountG1ElecLog(elecLog);
	}
	public BigDecimal SumG1ElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumG1ElecLog(elecLog);
	}
	public int CountG2ElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountG2ElecLog(elecLog);
	}
	public BigDecimal SumG2ElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumG2ElecLog(elecLog);
	}
	public int CountG3ElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountG3ElecLog(elecLog);
	}
	public BigDecimal SumG3ElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumG3ElecLog(elecLog);
	}
	
	
	
	
	public int CountWMElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountWMElecLog(elecLog);
	}
	public BigDecimal SumWMElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumWMElecLog(elecLog);
	}
	public int CountWSElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountWSElecLog(elecLog);
	}
	public BigDecimal SumWSElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumWSElecLog(elecLog);
	}
	public int CountWGElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountWGElecLog(elecLog);
	}
	public BigDecimal SumWGElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumWGElecLog(elecLog);
	}


	
	
	public int CountMMElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountMMElecLog(elecLog);
	}
	public BigDecimal SumMMElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumMMElecLog(elecLog);
	}
	public int CountMSElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountMSElecLog(elecLog);
	}
	public BigDecimal SumMSElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumMSElecLog(elecLog);
	}

	
	
	public int CountZElecLog(ElecLogVo elecLog) {
		return elecLogDao.CountZElecLog(elecLog);
	}
	public BigDecimal SumZElecLog(ElecLogVo elecLog) {
		return elecLogDao.SumZElecLog(elecLog);
	}

//月统计 end	
	
	
	
	
//年统计 start	
	
	public int Count1ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count1ElecLog(elecLog);
	}
	public BigDecimal Sum1ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum1ElecLog(elecLog);
	}

	
	public int Count2ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count2ElecLog(elecLog);
	}
	public BigDecimal Sum2ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum2ElecLog(elecLog);
	}

	public int Count3ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count3ElecLog(elecLog);
	}
	public BigDecimal Sum3ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum3ElecLog(elecLog);
	}


	public int Count4ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count4ElecLog(elecLog);
	}
	public BigDecimal Sum4ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum4ElecLog(elecLog);
	}

	
	public int Count5ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count5ElecLog(elecLog);
	}
	public BigDecimal Sum5ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum5ElecLog(elecLog);
	}


	public int Count6ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count6ElecLog(elecLog);
	}
	public BigDecimal Sum6ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum6ElecLog(elecLog);
	}

	
	public int Count7ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count7ElecLog(elecLog);
	}
	public BigDecimal Sum7ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum7ElecLog(elecLog);
	}

	
	public int Count8ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count8ElecLog(elecLog);
	}
	public BigDecimal Sum8ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum8ElecLog(elecLog);
	}

	
	public int Count9ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count9ElecLog(elecLog);
	}
	public BigDecimal Sum9ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum9ElecLog(elecLog);
	}


	public int Count10ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count10ElecLog(elecLog);
	}
	public BigDecimal Sum10ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum10ElecLog(elecLog);
	}


	public int Count11ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count11ElecLog(elecLog);
	}
	public BigDecimal Sum11ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum11ElecLog(elecLog);
	}


	public int Count12ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Count12ElecLog(elecLog);
	}
	public BigDecimal Sum12ElecLog(ElecLogVo elecLog) {
		return elecLogDao.Sum12ElecLog(elecLog);
	}

	
}
