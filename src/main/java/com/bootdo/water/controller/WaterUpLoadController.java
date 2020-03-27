package com.bootdo.water.controller;



import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.ExportExcel;
import com.bootdo.common.utils.POIUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.water.domain.WaterDO;
import com.bootdo.water.service.WaterService;
import com.bootdo.water.service.WaterUpLoadService;
import com.bootdo.water.vo.WaterVo;


 
@Controller
@RequestMapping("/water/waterUpLoad")
public class WaterUpLoadController {
	
	
	@Autowired
	private WaterUpLoadService waterUpLoadService;
	
	@Autowired
	private WaterService waterService;
	
	
	@GetMapping()
	@RequiresPermissions("water:waterUpLoad")
	String WaterUpLoad(){
	    return "water/waterUpLoad/waterUpLoad";
	}
	

	/**
	   *  水费生成
	 */
	 @ResponseBody
	 @PostMapping("/createWaterExcel")
	 public  String  createWaterExcel(WaterDO water) throws Exception {

		 String data="";


		 List<WaterDO> waterList = waterUpLoadService.getAllWaterById(water);

		 //创建新对象
		 WaterDO waterDO = new WaterDO();

		 //遍历结果更改数据
		 for (int i = 0; i < waterList.size(); i++) {
			 //把时间变成下个月的
			 String createTim = waterList.get(i).getCreateTime();
			 //月份处理及年份处理
			 String createTimfirst = createTim.substring(0, 4);
			 int numTwo ;//定义年int
			 String year;//定义年string
			 String createTimlast = createTim.substring(5);
			 int numOne;//定义月int
			 String month;//定义月int
			 numOne = Integer.parseInt(createTimlast) + 1;
			 numTwo = Integer.parseInt(createTimfirst);
			 if (numOne > 12) {
				 numOne = 1;
				 numTwo ++;
			 }
			 month = String.valueOf(numOne);
			 year = String.valueOf(numTwo);
			 if (month.length() == 1) {
				 month = "0" + month;
			 }

			 createTim = year + "-" + month;
			 waterList.get(i).setCreateTime(createTim);
			 //用水费余额减去水费
			 BigDecimal waterSum = waterList.get(i).getWaterSum();//水费余额
			 BigDecimal waterCost = waterList.get(i).getWaterCost();//水费
			 waterSum = waterSum.subtract(waterCost);
			 waterList.get(i).setWaterSum(waterSum);

			 waterDO.setCreateTime(createTim);
			 waterDO.setUserOrg(waterList.get(i).getUserOrg());

			 //校验本月是否生成过下月的数据,如果没有生成过返回个success，否则fail
			 //见判断第一次
			 if(i==0){
				 if(waterUpLoadService.getAllWaterById(waterDO) != null && !waterUpLoadService.getAllWaterById(waterDO).isEmpty()){

					 data="fail";
					 break;
				 }
				 else{
					 data="success";
					 //插入水费
					 waterService.save(waterList.get(i));
				 }
			 }else{
				 //插入水费
				 waterService.save(waterList.get(i));
			 }


		 }

		 return data;

	 }

	 
	 
	 
	 
	 @ResponseBody
	 @RequestMapping("/importWaterExcel")
	 public  String importWaterExcel(@RequestParam(value = "excelFile") MultipartFile excelFile,String createTime,String userOrg){
	
		 
			  String data="success";
			  
			  try {
				 List<String[]> waterList = POIUtil.readExcel(excelFile);
				 List<WaterDO> waterDOList = new ArrayList<WaterDO>();
	
				 int a ;
				 for(int i = 1;i<waterList.size();i++) {
					 a = i + 2;
					 String[] water = waterList.get(i);
					 WaterDO waterDO = new WaterDO();
					 waterDO.setUserId(water[0]);
					 waterDO.setUserName(water[1]);
			
					 if (water.length >= 10) {
				
						 if (!"".equals(water[2])
								 && !"".equals(water[4])
								 && !"".equals(water[6])
								 && !"".equals(water[7])
								 && !"".equals(water[8])
								 && !"".equals(water[9])
								 						) {
							 
							 
							 waterDO.setUserType(water[2]);
							 waterDO.setUserOrg(userOrg);
							 waterDO.setUserTell(water[3]);
							 waterDO.setUserState(water[4]);
							 waterDO.setWagesId(water[5]);
							 waterDO.setWaterType(water[6]);
							 waterDO.setWaterPrice(new BigDecimal(water[7]));
							 waterDO.setWaterCost(new BigDecimal(water[8]));
							 waterDO.setWaterSum(new BigDecimal(water[9]));
							 waterDO.setCreateTime(createTime);
							 waterDO.setCreateBy(ShiroUtils.getUserName());
							 waterDO.setUpdateTime(new Date());
							 waterDO.setUpdateBy(ShiroUtils.getUserName());

							 if (water.length == 11) {
								 waterDO.setRemark(water[10]);//备注
							 }
							 waterDOList.add(waterDO);

						 } else {
							 
							 if ("".equals(water[2])) {
								 data = "第" + a + "行，用户性质为空，请认真编写";
							 } else if ("".equals(water[4])) {
								 data = "第" + a + "行，用户状态为空，请认真编写";
							 } else if ("".equals(water[6])) {
								 data = "第" + a + "行，水费类型为空，请认真编写";
							 } else if ("".equals(water[7])) {
								 data = "第" + a + "行，用户水价为空，请认真编写";
							 } 	 else if ("".equals(water[8])) {
								 data = "第" + a + "行，用户水费为空，请认真编写";
							 } else if ("".equals(water[9])) {
								 data = "第" + a + "行，用户余额为空，请认真编写";
							 }
							 break;

						 }
					 } else {
						 


					 }

				 }


				 //批量增加的方法,有时间优化一下，现在遍历吧，真脏
				  //创建新对象
				  if( waterDOList.size()+1==waterList.size()){
					  for(int i = 0;i<waterDOList.size();i++){
						  waterService.save(waterDOList.get(i));
					  }
				  }


			 }  catch (IOException e) {
				  data="fail";
				 e.printStackTrace();
			 }

			 return data;
		 }


		 
		 
		 
		 
		 
		 
		 

		@ResponseBody
		@GetMapping("/exprotWaterExcel")
		public void exprotWaterExcel(WaterVo water,HttpServletResponse response){
			response.reset();
			
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				String createTime = water.getCreateTime();
				String userOrg =  water.getUserOrg();
				
				if ("2".equals(water.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(water.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				List<WaterVo> waterList = waterService.exprotWater(water);
				
				String[] rowName = {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用水类型","水费单价","用户水费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
				
				List<Object[]> dataList = new ArrayList<Object[]>();
				
				int dataSize = waterList.size();
				for(int i = 0;i < dataSize ; i++ ) {
					Object[] arrObj = new Object[rowName.length];
					arrObj[0] = waterList.get(i).getId();
					arrObj[1] = waterList.get(i).getUserId();
					arrObj[2] = waterList.get(i).getUserName();
					arrObj[3] = waterList.get(i).getUserOrgName();
					if ("A".equals(waterList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(waterList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(waterList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(waterList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = waterList.get(i).getUserTell();
					arrObj[6] = waterList.get(i).getWagesId();
					if ("1".equals(waterList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(waterList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(waterList.get(i).getWaterType())) {
						arrObj[8] =   "商用水";
					} else if ("2".equals(waterList.get(i).getWaterType())) {
						arrObj[8] =   "民用水";
					}
					arrObj[9] = waterList.get(i).getWaterPrice();
					arrObj[9] = waterList.get(i).getWaterCost();
					arrObj[10] = waterList.get(i).getWaterSum();
					arrObj[11] = waterList.get(i).getCreateTime();
					arrObj[12] = waterList.get(i).getCreateBy();
					arrObj[13] = formatter.format(waterList.get(i).getUpdateTime());
					arrObj[14] = waterList.get(i).getUpdateBy();
					arrObj[15] = waterList.get(i).getRemark();
					dataList.add(arrObj);
				}

				ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+"水费用户明细Excel",rowName, dataList);
				HSSFWorkbook workbook = excelPort.export();
				String fileName = "水费用户明细";
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
