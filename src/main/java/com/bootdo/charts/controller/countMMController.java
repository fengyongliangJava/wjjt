package com.bootdo.charts.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.ExportExcelSheet;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;
import com.bootdo.moneyLog.vo.HeatLogVo;
import com.bootdo.moneyLog.vo.WaterLogVo;


 
@Controller
@RequestMapping("/charts/countMM")
public class countMMController {
	
	
	@Autowired private ElecLogService elecLogService;
	
	@Autowired private HeatLogService heatLogService;
	
	@Autowired private WaterLogService waterLogService;
	
	
	@GetMapping()
	@RequiresPermissions("charts:countMM")
	public String countMM(){
	    return "charts/countMM";
	}
	
	
    @ResponseBody
	@PostMapping("/getCountMM")
	public  Map<String,Object>  getCountMM(ElecLogVo elecLog, WaterLogVo waterLog, HeatLogVo heatLog){
		
	   	Map<String,Object> map = new HashMap<String,Object>();
		
		
		 int  CountMElecLog   = elecLogService.CountMElecLog(elecLog);
		 BigDecimal  SumMElecLog   = elecLogService.SumMElecLog(elecLog);
		 int  CountSElecLog   = elecLogService.CountSElecLog(elecLog);
		 BigDecimal  SumSElecLog     = elecLogService.SumSElecLog(elecLog);
		 int  CountG1ElecLog  = elecLogService.CountG1ElecLog(elecLog);
		 BigDecimal  SumG1ElecLog    = elecLogService.SumG1ElecLog(elecLog);
		 int  CountG2ElecLog  = elecLogService.CountG2ElecLog(elecLog);
		 BigDecimal SumG2ElecLog    = elecLogService.SumG2ElecLog(elecLog);
		 int  CountG3ElecLog  = elecLogService.CountG3ElecLog(elecLog);
		 BigDecimal  SumG3ElecLog    = elecLogService.SumG3ElecLog(elecLog);
	  	 int  CountWMElecLog  = elecLogService.CountWMElecLog(elecLog);
	  	 BigDecimal  SumWMElecLog    = elecLogService.SumWMElecLog(elecLog);
	  	 int  CountWSElecLog  = elecLogService.CountWSElecLog(elecLog); 
	   	 BigDecimal  SumWSElecLog    = elecLogService.SumWSElecLog(elecLog); 
	  	 int  CountWGElecLog  = elecLogService.CountWGElecLog(elecLog); 
	  	 BigDecimal  SumWGElecLog    = elecLogService.SumWGElecLog(elecLog); 
	  	 int  CountMMElecLog  = elecLogService.CountMMElecLog(elecLog); 
	  	 BigDecimal  SumMMElecLog    = elecLogService.SumMMElecLog(elecLog); 
	  	 int  CountMSElecLog  = elecLogService.CountMSElecLog(elecLog);    
	  	 BigDecimal  SumMSElecLog    = elecLogService.SumMSElecLog(elecLog); 
	  	 int  CountZElecLog   = elecLogService.CountZElecLog (elecLog);  
	  	 BigDecimal  SumZElecLog     = elecLogService.SumZElecLog(elecLog); 
	
		 
		
		 int  CountMHeatLog  = heatLogService.CountMHeatLog(heatLog);
		 BigDecimal SumMHeatLog    = heatLogService.SumMHeatLog(heatLog);
		 int  CountSHeatLog  = heatLogService.CountSHeatLog(heatLog);
		 BigDecimal  SumSHeatLog    = heatLogService.SumSHeatLog(heatLog);
		 int  CountGMHeatLog  = heatLogService.CountGMHeatLog(heatLog);
		 BigDecimal  SumGMHeatLog    = heatLogService.SumGMHeatLog(heatLog);
		 int  CountGSHeatLog  = heatLogService.CountGSHeatLog(heatLog);
		 BigDecimal  SumGSHeatLog    = heatLogService.SumGSHeatLog(heatLog);
		
		 
		 
	
		 int  CountMWaterLog  = waterLogService.CountMWaterLog(waterLog);
		 BigDecimal  SumMWaterLog    = waterLogService.SumMWaterLog(waterLog);
		 int  CountS1WaterLog  = waterLogService.CountS1WaterLog(waterLog);
		 BigDecimal  SumS1WaterLog    = waterLogService.SumS1WaterLog(waterLog);
		 int  CountS2WaterLog  = waterLogService.CountS2WaterLog(waterLog);
		 BigDecimal  SumS2WaterLog    = waterLogService.SumS2WaterLog(waterLog);
		 int  CountS3WaterLog  = waterLogService.CountS3WaterLog(waterLog);
		 BigDecimal  SumS3WaterLog    = waterLogService.SumS3WaterLog(waterLog);
	
		 

		 
			map.put("CountMElecLog", CountMElecLog);
			map.put("SumMElecLog", SumMElecLog);
			map.put("CountSElecLog", CountSElecLog);
			map.put("SumSElecLog", SumSElecLog);
			map.put("CountG1ElecLog", CountG1ElecLog);
			map.put("SumG1ElecLog", SumG1ElecLog);
			map.put("CountG2ElecLog", CountG2ElecLog);
			map.put("SumG2ElecLog", SumG2ElecLog);
			map.put("CountG3ElecLog", CountG3ElecLog);
			map.put("SumG3ElecLog", SumG3ElecLog);
			map.put("CountWMElecLog", CountWMElecLog);
			map.put("SumWMElecLog", SumWMElecLog);
			map.put("CountWSElecLog", CountWSElecLog);
			map.put("SumWSElecLog", SumWSElecLog);
			map.put("CountWGElecLog", CountWGElecLog);
			map.put("SumWGElecLog", SumWGElecLog);
			map.put("CountMMElecLog", CountMMElecLog);
			map.put("SumMMElecLog", SumMMElecLog);
			map.put("CountMSElecLog", CountMSElecLog);
			map.put("SumMSElecLog", SumMSElecLog);
			map.put("CountZElecLog", CountZElecLog);
			map.put("SumZElecLog", SumZElecLog);
		 
		 
			map.put("CountMHeatLog", CountMHeatLog);
			map.put("SumMHeatLog", SumMHeatLog);
			map.put("CountSHeatLog", CountSHeatLog);
			map.put("SumSHeatLog", SumSHeatLog);
			map.put("CountGMHeatLog", CountGMHeatLog);
			map.put("SumGMHeatLog", SumGMHeatLog);
			map.put("CountGSHeatLog", CountGSHeatLog);
			map.put("SumGSHeatLog", SumGSHeatLog);

			map.put("CountMWaterLog", CountMWaterLog);
			map.put("SumMWaterLog", SumMWaterLog);
			map.put("CountS1WaterLog", CountS1WaterLog);
			map.put("SumS1WaterLog", SumS1WaterLog);
			map.put("CountS2WaterLog", CountS2WaterLog);
			map.put("SumS2WaterLog", SumS2WaterLog);
			map.put("CountS3WaterLog", CountS3WaterLog);
			map.put("SumS3WaterLog", SumS3WaterLog);
		
			int totalCount = CountMElecLog+CountSElecLog+CountG1ElecLog+CountG2ElecLog+CountG3ElecLog+CountWMElecLog+CountWSElecLog+CountWGElecLog+CountMMElecLog+CountMSElecLog+CountZElecLog+CountMHeatLog+CountSHeatLog+CountGMHeatLog+CountGSHeatLog+CountMWaterLog+CountS1WaterLog+CountS2WaterLog+CountS3WaterLog;
			 
			List<BigDecimal>  TotalSumList  = new ArrayList<>();
			
			TotalSumList.add(SumMElecLog);
			TotalSumList.add(SumSElecLog);
			TotalSumList.add(SumG1ElecLog);
			TotalSumList.add(SumG2ElecLog);
			TotalSumList.add(SumG3ElecLog);
			TotalSumList.add(SumWMElecLog);
			TotalSumList.add(SumWSElecLog);
			TotalSumList.add(SumWGElecLog);
			TotalSumList.add(SumMMElecLog);
			TotalSumList.add(SumMSElecLog);
			TotalSumList.add(SumZElecLog);
			
			TotalSumList.add(SumMHeatLog);
			TotalSumList.add(SumSHeatLog);
			TotalSumList.add(SumGMHeatLog);
			TotalSumList.add(SumGSHeatLog);
			
			TotalSumList.add(SumMWaterLog);
			TotalSumList.add(SumS1WaterLog);
			TotalSumList.add(SumS2WaterLog);
			TotalSumList.add(SumS3WaterLog);
			
			BigDecimal totalSum = new BigDecimal(0);
			
			for(BigDecimal a:TotalSumList) {	  	 
				totalSum = totalSum.add(a);  
			}
			
			int totalCountElec = CountMElecLog+CountSElecLog+CountG1ElecLog+CountG2ElecLog+CountG3ElecLog+CountWMElecLog+CountWSElecLog+CountWGElecLog+CountMMElecLog+CountMSElecLog+CountZElecLog;
			
			List<BigDecimal>  TotalSumListElec  = new ArrayList<>();
			
			TotalSumListElec.add(SumMElecLog);
			TotalSumListElec.add(SumSElecLog);
			TotalSumListElec.add(SumG1ElecLog);
			TotalSumListElec.add(SumG2ElecLog);
			TotalSumListElec.add(SumG3ElecLog);
			TotalSumListElec.add(SumWMElecLog);
			TotalSumListElec.add(SumWSElecLog);
			TotalSumListElec.add(SumWGElecLog);
			TotalSumListElec.add(SumMMElecLog);
			TotalSumListElec.add(SumMSElecLog);
			TotalSumListElec.add(SumZElecLog);
			
			
			BigDecimal totalSumElec = new BigDecimal(0);
			
			for(BigDecimal b:TotalSumListElec) {	  	 
				totalSumElec = totalSumElec.add(b);  
			}
			
			
			int totalCountHeat = CountMHeatLog+CountSHeatLog+CountGMHeatLog+CountGSHeatLog;
			
			List<BigDecimal>  TotalSumListHeat  = new ArrayList<>();
			
			TotalSumListHeat.add(SumMHeatLog);
			TotalSumListHeat.add(SumSHeatLog);
			TotalSumListHeat.add(SumGMHeatLog);
			TotalSumListHeat.add(SumGSHeatLog);
			
			BigDecimal totalSumHeat = new BigDecimal(0);
			
			for(BigDecimal c:TotalSumListHeat) {	  	 
				totalSumHeat = totalSumHeat.add(c);  
			}
			
			int totalCountWater = CountMWaterLog+CountS1WaterLog+CountS2WaterLog+CountS3WaterLog;
			
			List<BigDecimal>  TotalSumListWater  = new ArrayList<>();
			
			TotalSumListWater.add(SumMWaterLog);
			TotalSumListWater.add(SumS1WaterLog);
			TotalSumListWater.add(SumS2WaterLog);
			TotalSumListWater.add(SumS3WaterLog);
			
			BigDecimal totalSumWater = new BigDecimal(0);
			
			for(BigDecimal d:TotalSumListWater) {	  	 
				totalSumWater = totalSumWater.add(d);  
			}
			
			
			map.put("totalCount", totalCount);
			map.put("totalSum", totalSum);
			
			map.put("totalCountElec", totalCountElec);
			map.put("totalCountHeat", totalCountHeat);
			map.put("totalCountWater", totalCountWater);
			map.put("totalSumElec", totalSumElec);
			map.put("totalSumHeat", totalSumHeat);
			map.put("totalSumWater", totalSumWater);
			
	    return map;
	}
	
	
	
	
	@ResponseBody
	@GetMapping("/exportMM")
	public void exportMM(ElecLogVo elecLog,HeatLogVo heatLog,WaterLogVo waterLog,HttpServletResponse response){
		  
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HSSFWorkbook workbook = new HSSFWorkbook();
			OutputStream out = response.getOutputStream();
			
			
			String[] headers1 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","电费类型","缴费金额","缴费时间","收费时间","用户备注"};
			String[] headers2 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","暖费类型","缴费金额","缴费时间","收费时间","用户备注"};
			String[] headers3 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","水费类型","缴费金额","缴费时间","收费时间","用户备注"};
			
			String createTime = elecLog.getCreateTime();
			
			String userOrg = "";
			if ("2".equals(elecLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elecLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = "";
			
			if ("A".equals(elecLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(elecLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(elecLog.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(elecLog.getUserType())) {
				userType =   "银行转账";
			}
			
			
			String userId = elecLog.getUserId();
			
/*			if(userId == null && userId.length() == 0) {
				 userId = "";
			}*/
			
			List<ElecLogVo> elecLogList = elecLogService.exprotElecLogMM(elecLog);
			List<Object[]> elecdataList = new ArrayList<Object[]>();
			for(int i = 0;i < elecLogList.size(); i++ ) {
				Object[] arrObj = new Object[headers1.length];
				arrObj[0] = elecLogList.get(i).getId();
				arrObj[1] = elecLogList.get(i).getUserId();
				arrObj[2] = elecLogList.get(i).getUserName();
				if ("A".equals(elecLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(elecLogList.get(i).getUserType())) {
					arrObj[3]=   "工资代扣";
				} else if ("C".equals(elecLogList.get(i).getUserType())) {
					arrObj[3] =   "微信支付";
				} else if ("D".equals(elecLogList.get(i).getUserType())) {
					arrObj[3] =   "银行转账";
				}
				arrObj[4] = elecLogList.get(i).getUserOrgName();
				if ("1".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "矿民用";
				} else if ("2".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "矿商业";
				}else if ("3".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "矿工业1";
				}else if ("4".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "矿工业2";
				}else if ("5".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "矿工业3";
				}else if ("6".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "乌民用";
				}else if ("7".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "乌商业";
				}else if ("8".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "乌工业";
				}else if ("9".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "煤田民用";
				}else if ("10".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "煤田商业";
				}else if ("11".equals(elecLogList.get(i).getElecType())) {
					arrObj[5] =   "政府部门";
				}
				arrObj[6] = elecLogList.get(i).getElecMoney();
				arrObj[7] = formatter.format(elecLogList.get(i).getMoneyDate());
				arrObj[8] = elecLogList.get(i).getCreateTime();
				arrObj[9] = elecLogList.get(i).getRemark();
				elecdataList.add(arrObj);
			}
			List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogMM(heatLog);
			List<Object[]> heatdataList = new ArrayList<Object[]>();
			for(int i = 0;i < heatLogList.size(); i++ ) {
				Object[] arrObj = new Object[headers2.length];
				arrObj[0] = heatLogList.get(i).getId();
				arrObj[1] = heatLogList.get(i).getUserId();
				arrObj[2] = heatLogList.get(i).getUserName();
				if ("A".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(heatLogList.get(i).getUserType())) {
					arrObj[3]=   "工资代扣";
				} else if ("C".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "微信支付";
				} else if ("D".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "银行转账";
				}
				arrObj[4] = heatLogList.get(i).getUserOrgName();
				
				if ("1".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5] =   "矿民用";
				} else if ("2".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5] =   "矿商用";
				}else if ("3".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5] =   "工业民";
				}else if ("4".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5] =   "工业商";
				}
				
				arrObj[6] = heatLogList.get(i).getHeatMoney();
				arrObj[7] = formatter.format(heatLogList.get(i).getMoneyDate());
				arrObj[8] = heatLogList.get(i).getCreateTime();
				arrObj[9] = heatLogList.get(i).getRemark();
				heatdataList.add(arrObj);
			}
			
			

			List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogMM(waterLog);
			List<Object[]> waterdataList = new ArrayList<Object[]>();
			for(int i = 0;i < waterLogList.size(); i++ ) {
				Object[] arrObj = new Object[headers3.length];
				arrObj[0] = waterLogList.get(i).getId();
				arrObj[1] = waterLogList.get(i).getUserId();
				arrObj[2] = waterLogList.get(i).getUserName();
				
				if ("A".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3]=   "工资代扣";
				} else if ("C".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "微信支付";
				} else if ("D".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "银行转账";
				}
				arrObj[4] = waterLogList.get(i).getUserOrgName();
				if ("1".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5] =   "民用水";
				} else if ("2".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5] =   "商业水1";
				} else if ("3".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5] =   "商业水2";
				} else if ("4".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5] =   "商业水3";
				}
				arrObj[6] = waterLogList.get(i).getWaterMoney();
				arrObj[7] = formatter.format(waterLogList.get(i).getMoneyDate());
				arrObj[8] = waterLogList.get(i).getCreateTime();
				arrObj[9] = waterLogList.get(i).getRemark();
				waterdataList.add(arrObj);
			}

			String title =  "水电暖当月实收收费报表.xlsx";

			String oneheaders1 = createTime+userOrg+userType+userId+"电费当月实收表";
			String oneheaders2 = createTime+userOrg+userType+userId+"暖费当月实收表";
			String oneheaders3 = createTime+userOrg+userType+userId+"水费当月实收表";


			String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
			response.setContentType("octets/stream");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", headStr);
			
			ExportExcelSheet ex1  = new ExportExcelSheet("电费", oneheaders1, headers1, elecdataList);
			ExportExcelSheet ex2  = new ExportExcelSheet("暖费", oneheaders2, headers2, heatdataList);
			ExportExcelSheet ex3  = new ExportExcelSheet("水费", oneheaders3, headers3, waterdataList);

			ex1.export(workbook,out);
			ex2.export(workbook,out);
			ex3.export(workbook,out);

			workbook.write(out);  
			out.close();  

		} catch (Exception e) {
			e.printStackTrace();
		}
	}  





}
