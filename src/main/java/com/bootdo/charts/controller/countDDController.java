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
import com.bootdo.common.utils.Util;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;
import com.bootdo.moneyLog.vo.HeatLogVo;
import com.bootdo.moneyLog.vo.WaterLogVo;

@Controller
@RequestMapping("/charts/countDD")
public class countDDController {

	
  @Autowired
  private ElecLogService elecLogService;

  @Autowired
  private HeatLogService heatLogService;

  @Autowired
  private WaterLogService waterLogService;

  
  @GetMapping()
  @RequiresPermissions("charts:countDD")
   public String countDD() {
    return "charts/countDD";
  }

  
  
  @ResponseBody
  @PostMapping("/getCountDD")
  public Map<String,Object> getCountD(ElecLogVo elecLog, WaterLogVo waterLog, HeatLogVo heatLog) {
	  
	  
	  
	    Map<String,Object> map = new HashMap<>();
	  
		String Date = elecLog.getCreateTime();
		
		if(!Util.isNullOrEmpty(Date)) {
			elecLog.setBeginDate(Date+" 00:00:00");
			elecLog.setEndDate(Date + " 23:59:59");
			heatLog.setBeginDate(Date+" 00:00:00");
			heatLog.setEndDate(Date + " 23:59:59");
			waterLog.setBeginDate(Date+" 00:00:00");
			waterLog.setEndDate(Date + " 23:59:59");
		}
		
		
		
		int elecLogCount = elecLogService.getElecCountD(elecLog);
		BigDecimal elecLogSum = elecLogService.getElecSumD(elecLog);
		int waterLogCount = waterLogService.getWaterCountD(waterLog);
		BigDecimal waterLogSum = waterLogService.getWaterSumD(waterLog);
		int heatLogCount = heatLogService.getHeatCountD(heatLog);
		BigDecimal heatLogSum = heatLogService.getHeatSumD(heatLog);

		int totalCount = elecLogCount + heatLogCount + waterLogCount;
		
		

		List<BigDecimal>  totalSumList = new ArrayList<>();
		
		totalSumList.add(elecLogSum);
		totalSumList.add(heatLogSum);
		totalSumList.add(waterLogSum);
		
		BigDecimal totalSum = new BigDecimal(0);
		for(BigDecimal a:totalSumList) {
			totalSum = totalSum.add(a);
		}
	

		
		map.put("elecLogCount",elecLogCount);
		map.put("elecLogSum",elecLogSum);
		map.put("waterLogCount",waterLogCount);
		map.put("waterLogSum",waterLogSum);
		map.put("heatLogCount",heatLogCount);
		map.put("heatLogSum",heatLogSum);
		
		map.put("totalCount", totalCount);
		map.put("totalSum", totalSum);
		
		
	    return map;
	

		
		
  }



    @ResponseBody
	@GetMapping("/exportDD")
	public void exportDD(ElecLogVo elecLog,HeatLogVo heatLog,WaterLogVo waterLog,HttpServletResponse response){

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			OutputStream out = response.getOutputStream();
			
			String[] headers1 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","电费类型","缴费金额","缴费时间","收费时间","用户备注"};
			String[] headers2 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","暖费类型","缴费金额","缴费时间","收费时间","用户备注"};
			String[] headers3 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","水费类型","缴费金额","缴费时间","收费时间","用户备注"};
			
			String Date = elecLog.getCreateTime();
			if(!Util.isNullOrEmpty(Date)) {
				elecLog.setBeginDate(Date+" 00:00:00");
				elecLog.setEndDate(Date + " 23:59:59");
				heatLog.setBeginDate(Date+" 00:00:00");
				heatLog.setEndDate(Date + " 23:59:59");
				waterLog.setBeginDate(Date+" 00:00:00");
				waterLog.setEndDate(Date + " 23:59:59");
			}

			String userId = elecLog.getUserId();
			
			String userOrg = "";
			if ("2".equals(elecLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elecLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			
			List<ElecLogVo> elecLogList = elecLogService.exprotElecLogDD(elecLog);
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
			List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogDD(heatLog);
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

			List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogDD(waterLog);
			
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

			
			String title =  "水电暖当日收费报表.xlsx";
			
			String oneheaders1 = Date+userOrg+userId+"电费当日实收表";
			String oneheaders2 = Date+userOrg+userId+"暖费当日实收表";
			String oneheaders3 = Date+userOrg+userId+"水费当日实收表";
			
		
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
