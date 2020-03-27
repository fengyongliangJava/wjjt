package com.bootdo.moneyLog.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.moneyLog.dao.WaterLogDao;
import com.bootdo.moneyLog.domain.WaterLogDO;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.WaterLogVo;


@Service
public class WaterLogServiceImpl implements WaterLogService {
	
	@Autowired
	private WaterLogDao waterLogDao;

	public List<WaterLogVo> list(Map<String, Object> map) {
		return waterLogDao.list(map);
	}
	
	public List<WaterLogVo> exprotWaterLogDD(WaterLogVo waterLog) {
		return waterLogDao.exprotWaterLogDD(waterLog);
	}
	
	public List<WaterLogVo> exprotWaterLogMM(WaterLogVo waterLog) {
		return waterLogDao.exprotWaterLogMM(waterLog);
	}
	
	public List<WaterLogVo> exprotWaterLogYY(WaterLogVo waterLog) {
		return waterLogDao.exprotWaterLogYY(waterLog);
	}
	
	public int count(Map<String, Object> map) {
		return waterLogDao.count(map);
	}

	public int save(WaterLogDO waterLog) {
		return waterLogDao.save(waterLog);
	}

	public int update(WaterLogDO waterLog) {
		return waterLogDao.update(waterLog);
	}

	public int remove(Long id) {
		return waterLogDao.remove(id);
	}

	public int batchRemove(Long[] ids) {
		return waterLogDao.batchRemove(ids);
	}


	public WaterLogVo get(Long id) {
		return waterLogDao.get(id);
	}

	
//日统计 start	
	public int getWaterCountD(WaterLogVo waterLog) {
		return waterLogDao.getWaterCountD(waterLog);
	}
	
	public BigDecimal getWaterSumD(WaterLogVo waterLog) {
		return waterLogDao.getWaterSumD(waterLog);
	}
	
//日统计 end
	
	
//日统计表格 start
	
	public int WaterLogCountA(WaterLogVo waterLog) {
		return waterLogDao.WaterLogCountA(waterLog);
	}
	public BigDecimal WaterLogSumA(WaterLogVo waterLog) {
		return waterLogDao.WaterLogSumA(waterLog);
	}
	public int WaterLogCountC(WaterLogVo waterLog) {
		return waterLogDao.WaterLogCountC(waterLog);
	}
	public BigDecimal WaterLogSumC(WaterLogVo waterLog) {
		return waterLogDao.WaterLogSumC(waterLog);
	}
	
	
	
	public int MWaterLogCountA(WaterLogVo waterLog) {
		return waterLogDao.MWaterLogCountA(waterLog);
	}
	public BigDecimal MWaterLogSumA(WaterLogVo waterLog) {
		return waterLogDao.MWaterLogSumA(waterLog);
	}
	public int MWaterLogCountC(WaterLogVo waterLog) {
		return waterLogDao.MWaterLogCountC(waterLog);
	}
	public BigDecimal MWaterLogSumC(WaterLogVo waterLog) {
		return waterLogDao.MWaterLogSumC(waterLog);
	}
	
	
	
	
	public int SWaterLogCountA(WaterLogVo waterLog) {
		return waterLogDao.SWaterLogCountA(waterLog);
	}
	public BigDecimal SWaterLogSumA(WaterLogVo waterLog) {
		return waterLogDao.SWaterLogSumA(waterLog);
	}
	public int SWaterLogCountC(WaterLogVo waterLog) {
		return waterLogDao.SWaterLogCountC(waterLog);
	}
	public BigDecimal SWaterLogSumC(WaterLogVo waterLog) {
		return waterLogDao.SWaterLogSumC(waterLog);
	}
	
//日统计表格 end
	
	
	
//月统计 start
	
	public int CountMWaterLog(WaterLogVo waterLog) {
		return waterLogDao.CountMWaterLog(waterLog);
	}
	public BigDecimal SumMWaterLog(WaterLogVo waterLog) {
		return waterLogDao.SumMWaterLog(waterLog);
	}
	
	public int CountS1WaterLog(WaterLogVo waterLog) {
		return waterLogDao.CountS1WaterLog(waterLog);
	}
	public BigDecimal SumS1WaterLog(WaterLogVo waterLog) {
		return waterLogDao.SumS1WaterLog(waterLog);
	}
	
	public int CountS2WaterLog(WaterLogVo waterLog) {
		return waterLogDao.CountS2WaterLog(waterLog);
	}
	public BigDecimal SumS2WaterLog(WaterLogVo waterLog) {
		return waterLogDao.SumS2WaterLog(waterLog);
	}
	
	public int CountS3WaterLog(WaterLogVo waterLog) {
		return waterLogDao.CountS3WaterLog(waterLog);
	}
	public BigDecimal SumS3WaterLog(WaterLogVo waterLog) {
		return waterLogDao.SumS3WaterLog(waterLog);
	}
//月统计 end		
	

	
//年统计 start	
		
	public int Count1WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count1WaterLog(waterLog);
	}
	public BigDecimal Sum1WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum1WaterLog(waterLog);
	}
	
	
	public int Count2WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count2WaterLog(waterLog);
	}
	public BigDecimal Sum2WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum2WaterLog(waterLog);
	}

	public int Count3WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count3WaterLog(waterLog);
	}
	public BigDecimal Sum3WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum3WaterLog(waterLog);
	}


	public int Count4WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count4WaterLog(waterLog);
	}
	public BigDecimal Sum4WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum4WaterLog(waterLog);
	}

	
	public int Count5WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count5WaterLog(waterLog);
	}
	public BigDecimal Sum5WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum5WaterLog(waterLog);
	}


	public int Count6WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count6WaterLog(waterLog);
	}
	public BigDecimal Sum6WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum6WaterLog(waterLog);
	}

	
	public int Count7WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count7WaterLog(waterLog);
	}
	public BigDecimal Sum7WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum7WaterLog(waterLog);
	}

	
	public int Count8WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count8WaterLog(waterLog);
	}
	public BigDecimal Sum8WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum8WaterLog(waterLog);
	}

	
	public int Count9WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count9WaterLog(waterLog);
	}
	public BigDecimal Sum9WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum9WaterLog(waterLog);
	}


	public int Count10WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count10WaterLog(waterLog);
	}
	public BigDecimal Sum10WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum10WaterLog(waterLog);
	}


	public int Count11WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count11WaterLog(waterLog);
	}
	public BigDecimal Sum11WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum11WaterLog(waterLog);
	}


	public int Count12WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Count12WaterLog(waterLog);
	}
	public BigDecimal Sum12WaterLog(WaterLogVo waterLog) {
		return waterLogDao.Sum12WaterLog(waterLog);
	}
	
	
// 年统计 	
	
	

	
	
	
}
