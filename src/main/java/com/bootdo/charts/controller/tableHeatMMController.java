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
@RequestMapping("/charts/tableHeatMM")
public class tableHeatMMController {
	
	

	@Autowired private HeatService heatService;
	@Autowired private HeatLogService heatLogService;
	
	
	@GetMapping()
	public String tableHeatMM(){
	    return "charts/tableHeatMM";
	}
	
	


	@ResponseBody
	@PostMapping("/tableHeatMM")
	public  Map<String,Object>  tableHeatMM(HeatVo heat,HeatLogVo heatLog){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		
		
		heat.setHeatType("1");
	//年初余额
		int MPreHeatCountMMOld = heatService.PreHeatCountMMOld(heat);
		BigDecimal MPreHeatSumMMOld = heatService.PreHeatSumMMOld(heat);
		int MOweHeatCountMMOld = heatService.OweHeatCountMMOld(heat);
		BigDecimal MOweHeatSumMMOld = heatService.OweHeatSumMMOld(heat);
		
		int MTotalhuOld = MPreHeatCountMMOld + MOweHeatCountMMOld;
		BigDecimal MTotalyuOld = MPreHeatSumMMOld.add(MOweHeatSumMMOld);
		
		map.put("MPreHeatCountMMOld",MPreHeatCountMMOld);
		map.put("MPreHeatSumMMOld",MPreHeatSumMMOld);
		map.put("MOweHeatCountMMOld",MOweHeatCountMMOld);
		map.put("MOweHeatSumMMOld",MOweHeatSumMMOld);

		map.put("MTotalhuOld",MTotalhuOld);
		map.put("MTotalyuOld",MTotalyuOld);
		
	//累计应收
		
		
		
		BigDecimal MHeatAreaMM = heatService.HeatAreaMM(heat);
		map.put("MHeatAreaMM",MHeatAreaMM);
		
		int MHeatCostMMCount = heatService.HeatCostMMCount(heat);
		BigDecimal MHeatCostMMSum = heatService.HeatCostMMSum(heat);
		map.put("MHeatCostMMCount",MHeatCostMMCount);
		map.put("MHeatCostMMSum",MHeatCostMMSum);
		
	//累计实收
		int MHeatLogMMCount = heatService.HeatLogMMCount(heat);
		BigDecimal MHeatLogMMSum = heatService.HeatLogMMSum(heat);
		map.put("MHeatLogMMCount",MHeatLogMMCount);
		map.put("MHeatLogMMSum",MHeatLogMMSum);

		
   //期末余额	
		
		
		int MPreHeatCountMMNew =heatService.PreHeatCountMMNew(heat);
		BigDecimal MPreHeatSumMMNew =heatService.PreHeatSumMMNew(heat);
		int MOweHeatCountMMNew =heatService.OweHeatCountMMNew(heat);
		BigDecimal MOweHeatSumMMNew =heatService.OweHeatSumMMNew(heat);

		int MTotalhuNew = MPreHeatCountMMNew + MOweHeatCountMMNew;
		BigDecimal MTotalyuNew = MPreHeatSumMMNew.add(MOweHeatSumMMNew);

		map.put("MPreHeatCountMMNew",MPreHeatCountMMNew);
		map.put("MPreHeatSumMMNew",MPreHeatSumMMNew);
		map.put("MOweHeatCountMMNew",MOweHeatCountMMNew);
		map.put("MOweHeatSumMMNew",MOweHeatSumMMNew);

		map.put("MTotalhuNew",MTotalhuNew);
		map.put("MTotalyuNew",MTotalyuNew);		
		
		
		
		
		
		
		
		
		heat.setHeatType("2");
		
	//年初余额
		int SPreHeatCountMMOld =heatService.PreHeatCountMMOld(heat);
		BigDecimal SPreHeatSumMMOld =heatService.PreHeatSumMMOld(heat);
		int SOweHeatCountMMOld =heatService.OweHeatCountMMOld(heat);
		BigDecimal SOweHeatSumMMOld =heatService.OweHeatSumMMOld(heat);
		
		int STotalhuOld = SPreHeatCountMMOld + SOweHeatCountMMOld;
		BigDecimal STotalyuOld = SPreHeatSumMMOld.add(SOweHeatSumMMOld);
		
		map.put("SPreHeatCountMMOld",SPreHeatCountMMOld);
		map.put("SPreHeatSumMMOld",SPreHeatSumMMOld);
		map.put("SOweHeatCountMMOld",SOweHeatCountMMOld);
		map.put("SOweHeatSumMMOld",SOweHeatSumMMOld);

		map.put("STotalhuOld",STotalhuOld);
		map.put("STotalyuOld",STotalyuOld);
		
	//累计应收
		
		
		BigDecimal SHeatAreaMM = heatService.HeatAreaMM(heat);
		map.put("SHeatAreaMM",SHeatAreaMM);	
		
		int SHeatCostMMCount = heatService.HeatCostMMCount(heat);
		BigDecimal SHeatCostMMSum = heatService.HeatCostMMSum(heat);
		map.put("SHeatCostMMCount",SHeatCostMMCount);
		map.put("SHeatCostMMSum",SHeatCostMMSum);
		
	//累计实收
		int SHeatLogMMCount = heatService.HeatLogMMCount(heat);
		BigDecimal SHeatLogMMSum = heatService.HeatLogMMSum(heat);
		map.put("SHeatLogMMCount",SHeatLogMMCount);
		map.put("SHeatLogMMSum",SHeatLogMMSum);

		
   //期末余额	
		
		
		int SPreHeatCountMMNew =heatService.PreHeatCountMMNew(heat);
		BigDecimal SPreHeatSumMMNew =heatService.PreHeatSumMMNew(heat);
		int SOweHeatCountMMNew =heatService.OweHeatCountMMNew(heat);
		BigDecimal SOweHeatSumMMNew =heatService.OweHeatSumMMNew(heat);

		int STotalhuNew = SPreHeatCountMMNew + SOweHeatCountMMNew;
		BigDecimal STotalyuNew = SPreHeatSumMMNew.add(SOweHeatSumMMNew);

		map.put("SPreHeatCountMMNew",SPreHeatCountMMNew);
		map.put("SPreHeatSumMMNew",SPreHeatSumMMNew);
		map.put("SOweHeatCountMMNew",SOweHeatCountMMNew);
		map.put("SOweHeatSumMMNew",SOweHeatSumMMNew);

		map.put("STotalhuNew",STotalhuNew);
		map.put("STotalyuNew",STotalyuNew);		
		
		
		
	
		
		heat.setHeatType("3");
		
	//年初余额
		int GMPreHeatCountMMOld =heatService.PreHeatCountMMOld(heat);
		BigDecimal GMPreHeatSumMMOld =heatService.PreHeatSumMMOld(heat);
		int GMOweHeatCountMMOld =heatService.OweHeatCountMMOld(heat);
		BigDecimal GMOweHeatSumMMOld =heatService.OweHeatSumMMOld(heat);
		
		int GMTotalhuOld = GMPreHeatCountMMOld + GMOweHeatCountMMOld;
		BigDecimal GMTotalyuOld = GMPreHeatSumMMOld.add(GMOweHeatSumMMOld);
		
		map.put("GMPreHeatCountMMOld",GMPreHeatCountMMOld);
		map.put("GMPreHeatSumMMOld",GMPreHeatSumMMOld);
		map.put("GMOweHeatCountMMOld",GMOweHeatCountMMOld);
		map.put("GMOweHeatSumMMOld",GMOweHeatSumMMOld);

		map.put("GMTotalhuOld",GMTotalhuOld);
		map.put("GMTotalyuOld",GMTotalyuOld);
		
	//累计应收
		
		
		BigDecimal GMHeatAreaMM =heatService.HeatAreaMM(heat);
		map.put("GMHeatAreaMM",GMHeatAreaMM);
		
		int GMHeatCostMMCount = heatService.HeatCostMMCount(heat);
		BigDecimal GMHeatCostMMSum = heatService.HeatCostMMSum(heat);
		map.put("GMHeatCostMMCount",GMHeatCostMMCount);
		map.put("GMHeatCostMMSum",GMHeatCostMMSum);
		
	//累计实收
		int GMHeatLogMMCount = heatService.HeatLogMMCount(heat);
		BigDecimal GMHeatLogMMSum = heatService.HeatLogMMSum(heat);
		map.put("GMHeatLogMMCount",GMHeatLogMMCount);
		map.put("GMHeatLogMMSum",GMHeatLogMMSum);

		
   //期末余额	
		
		
		int GMPreHeatCountMMNew =heatService.PreHeatCountMMNew(heat);
		BigDecimal GMPreHeatSumMMNew =heatService.PreHeatSumMMNew(heat);
		int GMOweHeatCountMMNew =heatService.OweHeatCountMMNew(heat);
		BigDecimal GMOweHeatSumMMNew =heatService.OweHeatSumMMNew(heat);

		int GMTotalhuNew = GMPreHeatCountMMNew + GMOweHeatCountMMNew;
		BigDecimal GMTotalyuNew = GMPreHeatSumMMNew.add(GMOweHeatSumMMNew);

		map.put("GMPreHeatCountMMNew",GMPreHeatCountMMNew);
		map.put("GMPreHeatSumMMNew",GMPreHeatSumMMNew);
		map.put("GMOweHeatCountMMNew",GMOweHeatCountMMNew);
		map.put("GMOweHeatSumMMNew",GMOweHeatSumMMNew);

		map.put("GMTotalhuNew",GMTotalhuNew);
		map.put("GMTotalyuNew",GMTotalyuNew);		
		
		
		
		
		heat.setHeatType("4");
		
	//年初余额
		int GSPreHeatCountMMOld =heatService.PreHeatCountMMOld(heat);
		BigDecimal GSPreHeatSumMMOld =heatService.PreHeatSumMMOld(heat);
		int GSOweHeatCountMMOld =heatService.OweHeatCountMMOld(heat);
		BigDecimal GSOweHeatSumMMOld =heatService.OweHeatSumMMOld(heat);
		
		int GSTotalhuOld = GSPreHeatCountMMOld + GSOweHeatCountMMOld;
		BigDecimal GSTotalyuOld = GSPreHeatSumMMOld.add(GSOweHeatSumMMOld);
		
		map.put("GSPreHeatCountMMOld",GSPreHeatCountMMOld);
		map.put("GSPreHeatSumMMOld",GSPreHeatSumMMOld);
		map.put("GSOweHeatCountMMOld",GSOweHeatCountMMOld);
		map.put("GSOweHeatSumMMOld",GSOweHeatSumMMOld);

		map.put("GSTotalhuOld",GSTotalhuOld);
		map.put("GSTotalyuOld",GSTotalyuOld);
		
	//累计应收
		
		
		BigDecimal GSHeatAreaMM = heatService.HeatAreaMM(heat);
		map.put("GSHeatAreaMM",GSHeatAreaMM);	
		
		int GSHeatCostMMCount = heatService.HeatCostMMCount(heat);
		BigDecimal GSHeatCostMMSum = heatService.HeatCostMMSum(heat);
		map.put("GSHeatCostMMCount",GSHeatCostMMCount);
		map.put("GSHeatCostMMSum",GSHeatCostMMSum);
		
	//累计实收
		int GSHeatLogMMCount = heatService.HeatLogMMCount(heat);
		BigDecimal GSHeatLogMMSum = heatService.HeatLogMMSum(heat);
		map.put("GSHeatLogMMCount",GSHeatLogMMCount);
		map.put("GSHeatLogMMSum",GSHeatLogMMSum);

		
   //期末余额	
		
		
		int GSPreHeatCountMMNew =heatService.PreHeatCountMMNew(heat);
		BigDecimal GSPreHeatSumMMNew =heatService.PreHeatSumMMNew(heat);
		int GSOweHeatCountMMNew =heatService.OweHeatCountMMNew(heat);
		BigDecimal GSOweHeatSumMMNew =heatService.OweHeatSumMMNew(heat);

		int GSTotalhuNew = GSPreHeatCountMMNew + GSOweHeatCountMMNew;
		BigDecimal GSTotalyuNew = GSPreHeatSumMMNew.add(GSOweHeatSumMMNew);

		map.put("GSPreHeatCountMMNew",GSPreHeatCountMMNew);
		map.put("GSPreHeatSumMMNew",GSPreHeatSumMMNew);
		map.put("GSOweHeatCountMMNew",GSOweHeatCountMMNew);
		map.put("GSOweHeatSumMMNew",GSOweHeatSumMMNew);

		map.put("GSTotalhuNew",GSTotalhuNew);
		map.put("GSTotalyuNew",GSTotalyuNew);		
		
		


		//五九合计
		if(heat.getUserOrg().equals("2")) {
			

			List<BigDecimal> AreaList = new ArrayList<>();
			AreaList.add(MHeatAreaMM);
			AreaList.add(SHeatAreaMM);
			AreaList.add(GMHeatAreaMM);
			AreaList.add(GSHeatAreaMM);
			
			BigDecimal AreaTotalMM = new BigDecimal(0);
			for(BigDecimal a:AreaList) {	  	 
				AreaTotalMM = AreaTotalMM.add(a);  
			}
			map.put("AreaTotalMM", AreaTotalMM);
			
			//合计年初
			int TotalPrehuOld = MPreHeatCountMMOld + SPreHeatCountMMOld + GMPreHeatCountMMOld + GSPreHeatCountMMOld;
			
			List<BigDecimal> PreyList = new ArrayList<>();
			PreyList.add(MPreHeatSumMMOld);
			PreyList.add(SPreHeatSumMMOld);
			PreyList.add(GMPreHeatSumMMOld);
			PreyList.add(GSPreHeatSumMMOld);
			
			BigDecimal TotalPreyuOld = new BigDecimal(0);
			for(BigDecimal b:PreyList) {	  	 
				TotalPreyuOld = TotalPreyuOld.add(b);  
			}

			int TotalOwehuOld = MOweHeatCountMMOld + SOweHeatCountMMOld + GMOweHeatCountMMOld + GSOweHeatCountMMOld;
			
			List<BigDecimal> OweList = new ArrayList<>();
			OweList.add(MOweHeatSumMMOld);
			OweList.add(SOweHeatSumMMOld);
			OweList.add(GMOweHeatSumMMOld);
			OweList.add(GSOweHeatSumMMOld);
			
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
			int TotalCosthu = MHeatCostMMCount + SHeatCostMMCount + GMHeatCostMMCount + GSHeatCostMMCount;
			
			
			List<BigDecimal> CostList = new ArrayList<>();
			CostList.add(MHeatCostMMSum);
			CostList.add(SHeatCostMMSum);
			CostList.add(GMHeatCostMMSum);
			CostList.add(GSHeatCostMMSum);
			
			BigDecimal TotalCostyu = new BigDecimal(0);
			for(BigDecimal d: CostList) {	  	 
				TotalCostyu = TotalCostyu.add(d);  
			}

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MHeatLogMMCount + SHeatLogMMCount + GMHeatLogMMCount + GSHeatLogMMCount;
			
			
			List<BigDecimal> LogList = new ArrayList<>();
			LogList.add(MHeatLogMMSum);
			LogList.add(SHeatLogMMSum);
			LogList.add(GMHeatLogMMSum);
			LogList.add(GSHeatLogMMSum);
			
			BigDecimal TotalLogyu = new BigDecimal(0);
			for(BigDecimal e: LogList) {	  	 
				TotalLogyu = TotalLogyu.add(e);  
			}

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreHeatCountMMNew + SPreHeatCountMMNew + GMPreHeatCountMMNew + GSPreHeatCountMMNew;
			
			
			List<BigDecimal> PreListNew = new ArrayList<>();
			PreListNew.add(MPreHeatSumMMNew);
			PreListNew.add(SPreHeatSumMMNew);
			PreListNew.add(GMPreHeatSumMMNew);
			PreListNew.add(GSPreHeatSumMMNew);
			
			BigDecimal  TotalPreyuNew = new BigDecimal(0);
			for(BigDecimal e: PreListNew) {	  	 
				TotalPreyuNew = TotalPreyuNew.add(e);  
			}
			int TotalOwehuNew = MOweHeatCountMMNew + SOweHeatCountMMNew + GMOweHeatCountMMNew + GSOweHeatCountMMNew;
			
			List<BigDecimal> OweListNew = new ArrayList<>();
			OweListNew.add(MOweHeatSumMMNew);
			OweListNew.add(SOweHeatSumMMNew);
			OweListNew.add(GMOweHeatSumMMNew);
			OweListNew.add(GSOweHeatSumMMNew);
			
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
			
			
			BigDecimal  AreaTotalMM = MHeatAreaMM.add(SHeatAreaMM);
			map.put("AreaTotalMM", AreaTotalMM);
			
			//合计年初
			int TotalPrehuOld = MPreHeatCountMMOld + SPreHeatCountMMOld;
			BigDecimal TotalPreyuOld = MPreHeatSumMMOld.add(SPreHeatSumMMOld);
			int TotalOwehuOld = MOweHeatCountMMOld + SOweHeatCountMMOld;
			BigDecimal TotalOweyuOld = MOweHeatSumMMOld.add(SOweHeatSumMMOld);
			
			int TotalAllhuOld = MTotalhuOld + STotalhuOld;
			BigDecimal TotalAllyuOld = MTotalyuOld.add(STotalyuOld);
		
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MHeatCostMMCount + SHeatCostMMCount;
			BigDecimal TotalCostyu = MHeatCostMMSum.add(SHeatCostMMSum);

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MHeatLogMMCount + SHeatLogMMCount;
			BigDecimal TotalLogyu = MHeatLogMMSum.add(SHeatLogMMSum);

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreHeatCountMMNew + SPreHeatCountMMNew;
			BigDecimal TotalPreyuNew = MPreHeatSumMMNew.add(SPreHeatSumMMNew);
			int TotalOwehuNew = MOweHeatCountMMNew + SOweHeatCountMMNew;
			BigDecimal TotalOweyuNew = MOweHeatSumMMNew.add(SOweHeatSumMMNew);
			
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
	  @GetMapping("/exportHeatMM")
	  public void exportHeatMM(HeatVo heat,HeatLogVo heatLog,HttpServletResponse response){
	  
		  
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				HSSFWorkbook workbook = new HSSFWorkbook();
				OutputStream out = response.getOutputStream();
				
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用暖类型","用户暖价","用户暖费","用户余额","创建时间","更新时间","用户备注"};
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用暖类型","用户暖价","用户暖费","用户余额","创建时间","更新时间","用户备注"};
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","暖费类型","缴费金额","缴费时间","收费时间","收费人","更新时间","更新人","用户备注"};
				
				String createTime = heat.getCreateTime();
				String userId = heat.getUserId();
				
				String userOrg = "";
				if ("2".equals(heat.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(heat.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				
				List<HeatVo> heatPreList = heatService.exprotHeatPre(heat);
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
					} else if ("3".equals(heatPreList.get(i).getHeatType())) {
						arrObj[8] =   "工业民";
					} else if ("4".equals(heatPreList.get(i).getHeatType())) {
						arrObj[8] =   "工业商";
					} 
					arrObj[9] = heatPreList.get(i).getHeatPrice();
					arrObj[10] = heatPreList.get(i).getHeatCost();
					arrObj[11] = heatPreList.get(i).getHeatSum();
					arrObj[12] = heatPreList.get(i).getCreateTime();
					arrObj[13] = formatter.format(heatPreList.get(i).getUpdateTime());
					arrObj[14] = heatPreList.get(i).getRemark();
					heatPredataList.add(arrObj);
				}
				
				List<HeatVo> heatOweList = heatService.exprotHeatOwe(heat);
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
					} 
					arrObj[9] = heatOweList.get(i).getHeatPrice();
					arrObj[10] = heatOweList.get(i).getHeatCost();
					arrObj[11] = heatOweList.get(i).getHeatSum();
					arrObj[12] = heatOweList.get(i).getCreateTime();
					arrObj[13] = formatter.format(heatOweList.get(i).getUpdateTime());
					arrObj[14] = heatPreList.get(i).getRemark();
					heatOwedataList.add(arrObj);
				}
				
				
				List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogMM(heatLog);
				List<Object[]> heatLogdataList = new ArrayList<Object[]>();
				for(int i = 0;i < heatLogList.size(); i++ ) {
					Object[] arrObj = new Object[headers3.length];
					
					arrObj[0] = heatLogList.get(i).getId();
					arrObj[1] = heatLogList.get(i).getUserId();
					arrObj[2] = heatLogList.get(i).getUserName();
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
					} 
					arrObj[6] = heatLogList.get(i).getHeatMoney();
					arrObj[7] = formatter.format(heatLogList.get(i).getMoneyDate());
					arrObj[8] = heatLogList.get(i).getCreateTime();
					arrObj[9] = heatLogList.get(i).getCreateBy();
					arrObj[10] = formatter.format(heatLogList.get(i).getUpdateTime());
					arrObj[11] = heatLogList.get(i).getUpdateBy();
					arrObj[12] = heatLogList.get(i).getRemark();
					
					heatLogdataList.add(arrObj);
				}

				

				String title =  "暖费当月报表.xlsx";

				String oneheaders1 = createTime+userOrg+userId+"暖费预存表";
				String oneheaders2 = createTime+userOrg+userId+"暖费欠费表";
				String oneheaders3 = createTime+userOrg+userId+"暖费实收表";
				
				
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
