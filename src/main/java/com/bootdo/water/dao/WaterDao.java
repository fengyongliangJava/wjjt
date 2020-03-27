package com.bootdo.water.dao;

import java.math.BigDecimal;
import java.util.List;

import java.util.Map;

import com.bootdo.water.domain.WaterPriceDO;
import org.apache.ibatis.annotations.Mapper;

import com.bootdo.water.domain.WaterDO;
import com.bootdo.water.vo.WaterVo;


@Mapper
public interface WaterDao {

	WaterVo get(Long id);

	WaterVo getWater();

	List<WaterVo> list(Map<String, Object> map);

	List<WaterVo> exprotWater(WaterVo water);

	List<WaterVo> exprotWaterPre(WaterVo water);
	
	List<WaterVo> exprotWaterPreYY(WaterVo water);
	
	List<WaterVo> exprotWaterOwe(WaterVo water);

	List<WaterVo> exprotWaterOweYY(WaterVo water);

	int count(Map<String, Object> map);

	int save(WaterDO water);

	int update(WaterDO water);

	int updateState(WaterDO water);

	int remove(Long id);

	int batchRemove(Long[] ids);


//欠费计表 start
	int WaterCountOwe(WaterVo water);

	BigDecimal WaterSumOwe(WaterVo water);
//欠费统计 end


	
//月统计表格 start
	
	int PreWaterCountMMOld (WaterVo water);
	int PreWaterSumMMOld (WaterVo water);
	int OweWaterCountMMOld (WaterVo water);
	int OweWaterSumMMOld (WaterVo water);

	int WaterCostMMCount (WaterVo water);
	int WaterCostMMSum (WaterVo water);
	
	int WaterLogMMCount (WaterVo water);
	int WaterLogMMSum (WaterVo water);
	
	int PreWaterCountMMNew (WaterVo water);
	int PreWaterSumMMNew (WaterVo water);
	int OweWaterCountMMNew (WaterVo water);
	int OweWaterSumMMNew (WaterVo water);

//月统计表格 end


//年统计表格 start
	
	List<WaterPriceDO> WaterPrice();
	
	int PreWaterCountYYOld (WaterVo water);
	int PreWaterSumYYOld (WaterVo water);
	int OweWaterCountYYOld (WaterVo water);
	int OweWaterSumYYOld (WaterVo water);

	int WaterCostYYCount (WaterVo water);
	int WaterCostYYSum (WaterVo water);
	
	int WaterLogYYCount (WaterVo water);
	int WaterLogYYSum (WaterVo water);
	
	int PreWaterCountYYNew (WaterVo water);
	int PreWaterSumYYNew (WaterVo water);
	int OweWaterCountYYNew (WaterVo water);
	int OweWaterSumYYNew (WaterVo water);
	
//年统计表格 end
	
}
