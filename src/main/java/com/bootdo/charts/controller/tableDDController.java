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
@RequestMapping("/charts/tableDD")
public class tableDDController {

	
  @Autowired
  private ElecLogService elecLogService;
  
  @Autowired 
  private HeatLogService heatLogService;
  
  @Autowired 
  private WaterLogService waterLogService;

  
  @GetMapping()
  @RequiresPermissions("charts:tableDD")
   public String countDD() {
    return "charts/tableDD";
  }


  @ResponseBody
  @PostMapping("/getTableDD")
  public Map<String,Object> getTableDD(ElecLogVo elecLog, WaterLogVo waterLog, HeatLogVo heatLog) {
	  
	  Map<String,Object> map = new HashMap<String,Object>();
	  
	  
	  String createTime = elecLog.getCreateTime();
	  
	  if (!Util.isNullOrEmpty(createTime)) {

		  elecLog.setBeginDate(createTime + " 00:00:00");
		  elecLog.setEndDate(createTime + " 23:59:59");

		  waterLog.setBeginDate(createTime + " 00:00:00");
		  waterLog.setEndDate(createTime + " 23:59:59");

		  heatLog.setBeginDate(createTime + " 00:00:00");
		  heatLog.setEndDate(createTime + " 23:59:59");
	  }

	  int MelecLogCountA = elecLogService.MElecLogCountA(elecLog);
	  BigDecimal MelecLogSumA = elecLogService.MElecLogSumA(elecLog);
	  int MelecLogCountC = elecLogService.MElecLogCountC(elecLog);
	  BigDecimal MelecLogSumC = elecLogService.MElecLogSumC(elecLog);

	  int SelecLogCountA = elecLogService.SElecLogCountA(elecLog);
	  BigDecimal SelecLogSumA = elecLogService.SElecLogSumA(elecLog);
	  int SelecLogCountC = elecLogService.SElecLogCountC(elecLog);
	  BigDecimal SelecLogSumC = elecLogService.SElecLogSumC(elecLog);
	  
	  int GelecLogCountA = elecLogService.GElecLogCountA(elecLog);
	  BigDecimal GelecLogSumA = elecLogService.GElecLogSumA(elecLog);
	  int GelecLogCountC = elecLogService.GElecLogCountC(elecLog);
	  BigDecimal GelecLogSumC = elecLogService.GElecLogSumC(elecLog);
	  
	  int YXGelecLogCountA = elecLogService.YXGElecLogCountA(elecLog);
	  BigDecimal YXGelecLogSumA = elecLogService.YXGElecLogSumA(elecLog);
	  int YXGelecLogCountC = elecLogService.YXGElecLogCountC(elecLog);
	  BigDecimal YXGelecLogSumC = elecLogService.YXGElecLogSumC(elecLog);
	  
	  int TelecLogCountA = elecLogService.TElecLogCountA(elecLog);
	  BigDecimal TelecLogSumA = elecLogService.TElecLogSumA(elecLog);
	  int TelecLogCountC = elecLogService.TElecLogCountC(elecLog);
	  BigDecimal TelecLogSumC = elecLogService.TElecLogSumC(elecLog);

	  
	  int WelecLogCountA = elecLogService.WElecLogCountA(elecLog);
	  BigDecimal WelecLogSumA = elecLogService.WElecLogSumA(elecLog);
	  int WelecLogCountC = elecLogService.WElecLogCountC(elecLog);
	  BigDecimal WelecLogSumC = elecLogService.WElecLogSumC(elecLog);

	  
	  
	  
	  int MheatLogCountA = heatLogService.MHeatLogCountA(heatLog);
	  BigDecimal MheatLogSumA = heatLogService.MHeatLogSumA(heatLog);
	  int MheatLogCountC = heatLogService.MHeatLogCountC(heatLog);
	  BigDecimal MheatLogSumC = heatLogService.MHeatLogSumC(heatLog);

	  int SheatLogCountA = heatLogService.SHeatLogCountA(heatLog);
	  BigDecimal SheatLogSumA = heatLogService.SHeatLogSumA(heatLog);
	  int SheatLogCountC = heatLogService.SHeatLogCountC(heatLog);
	  BigDecimal SheatLogSumC = heatLogService.SHeatLogSumC(heatLog);

	 
	  int waterLogCountA = waterLogService.WaterLogCountA(waterLog);
	  BigDecimal waterLogSumA = waterLogService.WaterLogSumA(waterLog);
	  int waterLogCountC = waterLogService.WaterLogCountC(waterLog);
	  BigDecimal waterLogSumC = waterLogService.WaterLogSumC(waterLog);

	  
	  int MwaterLogCountA = waterLogService.MWaterLogCountA(waterLog);
	  BigDecimal MwaterLogSumA = waterLogService.MWaterLogSumA(waterLog);
	  int MwaterLogCountC = waterLogService.MWaterLogCountC(waterLog);
	  BigDecimal MwaterLogSumC = waterLogService.MWaterLogSumC(waterLog);
	  
	  int SwaterLogCountA = waterLogService.SWaterLogCountA(waterLog);
	  BigDecimal SwaterLogSumA = waterLogService.SWaterLogSumA(waterLog);
	  int SwaterLogCountC = waterLogService.SWaterLogCountC(waterLog);
	  BigDecimal SwaterLogSumC = waterLogService.SWaterLogSumC(waterLog);
	  
	  
	  


	  map.put("MelecLogCountA", MelecLogCountA);
	  map.put("MelecLogSumA", MelecLogSumA);
	  map.put("MelecLogCountC", MelecLogCountC);
	  map.put("MelecLogSumC", MelecLogSumC);

	  map.put("SelecLogCountA", SelecLogCountA);
	  map.put("SelecLogSumA", SelecLogSumA);
	  map.put("SelecLogCountC", SelecLogCountC);
	  map.put("SelecLogSumC", SelecLogSumC);

	  map.put("GelecLogCountA", GelecLogCountA);
	  map.put("GelecLogSumA", GelecLogSumA);
	  map.put("GelecLogCountC", GelecLogCountC);
	  map.put("GelecLogSumC", GelecLogSumC);
	  
	  map.put("YXGelecLogCountA", YXGelecLogCountA);
	  map.put("YXGelecLogSumA", YXGelecLogSumA);
	  map.put("YXGelecLogCountC", YXGelecLogCountC);
	  map.put("YXGelecLogSumC", YXGelecLogSumC);
	  
	  map.put("WelecLogCountA", WelecLogCountA);
	  map.put("WelecLogSumA", WelecLogSumA);
	  map.put("WelecLogCountC", WelecLogCountC);
	  map.put("WelecLogSumC", WelecLogSumC);

	  map.put("TelecLogCountA", TelecLogCountA);
	  map.put("TelecLogSumA", TelecLogSumA);
	  map.put("TelecLogCountC", TelecLogCountC);
	  map.put("TelecLogSumC", TelecLogSumC);

	  map.put("MheatLogCountA", MheatLogCountA);
	  map.put("MheatLogSumA", MheatLogSumA);
	  map.put("MheatLogCountC", MheatLogCountC);
	  map.put("MheatLogSumC", MheatLogSumC);

	  map.put("SheatLogCountA", SheatLogCountA);
	  map.put("SheatLogSumA", SheatLogSumA);
	  map.put("SheatLogCountC", SheatLogCountC);
	  map.put("SheatLogSumC", SheatLogSumC);

	  map.put("waterLogCountA", waterLogCountA);
	  map.put("waterLogSumA", waterLogSumA);
	  map.put("waterLogCountC", waterLogCountC);
	  map.put("waterLogSumC", waterLogSumC);
	  
	  
	  map.put("MwaterLogCountA", MwaterLogCountA);
	  map.put("MwaterLogSumA", MwaterLogSumA);
	  map.put("MwaterLogCountC", MwaterLogCountC);
	  map.put("MwaterLogSumC", MwaterLogSumC);
	  
	  map.put("SwaterLogCountA", SwaterLogCountA);
	  map.put("SwaterLogSumA", SwaterLogSumA);
	  map.put("SwaterLogCountC", SwaterLogCountC);
	  map.put("SwaterLogSumC", SwaterLogSumC);
	  
	  
	  
	  int Wtotal =  MelecLogCountC +  + SelecLogCountC   + YXGelecLogCountC  + MheatLogCountC  + SheatLogCountC   + MwaterLogCountC  + SwaterLogCountC;
	  
		List<BigDecimal> WsumList = new ArrayList<>();
		
		WsumList.add(MelecLogSumC);
		WsumList.add(SelecLogSumC);
		WsumList.add(YXGelecLogSumC);
		WsumList.add(MheatLogSumC);
		WsumList.add(SheatLogSumC);
		WsumList.add(MwaterLogSumC);
		WsumList.add(SwaterLogSumC);
		
		BigDecimal Wsum = new BigDecimal(0);
		for(BigDecimal a:WsumList) {	  	 
			Wsum = Wsum.add(a);  
		}
	  
	  int Xtotal =  MelecLogCountA +  + SelecLogCountA   + YXGelecLogCountA  + MheatLogCountA  + SheatLogCountA   + MwaterLogCountA  + SwaterLogCountA;
	  
		List<BigDecimal> XsumList = new ArrayList<>();
		
		XsumList.add(MelecLogSumA);
		XsumList.add(SelecLogSumA);
		XsumList.add(YXGelecLogSumA);
		XsumList.add(MheatLogSumA);
		XsumList.add(SheatLogSumA);
		XsumList.add(MwaterLogSumA);
		XsumList.add(SwaterLogSumA);
		
		BigDecimal Xsum = new BigDecimal(0);
		for(BigDecimal b:XsumList) {	  	 
			Xsum = Xsum.add(b);  
		}
	  
	  int WJTotal = MelecLogCountA + MelecLogCountC + SelecLogCountA + SelecLogCountC + GelecLogCountA + GelecLogCountC +  WelecLogCountA + WelecLogCountC + TelecLogCountA + TelecLogCountC + MheatLogCountA + MheatLogCountC + SheatLogCountA + SheatLogCountC  + waterLogCountA + waterLogCountC;
	  
		List<BigDecimal> WJSumList = new ArrayList<>();
		
		WJSumList.add(MelecLogSumA);
		WJSumList.add(MelecLogSumC);
		WJSumList.add(SelecLogSumA);
		WJSumList.add(SelecLogSumC);
		WJSumList.add(GelecLogSumA);
		WJSumList.add(GelecLogSumC);
		WJSumList.add(WelecLogSumA);
		WJSumList.add(WelecLogSumC);
		WJSumList.add(TelecLogSumA);
		WJSumList.add(TelecLogSumC);
		WJSumList.add(MheatLogSumA);
		WJSumList.add(MheatLogSumC);
		WJSumList.add(SheatLogSumA);
		WJSumList.add(SheatLogSumC);
		WJSumList.add(waterLogSumA);
		WJSumList.add(waterLogSumC);
		
		BigDecimal WJSum = new BigDecimal(0);
		for(BigDecimal c:WJSumList) {	  	 
			WJSum = WJSum.add(c);  
		}
		
	  int YXTotal = MelecLogCountA + MelecLogCountC + SelecLogCountA + SelecLogCountC  + YXGelecLogCountA + YXGelecLogCountC + MheatLogCountA + MheatLogCountC + SheatLogCountA + SheatLogCountC + MwaterLogCountA + MwaterLogCountC + SwaterLogCountA + SwaterLogCountC;
	  
		List<BigDecimal> YXSumList = new ArrayList<>();
		
		YXSumList.add(MelecLogSumA);
		YXSumList.add(MelecLogSumC);
		YXSumList.add(SelecLogSumA);
		YXSumList.add(SelecLogSumC);
		YXSumList.add(YXGelecLogSumA);
		YXSumList.add(YXGelecLogSumC);
		YXSumList.add(MheatLogSumA);
		YXSumList.add(MheatLogSumC);
		YXSumList.add(SheatLogSumA);
		YXSumList.add(SheatLogSumC);
		YXSumList.add(MwaterLogSumA);
		YXSumList.add(MwaterLogSumC);
		YXSumList.add(SwaterLogSumA);
		YXSumList.add(SwaterLogSumC);
		
		BigDecimal YXSum = new BigDecimal(0);
		for(BigDecimal d:YXSumList) {	  	 
			YXSum = YXSum.add(d);  
		}
	
	  
	  map.put("WJTotal", WJTotal);
	  map.put("WJSum", WJSum);
	  map.put("YXTotal", YXTotal);
	  map.put("YXSum", YXSum);
	  
	  map.put("Wtotal", Wtotal);
	  map.put("Wsum", Wsum);
	  map.put("Xtotal", Xtotal);
	  map.put("Xsum", Xsum);
	  
	  return map;
  }
  

    @ResponseBody
	@GetMapping("/exportDD")
    public void exportDD(ElecLogVo elecLog,HeatLogVo heatLog,WaterLogVo waterLog,HttpServletResponse response){

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			OutputStream out = response.getOutputStream();
			
			String[] headers1 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","电费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			String[] headers2 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","暖费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			String[] headers3 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","水费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
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
				arrObj[9] = elecLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(elecLogList.get(i).getUpdateTime());
				arrObj[11] = elecLogList.get(i).getUpdateBy();
				arrObj[12] = elecLogList.get(i).getRemark();
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
				arrObj[9] = heatLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(heatLogList.get(i).getUpdateTime());
				arrObj[11] = heatLogList.get(i).getUpdateBy();
				arrObj[12] = heatLogList.get(i).getRemark();
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
				arrObj[9] = waterLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(waterLogList.get(i).getUpdateTime());
				arrObj[11] = waterLogList.get(i).getUpdateBy();
				arrObj[12] = waterLogList.get(i).getRemark();
				
				
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
