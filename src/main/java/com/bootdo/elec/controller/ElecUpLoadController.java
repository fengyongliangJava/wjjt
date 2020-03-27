package com.bootdo.elec.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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
import com.bootdo.common.utils.Util;
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.service.ElecService;
import com.bootdo.elec.service.ElecUpLoadService;
import com.bootdo.elec.vo.ElecVo;

 
@Controller
@RequestMapping("/elec/elecUpLoad")
public class ElecUpLoadController {
	
	private static Logger logger = Logger.getLogger(ElecUpLoadController.class);
	
	@Autowired
	private ElecUpLoadService elecUpLoadService;
	
	@Autowired
	private ElecService elecService;


	
	@GetMapping()
	@RequiresPermissions("elec:elecUpLoad:elecUpLoad")
	String Elec(){
	    return "elec/elecUpLoad/elecUpLoad";
	}
	

	
	/**
 	 * 电字上传
	 * @param excelFile
	 * @param request
	 * @return
	 * @throws Exception
	 */
	
	 @ResponseBody
	 @PostMapping("/importElecExcel")
	 
	 public Map<String,Object> importElecExcel(@RequestParam(value = "excelFile")MultipartFile  excelFile, HttpServletRequest request) throws Exception {
		 
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		 List<String[]> readExcel = null;
		 
		 String createTime = request.getParameter("createTime");
		 String userOrg = request.getParameter("userOrg");
		 try {
			 readExcel = POIUtil.readExcel(excelFile);
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put("result", "notExcel");
				return resultMap;
			}
		 
		 boolean result = true;
		 
		 int excelSize = readExcel.size();
		 
		 for(int i = 0;i< excelSize;i++) {
			 String[] arr = readExcel.get(i);
			 if(!Util.isNullOrEmpty(arr[0]) && !Util.isNullOrEmpty(arr[1]) && !Util.isNullOrEmpty(arr[2])){
				 // 查询数据库 
				 ElecDO qryelec = new ElecDO();
				 qryelec.setUserId(arr[0]);
				 qryelec.setCreateTime(createTime);
				 qryelec.setUserOrg(userOrg);
				 ElecDO elec = elecUpLoadService.getElecByUserId(qryelec);
				 
				 if(Util.isNullOrEmpty(elec)) {
					 resultMap.put("result",(i + 1));
					 return resultMap;
				 }else {
					 
					 BigDecimal hu = new BigDecimal(elec.getHu());
					 BigDecimal elecAmount = (new BigDecimal(arr[2]).subtract(new BigDecimal(arr[1]))).multiply(hu);
					 BigDecimal onePrice = new BigDecimal(elec.getElecPrice().toString());
					 BigDecimal sumPrice =  onePrice.multiply(elecAmount);
					 elec.setElecCost(sumPrice);
					 BigDecimal costLeft = new BigDecimal(elec.getElecSum().toString());
					 BigDecimal leftSum = costLeft.subtract(sumPrice);
					 elec.setElecSum(leftSum);
					 
					 
					 
					 String[] arrObj = createTime.split("-");
					 arrObj[1] = String.valueOf(Integer.parseInt(arrObj[1]) + 1);

					 if((Integer.parseInt(arrObj[1]) + 1) > 12) {
						 arrObj[0] = String.valueOf(Integer.parseInt(arrObj[0]) + 1);
					 }
					 
					 if((Integer.parseInt(arrObj[1])) > 12) {
						 arrObj[1] = "1";
					 }
					 
					 
						 String c = String.valueOf(arrObj[0])+"-"+String.valueOf(arrObj[1]);
						 String d = String.valueOf(arrObj[0])+"-"+"0"+String.valueOf(arrObj[1]);
					 
						if(Integer.parseInt(arrObj[1]) > 9) {
							 elec.setCreateTime(c);
						}else {
							 elec.setCreateTime(d);
						}
					 
				
					 elec.setElecType(elec.getElecType());
					 elec.setStart(new BigDecimal(arr[1]));
					 elec.setEnd(new BigDecimal(arr[2]));
					 elec.setElecAmount(elecAmount);
					 elec.setCreateBy(ShiroUtils.getUserName());
					 elec.setUpdateBy(ShiroUtils.getUserName());
					 elec.setUpdateTime(new Date());
					 elecUpLoadService.createElecNextMonth(elec);
					 logger.info("excel表格上传文件解析成功,userId:"+arr[0]);
				 }
			 }else {
				 int a = i+ 1;
				 int b =  Integer.parseInt(arr[i]+1);
				 resultMap.put("result",a+"行"+b+"列");
				 return resultMap;
			 }
		 }
		 
		 if(result) {
			 resultMap.put("result","success");
		 }else {
			 resultMap.put("result", "fail");
		 }
	
		 return resultMap;
	 }
	


/**
 * 电费用户上传
 * @param excelFile
 * @param request
 * @return
 * @throws Exception
 */
@ResponseBody
@RequestMapping("/importElecUser")
public  String importElecUser(@RequestParam(value = "excelFile") MultipartFile excelFile,String createTime,String userOrg){
		

		String data="success";
		
		try {
		 List<String[]> elecList = POIUtil.readExcel(excelFile);
		 List<ElecDO> elecDOList = new ArrayList<ElecDO>();
		 //行数
		 int a ;
		 for(int i = 1;i<elecList.size();i++) {
			 a = i + 2;
		 String[] elec = elecList.get(i);
		 ElecDO elecDO = new ElecDO();
		 elecDO.setUserId(elec[0]);
		 elecDO.setUserName(elec[1]);

		 if (elec.length >= 14) {

			 if (!"".equals(elec[2])
					 && !"".equals(elec[4])
					 && !"".equals(elec[6])
					 && !"".equals(elec[7])
					 && !"".equals(elec[8])
					 && !"".equals(elec[9]) 
					 && !"".equals(elec[10])
					 && !"".equals(elec[11])
					 && !"".equals(elec[12])
					 && !"".equals(elec[13])
					 
					 							) {
		

				 elecDO.setUserType(elec[2]);
				 elecDO.setUserOrg(userOrg);
				 elecDO.setUserTell(elec[3]);
				 elecDO.setUserState(elec[4]);
				 elecDO.setWagesId(elec[5]);
				 elecDO.setStart(new BigDecimal(elec[6]));
				 elecDO.setEnd(new BigDecimal(elec[7]));
				 elecDO.setElecAmount(new BigDecimal(elec[8]));
				 elecDO.setHu(Integer.parseInt(elec[9]));
				 elecDO.setElecType(elec[10]);
				 elecDO.setElecPrice(new BigDecimal(elec[11]));
				 elecDO.setElecCost(new BigDecimal(elec[12]));
				 elecDO.setElecSum(new BigDecimal(elec[13]));
				 elecDO.setCreateTime(createTime);
				 elecDO.setCreateBy(ShiroUtils.getUserName());
				 elecDO.setUpdateTime(new Date());
				 elecDO.setUpdateBy(ShiroUtils.getUserName());
				 
				 if (elec.length == 15) {
					 elecDO.setRemark(elec[14]);//备注
				 }
				 elecDOList.add(elecDO);
		
			 } else {
				 if ("".equals(elec[2])) {
					 data = "第" + a + "行，用户性质为空，请认真编写";
				 } else if ("".equals(elec[4])) {
					 data = "第" + a + "行，用户状态为空，请认真编写";
				 } else if ("".equals(elec[6])) {
					 data = "第" + a + "行，上月电字为空，请认真编写";
				 } else if ("".equals(elec[7])) {
					 data = "第" + a + "行，本月电字为空，请认真编写";
				 } else if ("".equals(elec[8])) {
					 data = "第" + a + "行，电量为空，请认真编写";
				 } else if ("".equals(elec[9])) {
					 data = "第" + a + "行，互感比为空，请认真编写";
				 } else if ("".equals(elec[10])) {
					 data = "第" + a + "行，电费类型为空，请认真编写";
				 } else if ("".equals(elec[11])) {
					 data = "第" + a + "行，用户电价为空，请认真编写";
				 } else if ("".equals(elec[12])) {
					 data = "第" + a + "行，用户电费为空，请认真编写";
				 } else if ("".equals(elec[13])) {
					 data = "第" + a + "行，用户余额为空，请认真编写";
				 }
				 
				 break;
		
			 }
		 } else {
			 
	
		
			 }
		
		 }
		
		
		  if( elecDOList.size()+1==elecList.size()){
			  for(int i = 0;i<elecDOList.size();i++){
				  elecService.save(elecDOList.get(i));
			  }
		  }
		
		
		}  catch (IOException e) {
		    data="fail";
		 e.printStackTrace();
		}
		
		return data;
		
	}




	


	 
	
	@ResponseBody
	@GetMapping("/exprotElecExcel")
	public void exprotElecExcel(ElecVo elec,HttpServletResponse response){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			String createTime = elec.getCreateTime();
			String userOrg =  elec.getUserOrg();
			
			if ("2".equals(elec.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elec.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			List<ElecVo> elecList = elecService.exprotElec(elec);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户地区","用户性质","用户电话","工资代码","用户状态","电费类型","电费单价","上月电字","本月电字","互感比","用户电量","用户电费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			
			int dataSize = elecList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = elecList.get(i).getId();
				arrObj[1] = elecList.get(i).getUserId();
				arrObj[2] = elecList.get(i).getUserName();
				arrObj[3] = elecList.get(i).getUserOrgName();
				if ("A".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "现金缴费";
				} else if ("B".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "工资代扣";
				} else if ("C".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "微信支付";
				} else if ("D".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "银行转账";
				}
				arrObj[5] = elecList.get(i).getUserTell();
				arrObj[6] = elecList.get(i).getWagesId();
				if ("1".equals(elecList.get(i).getUserState())) {
					arrObj[7] =   "使用中";
				} else if ("0".equals(elecList.get(i).getUserState())) {
					arrObj[7] =   "未使用";
				} 
				if ("1".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿民用";
				} else if ("2".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿商业";
				}else if ("3".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿工业1";
				}else if ("4".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿工业2";
				}else if ("5".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿工业3";
				}else if ("6".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌民用";
				}else if ("7".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌商业";
				}else if ("8".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌工业";
				}else if ("9".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "煤田民用";
				}else if ("10".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "煤田商业";
				}else if ("11".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "政府部门";
				}
				arrObj[9] = elecList.get(i).getElecPrice();
				arrObj[10] = elecList.get(i).getStart();
				arrObj[11] = elecList.get(i).getEnd();
				arrObj[12] = elecList.get(i).getHu();
				arrObj[13] = elecList.get(i).getElecAmount();
				arrObj[14] = elecList.get(i).getElecCost();
				arrObj[15] = elecList.get(i).getElecSum();
				arrObj[16] = elecList.get(i).getCreateTime();
				arrObj[17] = elecList.get(i).getCreateBy();
				arrObj[18] = formatter.format(elecList.get(i).getUpdateTime());
				arrObj[19] = elecList.get(i).getUpdateBy();
				arrObj[20] = elecList.get(i).getRemark();
				dataList.add(arrObj);
			}
	
			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+"电费用户明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "电费用户明细";
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
