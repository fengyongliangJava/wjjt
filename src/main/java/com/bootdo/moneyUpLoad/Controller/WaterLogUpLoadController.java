package com.bootdo.moneyUpLoad.Controller;

import java.io.IOException;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.ExportExcel;
import com.bootdo.common.utils.POIUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.moneyLog.domain.WaterLogDO;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.WaterLogVo;

@Controller
@RequestMapping("/moneyUpLoad/WaterLogUpLoad")				  
 public class WaterLogUpLoadController {

	@Autowired
	private WaterLogService waterLogService;
	
	@GetMapping()
	String WaterLogLogUpLoad(){
	    return "moneyUpLoad/WaterLogUpLoad";
	}

	
	 @ResponseBody
	 @RequestMapping("/importWaterLogExcel")
	 public  String importWaterLogExcel(@RequestParam(value = "file") MultipartFile excelFile,String createTime,String userOrg){
			
		 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		//定义一个返回控制
		String data="success";
		try {
		List<String[]> waterLogList = POIUtil.readExcel(excelFile);
		List<WaterLogDO> waterLogDOList = new ArrayList<WaterLogDO>();
		//行数
		int a ;
		for(int i = 1;i<waterLogList.size();i++) {
			 a = i + 2;//行数赋值
			 String[] waterLog = waterLogList.get(i);
			 WaterLogDO waterLogDO = new WaterLogDO();
	
			 //赋值会出现数组长度变化
			 if (waterLog.length >= 5) {
				 //校验数据不能为空
				 if (!"".equals(waterLog[0])
						 && !"".equals(waterLog[1])
						 && !"".equals(waterLog[2])
						 && !"".equals(waterLog[3])
						 && !"".equals(waterLog[4])
						 && !"".equals(waterLog[5]) ) {
		
					 waterLogDO.setUserId(waterLog[0]);//用户编号
					 waterLogDO.setUserName(waterLog[1]);//用户姓名
					 waterLogDO.setUserOrg(userOrg);//用户地区
					 waterLogDO.setUserType(waterLog[2]);//用户性质
					 waterLogDO.setWaterType(waterLog[3]);//水费类型
					 waterLogDO.setWaterMoney(new BigDecimal(waterLog[4]));
					 
					 try {
						waterLogDO.setMoneyDate(formatter.parse(waterLog[5]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					 
					 waterLogDO.setCreateTime(createTime);//创建时间
					 waterLogDO.setCreateBy(ShiroUtils.getUserName());//创建人
					 waterLogDO.setUpdateTime(new Date());//更新时间（当前系统时间）
					 waterLogDO.setUpdateBy(ShiroUtils.getUserName());//更新人
					 waterLogDOList.add(waterLogDO);
		
				 } else {
					 if ("".equals(waterLog[0])) {
						 data = "第" + a + "行，用户编号为空，请认真编写";
					 } else if ("".equals(waterLog[1])) {
						 data = "第" + a + "行，用户姓名为空，请认真编写";
					 } else if ("".equals(waterLog[2])) {
						 data = "第" + a + "行，缴费方式为空，请认真编写";
					 } else if ("".equals(waterLog[3])) {
						 data = "第" + a + "行，水费类型为空，请认真编写";
					 } else if ("".equals(waterLog[4])) {
						 data = "第" + a + "行，缴费金额为空，请认真编写";
					 } else if ("".equals(waterLog[5])) {
						 data = "第" + a + "行，缴费时间为空，请认真编写";
					 } 
					 break;
		
				 }
			 } 
		
		}
		
	
		 if( waterLogDOList.size()+1==waterLogList.size()){
			  for(int i = 0;i<waterLogDOList.size();i++){
				  waterLogService.save(waterLogDOList.get(i));
			  }
		 }
		
		
		}  catch (IOException e) {
		 data="fail";
		e.printStackTrace();
		}
		
		return data;
	}
	
	
	

	@ResponseBody
	@GetMapping("/exprotWaterLogExcel")
	public void exprotWaterLogExcel(WaterLogVo waterLog,HttpServletResponse response,HttpServletRequest request){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime = waterLog.getCreateTime();
		
			
			String userOrg = "";
			if ("2".equals(waterLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(waterLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = "";
			if ("A".equals(waterLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(waterLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(waterLog.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(waterLog.getUserType())) {
				userType =   "银行转账";
			}
			
			String waterType = "";
			if ("1".equals(waterLog.getWaterType())) {
				waterType  =   "民用水";
			} else if ("2".equals(waterLog.getWaterType())) {
				waterType  =   "商业水1";
			} else if ("3".equals(waterLog.getWaterType())) {
				waterType  =   "商业水2";
			} else if ("4".equals(waterLog.getWaterType())) {
				waterType  =   "商业水3";
			}
			
			String UserId = waterLog.getUserId();
			
			List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogMM(waterLog);
			
			String[] rowName = {"序号","用户编号","用户姓名","缴费方式","用户地区","水费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			int dataSize = waterLogList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = waterLogList.get(i).getId();
				arrObj[1] = waterLogList.get(i).getUserId();
				arrObj[2] = waterLogList.get(i).getUserName();
				if ("A".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				}
				arrObj[4] = waterLogList.get(i).getUserOrgName();
				if ("1".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "民用水";
				} else if ("2".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "商业水1";
				} else if ("3".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "商业水2";
				} else if ("4".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "商业水3";
				}
				arrObj[6] = waterLogList.get(i).getWaterMoney();
				arrObj[7] = formatter.format(waterLogList.get(i).getMoneyDate());
				arrObj[8] = waterLogList.get(i).getCreateTime();
				arrObj[9] = waterLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(waterLogList.get(i).getUpdateTime());
				arrObj[11] = waterLogList.get(i).getUpdateBy();
				arrObj[12] = waterLogList.get(i).getRemark();
				
				dataList.add(arrObj);
			}
			
			
			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+waterType+" "+UserId+" "+"导出水费记录明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "水费记录明细";
			fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1"); 
			fileName = StringUtils.replace(fileName, " ", "%20"); 
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName+".xlsx");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expries", 0);
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
