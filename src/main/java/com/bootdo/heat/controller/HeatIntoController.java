package com.bootdo.heat.controller;

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
import com.bootdo.heat.service.HeatIntoService;
import com.bootdo.heat.vo.HeatVo;


@Controller
@RequestMapping("/heat/heatInto")
public class HeatIntoController {
	
/*	
 *  private static Logger logger = Logger.getLogger(HeatIntoController.class);
 * 	logger.info("excel表格上传文件解析成功,count:"+count+"heat.getId:"+heat.getUserId());
 * 
 * */
	
	
	@Autowired
	private HeatIntoService heatIntoService;
	

	
	
	@GetMapping()
	@RequiresPermissions("heat:heatInto")
	String HeatInto(){
	    return "heat/heatInto/heatInto";
	}
	
	@ResponseBody
	@GetMapping("/exprotHeatIntoExcel")
	public void exprotExcel(HeatVo heat,HttpServletResponse response){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime = heat.getCreateTime();
			String userOrg = "";
			if ("2".equals(heat.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(heat.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			List<HeatVo> heatList = heatIntoService.getHeatIntoList(heat);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","取暖类型","暖费单价","取暖面积","用户暖费","用户余额","创建时间","更新时间","用户备注"};
			
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
					arrObj[4]=   "工资代扣";
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
				}else if ("3".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "工业民";
				}else if ("4".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "工业商";
				}
				arrObj[9] = heatList.get(i).getHeatPrice();
				arrObj[10] = heatList.get(i).getHeatArea();
				arrObj[11] = heatList.get(i).getHeatCost();
				arrObj[12] = heatList.get(i).getHeatSum();
				arrObj[13] = heatList.get(i).getCreateTime();
				arrObj[14] = formatter.format(heatList.get(i).getUpdateTime());
				arrObj[15] = heatList.get(i).getRemark();
				dataList.add(arrObj);
			}

			ExportExcel excelPort = new ExportExcel(createTime+userOrg+"暖费工资代扣明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "暖费工资代扣明细";
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
	     @PostMapping("/importHeatIntoExcel")
 		public String importHeatIntoExcel(@RequestParam("file") MultipartFile file, HeatVo heat,HttpServletResponse response) throws Exception {

			 try {
				 return heatIntoService.importHeatIntoExcel(file,heat);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		 }
}
