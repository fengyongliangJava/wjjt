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
@RequestMapping("/charts/countRun")
public class countRunController {
	
	
	
   @Autowired private ElecLogService elecLogService;

   @Autowired private HeatLogService heatLogService;

   @Autowired private WaterLogService waterLogService;
	
	
	
	@GetMapping()
	@RequiresPermissions("charts:countRun")
	String countRun(){
	    return "charts/countRun";
	}
	

	 @ResponseBody
	 @PostMapping("/getCountRun")
	 public Map<String,Object> getCountRun(ElecLogVo elecLog,WaterLogVo waterLog, HeatLogVo heatLog) {
		 
		 
			Map<String,Object> map = new HashMap<>();
			 
			int elecLogCount1 = elecLogService.Count1ElecLog(elecLog);
			BigDecimal elecLogSum1 = elecLogService.Sum1ElecLog(elecLog);
			int elecLogCount2 = elecLogService.Count2ElecLog(elecLog);
			BigDecimal elecLogSum2 = elecLogService.Sum2ElecLog(elecLog);
			int elecLogCount3 = elecLogService.Count3ElecLog(elecLog);
			BigDecimal elecLogSum3 = elecLogService.Sum3ElecLog(elecLog);
			int elecLogCount4 = elecLogService.Count4ElecLog(elecLog);
			BigDecimal elecLogSum4 = elecLogService.Sum4ElecLog(elecLog);
			int elecLogCount5 = elecLogService.Count5ElecLog(elecLog);
			BigDecimal elecLogSum5 = elecLogService.Sum5ElecLog(elecLog);
			int elecLogCount6 = elecLogService.Count6ElecLog(elecLog);
			BigDecimal elecLogSum6 = elecLogService.Sum6ElecLog(elecLog);
			int elecLogCount7 = elecLogService.Count7ElecLog(elecLog);
			BigDecimal elecLogSum7 = elecLogService.Sum7ElecLog(elecLog);
			int elecLogCount8 = elecLogService.Count8ElecLog(elecLog);
			BigDecimal elecLogSum8 = elecLogService.Sum8ElecLog(elecLog);
			int elecLogCount9 = elecLogService.Count9ElecLog(elecLog);
			BigDecimal elecLogSum9 = elecLogService.Sum9ElecLog(elecLog);
			int elecLogCount10 = elecLogService.Count10ElecLog(elecLog);
			BigDecimal elecLogSum10 = elecLogService.Sum10ElecLog(elecLog);
			int elecLogCount11 = elecLogService.Count11ElecLog(elecLog);
			BigDecimal elecLogSum11 = elecLogService.Sum11ElecLog(elecLog);
			int elecLogCount12 = elecLogService.Count12ElecLog(elecLog);
			BigDecimal elecLogSum12 = elecLogService.Sum12ElecLog(elecLog);
			
			
			int heatLogCount1 = heatLogService.Count1HeatLog(heatLog);
			BigDecimal heatLogSum1 = heatLogService.Sum1HeatLog(heatLog);
			int heatLogCount2 = heatLogService.Count2HeatLog(heatLog);
			BigDecimal heatLogSum2 = heatLogService.Sum2HeatLog(heatLog);
			int heatLogCount3 = heatLogService.Count3HeatLog(heatLog);
			BigDecimal heatLogSum3 = heatLogService.Sum3HeatLog(heatLog);
			int heatLogCount4 = heatLogService.Count4HeatLog(heatLog);
			BigDecimal heatLogSum4 = heatLogService.Sum4HeatLog(heatLog);
			int heatLogCount5 = heatLogService.Count5HeatLog(heatLog);
			BigDecimal heatLogSum5 = heatLogService.Sum5HeatLog(heatLog);
			int heatLogCount6 = heatLogService.Count6HeatLog(heatLog);
			BigDecimal heatLogSum6 = heatLogService.Sum6HeatLog(heatLog);
			int heatLogCount7 = heatLogService.Count7HeatLog(heatLog);
			BigDecimal heatLogSum7 = heatLogService.Sum7HeatLog(heatLog);
			int heatLogCount8 = heatLogService.Count8HeatLog(heatLog);
			BigDecimal heatLogSum8 = heatLogService.Sum8HeatLog(heatLog);
			int heatLogCount9 = heatLogService.Count9HeatLog(heatLog);
			BigDecimal heatLogSum9 = heatLogService.Sum9HeatLog(heatLog);
			int heatLogCount10 = heatLogService.Count10HeatLog(heatLog);
			BigDecimal heatLogSum10 = heatLogService.Sum10HeatLog(heatLog);
			int heatLogCount11 = heatLogService.Count11HeatLog(heatLog);
			BigDecimal heatLogSum11 = heatLogService.Sum11HeatLog(heatLog);
			int heatLogCount12 = heatLogService.Count12HeatLog(heatLog);
			BigDecimal heatLogSum12 = heatLogService.Sum12HeatLog(heatLog);



			int waterLogCount1 = waterLogService.Count1WaterLog(waterLog);
			BigDecimal waterLogSum1 = waterLogService.Sum1WaterLog(waterLog);
			int waterLogCount2 = waterLogService.Count2WaterLog(waterLog);
			BigDecimal waterLogSum2 = waterLogService.Sum2WaterLog(waterLog);
			int waterLogCount3 = waterLogService.Count3WaterLog(waterLog);
			BigDecimal waterLogSum3 = waterLogService.Sum3WaterLog(waterLog);
			int waterLogCount4 = waterLogService.Count4WaterLog(waterLog);
			BigDecimal waterLogSum4 = waterLogService.Sum4WaterLog(waterLog);
			int waterLogCount5 = waterLogService.Count5WaterLog(waterLog);
			BigDecimal waterLogSum5 = waterLogService.Sum5WaterLog(waterLog);
			int waterLogCount6 = waterLogService.Count6WaterLog(waterLog);
			BigDecimal waterLogSum6 = waterLogService.Sum6WaterLog(waterLog);
			int waterLogCount7 = waterLogService.Count7WaterLog(waterLog);
			BigDecimal waterLogSum7 = waterLogService.Sum7WaterLog(waterLog);
			int waterLogCount8 = waterLogService.Count8WaterLog(waterLog);
			BigDecimal waterLogSum8 = waterLogService.Sum8WaterLog(waterLog);
			int waterLogCount9 = waterLogService.Count9WaterLog(waterLog);
			BigDecimal waterLogSum9 = waterLogService.Sum9WaterLog(waterLog);
			int waterLogCount10 = waterLogService.Count10WaterLog(waterLog);
			BigDecimal waterLogSum10 = waterLogService.Sum10WaterLog(waterLog);
			int waterLogCount11 = waterLogService.Count11WaterLog(waterLog);
			BigDecimal waterLogSum11 = waterLogService.Sum11WaterLog(waterLog);
			int waterLogCount12 = waterLogService.Count12WaterLog(waterLog);
			BigDecimal waterLogSum12 = waterLogService.Sum12WaterLog(waterLog);
			
			
			
			map.put("elecLogSum1", elecLogSum1);
			map.put("elecLogSum2", elecLogSum2);
			map.put("elecLogSum3", elecLogSum3);
			map.put("elecLogSum4", elecLogSum4);
			map.put("elecLogSum5", elecLogSum5);
			map.put("elecLogSum6", elecLogSum6);
			map.put("elecLogSum7", elecLogSum7);
			map.put("elecLogSum8", elecLogSum8);
			map.put("elecLogSum9", elecLogSum9);
			map.put("elecLogSum10", elecLogSum10);	
			map.put("elecLogSum11", elecLogSum11);
			map.put("elecLogSum12", elecLogSum12);
			
			map.put("heatLogSum1", heatLogSum1);
			map.put("heatLogSum2", heatLogSum2);
			map.put("heatLogSum3", heatLogSum3);
			map.put("heatLogSum4", heatLogSum4);
			map.put("heatLogSum5", heatLogSum5);
			map.put("heatLogSum6", heatLogSum6);
			map.put("heatLogSum7", heatLogSum7);
			map.put("heatLogSum8", heatLogSum8);
			map.put("heatLogSum9", heatLogSum9);
			map.put("heatLogSum10", heatLogSum10);	
			map.put("heatLogSum11", heatLogSum11);
			map.put("heatLogSum12", heatLogSum12);
			
			map.put("waterLogSum1", waterLogSum1);
			map.put("waterLogSum2", waterLogSum2);
			map.put("waterLogSum3", waterLogSum3);
			map.put("waterLogSum4", waterLogSum4);
			map.put("waterLogSum5", waterLogSum5);
			map.put("waterLogSum6", waterLogSum6);
			map.put("waterLogSum7", waterLogSum7);
			map.put("waterLogSum8", waterLogSum8);
			map.put("waterLogSum9", waterLogSum9);
			map.put("waterLogSum10", waterLogSum10);	
			map.put("waterLogSum11", waterLogSum11);
			map.put("waterLogSum12", waterLogSum12);
			
			
			int totalCount = elecLogCount1+elecLogCount2+elecLogCount3+elecLogCount4+elecLogCount5+elecLogCount6+elecLogCount7+elecLogCount8+elecLogCount9+elecLogCount10+elecLogCount11+elecLogCount12+heatLogCount1+heatLogCount2+heatLogCount3+heatLogCount4+heatLogCount5+heatLogCount6+heatLogCount7+heatLogCount8+heatLogCount9+heatLogCount10+heatLogCount11+heatLogCount12+waterLogCount1+waterLogCount2+waterLogCount3+waterLogCount4+waterLogCount5+waterLogCount6+waterLogCount7+waterLogCount8+waterLogCount9+waterLogCount10+waterLogCount11+waterLogCount12;
			
			List<BigDecimal> totalSumList = new ArrayList<>();
			
			totalSumList.add(elecLogSum1);
			totalSumList.add(elecLogSum2);
			totalSumList.add(elecLogSum3);
			totalSumList.add(elecLogSum4);
			totalSumList.add(elecLogSum5);
			totalSumList.add(elecLogSum6);
			totalSumList.add(elecLogSum7);
			totalSumList.add(elecLogSum8);
			totalSumList.add(elecLogSum9);
			totalSumList.add(elecLogSum10);
			totalSumList.add(elecLogSum11);
			totalSumList.add(elecLogSum12);
			
			totalSumList.add(heatLogSum1);
			totalSumList.add(heatLogSum2);
			totalSumList.add(heatLogSum3);
			totalSumList.add(heatLogSum4);
			totalSumList.add(heatLogSum5);
			totalSumList.add(heatLogSum6);
			totalSumList.add(heatLogSum7);
			totalSumList.add(heatLogSum8);
			totalSumList.add(heatLogSum9);
			totalSumList.add(heatLogSum10);
			totalSumList.add(heatLogSum11);
			totalSumList.add(heatLogSum12);
			
			totalSumList.add(waterLogSum1);
			totalSumList.add(waterLogSum2);
			totalSumList.add(waterLogSum3);
			totalSumList.add(waterLogSum4);
			totalSumList.add(waterLogSum5);
			totalSumList.add(waterLogSum6);
			totalSumList.add(waterLogSum7);
			totalSumList.add(waterLogSum8);
			totalSumList.add(waterLogSum9);
			totalSumList.add(waterLogSum10);
			totalSumList.add(waterLogSum11);
			totalSumList.add(waterLogSum12);
			
			BigDecimal totalSum = new BigDecimal(0);
			for(BigDecimal a:totalSumList ) {
				totalSum = totalSum.add(a);
			}
			
			map.put("totalCount", totalCount);
			map.put("totalSum", totalSum);
			
		    return map;
			

	  }

	
	 
	 
	 
	  
	  @ResponseBody
	  @GetMapping("/exportRun")
	  public void exportRun(ElecLogVo elecLog,HeatLogVo heatLog,WaterLogVo waterLog,HttpServletResponse response){
		  
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			HSSFWorkbook workbook = new HSSFWorkbook();
			OutputStream out = response.getOutputStream();
			
			
			String[] headers1 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","电费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			String[] headers2 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","暖费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			String[] headers3 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","水费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
			String createTime = elecLog.getCreateTime();
			String userOrg = elecLog.getUserOrg();
			if ("2".equals(elecLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elecLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = elecLog.getUserType();
			if ("1".equals(elecLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("2".equals(elecLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("3".equals(elecLog.getUserType())) {
				userType =   "微信支付";
			} else if ("4".equals(elecLog.getUserType())) {
				userType =   "银行转账";
			}
			
			
			String userId = elecLog.getUserId();
			
			List<ElecLogVo> elecLogList = elecLogService.exprotElecLogYY(elecLog);
			List<Object[]> elecdataList = new ArrayList<Object[]>();
			for(int i = 0;i < elecLogList.size(); i++ ) {
				Object[] arrObj = new Object[headers1.length];
				arrObj[0] = elecLogList.get(i).getId();
				arrObj[1] = elecLogList.get(i).getUserId();
				arrObj[2] = elecLogList.get(i).getUserName();
				if ("A".equals(elecLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(elecLogList.get(i).getUserType())) {
					arrObj[3]=    "工资代扣";
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
				arrObj[9] = elecLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(elecLogList.get(i).getUpdateTime());
				arrObj[11] = elecLogList.get(i).getUpdateBy();
				arrObj[12] = elecLogList.get(i).getRemark();
				elecdataList.add(arrObj);
			}
			List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogYY(heatLog);
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
				arrObj[9] = heatLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(heatLogList.get(i).getUpdateTime());
				arrObj[11] = heatLogList.get(i).getUpdateBy();
				arrObj[12] = heatLogList.get(i).getRemark();
				heatdataList.add(arrObj);
			}
			
			

			List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogYY(waterLog);
			List<Object[]> waterdataList = new ArrayList<Object[]>();
			for(int i = 0;i < waterLogList.size(); i++ ) {
				Object[] arrObj = new Object[headers3.length];
				arrObj[0] = waterLogList.get(i).getId();
				arrObj[1] = waterLogList.get(i).getUserId();
				arrObj[2] = waterLogList.get(i).getUserName();
				
				if ("A".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3]=    "工资代扣";
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
				arrObj[9] = waterLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(waterLogList.get(i).getUpdateTime());
				arrObj[11] = waterLogList.get(i).getUpdateBy();
				arrObj[12] = waterLogList.get(i).getRemark();
				waterdataList.add(arrObj);
			}

			String title =  "水电暖全年收费报表.xlsx";

			String oneheaders1 = createTime+userOrg+userType+userId+"电费全年实收表";
			String oneheaders2 = createTime+userOrg+userType+userId+"暖费全年实收表";
			String oneheaders3 = createTime+userOrg+userType+userId+"水费全年实收表";

			

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
