package com.bootdo.moneyList.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.heat.domain.HeatDO;
import com.bootdo.moneyList.damain.MoneyListDO;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.water.domain.WaterDO;


@Mapper
public interface MoneyListDao {
	

	//根据日期,用户归属地,用户编码查询出电费相应的数据
	List<MoneyListDO> getAll(MoneyListDO moneylist);

	//模糊查询姓名
	List<MoneyListDO> getAllUserName(MoneyListDO moneylist);

	//电费更新余额
	int updateElec(MoneyListDO moneylist);

	//暖费更新余额
	int updateHeat(MoneyListDO moneylist);

	//水费更新余额
	int updateWater(MoneyListDO moneylist);

	//查询打印表相关数据
	List<PrintDO>  getAllPrint (MoneyListDO moneylist);

	//查询打印表电费相关数据
	ElecDO getElecPrint (MoneyListDO moneylist);

	//查询打印表暖费相关数据
	HeatDO getHeatPrint (MoneyListDO moneylist);

	//查询打印表水费相关数据
	WaterDO getWaterPrint (MoneyListDO moneylist);



}
