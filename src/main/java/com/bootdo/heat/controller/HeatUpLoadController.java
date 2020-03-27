package com.bootdo.heat.controller;



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
import com.bootdo.heat.domain.HeatDO;
import com.bootdo.heat.service.HeatService;
import com.bootdo.heat.service.HeatUpLoadService;
import com.bootdo.heat.vo.HeatVo;


 
@Controller
@RequestMapping("/heat/heatUpLoad")
public class HeatUpLoadController {
	
	
	@Autowired
	private HeatUpLoadService heatUpLoadService;
	
	@Autowired
	private HeatService heatService;
	
	
	@GetMapping()
	@RequiresPermissions("heat:heatUpLoad")
	String HeatUpLoad(){
	    return "heat/heatUpLoad/heatUpLoad";
	}
	

	/**
	 *   暖费生成
	 */
	 @ResponseBody
	 @PostMapping("/createHeatExcel")
	 public  String  createHeatExcel(HeatDO heat) throws Exception {
		//定义一个返回控制
		 String data="";

		 //按日期，以及用户地区查找heat表里面的,暖费heatCost;暖费余额 eatSum;创建时间 createTime;
		 List<HeatDO> heatList = heatUpLoadService.getAllHeatById(heat);

		 //创建新对象
		 HeatDO heatDO = new HeatDO();

		 //遍历结果更改数据
		 for (int i = 0; i < heatList.size(); i++) {
			 //把时间变成下个月的
			 String createTim = heatList.get(i).getCreateTime();
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
			 heatList.get(i).setCreateTime(createTim);
			 //用暖费余额减去暖费
			 BigDecimal heatSum = heatList.get(i).getHeatSum();//暖费余额
			 BigDecimal heatCost = heatList.get(i).getHeatCost();//暖费
			 heatSum = heatSum.subtract(heatCost);
			 heatList.get(i).setHeatSum(heatSum);

			 heatDO.setCreateTime(createTim);
			 heatDO.setUserOrg(heatList.get(i).getUserOrg());

			 //校验本月是否生成过下月的数据,如果没有生成过返回个success，否则fail
			 //见判断第一次
			 if(i==0){
				 if(heatUpLoadService.getAllHeatById(heatDO) != null && !heatUpLoadService.getAllHeatById(heatDO).isEmpty()){

					 data="fail";
					 break;
				 }
				 else{
					 data="success";
					 //插入暖费
					 heatService.save(heatList.get(i));
				 }
			 }else{
				 //插入暖费
				 heatService.save(heatList.get(i));
			 }


		 }

		 return data;

	 }


		/**
		 * 下月暖费
		 */
		 @ResponseBody
		 @PostMapping("/nextHeatExcel")
		 public  String  nextHeatExcel(HeatDO heat) throws Exception {
			//定义一个返回控制
			 String data="";

			 //按日期，以及用户地区查找heat表里面的,暖费heatCost;暖费余额 eatSum;创建时间 createTime;
			 List<HeatDO> heatList = heatUpLoadService.getAllHeatById(heat);

			 //创建新对象
			 HeatDO heatDO = new HeatDO();

			 //遍历结果更改数据
			 for (int i = 0; i < heatList.size(); i++) {
				 //把时间变成下个月的
				 String createTim = heatList.get(i).getCreateTime();
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
				 heatList.get(i).setCreateTime(createTim);

				 heatDO.setCreateTime(createTim);
				 heatDO.setUserOrg(heatList.get(i).getUserOrg());
				 heatDO.setHeatCost(new BigDecimal(0));
				 //校验本月是否生成过下月的数据,如果没有生成过返回个success，否则fail
				 //见判断第一次
				 if(i==0){
					 if(heatUpLoadService.getAllHeatById(heatDO) != null && !heatUpLoadService.getAllHeatById(heatDO).isEmpty()){

						 data="fail";
						 break;
					 }
					 else{
						 data="success";
						 //插入暖费
						 heatService.save(heatList.get(i));
					 }
				 }else{
					 //插入暖费
					 heatService.save(heatList.get(i));
				 }


			 }

			 return data;

		 }
	 
	 
	 
	 
	 
	 
	 
	 /**
      *   暖费上传
	  */
	 @ResponseBody
	 @RequestMapping("/importHeatExcel")
	 public  String importHeatExcel(@RequestParam(value = "excelFile") MultipartFile excelFile,String createTime,String userOrg){
		 
		 
		  //定义一个返回控制
		  String data="success";
		  try {
			 List<String[]> heatList = POIUtil.readExcel(excelFile);
			 List<HeatDO> heatDOList = new ArrayList<HeatDO>();
			 //行数
			 int a ;
			 for(int i = 1;i<heatList.size();i++) {
				 a = i + 2;//行数赋值
				 String[] heat = heatList.get(i);
				 HeatDO heatDO = new HeatDO();
				 heatDO.setUserId(heat[0]);//用户编号
				 heatDO.setUserName(heat[1]);//用户姓名
				 //赋值会出现数组长度变化
				 if (heat.length >= 11) {
					 //校验数据不能为空
					 if (!"".equals(heat[2])
							 && !"".equals(heat[4])
							 && !"".equals(heat[6])
							 && !"".equals(heat[7])
							 && !"".equals(heat[8])
							 && !"".equals(heat[9]) && !"".equals(heat[10])) {

			/*			 if (heatUpLoadService.getEqualUserId(heatDO) != 0) {
							 //有相同数据
							 data = "第" + a + "行，用户编号已存在，请认真编写";
							 break;
						 }*/
						 //截取字符串
						 heatDO.setUserType(heat[2].substring(0, 1));//用户性质
						 heatDO.setUserOrg(userOrg);//用户地区
						 heatDO.setUserTell(heat[3]);//用户电话
						 heatDO.setUserState(heat[4].substring(0, 1));//用户状态.................
						 heatDO.setWagesId(heat[5]);//工资代码
						 heatDO.setHeatType(heat[6].substring(0, 1));//取暖类型................
						 //BigDecimal 类型重新赋值
						 heatDO.setHeatPrice(new BigDecimal(heat[7]));//用户暖价
						 heatDO.setHeatArea(new BigDecimal(heat[8]));//取暖面积
						 heatDO.setHeatCost(new BigDecimal(heat[9]));//用户暖费
						 heatDO.setHeatSum(new BigDecimal(heat[10]));//用户余额
					
						 heatDO.setCreateTime(createTime);//创建时间
						 heatDO.setCreateBy(ShiroUtils.getUserName());//创建人
						 heatDO.setUpdateTime(new Date());//更新时间（当前系统时间）
						 heatDO.setUpdateBy(ShiroUtils.getUserName());//更新人
						 if (heat.length == 12) {
							 heatDO.setRemark(heat[11]);//备注
						 }
						 heatDOList.add(heatDO);

					 } else {
						 if ("".equals(heat[2])) {
							 data = "第" + a + "行，用户性质为空，请认真编写";
						 } else if ("".equals(heat[4])) {
							 data = "第" + a + "行，用户状态为空，请认真编写";
						 } else if ("".equals(heat[6])) {
							 data = "第" + a + "行，取暖类型为空，请认真编写";
						 } else if ("".equals(heat[7])) {
							 data = "第" + a + "行，用户暖价为空，请认真编写";
						 } else if ("".equals(heat[8])) {
							 data = "第" + a + "行，取暖面积为空，请认真编写";
						 } else if ("".equals(heat[9])) {
							 data = "第" + a + "行，用户暖费为空，请认真编写";
						 } else if ("".equals(heat[10])) {
							 data = "第" + a + "行，用户余额为空，请认真编写";
						 }
						 break;

					 }
				 } else {
					 
					 //校验是否有相同用户编号的数据
					 if (heat.length == 3) {
						 if ("".equals(heat[2])) {
							 data = "第" + a + "行，用户性质为空，请认真编写";
						 } else {
							 data = "第" + a + "行，用户状态为空，请认真编写";
						 }
					 } else if (heat.length == 6) {
						 if ("".equals(heat[4])) {
							 data = "第" + a + "行，用户状态为空，请认真编写";
						 }  else {
							 data = "第" + a + "行，用户暖价为空，请认真编写";
						 }
						 break;
					 } else if (heat.length == 7) {
						 if ("".equals(heat[4])) {
							 data = "第" + a + "行，用户状态为空，请认真编写";
						 }  else {
							 data = "第" + a + "行，用户暖价为空，请认真编写";
						 }
						 break;
					 }  else if (heat.length == 8) {
						 if ("".equals(heat[4])) {
							 data = "第" + a + "行，用户暖价为空，请认真编写";
						 }  else {
							 data = "第" + a + "行，取暖面积为空，请认真编写";
						 }
						 break;
					 }  else if (heat.length == 9) {
						 if ("".equals(heat[7])) {
							 data = "第" + a + "行，用户暖价为空，请认真编写";
						 } else{
							 data = "第" + a + "行，用户暖费为空，请认真编写";
						 }
						 break;
					 } else if (heat.length == 10) {
						 if ("".equals(heat[7])) {
							 data = "第" + a + "行，用户暖价为空，请认真编写";
						 }else if ("".equals(heat[8])) {
							 data = "第" + a + "行，取暖面积为空，请认真编写";
						 } else {
							 data = "第" + a + "行，用户余额为空，请认真编写";
						 }
						 break;
					 }


				 }

			 }


			 //批量增加的方法,有时间优化一下，现在遍历吧，真脏
			  //创建新对象
			  if( heatDOList.size()+1==heatList.size()){
				  for(int i = 0;i<heatDOList.size();i++){
					  heatService.save(heatDOList.get(i));
				  }
			  }


		 }  catch (IOException e) {
			  data="fail";
			 e.printStackTrace();
		 }

		 return data;
	 }


		/**
		 *   半月生成
		 */
		 @ResponseBody
		 @PostMapping("/halfHeatExcel")
		 public  String  halfHeatExcel(HeatDO heat) throws Exception {

			 String data="";


			 List<HeatDO> heatList = heatUpLoadService.getAllHeatById(heat);


			 HeatDO heatDO = new HeatDO();

			 for (int i = 0; i < heatList.size(); i++) {

				 String createTim = heatList.get(i).getCreateTime();

				 String createTimfirst = createTim.substring(0, 4);
				 int numTwo ;
				 String year;
				 String createTimlast = createTim.substring(5);
				 int numOne;
				 String month;
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
				 heatList.get(i).setCreateTime(createTim);

				 BigDecimal heatSum = heatList.get(i).getHeatSum();
				 BigDecimal heatCost = heatList.get(i).getHeatCost();
				 
				 BigDecimal w = new  BigDecimal(0);
				 BigDecimal t = new  BigDecimal(2); 
				 if(heatCost !=w ) {
					 heatSum = heatSum.subtract(heatCost.divide(t));
				 }

				 heatList.get(i).setHeatSum(heatSum);

				 heatDO.setCreateTime(createTim);
				 heatDO.setUserOrg(heatList.get(i).getUserOrg());



				 if(i==0){
					 if(heatUpLoadService.getAllHeatById(heatDO) != null && !heatUpLoadService.getAllHeatById(heatDO).isEmpty()){

						 data="fail";
						 break;
					 }
					 else{
						 data="success";
	
						 heatService.save(heatList.get(i));
					 }
				 }else{

					 heatService.save(heatList.get(i));
				 }


			 }

			 return data;

		 }

	 
	 
	 
	 
	 
	 
	 
	 


		@ResponseBody
		@GetMapping("/exprotHeatExcel")
		public void exprotHeatExcel(HeatVo heat,HttpServletResponse response){
			response.reset();
			
			try {
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				
				String createTime = heat.getCreateTime();
				String userOrg =  heat.getUserOrg();
				
				if ("2".equals(heat.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(heat.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				List<HeatVo> heatList = heatService.exprotHeat(heat);
				
				String[] rowName = {"序号","用户编号","用户姓名","用户地区","用户性质","用户电话","工资代码","用户状态","取暖类型","暖费单价","取暖面积","用户暖费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
				
				List<Object[]> dataList = new ArrayList<Object[]>();
				
				
				int dataSize = heatList.size();
				for(int i = 0;i < dataSize ; i++ ) {
					Object[] arrObj = new Object[rowName.length];
					arrObj[0] = heatList.get(i).getId();
					arrObj[1] = heatList.get(i).getUserId();
					arrObj[2] = heatList.get(i).getUserName();
					arrObj[3] = heatList.get(i).getUserOrgName();
					if ("A".equals(heatList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(heatList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(heatList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(heatList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = heatList.get(i).getUserTell();
					arrObj[6] = heatList.get(i).getWagesId();
					if ("1".equals(heatList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(heatList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(heatList.get(i).getHeatType())) {
						arrObj[8] =   "民用暖";
					} else if ("2".equals(heatList.get(i).getHeatType())) {
						arrObj[8] =   "商业暖";
					} else if ("3".equals(heatList.get(i).getHeatType())) {
						arrObj[8] =   "工业民";
					} else if ("4".equals(heatList.get(i).getHeatType())) {
						arrObj[8] =   "工业商";
					} 
					arrObj[9] = heatList.get(i).getHeatPrice();
					arrObj[10] = heatList.get(i).getHeatArea();
					arrObj[11] = heatList.get(i).getHeatCost();
					arrObj[12] = heatList.get(i).getHeatSum();
					arrObj[13] = heatList.get(i).getCreateTime();
					arrObj[14] = heatList.get(i).getCreateBy();
					arrObj[15] = formatter.format(heatList.get(i).getUpdateTime());
					arrObj[16] = heatList.get(i).getUpdateBy();
					arrObj[17] = heatList.get(i).getRemark();
					dataList.add(arrObj);
				}

				ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+"暖费用户明细Excel",rowName, dataList);
				HSSFWorkbook workbook = excelPort.export();
				String fileName = "暖费用户明细";
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
