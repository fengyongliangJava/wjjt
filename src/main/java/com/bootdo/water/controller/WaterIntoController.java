package com.bootdo.water.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.bootdo.water.service.WaterIntoService;
import com.bootdo.water.vo.WaterVo;






@Controller
@RequestMapping("/water/waterInto")
public class WaterIntoController {
	
/*	
 * private static Logger logger = Logger.getLogger(WaterIntoController.class);
 * logger.info("excel表格上传文件解析成功,count:"+count+"water.getId:"+water.getUserId());
 */
	
	@Autowired
	private WaterIntoService waterIntoService;
	
	
	
	@GetMapping()
	@RequiresPermissions("water:waterInto")
	String WaterInto(){
	    return "water/waterInto/waterInto";
	}
	
	@ResponseBody
	@GetMapping("/exprotWaterIntoExcel")
	public void exprotExcel(WaterVo water,HttpServletResponse response){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime = water.getCreateTime();
			String userOrg = "";
			if ("2".equals(water.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(water.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			List<WaterVo> waterList = waterIntoService.getWaterIntoList(water);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用水类型","水费单价","用户水费","用户余额","创建时间","更新时间","用户备注"};
			
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
					arrObj[4]=   "工资代扣";
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
					arrObj[8] =   "民用水";
				} else if ("2".equals(waterList.get(i).getWaterType())) {
					arrObj[8] =   "商业水";
				}
				arrObj[9] = waterList.get(i).getWaterPrice();
				arrObj[11] = waterList.get(i).getWaterCost();
				arrObj[12] = waterList.get(i).getWaterSum();
				arrObj[13] = waterList.get(i).getCreateTime();
				arrObj[14] = formatter.format(waterList.get(i).getUpdateTime());
				arrObj[15] = waterList.get(i).getRemark();
				dataList.add(arrObj);
			}

			ExportExcel excelPort = new ExportExcel(createTime+userOrg+"水费工资代扣明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "水费工资代扣明细";
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

	 /**
	  * 导入工资代扣Excel表格
	  * @param excelFile
	  * @return
	  * @throws Exception
	  */
	 @ResponseBody
	 @PostMapping("/importWaterIntoExcel")
		public String importWaterIntoExcel(@RequestParam("file") MultipartFile file, WaterVo water,HttpServletResponse response) throws Exception {
			 try {
				 return waterIntoService.importWaterIntoExcel(file,water);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
}
