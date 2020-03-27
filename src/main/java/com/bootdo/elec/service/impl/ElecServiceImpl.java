package com.bootdo.elec.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.elec.dao.ElecDao;
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.domain.ElecPriceDO;
import com.bootdo.elec.service.ElecService;
import com.bootdo.elec.vo.ElecVo;



@Service
public class ElecServiceImpl implements ElecService {
	
	@Autowired
	private ElecDao elecDao;
	

	public ElecVo get(Long id){
		return elecDao.get(id);
	}
	

	public List<ElecVo> list(Map<String, Object> map){
		return elecDao.list(map);
	}

	public List<ElecVo> exprotElec(ElecVo elec) {
		return elecDao.exprotElec(elec);
	}
	
	public List<ElecVo> exprotElecPre(ElecVo elec) {
		return elecDao.exprotElecPre(elec);
	}

	public List<ElecVo> exprotElecOwe(ElecVo elec) {
		return elecDao.exprotElecOwe(elec);
	}

	public List<ElecVo> exprotElecPreYY(ElecVo elec) {
		return elecDao.exprotElecPreYY(elec);
	}

	public List<ElecVo> exprotElecOweYY(ElecVo elec) {
		return elecDao.exprotElecOweYY(elec);
	}

	public int count(Map<String, Object> map){
		return elecDao.count(map);
	}
	

	public int save(ElecDO elec){
		return elecDao.save(elec);
	}
	

	public int update(ElecDO elec){
		return elecDao.update(elec);
	}
	public int updateState(ElecDO elec) {
		return elecDao.updateState(elec);
	}
	

	

	public int remove(Long id){
		return elecDao.remove(id);
	}
	public int batchRemove(Long[] ids){
		return elecDao.batchRemove(ids);
	}


	public int ElecCountOwe(ElecVo elec) {
		return elecDao.ElecCountOwe(elec);
	}
	public BigDecimal ElecSumOwe(ElecVo elec) {
		return elecDao.ElecSumOwe(elec);
	}

	
	
	
	
	
//月统计表格    start

	public int ElecAmountMM(ElecVo elec) {
		return elecDao.ElecAmountMM(elec);
	}
	
	
	
	public int PreElecCountMMOld(ElecVo elec) {
		return elecDao.PreElecCountMMOld(elec);
	}
	public BigDecimal PreElecSumMMOld(ElecVo elec) {
		return elecDao.PreElecSumMMOld(elec);
	}
	public int OweElecCountMMOld(ElecVo elec) {
		return elecDao.OweElecCountMMOld(elec);
	}
	public BigDecimal OweElecSumMMOld(ElecVo elec) {
		return elecDao.OweElecSumMMOld(elec);
	}
	
	
	
	public int ElecCostMMCount(ElecVo elec) {
		return elecDao.ElecCostMMCount(elec);
	}
	public BigDecimal ElecCostMMSum(ElecVo elec) {
		return elecDao.ElecCostMMSum(elec);
	}
	

	public int ElecLogMMCount(ElecVo elec) {
		return elecDao.ElecLogMMCount(elec);
	}
	public BigDecimal ElecLogMMSum(ElecVo elec) {
		return elecDao.ElecLogMMSum(elec);
	}
	
	
	public int PreElecCountMMNew(ElecVo elec) {
		return elecDao.PreElecCountMMNew(elec);
	}
	public BigDecimal PreElecSumMMNew(ElecVo elec) {
		return elecDao.PreElecSumMMNew(elec);
	}
	public int OweElecCountMMNew(ElecVo elec) {
		return elecDao.OweElecCountMMNew(elec);
	}
	public BigDecimal OweElecSumMMNew(ElecVo elec) {
		return elecDao.OweElecSumMMNew(elec);
	}
	

//月统计表格    end
	
	
	


//年统计表格 start 
	
	public List<ElecPriceDO> ElecPrice() {
		return elecDao.ElecPrice();
	}
	public int ElecAmountYY(ElecVo elec) {
		return elecDao.ElecAmountYY(elec);
	}
	
	
	
	public int PreElecCountYYOld(ElecVo elec) {
		return elecDao.PreElecCountYYOld(elec);
	}
	public BigDecimal PreElecSumYYOld(ElecVo elec) {
		return elecDao.PreElecSumYYOld(elec);
	}
	public int OweElecCountYYOld(ElecVo elec) {
		return elecDao.OweElecCountYYOld(elec);
	}
	public BigDecimal OweElecSumYYOld(ElecVo elec) {
		return elecDao.OweElecSumYYOld(elec);
	}
	
	
	
	public int ElecCostYYCount(ElecVo elec) {
		return elecDao.ElecCostYYCount(elec);
	}
	public BigDecimal ElecCostYYSum(ElecVo elec) {
		return elecDao.ElecCostYYSum(elec);
	}
	

	public int ElecLogYYCount(ElecVo elec) {
		return elecDao.ElecLogYYCount(elec);
	}
	public BigDecimal ElecLogYYSum(ElecVo elec) {
		return elecDao.ElecLogYYSum(elec);
	}
	
	
	public int PreElecCountYYNew(ElecVo elec) {
		return elecDao.PreElecCountYYNew(elec);
	}
	public BigDecimal PreElecSumYYNew(ElecVo elec) {
		return elecDao.PreElecSumYYNew(elec);
	}
	public int OweElecCountYYNew(ElecVo elec) {
		return elecDao.OweElecCountYYNew(elec);
	}
	public BigDecimal OweElecSumYYNew(ElecVo elec) {
		return elecDao.OweElecSumYYNew(elec);
	}
  
	
//年统计表格 end	

	
}
