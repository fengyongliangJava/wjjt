package com.bootdo.moneyLog.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.moneyLog.dao.HeatLogDao;
import com.bootdo.moneyLog.domain.HeatLogDO;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.vo.HeatLogVo;


@Service
public class HeatLogServiceImpl implements HeatLogService {
	
	@Autowired
	private HeatLogDao heatLogDao;
	

	public HeatLogVo get(Long id) {
		return heatLogDao.get(id);
	}

	public List<HeatLogVo> list(Map<String, Object> map) {
		
		return heatLogDao.list(map);
	}
	
	public List<HeatLogVo> exprotHeatLogDD(HeatLogVo heatLog) {
		return heatLogDao.exprotHeatLogDD(heatLog);
	}
	
	public List<HeatLogVo> exprotHeatLogMM(HeatLogVo heatLog) {
		return heatLogDao.exprotHeatLogMM(heatLog);
	}
	
	public List<HeatLogVo> exprotHeatLogYY(HeatLogVo heatLog) {
		return heatLogDao.exprotHeatLogYY(heatLog);
	}

	public int count(Map<String, Object> map) {
		return heatLogDao.count(map);
	}

	public int save(HeatLogDO heatLog) {
		return heatLogDao.save(heatLog);
	}

	public int update(HeatLogDO heatLog) {
		return heatLogDao.update(heatLog);
	}

	public int remove(Long id) {
		return heatLogDao.remove(id);
	}

	public int batchRemove(Long[] ids) {
		return heatLogDao.batchRemove(ids);
	}



	
//日统计 start		
	
	public int getHeatCountD(HeatLogVo heatLog) {
		return heatLogDao.getHeatCountD(heatLog);
	}
	public BigDecimal getHeatSumD(HeatLogVo heatLog) {
		return heatLogDao.getHeatSumD(heatLog);
	}
	
//日统计 end
	
	
	
//日统计表格 start
	
	public int MHeatLogCountA(HeatLogVo heatLog) {
		return heatLogDao.MHeatLogCountA(heatLog);
	}
	public BigDecimal MHeatLogSumA(HeatLogVo heatLog) {
		return heatLogDao.MHeatLogSumA(heatLog);
	}
	public int MHeatLogCountC(HeatLogVo heatLog) {
		return heatLogDao.MHeatLogCountC(heatLog);
	}
	public BigDecimal MHeatLogSumC(HeatLogVo heatLog) {
		return heatLogDao.MHeatLogSumC(heatLog);
	}
	public int SHeatLogCountA(HeatLogVo heatLog) {
		return heatLogDao.SHeatLogCountA(heatLog);
	}
	public BigDecimal SHeatLogSumA(HeatLogVo heatLog) {
		return heatLogDao.SHeatLogSumA(heatLog);
	}
	public int SHeatLogCountC(HeatLogVo heatLog) {
		return heatLogDao.SHeatLogCountC(heatLog);
	}
	public BigDecimal SHeatLogSumC(HeatLogVo heatLog) {
		return heatLogDao.SHeatLogSumC(heatLog);
	}
	
//日统计表格 end	
	
	
//月统计表格 start		
	
	public int CountMHeatLog(HeatLogVo heatLog) {
		return heatLogDao.CountMHeatLog(heatLog);
	}
	public BigDecimal SumMHeatLog(HeatLogVo heatLog) {
		return heatLogDao.SumMHeatLog(heatLog);
	}
	
	public int CountSHeatLog(HeatLogVo heatLog) {
		return heatLogDao.CountSHeatLog(heatLog);
	}
	public BigDecimal SumSHeatLog(HeatLogVo heatLog) {
		return heatLogDao.SumSHeatLog(heatLog);
	}
	
	public int CountGMHeatLog(HeatLogVo heatLog) {
		return heatLogDao.CountGMHeatLog(heatLog);
	}
	public BigDecimal SumGMHeatLog(HeatLogVo heatLog) {
		return heatLogDao.SumGMHeatLog(heatLog);
	}
	
	public int CountGSHeatLog(HeatLogVo heatLog) {
		return heatLogDao.CountGSHeatLog(heatLog);
	}
	public BigDecimal SumGSHeatLog(HeatLogVo heatLog) {
		return heatLogDao.SumGSHeatLog(heatLog);
	}
//月统计表格 end	

	
//年统计 start	
	
		public int Count1HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count1HeatLog(heatLog);
		}
		public BigDecimal Sum1HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum1HeatLog(heatLog);
		}

		
		public int Count2HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count2HeatLog(heatLog);
		}
		public BigDecimal Sum2HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum2HeatLog(heatLog);
		}

		public int Count3HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count3HeatLog(heatLog);
		}
		public BigDecimal Sum3HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum3HeatLog(heatLog);
		}


		public int Count4HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count4HeatLog(heatLog);
		}
		public BigDecimal Sum4HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum4HeatLog(heatLog);
		}

		
		public int Count5HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count5HeatLog(heatLog);
		}
		public BigDecimal Sum5HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum5HeatLog(heatLog);
		}


		public int Count6HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count6HeatLog(heatLog);
		}
		public BigDecimal Sum6HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum6HeatLog(heatLog);
		}

		
		public int Count7HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count7HeatLog(heatLog);
		}
		public BigDecimal Sum7HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum7HeatLog(heatLog);
		}

		
		public int Count8HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count8HeatLog(heatLog);
		}
		public BigDecimal Sum8HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum8HeatLog(heatLog);
		}

		
		public int Count9HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count9HeatLog(heatLog);
		}
		public BigDecimal Sum9HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum9HeatLog(heatLog);
		}


		public int Count10HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count10HeatLog(heatLog);
		}
		public BigDecimal Sum10HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum10HeatLog(heatLog);
		}


		public int Count11HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count11HeatLog(heatLog);
		}
		public BigDecimal Sum11HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum11HeatLog(heatLog);
		}


		public int Count12HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Count12HeatLog(heatLog);
		}
		public BigDecimal Sum12HeatLog(HeatLogVo heatLog) {
			return heatLogDao.Sum12HeatLog(heatLog);
		}

		
//年统计 end	
	


		
		
		
		
		
		
	
}
