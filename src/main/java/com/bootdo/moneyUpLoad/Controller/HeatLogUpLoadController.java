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
import com.bootdo.moneyLog.domain.HeatLogDO;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.vo.HeatLogVo;

@Controller
@RequestMapping("/moneyUpLoad/HeatLogUpLoad")
public class HeatLogUpLoadController {


	@Autowired
	private HeatLogService heatLogService;
	
	
	@GetMapping()
	String HeatLogUpLoad(){
	    return "moneyUpLoad/HeatLogUpLoad";
	}
	


	 @ResponseBody
	 @RequestMapping("/importHeatLogExcel")
	 public  String importHeatLogExcel(@RequestParam(value = "file") MultipartFile excelFile,String createTime,String userOrg){
			
		 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		//定义一个返回控制
		String data="success";
		try {
		List<String[]> heatLogList = POIUtil.readExcel(excelFile);
		List<HeatLogDO> heatLogDOList = new ArrayList<HeatLogDO>();
		//行数
		int a ;
		for(int i = 1;i<heatLogList.size();i++) {
			 a = i + 2;//行数赋值
			 String[] heatLog = heatLogList.get(i);
			 HeatLogDO heatLogDO = new HeatLogDO();
	
			 //赋值会出现数组长度变化
			 if (heatLog.length >= 5) {
				 //校验数据不能为空
				 if (!"".equals(heatLog[0])
						 && !"".equals(heatLog[1])
						 && !"".equals(heatLog[2])
						 && !"".equals(heatLog[3])
						 && !"".equals(heatLog[4])
						 && !"".equals(heatLog[5]) ) {
		
					 heatLogDO.setUserId(heatLog[0]);//用户编号
					 heatLogDO.setUserName(heatLog[1]);//用户姓名
					 heatLogDO.setUserOrg(userOrg);//用户地区
					 heatLogDO.setUserType(heatLog[2]);//用户性质
					 heatLogDO.setHeatType(heatLog[3]);//水费类型
					 heatLogDO.setHeatMoney(new BigDecimal(heatLog[4]));
					 
					 try {
						heatLogDO.setMoneyDate(formatter.parse(heatLog[5]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					 
					 heatLogDO.setCreateTime(createTime);//创建时间
					 heatLogDO.setCreateBy(ShiroUtils.getUserName());//创建人
					 heatLogDO.setUpdateTime(new Date());//更新时间（当前系统时间）
					 heatLogDO.setUpdateBy(ShiroUtils.getUserName());//更新人
					 heatLogDOList.add(heatLogDO);
		
				 } else {
					 if ("".equals(heatLog[0])) {
						 data = "第" + a + "行，用户编号为空，请认真编写";
					 } else if ("".equals(heatLog[1])) {
						 data = "第" + a + "行，用户姓名为空，请认真编写";
					 } else if ("".equals(heatLog[2])) {
						 data = "第" + a + "行，缴费方式为空，请认真编写";
					 } else if ("".equals(heatLog[3])) {
						 data = "第" + a + "行，暖费类型为空，请认真编写";
					 } else if ("".equals(heatLog[4])) {
						 data = "第" + a + "行，缴费金额为空，请认真编写";
					 } else if ("".equals(heatLog[5])) {
						 data = "第" + a + "行，缴费时间为空，请认真编写";
					 } 
					 break;
		
				 }
			 } 
		
		}
		
	
		 if( heatLogDOList.size()+1==heatLogList.size()){
			  for(int i = 0;i<heatLogDOList.size();i++){
				  heatLogService.save(heatLogDOList.get(i));
			  }
		 }
		
		
		}  catch (IOException e) {
		 data="fail";
		e.printStackTrace();
		}
		
		return data;
	}
	
	
	
	
	
	
	
	
	
	@ResponseBody
	@GetMapping("/exprotHeatLogExcel")
	public void exprotHeatLogExcel(HeatLogVo heatLog,HttpServletResponse response,HttpServletRequest request){
		response.reset();
		
	
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime = heatLog.getCreateTime();

			
			String userOrg = "";
			if ("2".equals(heatLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(heatLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = "";
			if ("A".equals(heatLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(heatLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(heatLog.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(heatLog.getUserType())) {
				userType =   "银行转账";
			}
			
			
			String heatType = "";
			if ("1".equals(heatLog.getHeatType())) {
				heatType  =   "民用暖";
			} else if ("2".equals(heatLog.getHeatType())) {
				heatType  =   "商业暖";
			}else if ("3".equals(heatLog.getHeatType())) {
				heatType  =   "工业民";
			}else if ("4".equals(heatLog.getHeatType())) {
				heatType  =   "工业商";
			}

			
			String UserId = heatLog.getUserId();
			
			List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogMM(heatLog);
			
			String[] rowName = {"序号","用户编号","用户姓名","缴费方式","用户地区","暖费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			int dataSize = heatLogList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = heatLogList.get(i).getId();
				arrObj[1] = heatLogList.get(i).getUserId();
				arrObj[2] = heatLogList.get(i).getUserName();
				if ("A".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				} else if ("c".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "微信支付";
				} else if ("D".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "银行转账";
				}
				arrObj[4] = heatLogList.get(i).getUserOrgName();
				if ("1".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "民用暖";
				} else if ("2".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "商业暖";
				}else if ("3".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "工业民";
				}else if ("4".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "工业商";
				}
				arrObj[6] = heatLogList.get(i).getHeatMoney();
				arrObj[7] = formatter.format(heatLogList.get(i).getMoneyDate());
				arrObj[8] = heatLogList.get(i).getCreateTime();
				arrObj[9] = heatLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(heatLogList.get(i).getUpdateTime());
				arrObj[11] = heatLogList.get(i).getUpdateBy();
				arrObj[12] = heatLogList.get(i).getRemark();
				
				dataList.add(arrObj);
			}
			
			
			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+heatType+" "+UserId+" "+"导出暖费记录明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "暖费记录明细";
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
