package com.bootdo.water.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bootdo.water.domain.WaterPriceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.water.dao.WaterDao;
import com.bootdo.water.domain.WaterDO;
import com.bootdo.water.service.WaterService;
import com.bootdo.water.vo.WaterVo;


@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterDao waterDao;

    public WaterVo get(Long id) {
        return waterDao.get(id);
    }

    public List<WaterVo> list(Map<String, Object> map) {
        return waterDao.list(map);
    }

    public List<WaterVo> exprotWater(WaterVo water) {
        return waterDao.exprotWater(water);
    }

    public List<WaterVo> exprotWaterPre(WaterVo water) {
        return waterDao.exprotWaterPre(water);
    }

    public List<WaterVo> exprotWaterPreYY(WaterVo water) {
        return waterDao.exprotWaterPreYY(water);
    }
    
    public List<WaterVo> exprotWaterOwe(WaterVo water) {
        return waterDao.exprotWaterOwe(water);
    }
    
    public List<WaterVo> exprotWaterOweYY(WaterVo water) {
        return waterDao.exprotWaterOweYY(water);
    }

    public int count(Map<String, Object> map) {
        return waterDao.count(map);
    }

    public int save(WaterDO water) {
        return waterDao.save(water);
    }

    public int update(WaterDO water) {
        return waterDao.update(water);
    }

    public int updateState(WaterDO water) {
        return waterDao.updateState(water);
    }

    public int remove(Long id) {
        return waterDao.remove(id);
    }

    public int batchRemove(Long[] ids) {
        return waterDao.batchRemove(ids);
    }


    public int WaterCountOwe(WaterVo water) {
        return waterDao.WaterCountOwe(water);
    }

    public BigDecimal WaterSumOwe(WaterVo water) {
        return waterDao.WaterSumOwe(water);
    }


//月统计

	public int PreWaterCountMMOld(WaterVo water) {
		return waterDao.PreWaterCountMMOld(water);
	}
	public int PreWaterSumMMOld(WaterVo water) {
		return waterDao.PreWaterSumMMOld(water);
	}
	public int OweWaterCountMMOld(WaterVo water) {
		return waterDao.OweWaterCountMMOld(water);
	}
	public int OweWaterSumMMOld(WaterVo water) {
		return waterDao.OweWaterSumMMOld(water);
	}


	public int WaterCostMMCount(WaterVo water) {
		return waterDao.WaterCostMMCount(water);
	}
	public int WaterCostMMSum(WaterVo water) {
		return waterDao.WaterCostMMSum(water);
	}

	
	public int WaterLogMMCount(WaterVo water) {
		return waterDao.WaterLogMMCount(water);
	}
	public int WaterLogMMSum(WaterVo water) {
		return waterDao.WaterLogMMSum(water);
	}

	
	
	public int PreWaterCountMMNew(WaterVo water) {
		return waterDao.PreWaterCountMMNew(water);
	}
	public int PreWaterSumMMNew(WaterVo water) {
		return waterDao.PreWaterSumMMNew(water);
	}
	public int OweWaterCountMMNew(WaterVo water) {
		return waterDao.OweWaterCountMMNew(water);
	}
	public int OweWaterSumMMNew(WaterVo water) {
		return waterDao.OweWaterSumMMNew(water);
	}
   


//月统计 	

    
    
    
    
//年统计
   
	public List<WaterPriceDO> WaterPrice() {
		return waterDao.WaterPrice();
	}

	
	
	public int PreWaterCountYYOld(WaterVo water) {
		return waterDao.PreWaterCountYYOld(water);
	}
	public int PreWaterSumYYOld(WaterVo water) {
		return waterDao.PreWaterSumYYOld(water);
	}
	public int OweWaterCountYYOld(WaterVo water) {
		return waterDao.OweWaterCountYYOld(water);
	}
	public int OweWaterSumYYOld(WaterVo water) {
		return waterDao.OweWaterSumYYOld(water);
	}


	public int WaterCostYYCount(WaterVo water) {
		return waterDao.WaterCostYYCount(water);
	}
	public int WaterCostYYSum(WaterVo water) {
		return waterDao.WaterCostYYSum(water);
	}

	
	public int WaterLogYYCount(WaterVo water) {
		return waterDao.WaterLogYYCount(water);
	}
	public int WaterLogYYSum(WaterVo water) {
		return waterDao.WaterLogYYSum(water);
	}

	
	
	public int PreWaterCountYYNew(WaterVo water) {
		return waterDao.PreWaterCountYYNew(water);
	}
	public int PreWaterSumYYNew(WaterVo water) {
		return waterDao.PreWaterSumYYNew(water);
	}
	public int OweWaterCountYYNew(WaterVo water) {
		return waterDao.OweWaterCountYYNew(water);
	}
	public int OweWaterSumYYNew(WaterVo water) {
		return waterDao.OweWaterSumYYNew(water);
	}

//年统计

}
