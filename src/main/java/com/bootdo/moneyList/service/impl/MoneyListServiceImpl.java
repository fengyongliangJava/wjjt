package com.bootdo.moneyList.service.impl;

import com.bootdo.elec.domain.ElecDO;
import com.bootdo.moneyList.damain.MoneyListDO;
import com.bootdo.moneyList.dao.MoneyListDao;
import com.bootdo.water.domain.WaterDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bootdo.heat.domain.HeatDO;
import com.bootdo.moneyList.service.MoneyListService;
import com.bootdo.print.domain.PrintDO;

import java.util.List;


@Service
public class MoneyListServiceImpl implements MoneyListService {

	@Autowired
	private MoneyListDao MoneyListDao;
	

	

	//根据暖费查询相应的信息
	public  List<MoneyListDO> getAll(MoneyListDO moneylist) {
		return MoneyListDao.getAll(moneylist);
	}
	//查询姓名信息
	public  List<MoneyListDO>  getAllUserName(MoneyListDO moneylist) {
		return MoneyListDao.getAllUserName(moneylist);
	}
	//电费更新余额
	public int updateElec(MoneyListDO moneylist){
		return MoneyListDao.updateElec(moneylist);
	}
	//暖费更新余额
	public int updateHeat(MoneyListDO moneylist){
		return MoneyListDao.updateHeat(moneylist);
	}
	//水费更新余额
	public int updateWater(MoneyListDO moneylist){
		return MoneyListDao.updateWater(moneylist);
	}

	//查询打印表相关数据
	public  List<PrintDO> getAllPrint(MoneyListDO moneylist) {
		return MoneyListDao.getAllPrint(moneylist);
	}
	//查询打印表电费相关数据
	public ElecDO getElecPrint(MoneyListDO moneylist) {
		return MoneyListDao.getElecPrint(moneylist);
	}
	//查询打印表暖费相关数据
	public  HeatDO getHeatPrint(MoneyListDO moneylist) {
		return MoneyListDao.getHeatPrint(moneylist);
	}
	//查询打印表水费相关数据
	public WaterDO getWaterPrint(MoneyListDO moneylist) {
		return MoneyListDao.getWaterPrint(moneylist);
	}



}
