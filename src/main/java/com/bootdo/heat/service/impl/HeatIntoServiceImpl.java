package com.bootdo.heat.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.POIUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.Util;
import com.bootdo.heat.dao.HeatIntoDao;
import com.bootdo.heat.service.HeatIntoService;
import com.bootdo.heat.vo.HeatVo;
import com.bootdo.moneyLog.dao.HeatLogDao;
import com.bootdo.moneyLog.domain.HeatLogDO;
import com.bootdo.print.dao.PrintDao;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.vo.PrintVo;


@Service
public class HeatIntoServiceImpl implements HeatIntoService {
	
	
	@Autowired
	private HeatIntoDao heatIntoDao;

	@Autowired
	private HeatLogDao heatLogDao;
	
	@Autowired
	private PrintDao printDao;
	
	
	public List<HeatVo> getHeatIntoList(HeatVo heat) {
		return heatIntoDao.getHeatIntoList(heat);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public String importHeatIntoExcel(MultipartFile file, HeatVo heat) throws Exception {
		
		String data = "success";
		try {
			
			List<String[]> readExcel = POIUtil.readExcel(file);

			int excelSize = readExcel.size();

			for(int i = 0;i< excelSize;i++) {
				String[] arr = readExcel.get(i);
				if(!Util.isNullOrEmpty(arr[0])){
					heat.setUserId(arr[0]);
				}else {
					int a=i+2;
					data = "第" + a + "行，用户编号为空请认真编写";
					throw new Exception(data);
				}
				
				
				if(!Util.isNullOrEmpty(arr[1])){
					heat.setUserName(arr[1]);
				}else {
					int a=i+2;
					data = "第" + a + "行，用户姓名为空请认真编写";
					throw new Exception(data);
				}
				
				
				if(!Util.isNullOrEmpty(arr[2])){
					heat.setHeatSum(new BigDecimal(arr[2]));
				}else {
					int a=i+2;
					data = "第" + a + "行，用户金额为空请认真编写";
					throw new Exception(data);
				}
		
				
				int count = heatIntoDao.importHeatIntoExcel(heat);
				
				 List<HeatVo>  lstHeat = heatIntoDao.getHeatNumber(heat);
				if(lstHeat.size()==0){
		
					int a=i+2;
					data = "第" + a + "行，数据与数据库不匹配请认真编写";
					throw new Exception(data);
				}
				
				if(count == 0) {
					throw  new Exception("表格上传文件解析失败");
				}
				
				

				
				
				if(CollectionUtils.isNotEmpty(lstHeat)) {
					for(int b = 0;b< lstHeat.size();b++) {
						heat = lstHeat.get(b);
						
						HeatLogDO heatLog = new HeatLogDO();

						heatLog.setUserId(heat.getUserId());
						heatLog.setUserName(heat.getUserName());
						heatLog.setUserType(heat.getUserType());
						heatLog.setUserOrg(heat.getUserOrg());
						heatLog.setHeatType(heat.getHeatType());
						heatLog.setHeatMoney(new BigDecimal(arr[2]));
						heatLog.setMoneyDate(new Date());
						heatLog.setCreateTime(heat.getCreateTime());
						heatLog.setCreateBy(ShiroUtils.getUserName());
						heatLog.setUpdateTime(new Date());
						heatLog.setUpdateBy(ShiroUtils.getUserName());

						heatLogDao.save(heatLog);
						
						
						
						//判断print表里有没有某条数据
						PrintVo printRecord = printDao.selectByCondition(heat.getCreateTime(),heat.getUserOrg(),heat.getUserId(),heatLog.getMoneyDate());
						
						
						if (printRecord != null) {
							
							printRecord.setUserId(heat.getUserId());
							printRecord.setUserName(heat.getUserName());
							printRecord.setUserType(heat.getUserType());
							printRecord.setUserOrg(heat.getUserOrg());
							printRecord.setHeatPrice(heat.getHeatPrice());
							printRecord.setHeatArea(heat.getHeatArea());
							printRecord.setHeatCost(heat.getHeatCost());
							printRecord.setHeatSum(heat.getHeatSum());
							printRecord.setHeatMoney(new BigDecimal(arr[2]));
							printRecord.setPrintDate(new Date());
							printRecord.setCreateTime(heat.getCreateTime());
							printRecord.setCreateBy(ShiroUtils.getUserName());
							printRecord.setUpdateTime(new Date());
							printRecord.setUpdateBy(ShiroUtils.getUserName());

							printDao.update(printRecord);
							
							
						} else {
							
							PrintDO print = new PrintDO();
							
							print.setUserId(heat.getUserId());
							print.setUserName(heat.getUserName());
							print.setUserType(heat.getUserType());
							print.setUserOrg(heat.getUserOrg());
							print.setHeatPrice(heat.getHeatPrice());
							print.setHeatArea(heat.getHeatArea());
							print.setHeatCost(heat.getHeatCost());
							print.setHeatSum(heat.getHeatSum());
							print.setHeatMoney(new BigDecimal(arr[2]));
							print.setPrintDate(new Date());
							print.setCreateTime(heat.getCreateTime());
							print.setCreateBy(ShiroUtils.getUserName());
							print.setUpdateTime(new Date());
							print.setUpdateBy(ShiroUtils.getUserName());

							printDao.save(print);
						}

						
					}
				}
				
				
			}
	} catch (Exception e) {
		e.printStackTrace();
		data="fail";
		throw new Exception(e);
	}
 return data;
}
		
		

	

	public List<HeatVo> getHeatNumber(HeatVo heat) {
		return heatIntoDao.getHeatNumber(heat);
	}
	

	
}
