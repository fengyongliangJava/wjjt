package com.bootdo.water.service.impl;

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
import com.bootdo.moneyLog.dao.WaterLogDao;
import com.bootdo.moneyLog.domain.WaterLogDO;
import com.bootdo.print.dao.PrintDao;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.vo.PrintVo;
import com.bootdo.water.dao.WaterIntoDao;
import com.bootdo.water.service.WaterIntoService;
import com.bootdo.water.vo.WaterVo;



@Service
public class WaterIntoServiceImpl implements WaterIntoService {
	
	
	@Autowired
	private WaterIntoDao waterIntoDao;

	@Autowired
	private WaterLogDao waterLogDao;
	
	@Autowired
	private PrintDao printDao;
	
	
	public List<WaterVo> getWaterIntoList(WaterVo water) {
		return waterIntoDao.getWaterIntoList(water);
	}

	
	@Transactional(rollbackFor = Exception.class)
	public String importWaterIntoExcel(MultipartFile file, WaterVo water) throws Exception {
		
		 String data = "success";
			try {
				
				List<String[]> readExcel = POIUtil.readExcel(file);

				int excelSize = readExcel.size();

				for(int i = 0;i< excelSize;i++) {
					String[] arr = readExcel.get(i);
					if(!Util.isNullOrEmpty(arr[0])){
						water.setUserId(arr[0]);
					}else {
						int a=i+2;
						data = "第" + a + "行，用户编号为空请认真编写";
						throw new Exception(data);
					}
					
					
					if(!Util.isNullOrEmpty(arr[1])){
						water.setUserName(arr[1]);
					}else {
						int a=i+2;
						data = "第" + a + "行，用户姓名为空请认真编写";
						throw new Exception(data);
					}
					
					
					if(!Util.isNullOrEmpty(arr[2])){
						water.setWaterSum(new BigDecimal(arr[2]));
					}else {
						int a=i+2;
						data = "第" + a + "行，用户金额为空请认真编写";
						throw new Exception(data);
					}
			
					
					int count = waterIntoDao.importWaterIntoExcel(water);
					
					 List<WaterVo>  lstWater = waterIntoDao.getWaterNumber(water);
					if(lstWater.size()==0){
			
						int a=i+2;
						data = "第" + a + "行，数据与数据库不匹配请认真编写";
						throw new Exception(data);
					}
					
					if(count == 0) {
						throw  new Exception("表格上传文件解析失败");
					}
					
			

					
					
					if(CollectionUtils.isNotEmpty(lstWater)) {
						for(int b = 0;b< lstWater.size();b++) {
							water = lstWater.get(b);
							
							WaterLogDO waterLog = new WaterLogDO();

							waterLog.setUserId(water.getUserId());
							waterLog.setUserName(water.getUserName());
							waterLog.setUserType(water.getUserType());
							waterLog.setUserOrg(water.getUserOrg());
							waterLog.setWaterType(water.getWaterType());
							waterLog.setWaterMoney(new BigDecimal(arr[2]));
							waterLog.setMoneyDate(new Date());
							waterLog.setCreateTime(water.getCreateTime());
							waterLog.setCreateBy(ShiroUtils.getUserName());
							waterLog.setUpdateTime(new Date());
							waterLog.setUpdateBy(ShiroUtils.getUserName());

							waterLogDao.save(waterLog);
							
							
							
							//判断print表里有没有某条数据
							PrintVo printRecord = printDao.selectByCondition(water.getCreateTime(),water.getUserOrg(),water.getUserId(),waterLog.getMoneyDate());
							
							
							if (printRecord != null) {
								
								printRecord.setUserId(water.getUserId());
								printRecord.setUserName(water.getUserName());
								printRecord.setUserType(water.getUserType());
								printRecord.setUserOrg(water.getUserOrg());
								printRecord.setWaterPrice(water.getWaterPrice());
								printRecord.setWaterCost(water.getWaterCost());
								printRecord.setWaterSum(water.getWaterSum());
								printRecord.setWaterMoney(new BigDecimal(arr[2]));
								printRecord.setPrintDate(new Date());
								printRecord.setCreateTime(water.getCreateTime());
								printRecord.setCreateBy(ShiroUtils.getUserName());
								printRecord.setUpdateTime(new Date());
								printRecord.setUpdateBy(ShiroUtils.getUserName());

								printDao.update(printRecord);
								
								
							} else {
								
								PrintDO print = new PrintDO();
								
								print.setUserId(water.getUserId());
								print.setUserName(water.getUserName());
								print.setUserType(water.getUserType());
								print.setUserOrg(water.getUserOrg());
								print.setWaterPrice(water.getWaterPrice());
								print.setWaterCost(water.getWaterCost());
								print.setWaterSum(water.getWaterSum());
								print.setWaterMoney(new BigDecimal(arr[2]));
								print.setPrintDate(new Date());
								print.setCreateTime(water.getCreateTime());
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
		
		
		
		
		


	public List<WaterVo> getWaterNumber(WaterVo water) {
		return waterIntoDao.getWaterNumber(water);
	}
	

	
}
