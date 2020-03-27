package com.bootdo.heat.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.heat.dao.HeatDao;
import com.bootdo.heat.domain.HeatDO;
import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.service.HeatService;
import com.bootdo.heat.vo.HeatVo;



@Service
public class HeatServiceImpl implements HeatService {
	
	@Autowired
	private HeatDao heatDao;
	
	
	public HeatVo get(Integer id){
		return heatDao.get(id);
	}
	public List<HeatVo> list(Map<String, Object> map){
		return heatDao.list(map);
	}
	public List<HeatVo> exprotHeat(HeatVo heat) {
		return heatDao.exprotHeat(heat);
	}
	public List<HeatVo> exprotHeatPre(HeatVo heat) {
		return heatDao.exprotHeatPre(heat);
	}
	public List<HeatVo> exprotHeatOwe(HeatVo heat) {
		return heatDao.exprotHeatOwe(heat);
	}
	
	public List<HeatVo> exprotHeatPreYY(HeatVo heat) {
		return heatDao.exprotHeatPreYY(heat);
	}
	public List<HeatVo> exprotHeatOweYY(HeatVo heat) {
		return heatDao.exprotHeatOweYY(heat);
	}
	
	public int count(Map<String, Object> map){
		return heatDao.count(map);
	}
	public int save(HeatDO heat){
		return heatDao.save(heat);
	}
	public int update(HeatDO heat){
		return heatDao.update(heat);
	}
	public int updateState(HeatDO heat) {
		return heatDao.updateState(heat);
	}
	
	public int remove(Integer id){
		return heatDao.remove(id);
	}
	public int batchRemove(Integer[] ids){
		return heatDao.batchRemove(ids);
	}
	
	

	public int HeatCountOwe(HeatVo heat) {
		return heatDao.HeatCountOwe(heat);
	}
	public BigDecimal HeatSumOwe(HeatVo heat) {
		return heatDao.HeatSumOwe(heat);
	}
	
	
//月统计  start

	public BigDecimal HeatAreaMM(HeatVo heat){
		return heatDao.HeatAreaMM(heat);
	}
	
	
	public int PreHeatCountMMOld(HeatVo heat) {
		return heatDao.PreHeatCountMMOld(heat);
	}
	public BigDecimal PreHeatSumMMOld(HeatVo heat) {
		return heatDao.PreHeatSumMMOld(heat);
	}
	public int OweHeatCountMMOld(HeatVo heat) {
		return heatDao.OweHeatCountMMOld(heat);
	}
	public BigDecimal OweHeatSumMMOld(HeatVo heat) {
		return heatDao.OweHeatSumMMOld(heat);
	}
	
	
	
	public int HeatCostMMCount(HeatVo heat) {
		return heatDao.HeatCostMMCount(heat);
	}
	public BigDecimal HeatCostMMSum(HeatVo heat) {
		return heatDao.HeatCostMMSum(heat);
	}
	

	public int HeatLogMMCount(HeatVo heat) {
		return heatDao.HeatLogMMCount(heat);
	}
	public BigDecimal HeatLogMMSum(HeatVo heat) {
		return heatDao.HeatLogMMSum(heat);
	}
	
	
	public int PreHeatCountMMNew(HeatVo heat) {
		return heatDao.PreHeatCountMMNew(heat);
	}
	public BigDecimal PreHeatSumMMNew(HeatVo heat) {
		return heatDao.PreHeatSumMMNew(heat);
	}
	public int OweHeatCountMMNew(HeatVo heat) {
		return heatDao.OweHeatCountMMNew(heat);
	}
	public BigDecimal OweHeatSumMMNew(HeatVo heat) {
		return heatDao.OweHeatSumMMNew(heat);
	}

	

//月统计  end 
	
	
//年统计表格 start 
	
	
	public List<HeatPriceDO> HeatPrice() {
		return heatDao.HeatPrice();
	}
	public BigDecimal HeatAreaYY(HeatVo heat) {
		return heatDao.HeatAreaYY(heat);
	}
	
	
	
	public int PreHeatCountYYOld(HeatVo heat) {
		return heatDao.PreHeatCountYYOld(heat);
	}
	public BigDecimal PreHeatSumYYOld(HeatVo heat) {
		return heatDao.PreHeatSumYYOld(heat);
	}
	public int OweHeatCountYYOld(HeatVo heat) {
		return heatDao.OweHeatCountYYOld(heat);
	}
	public BigDecimal OweHeatSumYYOld(HeatVo heat) {
		return heatDao.OweHeatSumYYOld(heat);
	}
	
	
	
	public int HeatCostYYCount(HeatVo heat) {
		return heatDao.HeatCostYYCount(heat);
	}
	public BigDecimal HeatCostYYSum(HeatVo heat) {
		return heatDao.HeatCostYYSum(heat);
	}
	

	public int HeatLogYYCount(HeatVo heat) {
		return heatDao.HeatLogYYCount(heat);
	}
	public BigDecimal HeatLogYYSum(HeatVo heat) {
		return heatDao.HeatLogYYSum(heat);
	}
	
	
	public int PreHeatCountYYNew(HeatVo heat) {
		return heatDao.PreHeatCountYYNew(heat);
	}
	public BigDecimal PreHeatSumYYNew(HeatVo heat) {
		return heatDao.PreHeatSumYYNew(heat);
	}
	public int OweHeatCountYYNew(HeatVo heat) {
		return heatDao.OweHeatCountYYNew(heat);
	}
	public BigDecimal OweHeatSumYYNew(HeatVo heat) {
		return heatDao.OweHeatSumYYNew(heat);
	}

//年统计表格 end	
	
	
	
	
}
