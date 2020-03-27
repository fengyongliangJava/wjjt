package com.bootdo.elec.service.impl;

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
import com.bootdo.elec.dao.ElecIntoDao;
import com.bootdo.elec.service.ElecIntoService;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.moneyLog.dao.ElecLogDao;
import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.print.dao.PrintDao;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.vo.PrintVo;


@Service
public class ElecIntoServiceImpl implements ElecIntoService {
	
	
	@Autowired
	private ElecIntoDao elecIntoDao;

	
	@Autowired
	private PrintDao printDao;
	
	@Autowired
	private ElecLogDao elecLogDao;

	public List<ElecVo> getElecIntoList(ElecVo elec) {
		return elecIntoDao.getElecIntoList(elec);
	}

	@Transactional(rollbackFor = Exception.class)
	public String importElecIntoExcel(MultipartFile file, ElecVo elec) throws Exception {
		
		String data = "success";
		try {
			
			List<String[]> readExcel = POIUtil.readExcel(file);

			int excelSize = readExcel.size();

			for(int i = 0;i< excelSize;i++) {
				String[] arr = readExcel.get(i);
				if(!Util.isNullOrEmpty(arr[0])){
					elec.setUserId(arr[0]);
				}else {
					int a=i+2;
					data = "第" + a + "行，用户编号为空请认真编写";
					throw new Exception(data);
				}
				
				
				if(!Util.isNullOrEmpty(arr[1])){
					elec.setUserName(arr[1]);
				}else {
					int a=i+2;
					data = "第" + a + "行，用户姓名为空请认真编写";
					throw new Exception(data);
				}
				
				
				if(!Util.isNullOrEmpty(arr[2])){
					elec.setElecSum(new BigDecimal(arr[2]));
				}else {
					int a=i+2;
					data = "第" + a + "行，用户金额为空请认真编写";
					throw new Exception(data);
				}
		
				
				int count = elecIntoDao.importElecIntoExcel(elec);
				
				 List<ElecVo>  lstElec = elecIntoDao.getElecNumber(elec);
				if(lstElec.size()==0){
		
					int a=i+2;
					data = "第" + a + "行，数据与数据库不匹配请认真编写";
					throw new Exception(data);
				}
				
				if(count == 0) {
					throw  new Exception("表格上传文件解析失败");
				}
				if(CollectionUtils.isNotEmpty(lstElec)) {
					for(int b = 0;b< lstElec.size();b++) {
						elec = lstElec.get(b);
						
						ElecLogDO elecLog = new ElecLogDO();

						elecLog.setUserId(elec.getUserId());
						elecLog.setUserName(elec.getUserName());
						elecLog.setUserType(elec.getUserType());
						elecLog.setUserOrg(elec.getUserOrg());
						elecLog.setElecType(elec.getElecType());
						elecLog.setElecMoney(new BigDecimal(arr[2]));
						elecLog.setMoneyDate(new Date());
						elecLog.setCreateTime(elec.getCreateTime());
						elecLog.setCreateBy(ShiroUtils.getUserName());
						elecLog.setUpdateTime(new Date());
						elecLog.setUpdateBy(ShiroUtils.getUserName());

						elecLogDao.save(elecLog);
						
						
						
						//判断print表里有没有某条数据
						PrintVo printRecord = printDao.selectByCondition(elec.getCreateTime(),elec.getUserOrg(),elec.getUserId(),elecLog.getMoneyDate());
						
						
						if (printRecord != null) {
							
							printRecord.setUserId(elec.getUserId());
							printRecord.setUserName(elec.getUserName());
							printRecord.setUserType(elec.getUserType());
							printRecord.setUserOrg(elec.getUserOrg());
							printRecord.setStart(elec.getStart());
							printRecord.setEnd(elec.getEnd());
							printRecord.setHu(elec.getHu());
							printRecord.setElecPrice(elec.getElecPrice());
							printRecord.setElecAmount(elec.getElecAmount());
							printRecord.setElecCost(elec.getElecCost());
							printRecord.setElecSum(elec.getElecSum());
							printRecord.setElecMoney(new BigDecimal(arr[2]));
							printRecord.setPrintDate(new Date());
							printRecord.setCreateTime(elec.getCreateTime());
							printRecord.setCreateBy(ShiroUtils.getUserName());
							printRecord.setUpdateTime(new Date());
							printRecord.setUpdateBy(ShiroUtils.getUserName());

							printDao.update(printRecord);
							
							
						} else {
							
							PrintDO print = new PrintDO();
							
							print.setUserId(elec.getUserId());
							print.setUserName(elec.getUserName());
							print.setUserType(elec.getUserType());
							print.setUserOrg(elec.getUserOrg());
							print.setStart(elec.getStart());
							print.setEnd(elec.getEnd());
							print.setHu(elec.getHu());
							print.setElecPrice(elec.getElecPrice());
							print.setElecAmount(elec.getElecAmount());
							print.setElecCost(elec.getElecCost());
							print.setElecSum(elec.getElecSum());
							print.setElecMoney(new BigDecimal(arr[2]));
							print.setPrintDate(new Date());
							print.setCreateTime(elec.getCreateTime());
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

	public List<ElecVo> getElecNumber(ElecVo elec) {
		return elecIntoDao.getElecNumber(elec);
	}
	

	
}
