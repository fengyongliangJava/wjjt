

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
import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.service.HeatService;
import com.bootdo.heat.vo.HeatVo;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.vo.HeatLogVo;


 
@Controller
@RequestMapping("/charts/tableHeatYY")
public class tableHeatYYController {
	
	
	
	@Autowired private HeatService heatService;
	@Autowired private HeatLogService heatLogService;
	

	
	@GetMapping()
	@RequiresPermissions("charts:tableYY")
	public String tableHeatYY(){
	    return "charts/tableHeatYY";
	}
	
	
	@ResponseBody
	@PostMapping("/tableHeatYY")
	public  Map<String,Object>  tableHeatYY(HeatVo heat,HeatLogVo heatLog){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		String createTime = heat.getCreateTime();
		int year = Integer.parseInt(createTime) - 1;
		String oldYear = String.valueOf(year);
		String oldcreateTime = oldYear+"-"+"12";
		
		
		heat.setHeatType("1");
		heat.setCreateTime(oldcreateTime);
		
	
		
		
	//年初余额
		int MPreHeatCountYYOld = heatService.PreHeatCountYYOld(heat);
		BigDecimal MPreHeatSumYYOld = heatService.PreHeatSumYYOld(heat);
		int MOweHeatCountYYOld = heatService.OweHeatCountYYOld(heat);
		BigDecimal MOweHeatSumYYOld = heatService.OweHeatSumYYOld(heat);
		
		int MTotalhuOld = MPreHeatCountYYOld + MOweHeatCountYYOld;
		BigDecimal MTotalyuOld = MPreHeatSumYYOld.add(MOweHeatSumYYOld);
		
		map.put("MPreHeatCountYYOld",MPreHeatCountYYOld);
		map.put("MPreHeatSumYYOld",MPreHeatSumYYOld);
		map.put("MOweHeatCountYYOld",MOweHeatCountYYOld);
		map.put("MOweHeatSumYYOld",MOweHeatSumYYOld);

		map.put("MTotalhuOld",MTotalhuOld);
		map.put("MTotalyuOld",MTotalyuOld);
		
	//累计应收
		heat.setCreateTime(createTime);
		
		
		BigDecimal MHeatAreaYY = heatService.HeatAreaYY(heat);
		map.put("MHeatAreaYY",MHeatAreaYY);
		
		int MHeatCostYYCount = heatService.HeatCostYYCount(heat);
		BigDecimal MHeatCostYYSum = heatService.HeatCostYYSum(heat);
		map.put("MHeatCostYYCount",MHeatCostYYCount);
		map.put("MHeatCostYYSum",MHeatCostYYSum);
		
	//累计实收
		int MHeatLogYYCount = heatService.HeatLogYYCount(heat);
		BigDecimal MHeatLogYYSum = heatService.HeatLogYYSum(heat);
		map.put("MHeatLogYYCount",MHeatLogYYCount);
		map.put("MHeatLogYYSum",MHeatLogYYSum);

		
   //期末余额	
		heat.setCreateTime(createTime);
		
		int MPreHeatCountYYNew =heatService.PreHeatCountYYNew(heat);
		BigDecimal MPreHeatSumYYNew =heatService.PreHeatSumYYNew(heat);
		int MOweHeatCountYYNew =heatService.OweHeatCountYYNew(heat);
		BigDecimal MOweHeatSumYYNew =heatService.OweHeatSumYYNew(heat);

		int MTotalhuNew = MPreHeatCountYYNew + MOweHeatCountYYNew;
		BigDecimal MTotalyuNew = MPreHeatSumYYNew.add(MOweHeatSumYYNew);

		map.put("MPreHeatCountYYNew",MPreHeatCountYYNew);
		map.put("MPreHeatSumYYNew",MPreHeatSumYYNew);
		map.put("MOweHeatCountYYNew",MOweHeatCountYYNew);
		map.put("MOweHeatSumYYNew",MOweHeatSumYYNew);

		map.put("MTotalhuNew",MTotalhuNew);
		map.put("MTotalyuNew",MTotalyuNew);		
		
		
		
		
		
		
		
		
		heat.setHeatType("2");
		heat.setCreateTime(oldcreateTime);
		
	//年初余额
		int SPreHeatCountYYOld =heatService.PreHeatCountYYOld(heat);
		BigDecimal SPreHeatSumYYOld =heatService.PreHeatSumYYOld(heat);
		int SOweHeatCountYYOld =heatService.OweHeatCountYYOld(heat);
		BigDecimal SOweHeatSumYYOld =heatService.OweHeatSumYYOld(heat);
		
		int STotalhuOld = SPreHeatCountYYOld + SOweHeatCountYYOld;
		BigDecimal STotalyuOld = SPreHeatSumYYOld.add(SOweHeatSumYYOld);
		
		map.put("SPreHeatCountYYOld",SPreHeatCountYYOld);
		map.put("SPreHeatSumYYOld",SPreHeatSumYYOld);
		map.put("SOweHeatCountYYOld",SOweHeatCountYYOld);
		map.put("SOweHeatSumYYOld",SOweHeatSumYYOld);

		map.put("STotalhuOld",STotalhuOld);
		map.put("STotalyuOld",STotalyuOld);
		
	//累计应收
		heat.setCreateTime(createTime);
		
		BigDecimal SHeatAreaYY = heatService.HeatAreaYY(heat);
		map.put("SHeatAreaYY",SHeatAreaYY);	
		
		int SHeatCostYYCount = heatService.HeatCostYYCount(heat);
		BigDecimal SHeatCostYYSum = heatService.HeatCostYYSum(heat);
		map.put("SHeatCostYYCount",SHeatCostYYCount);
		map.put("SHeatCostYYSum",SHeatCostYYSum);
		
	//累计实收
		int SHeatLogYYCount = heatService.HeatLogYYCount(heat);
		BigDecimal SHeatLogYYSum = heatService.HeatLogYYSum(heat);
		map.put("SHeatLogYYCount",SHeatLogYYCount);
		map.put("SHeatLogYYSum",SHeatLogYYSum);

		
   //期末余额	
		heat.setCreateTime(createTime);
		
		int SPreHeatCountYYNew =heatService.PreHeatCountYYNew(heat);
		BigDecimal SPreHeatSumYYNew =heatService.PreHeatSumYYNew(heat);
		int SOweHeatCountYYNew =heatService.OweHeatCountYYNew(heat);
		BigDecimal SOweHeatSumYYNew =heatService.OweHeatSumYYNew(heat);

		int STotalhuNew = SPreHeatCountYYNew + SOweHeatCountYYNew;
		BigDecimal STotalyuNew = SPreHeatSumYYNew.add(SOweHeatSumYYNew);

		map.put("SPreHeatCountYYNew",SPreHeatCountYYNew);
		map.put("SPreHeatSumYYNew",SPreHeatSumYYNew);
		map.put("SOweHeatCountYYNew",SOweHeatCountYYNew);
		map.put("SOweHeatSumYYNew",SOweHeatSumYYNew);

		map.put("STotalhuNew",STotalhuNew);
		map.put("STotalyuNew",STotalyuNew);		
		
		
		
	
		
		heat.setHeatType("3");
		heat.setCreateTime(oldcreateTime);
		
	//年初余额
		int GMPreHeatCountYYOld =heatService.PreHeatCountYYOld(heat);
		BigDecimal GMPreHeatSumYYOld =heatService.PreHeatSumYYOld(heat);
		int GMOweHeatCountYYOld =heatService.OweHeatCountYYOld(heat);
		BigDecimal GMOweHeatSumYYOld =heatService.OweHeatSumYYOld(heat);
		
		int GMTotalhuOld = GMPreHeatCountYYOld + GMOweHeatCountYYOld;
		BigDecimal GMTotalyuOld = GMPreHeatSumYYOld.add(GMOweHeatSumYYOld);
		
		map.put("GMPreHeatCountYYOld",GMPreHeatCountYYOld);
		map.put("GMPreHeatSumYYOld",GMPreHeatSumYYOld);
		map.put("GMOweHeatCountYYOld",GMOweHeatCountYYOld);
		map.put("GMOweHeatSumYYOld",GMOweHeatSumYYOld);

		map.put("GMTotalhuOld",GMTotalhuOld);
		map.put("GMTotalyuOld",GMTotalyuOld);
		
	//累计应收
		heat.setCreateTime(createTime);
		
		BigDecimal GMHeatAreaYY =heatService.HeatAreaYY(heat);
		map.put("GMHeatAreaYY",GMHeatAreaYY);
		
		int GMHeatCostYYCount = heatService.HeatCostYYCount(heat);
		BigDecimal GMHeatCostYYSum = heatService.HeatCostYYSum(heat);
		map.put("GMHeatCostYYCount",GMHeatCostYYCount);
		map.put("GMHeatCostYYSum",GMHeatCostYYSum);
		
	//累计实收
		int GMHeatLogYYCount = heatService.HeatLogYYCount(heat);
		BigDecimal GMHeatLogYYSum = heatService.HeatLogYYSum(heat);
		map.put("GMHeatLogYYCount",GMHeatLogYYCount);
		map.put("GMHeatLogYYSum",GMHeatLogYYSum);

		
   //期末余额	
		heat.setCreateTime(createTime);
		
		int GMPreHeatCountYYNew =heatService.PreHeatCountYYNew(heat);
		BigDecimal GMPreHeatSumYYNew =heatService.PreHeatSumYYNew(heat);
		int GMOweHeatCountYYNew =heatService.OweHeatCountYYNew(heat);
		BigDecimal GMOweHeatSumYYNew =heatService.OweHeatSumYYNew(heat);

		int GMTotalhuNew = GMPreHeatCountYYNew + GMOweHeatCountYYNew;
		BigDecimal GMTotalyuNew = GMPreHeatSumYYNew.add(GMOweHeatSumYYNew);

		map.put("GMPreHeatCountYYNew",GMPreHeatCountYYNew);
		map.put("GMPreHeatSumYYNew",GMPreHeatSumYYNew);
		map.put("GMOweHeatCountYYNew",GMOweHeatCountYYNew);
		map.put("GMOweHeatSumYYNew",GMOweHeatSumYYNew);

		map.put("GMTotalhuNew",GMTotalhuNew);
		map.put("GMTotalyuNew",GMTotalyuNew);		
		
		
		
		
		
		
		
		heat.setHeatType("4");
		heat.setCreateTime(oldcreateTime);
		
	//年初余额
		int GSPreHeatCountYYOld =heatService.PreHeatCountYYOld(heat);
		BigDecimal GSPreHeatSumYYOld =heatService.PreHeatSumYYOld(heat);
		int GSOweHeatCountYYOld =heatService.OweHeatCountYYOld(heat);
		BigDecimal GSOweHeatSumYYOld =heatService.OweHeatSumYYOld(heat);
		
		int GSTotalhuOld = GSPreHeatCountYYOld + GSOweHeatCountYYOld;
		BigDecimal GSTotalyuOld = GSPreHeatSumYYOld.add(GSOweHeatSumYYOld);
		
		map.put("GSPreHeatCountYYOld",GSPreHeatCountYYOld);
		map.put("GSPreHeatSumYYOld",GSPreHeatSumYYOld);
		map.put("GSOweHeatCountYYOld",GSOweHeatCountYYOld);
		map.put("GSOweHeatSumYYOld",GSOweHeatSumYYOld);

		map.put("GSTotalhuOld",GSTotalhuOld);
		map.put("GSTotalyuOld",GSTotalyuOld);
		
	//累计应收
		heat.setCreateTime(createTime);
		
		BigDecimal GSHeatAreaYY = heatService.HeatAreaYY(heat);
		map.put("GSHeatAreaYY",GSHeatAreaYY);	
		
		int GSHeatCostYYCount = heatService.HeatCostYYCount(heat);
		BigDecimal GSHeatCostYYSum = heatService.HeatCostYYSum(heat);
		map.put("GSHeatCostYYCount",GSHeatCostYYCount);
		map.put("GSHeatCostYYSum",GSHeatCostYYSum);
		
	//累计实收
		int GSHeatLogYYCount = heatService.HeatLogYYCount(heat);
		BigDecimal GSHeatLogYYSum = heatService.HeatLogYYSum(heat);
		map.put("GSHeatLogYYCount",GSHeatLogYYCount);
		map.put("GSHeatLogYYSum",GSHeatLogYYSum);

		
   //期末余额	
		heat.setCreateTime(createTime);
		
		int GSPreHeatCountYYNew =heatService.PreHeatCountYYNew(heat);
		BigDecimal GSPreHeatSumYYNew =heatService.PreHeatSumYYNew(heat);
		int GSOweHeatCountYYNew =heatService.OweHeatCountYYNew(heat);
		BigDecimal GSOweHeatSumYYNew =heatService.OweHeatSumYYNew(heat);

		int GSTotalhuNew = GSPreHeatCountYYNew + GSOweHeatCountYYNew;
		BigDecimal GSTotalyuNew = GSPreHeatSumYYNew.add(GSOweHeatSumYYNew);

		map.put("GSPreHeatCountYYNew",GSPreHeatCountYYNew);
		map.put("GSPreHeatSumYYNew",GSPreHeatSumYYNew);
		map.put("GSOweHeatCountYYNew",GSOweHeatCountYYNew);
		map.put("GSOweHeatSumYYNew",GSOweHeatSumYYNew);

		map.put("GSTotalhuNew",GSTotalhuNew);
		map.put("GSTotalyuNew",GSTotalyuNew);		
		
		


		//五九合计
		if(heat.getUserOrg().equals("2")) {
			

			List<BigDecimal> AreaList = new ArrayList<>();
			AreaList.add(MHeatAreaYY);
			AreaList.add(SHeatAreaYY);
			AreaList.add(GMHeatAreaYY);
			AreaList.add(GSHeatAreaYY);
			
			BigDecimal AreaTotalYY = new BigDecimal(0);
			for(BigDecimal a:AreaList) {	  	 
				AreaTotalYY = AreaTotalYY.add(a);  
			}
			map.put("AreaTotalYY", AreaTotalYY);
			
			//合计年初
			int TotalPrehuOld = MPreHeatCountYYOld + SPreHeatCountYYOld + GMPreHeatCountYYOld + GSPreHeatCountYYOld;
			
			List<BigDecimal> PreyList = new ArrayList<>();
			PreyList.add(MPreHeatSumYYOld);
			PreyList.add(SPreHeatSumYYOld);
			PreyList.add(GMPreHeatSumYYOld);
			PreyList.add(GSPreHeatSumYYOld);
			
			BigDecimal TotalPreyuOld = new BigDecimal(0);
			for(BigDecimal b:PreyList) {	  	 
				TotalPreyuOld = TotalPreyuOld.add(b);  
			}

			int TotalOwehuOld = MOweHeatCountYYOld + SOweHeatCountYYOld + GMOweHeatCountYYOld + GSOweHeatCountYYOld;
			
			List<BigDecimal> OweList = new ArrayList<>();
			OweList.add(MOweHeatSumYYOld);
			OweList.add(SOweHeatSumYYOld);
			OweList.add(GMOweHeatSumYYOld);
			OweList.add(GSOweHeatSumYYOld);
			
			BigDecimal TotalOweyuOld = new BigDecimal(0);
			for(BigDecimal c:OweList) {	  	 
				TotalOweyuOld = TotalOweyuOld.add(c);  
			}
			
			int TotalAllhuOld = MTotalhuOld + STotalhuOld + GMTotalhuOld + GSTotalhuOld;
			
			
			List<BigDecimal> AllyuList = new ArrayList<>();
			AllyuList.add(MTotalyuOld);
			AllyuList.add(STotalyuOld);
			AllyuList.add(GMTotalyuOld);
			AllyuList.add(GSTotalyuOld);
			
			BigDecimal TotalAllyuOld = new BigDecimal(0);
			for(BigDecimal d: AllyuList) {	  	 
				TotalAllyuOld = TotalAllyuOld.add(d);  
			}
			
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MHeatCostYYCount + SHeatCostYYCount + GMHeatCostYYCount + GSHeatCostYYCount;
			
			
			List<BigDecimal> CostList = new ArrayList<>();
			CostList.add(MHeatCostYYSum);
			CostList.add(SHeatCostYYSum);
			CostList.add(GMHeatCostYYSum);
			CostList.add(GSHeatCostYYSum);
			
			BigDecimal TotalCostyu = new BigDecimal(0);
			for(BigDecimal d: CostList) {	  	 
				TotalCostyu = TotalCostyu.add(d);  
			}

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MHeatLogYYCount + SHeatLogYYCount + GMHeatLogYYCount + GSHeatLogYYCount;
			
			
			List<BigDecimal> LogList = new ArrayList<>();
			LogList.add(MHeatLogYYSum);
			LogList.add(SHeatLogYYSum);
			LogList.add(GMHeatLogYYSum);
			LogList.add(GSHeatLogYYSum);
			
			BigDecimal TotalLogyu = new BigDecimal(0);
			for(BigDecimal e: LogList) {	  	 
				TotalLogyu = TotalLogyu.add(e);  
			}

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreHeatCountYYNew + SPreHeatCountYYNew + GMPreHeatCountYYNew + GSPreHeatCountYYNew;
			
			
			List<BigDecimal> PreListNew = new ArrayList<>();
			PreListNew.add(MPreHeatSumYYNew);
			PreListNew.add(SPreHeatSumYYNew);
			PreListNew.add(GMPreHeatSumYYNew);
			PreListNew.add(GSPreHeatSumYYNew);
			
			BigDecimal  TotalPreyuNew = new BigDecimal(0);
			for(BigDecimal e: PreListNew) {	  	 
				TotalPreyuNew = TotalPreyuNew.add(e);  
			}
			int TotalOwehuNew = MOweHeatCountYYNew + SOweHeatCountYYNew + GMOweHeatCountYYNew + GSOweHeatCountYYNew;
			
			List<BigDecimal> OweListNew = new ArrayList<>();
			OweListNew.add(MOweHeatSumYYNew);
			OweListNew.add(SOweHeatSumYYNew);
			OweListNew.add(GMOweHeatSumYYNew);
			OweListNew.add(GSOweHeatSumYYNew);
			
			BigDecimal TotalOweyuNew = new BigDecimal(0);
			for(BigDecimal f: OweListNew) {	  	 
				TotalOweyuNew = TotalOweyuNew.add(f);  
			}
			
			int TotalAllhuNew = MTotalhuNew + STotalhuNew + GMTotalhuNew + GSTotalhuNew;
			
			List<BigDecimal> AllyuNewList = new ArrayList<>();
			 AllyuNewList.add(MTotalyuNew);
			 AllyuNewList.add(STotalyuNew);
			 AllyuNewList.add(GMTotalyuNew);
			 AllyuNewList.add(GSTotalyuNew);
			
			BigDecimal  TotalAllyuNew = new BigDecimal(0);
			for(BigDecimal f: AllyuNewList) {	  	 
				 TotalAllyuNew =  TotalAllyuNew.add(f);  
			}
			
			map.put("TotalPrehuNew", TotalPrehuNew);
			map.put("TotalPreyuNew", TotalPreyuNew);
			map.put("TotalOwehuNew", TotalOwehuNew);
			map.put("TotalOweyuNew", TotalOweyuNew);
			
			map.put("TotalAllhuNew", TotalAllhuNew);
			map.put("TotalAllyuNew", TotalAllyuNew);
			
			map.put("type", 2);
		}


		//亚星合计
		if(heat.getUserOrg().equals("3")) {
			
			
			BigDecimal  AreaTotalYY = MHeatAreaYY.add(SHeatAreaYY);
			map.put("AreaTotalYY", AreaTotalYY);
			
			//合计年初
			int TotalPrehuOld = MPreHeatCountYYOld + SPreHeatCountYYOld;
			BigDecimal TotalPreyuOld = MPreHeatSumYYOld.add(SPreHeatSumYYOld);
			int TotalOwehuOld = MOweHeatCountYYOld + SOweHeatCountYYOld;
			BigDecimal TotalOweyuOld = MOweHeatSumYYOld.add(SOweHeatSumYYOld);
			
			int TotalAllhuOld = MTotalhuOld + STotalhuOld;
			BigDecimal TotalAllyuOld = MTotalyuOld.add(STotalyuOld);
		
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MHeatCostYYCount + SHeatCostYYCount;
			BigDecimal TotalCostyu = MHeatCostYYSum.add(SHeatCostYYSum);

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MHeatLogYYCount + SHeatLogYYCount;
			BigDecimal TotalLogyu = MHeatLogYYSum.add(SHeatLogYYSum);

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreHeatCountYYNew + SPreHeatCountYYNew;
			BigDecimal TotalPreyuNew = MPreHeatSumYYNew.add(SPreHeatSumYYNew);
			int TotalOwehuNew = MOweHeatCountYYNew + SOweHeatCountYYNew;
			BigDecimal TotalOweyuNew = MOweHeatSumYYNew.add(SOweHeatSumYYNew);
			
			int TotalAllhuNew = MTotalhuNew + STotalhuNew;
			BigDecimal TotalAllyuNew = MTotalyuNew.add(STotalyuNew);
			
			map.put("TotalPrehuNew", TotalPrehuNew);
			map.put("TotalPreyuNew", TotalPreyuNew);
			map.put("TotalOwehuNew", TotalOwehuNew);
			map.put("TotalOweyuNew", TotalOweyuNew);
			
			map.put("TotalAllhuNew", TotalAllhuNew);
			map.put("TotalAllyuNew", TotalAllyuNew);
			
			map.put("type", 3);
		}

		
		

		
		
		
		//查询单价
		List<HeatPriceDO> heatPriceDO = new ArrayList<>();
		heatPriceDO = heatService.HeatPrice();
		BigDecimal priceTotal = new BigDecimal(0);
		//判断
		for(int i=0 ; i<heatPriceDO.size();i++){
			if(heatPriceDO.get(i).getHeatOrg().equals("3")&&heat.getUserOrg().equals("3")){
			
				BigDecimal MHeatPrice = heatPriceDO.get(i).getmHeat();
				map.put("MHeatPrice",MHeatPrice);
		
				BigDecimal SHeatPrice = heatPriceDO.get(i).getsHeat();
				map.put("SHeatPrice",SHeatPrice);
				//合计
				priceTotal =MHeatPrice.add(SHeatPrice);
				map.put("PriceTotal",priceTotal);
			}
			if(heatPriceDO.get(i).getHeatOrg().equals("2")&&heat.getUserOrg().equals("2")){
	
				BigDecimal MHeatPrice = heatPriceDO.get(i).getmHeat();
				map.put("MHeatPrice",MHeatPrice);

				BigDecimal SHeatPrice = heatPriceDO.get(i).getsHeat();
				map.put("SHeatPrice",SHeatPrice);

				BigDecimal GMHeatPrice = heatPriceDO.get(i).getGmHeat();
				map.put("GMHeatPrice",GMHeatPrice);

				BigDecimal GSHeatPrice = heatPriceDO.get(i).getGsHeat();
				map.put("GSHeatPrice",GSHeatPrice);
				//合计
				
				List<BigDecimal> priceTotalList = new ArrayList<>();
				priceTotalList.add(MHeatPrice);
				priceTotalList.add(SHeatPrice);
				priceTotalList.add(GMHeatPrice);
				priceTotalList.add(GSHeatPrice);
		
				for(BigDecimal g: priceTotalList) {	  	 
					priceTotal =  priceTotal.add(g);  
				}
				
				map.put("PriceTotal",priceTotal);
			}
		}
		
		
		return map;
		
	}
	
	
	

	
	
	  @ResponseBody
	  @GetMapping("/exportHeatYY")
	  public void exportHeatYY(HeatVo heat,HeatLogVo heatLog,HttpServletResponse response){
	  
		  
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				
				OutputStream out = response.getOutputStream();
				
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","取暖类型","用户暖价","取暖面积","用户暖费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","取暖类型","用户暖价","取暖面积","用户暖费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","暖费类型","缴费金额","缴费时间","收费时间","收费人","更新时间","更新人","用户备注"};
				
				String createTime = heat.getCreateTime();
				
				String userOrg = "";
				if ("2".equals(heat.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(heat.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				String userType = "";
				if ("A".equals(heat.getUserType())) {
					userType =   "现金缴费";
				} else if ("B".equals(heat.getUserType())) {
					userType =   "工资代扣";
				} else if ("C".equals(heat.getUserType())) {
					userType =   "微信支付";
				} else if ("D".equals(heat.getUserType())) {
					userType =   "银行转账";
				}
				
				String userId = heat.getUserId();
				
				List<HeatVo> heatPreList = heatService.exprotHeatPreYY(heat);
				List<Object[]> heatPredataList = new ArrayList<Object[]>();
				for(int i = 0;i < heatPreList.size(); i++ ) {
					Object[] arrObj = new Object[headers1.length];
					arrObj[0] = heatPreList.get(i).getId();
					arrObj[1] = heatPreList.get(i).getUserId();
					arrObj[2] = heatPreList.get(i).getUserName();
					arrObj[3] = heatPreList.get(i).getUserOrgName();
					if ("A".equals(heatPreList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(heatPreList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(heatPreList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(heatPreList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = heatPreList.get(i).getUserTell();
					arrObj[6] = heatPreList.get(i).getWagesId();
					if ("1".equals(heatPreList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(heatPreList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(heatPreList.get(i).getHeatType())) {
						arrObj[8] =   "民用暖";
					} else if ("2".equals(heatPreList.get(i).getHeatType())) {
						arrObj[8] =   "商业暖";
					}else if ("8".equals(heatPreList.get(i).getHeatType())) {
						arrObj[5] =   "工业民";
					}else if ("4".equals(heatPreList.get(i).getHeatType())) {
						arrObj[8] =   "工业商";
					}
					arrObj[9] = heatPreList.get(i).getHeatPrice();
					arrObj[10] = heatPreList.get(i).getHeatArea();
					arrObj[11] = heatPreList.get(i).getHeatCost();
					arrObj[12] = heatPreList.get(i).getHeatSum();
					arrObj[13] = heatPreList.get(i).getCreateTime();
					arrObj[14] = heatPreList.get(i).getCreateBy();
					arrObj[15] = formatter.format(heatPreList.get(i).getUpdateTime());
					arrObj[16] = heatPreList.get(i).getUpdateBy();
					arrObj[17] = heatPreList.get(i).getRemark();
					heatPredataList.add(arrObj);
				}
				
				List<HeatVo> heatOweList = heatService.exprotHeatOweYY(heat);
				List<Object[]> heatOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < heatOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers2.length];
					arrObj[0] = heatOweList.get(i).getId();
					arrObj[1] = heatOweList.get(i).getUserId();
					arrObj[2] = heatOweList.get(i).getUserName();
					arrObj[3] = heatOweList.get(i).getUserOrgName();
					if ("A".equals(heatOweList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(heatOweList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(heatOweList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(heatOweList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = heatOweList.get(i).getUserTell();
					arrObj[6] = heatOweList.get(i).getWagesId();
					if ("1".equals(heatOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(heatOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "民用暖";
					} else if ("2".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "商业暖";
					}else if ("8".equals(heatOweList.get(i).getHeatType())) {
						arrObj[5] =   "工业民";
					}else if ("4".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "工业商";
					}
					arrObj[9] = heatOweList.get(i).getHeatPrice();
					arrObj[10] = heatOweList.get(i).getHeatArea();
					arrObj[11] = heatOweList.get(i).getHeatCost();
					arrObj[12] = heatOweList.get(i).getHeatSum();
					arrObj[13] = heatOweList.get(i).getCreateTime();
					arrObj[14] = heatOweList.get(i).getCreateBy();
					arrObj[15] = formatter.format(heatOweList.get(i).getUpdateTime());
					arrObj[16] = heatOweList.get(i).getUpdateBy();
					arrObj[17] = heatOweList.get(i).getRemark();
					heatOwedataList.add(arrObj);
				}
				
				
				
				List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogYY(heatLog);
				List<Object[]> heatLogdataList = new ArrayList<Object[]>();
				for(int i = 0;i < heatLogList.size(); i++ ) {
					Object[] arrObj = new Object[headers3.length];
					
					arrObj[0] =heatLogList.get(i).getId();
					arrObj[1] =heatLogList.get(i).getUserId();
					arrObj[2] =heatLogList.get(i).getUserName();
					arrObj[3] = heatLogList.get(i).getUserOrgName();
					if ("A".equals(heatLogList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(heatLogList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(heatLogList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(heatLogList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}

					if ("1".equals(heatLogList.get(i).getHeatType())) {
						arrObj[5] =   "民用暖";
					} else if ("2".equals(heatLogList.get(i).getHeatType())) {
						arrObj[5] =   "商业暖";
					}else if ("8".equals(heatLogList.get(i).getHeatType())) {
						arrObj[5] =   "工业民";
					}else if ("4".equals(heatLogList.get(i).getHeatType())) {
						arrObj[5] =   "工业商";
					}
					arrObj[6] = heatLogList.get(i).getHeatMoney();
					arrObj[7] =  formatter.format(heatLogList.get(i).getMoneyDate());
					arrObj[8] = heatLogList.get(i).getCreateTime();
					arrObj[9] = heatLogList.get(i).getCreateBy();
					arrObj[10] = formatter.format(heatLogList.get(i).getUpdateTime());
					arrObj[11] = heatLogList.get(i).getUpdateBy();
					arrObj[12] = heatLogList.get(i).getRemark();
					heatLogdataList.add(arrObj);
				}


				String title =  "暖费全年报表.xlsx";

				String oneheaders1 = createTime+userOrg+userType+userId+"暖费全年预存表";
				String oneheaders2 = createTime+userOrg+userType+userId+"暖费全年欠费表";
				String oneheaders3 = createTime+userOrg+userType+userId+"暖费全年实收表";
				
				
				String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
				response.setContentType("octets/stream");
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				
				ExportExcelSheet ex1  = new ExportExcelSheet("预存表", oneheaders1, headers1, heatPredataList);
				ExportExcelSheet ex2  = new ExportExcelSheet("欠费表", oneheaders2, headers2, heatOwedataList);
				ExportExcelSheet ex3  = new ExportExcelSheet("实收表", oneheaders3, headers3, heatLogdataList);

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

