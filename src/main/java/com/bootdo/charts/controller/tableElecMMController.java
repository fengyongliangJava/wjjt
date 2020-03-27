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
import com.bootdo.elec.domain.ElecPriceDO;
import com.bootdo.elec.service.ElecService;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;


 
@Controller
@RequestMapping("/charts/tableElecMM")
public class tableElecMMController {
	
	
	
	@Autowired private ElecService elecService;
	@Autowired private ElecLogService elecLogService;
	

	
	@GetMapping()
	@RequiresPermissions("charts:tableMM")
	public String tableElecMM(){
	    return "charts/tableElecMM";
	}
	
	
	
	

	@ResponseBody
	@PostMapping("/tableElecMM")
	public  Map<String,Object>  tableElecMM(ElecVo elec,ElecLogVo elecLog){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		
		elec.setElecType("1");
	//上月余额
		int MPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal MPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int MOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal MOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int MTotalhuOld = MPreElecCountMMOld + MOweElecCountMMOld;
		BigDecimal MTotalyuOld = MPreElecSumMMOld.add(MOweElecSumMMOld);
		
		map.put("MPreElecCountMMOld",MPreElecCountMMOld);
		map.put("MPreElecSumMMOld",MPreElecSumMMOld);
		map.put("MOweElecCountMMOld",MOweElecCountMMOld);
		map.put("MOweElecSumMMOld",MOweElecSumMMOld);

		map.put("MTotalhuOld",MTotalhuOld);
		map.put("MTotalyuOld",MTotalyuOld);
		
	//累计应收
		
		
		int MElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("MElecAmountMM",MElecAmountMM);
		
		int MElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal MElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("MElecCostMMCount",MElecCostMMCount);
		map.put("MElecCostMMSum",MElecCostMMSum);
		
	//累计实收
		int MElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal MElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("MElecLogMMCount",MElecLogMMCount);
		map.put("MElecLogMMSum",MElecLogMMSum);

		
   //本月余额	
		
		
		
		int MPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal MPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int MOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal MOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int MTotalhuNew = MPreElecCountMMNew + MOweElecCountMMNew;
		BigDecimal MTotalyuNew = MPreElecSumMMNew.add(MOweElecSumMMNew);

		map.put("MPreElecCountMMNew",MPreElecCountMMNew);
		map.put("MPreElecSumMMNew",MPreElecSumMMNew);
		map.put("MOweElecCountMMNew",MOweElecCountMMNew);
		map.put("MOweElecSumMMNew",MOweElecSumMMNew);

		map.put("MTotalhuNew",MTotalhuNew);
		map.put("MTotalyuNew",MTotalyuNew);		
		
		
		
		
		
		
		
		
		elec.setElecType("2");
		
	//上月余额
		int SPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal SPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int SOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal SOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int STotalhuOld = SPreElecCountMMOld + SOweElecCountMMOld;
		BigDecimal STotalyuOld = SPreElecSumMMOld.add(SOweElecSumMMOld);
		
		map.put("SPreElecCountMMOld",SPreElecCountMMOld);
		map.put("SPreElecSumMMOld",SPreElecSumMMOld);
		map.put("SOweElecCountMMOld",SOweElecCountMMOld);
		map.put("SOweElecSumMMOld",SOweElecSumMMOld);

		map.put("STotalhuOld",STotalhuOld);
		map.put("STotalyuOld",STotalyuOld);
		
	//累计应收
		
		
		int SElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("SElecAmountMM",SElecAmountMM);	
		
		int SElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal SElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("SElecCostMMCount",SElecCostMMCount);
		map.put("SElecCostMMSum",SElecCostMMSum);
		
	//累计实收
		int SElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal SElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("SElecLogMMCount",SElecLogMMCount);
		map.put("SElecLogMMSum",SElecLogMMSum);

		
   //本月余额	
		
		
		int SPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal SPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int SOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal SOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int STotalhuNew = SPreElecCountMMNew + SOweElecCountMMNew;
		BigDecimal STotalyuNew = SPreElecSumMMNew.add(SOweElecSumMMNew);

		map.put("SPreElecCountMMNew",SPreElecCountMMNew);
		map.put("SPreElecSumMMNew",SPreElecSumMMNew);
		map.put("SOweElecCountMMNew",SOweElecCountMMNew);
		map.put("SOweElecSumMMNew",SOweElecSumMMNew);

		map.put("STotalhuNew",STotalhuNew);
		map.put("STotalyuNew",STotalyuNew);		
		
		
		
	
		
		elec.setElecType("3");
		
	//上月余额
		int G1PreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal G1PreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int G1OweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal G1OweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int G1TotalhuOld = G1PreElecCountMMOld + G1OweElecCountMMOld;
		BigDecimal G1TotalyuOld = G1PreElecSumMMOld.add(G1OweElecSumMMOld);
		
		map.put("G1PreElecCountMMOld",G1PreElecCountMMOld);
		map.put("G1PreElecSumMMOld",G1PreElecSumMMOld);
		map.put("G1OweElecCountMMOld",G1OweElecCountMMOld);
		map.put("G1OweElecSumMMOld",G1OweElecSumMMOld);

		map.put("G1TotalhuOld",G1TotalhuOld);
		map.put("G1TotalyuOld",G1TotalyuOld);
		
	//累计应收
		
		
		int G1ElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("G1ElecAmountMM",G1ElecAmountMM);
		
		int G1ElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal G1ElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("G1ElecCostMMCount",G1ElecCostMMCount);
		map.put("G1ElecCostMMSum",G1ElecCostMMSum);
		
	//累计实收
		int G1ElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal G1ElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("G1ElecLogMMCount",G1ElecLogMMCount);
		map.put("G1ElecLogMMSum",G1ElecLogMMSum);

		
    //本月余额	
		
		
		int G1PreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal G1PreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int G1OweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal G1OweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int G1TotalhuNew = G1PreElecCountMMNew + G1OweElecCountMMNew;
		BigDecimal G1TotalyuNew = G1PreElecSumMMNew.add(G1OweElecSumMMNew);

		map.put("G1PreElecCountMMNew",G1PreElecCountMMNew);
		map.put("G1PreElecSumMMNew",G1PreElecSumMMNew);
		map.put("G1OweElecCountMMNew",G1OweElecCountMMNew);
		map.put("G1OweElecSumMMNew",G1OweElecSumMMNew);

		map.put("G1TotalhuNew",G1TotalhuNew);
		map.put("G1TotalyuNew",G1TotalyuNew);		
		
		
		
		
		
		
		
		elec.setElecType("4");
		
	//上月余额
		int G2PreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal G2PreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int G2OweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal G2OweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int G2TotalhuOld = G2PreElecCountMMOld + G2OweElecCountMMOld;
		BigDecimal G2TotalyuOld = G2PreElecSumMMOld.add(G2OweElecSumMMOld);
		
		map.put("G2PreElecCountMMOld",G2PreElecCountMMOld);
		map.put("G2PreElecSumMMOld",G2PreElecSumMMOld);
		map.put("G2OweElecCountMMOld",G2OweElecCountMMOld);
		map.put("G2OweElecSumMMOld",G2OweElecSumMMOld);

		map.put("G2TotalhuOld",G2TotalhuOld);
		map.put("G2TotalyuOld",G2TotalyuOld);
		
	//累计应收
		
		
		int G2ElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("G2ElecAmountMM",G2ElecAmountMM);	
		
		int G2ElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal G2ElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("G2ElecCostMMCount",G2ElecCostMMCount);
		map.put("G2ElecCostMMSum",G2ElecCostMMSum);
		
	//累计实收
		int G2ElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal G2ElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("G2ElecLogMMCount",G2ElecLogMMCount);
		map.put("G2ElecLogMMSum",G2ElecLogMMSum);

		
   //本月余额	
		
		
		int G2PreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal G2PreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int G2OweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal G2OweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int G2TotalhuNew = G2PreElecCountMMNew + G2OweElecCountMMNew;
		BigDecimal G2TotalyuNew = G2PreElecSumMMNew.add(G2OweElecSumMMNew);

		map.put("G2PreElecCountMMNew",G2PreElecCountMMNew);
		map.put("G2PreElecSumMMNew",G2PreElecSumMMNew);
		map.put("G2OweElecCountMMNew",G2OweElecCountMMNew);
		map.put("G2OweElecSumMMNew",G2OweElecSumMMNew);

		map.put("G2TotalhuNew",G2TotalhuNew);
		map.put("G2TotalyuNew",G2TotalyuNew);		
		
		
		
		
		

		elec.setElecType("5");
		
	//上月余额
		int G3PreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal G3PreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int G3OweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal G3OweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int G3TotalhuOld = G3PreElecCountMMOld + G3OweElecCountMMOld;
		BigDecimal G3TotalyuOld = G3PreElecSumMMOld.add(G3OweElecSumMMOld);
		
		map.put("G3PreElecCountMMOld",G3PreElecCountMMOld);
		map.put("G3PreElecSumMMOld",G3PreElecSumMMOld);
		map.put("G3OweElecCountMMOld",G3OweElecCountMMOld);
		map.put("G3OweElecSumMMOld",G3OweElecSumMMOld);

		map.put("G3TotalhuOld",G3TotalhuOld);
		map.put("G3TotalyuOld",G3TotalyuOld);
		
	//累计应收
		
		
		int G3ElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("G3ElecAmountMM",G3ElecAmountMM);
		
		int G3ElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal G3ElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("G3ElecCostMMCount",G3ElecCostMMCount);
		map.put("G3ElecCostMMSum",G3ElecCostMMSum);
		
	//累计实收
		int G3ElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal G3ElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("G3ElecLogMMCount",G3ElecLogMMCount);
		map.put("G3ElecLogMMSum",G3ElecLogMMSum);

		
   //本月余额	
		
		
		int G3PreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal G3PreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int G3OweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal G3OweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int G3TotalhuNew = G3PreElecCountMMNew + G3OweElecCountMMNew;
		BigDecimal G3TotalyuNew = G3PreElecSumMMNew.add(G3OweElecSumMMNew);

		map.put("G3PreElecCountMMNew",G3PreElecCountMMNew);
		map.put("G3PreElecSumMMNew",G3PreElecSumMMNew);
		map.put("G3OweElecCountMMNew",G3OweElecCountMMNew);
		map.put("G3OweElecSumMMNew",G3OweElecSumMMNew);

		map.put("G3TotalhuNew",G3TotalhuNew);
		map.put("G3TotalyuNew",G3TotalyuNew);		
		
		
		
		
		

		elec.setElecType("6");
		
	//上月余额
		int WMPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal WMPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int WMOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal WMOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int WMTotalhuOld = WMPreElecCountMMOld + WMOweElecCountMMOld;
		BigDecimal WMTotalyuOld = WMPreElecSumMMOld.add(WMOweElecSumMMOld);
		
		map.put("WMPreElecCountMMOld",WMPreElecCountMMOld);
		map.put("WMPreElecSumMMOld",WMPreElecSumMMOld);
		map.put("WMOweElecCountMMOld",WMOweElecCountMMOld);
		map.put("WMOweElecSumMMOld",WMOweElecSumMMOld);

		map.put("WMTotalhuOld",WMTotalhuOld);
		map.put("WMTotalyuOld",WMTotalyuOld);
		
	//累计应收
		
		
		int WMElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("WMElecAmountMM",WMElecAmountMM);

		
		int WMElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal WMElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("WMElecCostMMCount",WMElecCostMMCount);
		map.put("WMElecCostMMSum",WMElecCostMMSum);
		
	//累计实收
		int WMElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal WMElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("WMElecLogMMCount",WMElecLogMMCount);
		map.put("WMElecLogMMSum",WMElecLogMMSum);

		
   //本月余额	
		
		
		int WMPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal WMPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int WMOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal WMOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int WMTotalhuNew = WMPreElecCountMMNew + WMOweElecCountMMNew;
		BigDecimal WMTotalyuNew = WMPreElecSumMMNew.add(WMOweElecSumMMNew);

		map.put("WMPreElecCountMMNew",WMPreElecCountMMNew);
		map.put("WMPreElecSumMMNew",WMPreElecSumMMNew);
		map.put("WMOweElecCountMMNew",WMOweElecCountMMNew);
		map.put("WMOweElecSumMMNew",WMOweElecSumMMNew);

		map.put("WMTotalhuNew",WMTotalhuNew);
		map.put("WMTotalyuNew",WMTotalyuNew);		
		
		
		

		elec.setElecType("7");
		
	//上月余额
		int WSPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal WSPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int WSOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal WSOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int WSTotalhuOld = WSPreElecCountMMOld + WSOweElecCountMMOld;
		BigDecimal WSTotalyuOld = WSPreElecSumMMOld.add( WSOweElecSumMMOld);
		
		map.put("WSPreElecCountMMOld",WSPreElecCountMMOld);
		map.put("WSPreElecSumMMOld",WSPreElecSumMMOld);
		map.put("WSOweElecCountMMOld",WSOweElecCountMMOld);
		map.put("WSOweElecSumMMOld",WSOweElecSumMMOld);

		map.put("WSTotalhuOld",WSTotalhuOld);
		map.put("WSTotalyuOld",WSTotalyuOld);
		
	//累计应收
		
		
		int WSElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("WSElecAmountMM",WSElecAmountMM);	
		
		int WSElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal WSElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("WSElecCostMMCount",WSElecCostMMCount);
		map.put("WSElecCostMMSum",WSElecCostMMSum);
		
	//累计实收
		int WSElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal WSElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("WSElecLogMMCount",WSElecLogMMCount);
		map.put("WSElecLogMMSum",WSElecLogMMSum);

		
   //本月余额	
		
		
		int WSPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal WSPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int WSOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal WSOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int WSTotalhuNew = WSPreElecCountMMNew + WSOweElecCountMMNew;
		BigDecimal WSTotalyuNew = WSPreElecSumMMNew.add( WSOweElecSumMMNew);

		map.put("WSPreElecCountMMNew",WSPreElecCountMMNew);
		map.put("WSPreElecSumMMNew",WSPreElecSumMMNew);
		map.put("WSOweElecCountMMNew",WSOweElecCountMMNew);
		map.put("WSOweElecSumMMNew",WSOweElecSumMMNew);

		map.put("WSTotalhuNew",WSTotalhuNew);
		map.put("WSTotalyuNew",WSTotalyuNew);		
		
		
		
		

		elec.setElecType("8");
		
	//上月余额
		int WGPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal WGPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int WGOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal WGOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int WGTotalhuOld = WGPreElecCountMMOld + WGOweElecCountMMOld;
		BigDecimal WGTotalyuOld = WGPreElecSumMMOld.add(WGOweElecSumMMOld);
		
		map.put("WGPreElecCountMMOld",WGPreElecCountMMOld);
		map.put("WGPreElecSumMMOld",WGPreElecSumMMOld);
		map.put("WGOweElecCountMMOld",WGOweElecCountMMOld);
		map.put("WGOweElecSumMMOld",WGOweElecSumMMOld);

		map.put("WGTotalhuOld",WGTotalhuOld);
		map.put("WGTotalyuOld",WGTotalyuOld);
		
	//累计应收
		
		
		int WGElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("WGElecAmountMM",WGElecAmountMM);
		
		int WGElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal WGElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("WGElecCostMMCount",WGElecCostMMCount);
		map.put("WGElecCostMMSum",WGElecCostMMSum);
		
	//累计实收
		int WGElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal WGElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("WGElecLogMMCount",WGElecLogMMCount);
		map.put("WGElecLogMMSum",WGElecLogMMSum);

		
   //本月余额	
		
		
		int WGPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal WGPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int WGOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal WGOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int WGTotalhuNew = WGPreElecCountMMNew + WGOweElecCountMMNew;
		BigDecimal WGTotalyuNew = WGPreElecSumMMNew.add(WGOweElecSumMMNew);

		map.put("WGPreElecCountMMNew",WGPreElecCountMMNew);
		map.put("WGPreElecSumMMNew",WGPreElecSumMMNew);
		map.put("WGOweElecCountMMNew",WGOweElecCountMMNew);
		map.put("WGOweElecSumMMNew",WGOweElecSumMMNew);

		map.put("WGTotalhuNew",WGTotalhuNew);
		map.put("WGTotalyuNew",WGTotalyuNew);		
		
		
		
		

		elec.setElecType("9");
		
	//上月余额
		int MMPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal MMPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int MMOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal MMOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int MMTotalhuOld = MMPreElecCountMMOld + MMOweElecCountMMOld;
		BigDecimal MMTotalyuOld = MMPreElecSumMMOld.add(MMOweElecSumMMOld);
		
		map.put("MMPreElecCountMMOld",MMPreElecCountMMOld);
		map.put("MMPreElecSumMMOld",MMPreElecSumMMOld);
		map.put("MMOweElecCountMMOld",MMOweElecCountMMOld);
		map.put("MMOweElecSumMMOld",MMOweElecSumMMOld);

		map.put("MMTotalhuOld",MMTotalhuOld);
		map.put("MMTotalyuOld",MMTotalyuOld);
		
	//累计应收
		
		
		int MMElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("MMElecAmountMM",MMElecAmountMM);	

		int MMElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal MMElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("MMElecCostMMCount",MMElecCostMMCount);
		map.put("MMElecCostMMSum",MMElecCostMMSum);
		
	//累计实收
		int MMElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal MMElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("MMElecLogMMCount",MMElecLogMMCount);
		map.put("MMElecLogMMSum",MMElecLogMMSum);

		
   //本月余额	
		
		
		int MMPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal MMPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int MMOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal MMOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int MMTotalhuNew = MMPreElecCountMMNew + MMOweElecCountMMNew;
		BigDecimal MMTotalyuNew = MMPreElecSumMMNew .add(MMOweElecSumMMNew);

		map.put("MMPreElecCountMMNew",MMPreElecCountMMNew);
		map.put("MMPreElecSumMMNew",MMPreElecSumMMNew);
		map.put("MMOweElecCountMMNew",MMOweElecCountMMNew);
		map.put("MMOweElecSumMMNew",MMOweElecSumMMNew);

		map.put("MMTotalhuNew",MMTotalhuNew);
		map.put("MMTotalyuNew",MMTotalyuNew);		
		
		
		
		

		elec.setElecType("10");
		
	//上月余额
		int MSPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal MSPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int MSOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal MSOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int MSTotalhuOld = MSPreElecCountMMOld + MSOweElecCountMMOld;
		BigDecimal MSTotalyuOld = MSPreElecSumMMOld.add(MSOweElecSumMMOld);
		
		map.put("MSPreElecCountMMOld",MSPreElecCountMMOld);
		map.put("MSPreElecSumMMOld",MSPreElecSumMMOld);
		map.put("MSOweElecCountMMOld",MSOweElecCountMMOld);
		map.put("MSOweElecSumMMOld",MSOweElecSumMMOld);

		map.put("MSTotalhuOld",MSTotalhuOld);
		map.put("MSTotalyuOld",MSTotalyuOld);
		
	//累计应收
		
		
		int MSElecAmountMM =elecService.ElecAmountMM(elec);	
		map.put("MSElecAmountMM",MSElecAmountMM);

		int MSElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal MSElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("MSElecCostMMCount",MSElecCostMMCount);
		map.put("MSElecCostMMSum",MSElecCostMMSum);
		
	//累计实收
		int MSElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal MSElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("MSElecLogMMCount",MSElecLogMMCount);
		map.put("MSElecLogMMSum",MSElecLogMMSum);

		
   //本月余额	
		
		
		int MSPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal MSPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int MSOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal MSOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int MSTotalhuNew = MSPreElecCountMMNew + MSOweElecCountMMNew;
		BigDecimal MSTotalyuNew = MSPreElecSumMMNew.add(MSOweElecSumMMNew);

		map.put("MSPreElecCountMMNew",MSPreElecCountMMNew);
		map.put("MSPreElecSumMMNew",MSPreElecSumMMNew);
		map.put("MSOweElecCountMMNew",MSOweElecCountMMNew);
		map.put("MSOweElecSumMMNew",MSOweElecSumMMNew);

		map.put("MSTotalhuNew",MSTotalhuNew);
		map.put("MSTotalyuNew",MSTotalyuNew);		
		
		
		
		
		

		elec.setElecType("11");
		
	//上月余额
		int ZPreElecCountMMOld =elecService.PreElecCountMMOld(elec);
		BigDecimal ZPreElecSumMMOld =elecService.PreElecSumMMOld(elec);
		int ZOweElecCountMMOld =elecService.OweElecCountMMOld(elec);
		BigDecimal ZOweElecSumMMOld =elecService.OweElecSumMMOld(elec);
		
		int ZTotalhuOld = ZPreElecCountMMOld + ZOweElecCountMMOld;
		BigDecimal ZTotalyuOld = ZPreElecSumMMOld.add(ZOweElecSumMMOld);
		
		map.put("ZPreElecCountMMOld",ZPreElecCountMMOld);
		map.put("ZPreElecSumMMOld",ZPreElecSumMMOld);
		map.put("ZOweElecCountMMOld",ZOweElecCountMMOld);
		map.put("ZOweElecSumMMOld",ZOweElecSumMMOld);

		map.put("ZTotalhuOld",ZTotalhuOld);
		map.put("ZTotalyuOld",ZTotalyuOld);
		
	//累计应收
		
		
		int ZElecAmountMM =elecService.ElecAmountMM(elec);
		map.put("ZElecAmountMM",ZElecAmountMM);
		
		int ZElecCostMMCount = elecService.ElecCostMMCount(elec);
		BigDecimal ZElecCostMMSum = elecService.ElecCostMMSum(elec);
		map.put("ZElecCostMMCount",ZElecCostMMCount);
		map.put("ZElecCostMMSum",ZElecCostMMSum);
		
	//累计实收
		int ZElecLogMMCount = elecService.ElecLogMMCount(elec);
		BigDecimal ZElecLogMMSum = elecService.ElecLogMMSum(elec);
		map.put("ZElecLogMMCount",ZElecLogMMCount);
		map.put("ZElecLogMMSum",ZElecLogMMSum);

		
   //本月余额	
		
		
		int ZPreElecCountMMNew =elecService.PreElecCountMMNew(elec);
		BigDecimal ZPreElecSumMMNew =elecService.PreElecSumMMNew(elec);
		int ZOweElecCountMMNew =elecService.OweElecCountMMNew(elec);
		BigDecimal ZOweElecSumMMNew =elecService.OweElecSumMMNew(elec);

		int ZTotalhuNew = ZPreElecCountMMNew + ZOweElecCountMMNew;
		BigDecimal ZTotalyuNew = ZPreElecSumMMNew.add(ZOweElecSumMMNew);

		map.put("ZPreElecCountMMNew",ZPreElecCountMMNew);
		map.put("ZPreElecSumMMNew",ZPreElecSumMMNew);
		map.put("ZOweElecCountMMNew",ZOweElecCountMMNew);
		map.put("ZOweElecSumMMNew",ZOweElecSumMMNew);

		map.put("ZTotalhuNew",ZTotalhuNew);
		map.put("ZTotalyuNew",ZTotalyuNew);		
		
		
		
		

		//五九合计
		if(elec.getUserOrg().equals("2")) {
			
			int AmountTotalMM = MElecAmountMM + SElecAmountMM + G1ElecAmountMM + G2ElecAmountMM + G3ElecAmountMM + WMElecAmountMM + WSElecAmountMM + WGElecAmountMM + MMElecAmountMM + MSElecAmountMM + ZElecAmountMM;
			map.put("AmountTotalMM", AmountTotalMM);
			
			//合计上月
			int TotalPrehuOld = MPreElecCountMMOld + SPreElecCountMMOld + G1PreElecCountMMOld + G2PreElecCountMMOld + G3PreElecCountMMOld + WMPreElecCountMMOld + WSPreElecCountMMOld + WGPreElecCountMMOld + MMPreElecCountMMOld + MSPreElecCountMMOld + ZPreElecCountMMOld;
			
			List<BigDecimal> TotalPreyuOldList = new ArrayList<>();
			TotalPreyuOldList.add(MPreElecSumMMOld);
			TotalPreyuOldList.add(SPreElecSumMMOld);
			TotalPreyuOldList.add(G1PreElecSumMMOld);
			TotalPreyuOldList.add(G2PreElecSumMMOld);
			TotalPreyuOldList.add(G3PreElecSumMMOld);
			TotalPreyuOldList.add(WMPreElecSumMMOld);
			TotalPreyuOldList.add(WSPreElecSumMMOld);
			TotalPreyuOldList.add(WGPreElecSumMMOld);
			TotalPreyuOldList.add(MMPreElecSumMMOld);
			TotalPreyuOldList.add(MSPreElecSumMMOld);
			TotalPreyuOldList.add(ZPreElecSumMMOld);
			
			
			BigDecimal TotalPreyuOld = new BigDecimal(0);
			for(BigDecimal a:TotalPreyuOldList) {	  	 
				TotalPreyuOld = TotalPreyuOld.add(a);  
			}
			
			int TotalOwehuOld = MOweElecCountMMOld + SOweElecCountMMOld + G1OweElecCountMMOld + G2OweElecCountMMOld + G3OweElecCountMMOld + WMOweElecCountMMOld + WSOweElecCountMMOld + WGOweElecCountMMOld + MMOweElecCountMMOld + MSOweElecCountMMOld + ZOweElecCountMMOld;
			
			
			List<BigDecimal> TotalOweyuOldList = new ArrayList<>();
			TotalOweyuOldList.add(MOweElecSumMMOld);
			TotalOweyuOldList.add(SOweElecSumMMOld);
			TotalOweyuOldList.add(G1OweElecSumMMOld);
			TotalOweyuOldList.add(G2OweElecSumMMOld);
			TotalOweyuOldList.add(G3OweElecSumMMOld);
			TotalOweyuOldList.add(WMOweElecSumMMOld);
			TotalOweyuOldList.add(WSOweElecSumMMOld);
			TotalOweyuOldList.add(WGOweElecSumMMOld);
			TotalOweyuOldList.add(MMOweElecSumMMOld);
			TotalOweyuOldList.add(MSOweElecSumMMOld);
			TotalOweyuOldList.add(ZOweElecSumMMOld);
			
			
			BigDecimal TotalOweyuOld = new BigDecimal(0);
			for(BigDecimal b:TotalOweyuOldList) {	  	 
				TotalOweyuOld = TotalOweyuOld.add(b);  
			}
			
			int TotalAllhuOld = MTotalhuOld + STotalhuOld + G1TotalhuOld + G2TotalhuOld + G3TotalhuOld + WMTotalhuOld + WSTotalhuOld + WGTotalhuOld + MMTotalhuOld + MSTotalhuOld + ZTotalhuOld;
			
			List<BigDecimal> TotalAllyuOldList = new ArrayList<>();
			TotalAllyuOldList.add(MTotalyuOld);
			TotalAllyuOldList.add(STotalyuOld);
			TotalAllyuOldList.add(G1TotalyuOld);
			TotalAllyuOldList.add(G2TotalyuOld);
			TotalAllyuOldList.add(G3TotalyuOld);
			TotalAllyuOldList.add(WMTotalyuOld);
			TotalAllyuOldList.add(WSTotalyuOld);
			TotalAllyuOldList.add(WGTotalyuOld);
			TotalAllyuOldList.add(MMTotalyuOld);
			TotalAllyuOldList.add(MSTotalyuOld);
			TotalAllyuOldList.add(ZTotalyuOld);
			
			BigDecimal TotalAllyuOld = new BigDecimal(0);
			for(BigDecimal c:TotalAllyuOldList) {	  	 
				TotalAllyuOld = TotalAllyuOld.add(c);  
			}
			
			
		
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MElecCostMMCount + SElecCostMMCount + G1ElecCostMMCount + G2ElecCostMMCount + G3ElecCostMMCount + WMElecCostMMCount + WSElecCostMMCount + WGElecCostMMCount + MMElecCostMMCount + MSElecCostMMCount + ZElecCostMMCount;
			
			List<BigDecimal> TotalCostyuList = new ArrayList<>();
			
			TotalCostyuList.add(MElecCostMMSum);
			TotalCostyuList.add(SElecCostMMSum);
			TotalCostyuList.add(G1ElecCostMMSum);
			TotalCostyuList.add(G2ElecCostMMSum);
			TotalCostyuList.add(G3ElecCostMMSum);
			TotalCostyuList.add(WMElecCostMMSum);
			TotalCostyuList.add(WSElecCostMMSum);
			TotalCostyuList.add(WGElecCostMMSum);
			TotalCostyuList.add(MMElecCostMMSum);
			TotalCostyuList.add(MSElecCostMMSum);
			TotalCostyuList.add(ZElecCostMMSum);
			
			BigDecimal TotalCostyu = new BigDecimal(0);
			for(BigDecimal d:TotalCostyuList) {	  	 
				TotalCostyu = TotalCostyu.add(d);  
			}

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MElecLogMMCount + SElecLogMMCount + G1ElecLogMMCount + G2ElecLogMMCount + G3ElecLogMMCount + WMElecLogMMCount + WSElecLogMMCount + WGElecLogMMCount + MMElecLogMMCount + MSElecLogMMCount + ZElecLogMMCount;
			
			
			List<BigDecimal> TotalLogyuList = new ArrayList<>();
			
			TotalLogyuList.add(MElecLogMMSum);
			TotalLogyuList.add(SElecLogMMSum);
			TotalLogyuList.add(G1ElecLogMMSum);
			TotalLogyuList.add(G2ElecLogMMSum);
			TotalLogyuList.add(G3ElecLogMMSum);
			TotalLogyuList.add(WMElecLogMMSum);
			TotalLogyuList.add(WSElecLogMMSum);
			TotalLogyuList.add(WGElecLogMMSum);
			TotalLogyuList.add(MMElecLogMMSum);
			TotalLogyuList.add(MSElecLogMMSum);
			TotalLogyuList.add(ZElecLogMMSum);
			
			BigDecimal TotalLogyu = new BigDecimal(0);
			for(BigDecimal e:TotalLogyuList) {	  	 
				TotalLogyu = TotalLogyu.add(e);  
			}
			
			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreElecCountMMNew + SPreElecCountMMNew + G1PreElecCountMMNew + G2PreElecCountMMNew + G3PreElecCountMMNew + WMPreElecCountMMNew + WSPreElecCountMMNew + WGPreElecCountMMNew + MMPreElecCountMMNew + MSPreElecCountMMNew + ZPreElecCountMMNew;
			
			
			List<BigDecimal> TotalPreyuNewList = new ArrayList<>();
			
			TotalPreyuNewList.add(MPreElecSumMMNew);
			TotalPreyuNewList.add(SPreElecSumMMNew);
			TotalPreyuNewList.add(G1PreElecSumMMNew);
			TotalPreyuNewList.add(G2PreElecSumMMNew);
			TotalPreyuNewList.add(G3PreElecSumMMNew);
			TotalPreyuNewList.add(WMPreElecSumMMNew);
			TotalPreyuNewList.add(WSPreElecSumMMNew);
			TotalPreyuNewList.add(WGPreElecSumMMNew);
			TotalPreyuNewList.add(MMPreElecSumMMNew);
			TotalPreyuNewList.add(MSPreElecSumMMNew);
			TotalPreyuNewList.add(ZPreElecSumMMNew);
			
			BigDecimal TotalPreyuNew = new BigDecimal(0);
			for(BigDecimal f:TotalPreyuNewList) {	  	 
				TotalPreyuNew = TotalPreyuNew.add(f);  
			}
			
			
			int TotalOwehuNew = MOweElecCountMMNew + SOweElecCountMMNew + G1OweElecCountMMNew + G2OweElecCountMMNew + G3OweElecCountMMNew + WMOweElecCountMMNew + WSOweElecCountMMNew + WGOweElecCountMMNew + MMOweElecCountMMNew + MSOweElecCountMMNew + ZOweElecCountMMNew;
			
			
			List<BigDecimal> TotalOweyuNewList = new ArrayList<>();
			
			TotalOweyuNewList.add(MOweElecSumMMNew);
			TotalOweyuNewList.add(SOweElecSumMMNew);
			TotalOweyuNewList.add(G1OweElecSumMMNew);
			TotalOweyuNewList.add(G2OweElecSumMMNew);
			TotalOweyuNewList.add(G3OweElecSumMMNew);
			TotalOweyuNewList.add(WMOweElecSumMMNew);
			TotalOweyuNewList.add(WSOweElecSumMMNew);
			TotalOweyuNewList.add(WGOweElecSumMMNew);
			TotalOweyuNewList.add(MMOweElecSumMMNew);
			TotalOweyuNewList.add(MSOweElecSumMMNew);
			TotalOweyuNewList.add(ZOweElecSumMMNew);
			
			BigDecimal TotalOweyuNew = new BigDecimal(0);
			for(BigDecimal g:TotalOweyuNewList) {	  	 
				TotalOweyuNew = TotalOweyuNew.add(g);  
			}
			
			
			int TotalAllhuNew = MTotalhuNew + STotalhuNew + G1TotalhuNew + G2TotalhuNew + G3TotalhuNew + WMTotalhuNew + WSTotalhuNew + WGTotalhuNew + MMTotalhuNew + MSTotalhuNew + ZTotalhuNew;
			
			
			List<BigDecimal> TotalAllyuNewList = new ArrayList<>();
			
			TotalAllyuNewList.add(MTotalyuNew);
			TotalAllyuNewList.add(STotalyuNew);
			TotalAllyuNewList.add(G1TotalyuNew);
			TotalAllyuNewList.add(G2TotalyuNew);
			TotalAllyuNewList.add(G3TotalyuNew);
			TotalAllyuNewList.add(WMTotalyuNew);
			TotalAllyuNewList.add(WSTotalyuNew);
			TotalAllyuNewList.add(WGTotalyuNew);
			TotalAllyuNewList.add(MMTotalyuNew);
			TotalAllyuNewList.add(MSTotalyuNew);
			TotalAllyuNewList.add(ZTotalyuNew);
			
			BigDecimal TotalAllyuNew = new BigDecimal(0);
			for(BigDecimal h:TotalAllyuNewList) {	  	 
				TotalAllyuNew = TotalAllyuNew.add(h);  
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
		if(elec.getUserOrg().equals("3")) {
			
			
			int AmountTotalMM = MElecAmountMM + SElecAmountMM + G1ElecAmountMM;
			map.put("AmountTotalMM", AmountTotalMM);
			
			//合计上月
			int TotalPrehuOld = MPreElecCountMMOld + SPreElecCountMMOld + G1PreElecCountMMOld;
			
			
			List<BigDecimal> TotalPreyuOldList = new ArrayList<>();
			TotalPreyuOldList.add(MPreElecSumMMOld);
			TotalPreyuOldList.add(SPreElecSumMMOld);
			TotalPreyuOldList.add(G1PreElecSumMMOld);
			
			
			BigDecimal TotalPreyuOld = new BigDecimal(0);
			for(BigDecimal a:TotalPreyuOldList) {	  	 
				TotalPreyuOld = TotalPreyuOld.add(a);  
			}
			
			int TotalOwehuOld = MOweElecCountMMOld + SOweElecCountMMOld + G1OweElecCountMMOld;
			
			List<BigDecimal> TotalOweyuOldList = new ArrayList<>();
			TotalOweyuOldList.add(MOweElecSumMMOld);
			TotalOweyuOldList.add(SOweElecSumMMOld);
			TotalOweyuOldList.add(G1OweElecSumMMOld);
			
			BigDecimal TotalOweyuOld = new BigDecimal(0);
			for(BigDecimal b:TotalOweyuOldList) {	  	 
				TotalOweyuOld = TotalOweyuOld.add(b);  
			}
			
		
			
			int TotalAllhuOld = MTotalhuOld + STotalhuOld + G1TotalhuOld;
			
			
			List<BigDecimal> TotalAllyuOldList = new ArrayList<>();
			TotalAllyuOldList.add(MTotalyuOld);
			TotalAllyuOldList.add(STotalyuOld);
			TotalAllyuOldList.add(G1TotalyuOld);

			BigDecimal TotalAllyuOld = new BigDecimal(0);
			for(BigDecimal c:TotalAllyuOldList) {	  	 
				TotalAllyuOld = TotalAllyuOld.add(c);  
			}
			
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MElecCostMMCount + SElecCostMMCount  + G1ElecCostMMCount;
			
			
			List<BigDecimal> TotalCostyuList = new ArrayList<>();
			
			TotalCostyuList.add(MElecCostMMSum);
			TotalCostyuList.add(SElecCostMMSum);
			TotalCostyuList.add(G1ElecCostMMSum);
			
			BigDecimal TotalCostyu = new BigDecimal(0);
			for(BigDecimal d:TotalCostyuList) {	  	 
				TotalCostyu = TotalCostyu.add(d);  
			}


			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MElecLogMMCount + SElecLogMMCount + G1ElecLogMMCount;
			
			
			
			List<BigDecimal> TotalLogyuList = new ArrayList<>();
			
			TotalLogyuList.add(MElecLogMMSum);
			TotalLogyuList.add(SElecLogMMSum);
			TotalLogyuList.add(G1ElecLogMMSum);

			
			BigDecimal TotalLogyu = new BigDecimal(0);
			for(BigDecimal e:TotalLogyuList) {	  	 
				TotalLogyu = TotalLogyu.add(e);  
			}
			

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreElecCountMMNew + SPreElecCountMMNew + G1PreElecCountMMNew;
			
			
			List<BigDecimal> TotalPreyuNewList = new ArrayList<>();
			
			TotalPreyuNewList.add(MPreElecSumMMNew);
			TotalPreyuNewList.add(SPreElecSumMMNew);
			TotalPreyuNewList.add(G1PreElecSumMMNew);
			
			BigDecimal TotalPreyuNew = new BigDecimal(0);
			for(BigDecimal f:TotalPreyuNewList) {	  	 
				TotalPreyuNew = TotalPreyuNew.add(f);  
			}
			
			int TotalOwehuNew = MOweElecCountMMNew + SOweElecCountMMNew + G1OweElecCountMMNew;
			
			
			List<BigDecimal> TotalOweyuNewList = new ArrayList<>();
			
			TotalOweyuNewList.add(MOweElecSumMMNew);
			TotalOweyuNewList.add(SOweElecSumMMNew);
			TotalOweyuNewList.add(G1OweElecSumMMNew);

			BigDecimal TotalOweyuNew = new BigDecimal(0);
			for(BigDecimal g:TotalOweyuNewList) {	  	 
				TotalOweyuNew = TotalOweyuNew.add(g);  
			}
			
			int TotalAllhuNew = MTotalhuNew + STotalhuNew + G1TotalhuNew;
			
			
			List<BigDecimal> TotalAllyuNewList = new ArrayList<>();
			
			TotalAllyuNewList.add(MTotalyuNew);
			TotalAllyuNewList.add(STotalyuNew);
			TotalAllyuNewList.add(G1TotalyuNew);
			
			BigDecimal TotalAllyuNew = new BigDecimal(0);
			for(BigDecimal h:TotalAllyuNewList) {	  	 
				TotalAllyuNew = TotalAllyuNew.add(h);  
			}
			
			
			map.put("TotalPrehuNew", TotalPrehuNew);
			map.put("TotalPreyuNew", TotalPreyuNew);
			map.put("TotalOwehuNew", TotalOwehuNew);
			map.put("TotalOweyuNew", TotalOweyuNew);
			
			map.put("TotalAllhuNew", TotalAllhuNew);
			map.put("TotalAllyuNew", TotalAllyuNew);
			
			map.put("type", 3);
		}
			
			

			//查询单价
			List<ElecPriceDO> elecPriceDO = new ArrayList<>();
			elecPriceDO = elecService.ElecPrice();

			
			BigDecimal priceTotal = new BigDecimal(0);
			//判断
			for(int i=0 ; i<elecPriceDO.size();i++){
				if(elecPriceDO.get(i).getElecOrg().equals("3") &&elec.getUserOrg().equals("3")){
					
					BigDecimal MElecPrice = elecPriceDO.get(i).getmElec();
					map.put("MElecPrice",MElecPrice);
					
					BigDecimal SElecPrice = elecPriceDO.get(i).getsElec();
					map.put("SElecPrice",SElecPrice);
					
					BigDecimal G1ElecPrice = elecPriceDO.get(i).getG1Elec();
					map.put("G1ElecPrice",G1ElecPrice);
					
					
					List<BigDecimal> priceTotalList = new ArrayList<>();
					
					priceTotalList.add(MElecPrice);
					priceTotalList.add(SElecPrice);
					priceTotalList.add(G1ElecPrice);
	
					
					for(BigDecimal s:priceTotalList) {	  	 
						priceTotal = priceTotal.add(s);  
					}
					
					map.put("PriceTotal",priceTotal);
				}
				if(elecPriceDO.get(i).getElecOrg().equals("2")&&elec.getUserOrg().equals("2")){
					
					BigDecimal MElecPrice = elecPriceDO.get(i).getmElec();
					map.put("MElecPrice",MElecPrice);
					
					BigDecimal SElecPrice = elecPriceDO.get(i).getsElec();
					map.put("SElecPrice",SElecPrice);
					
					BigDecimal G1ElecPrice = elecPriceDO.get(i).getG1Elec();
					map.put("G1ElecPrice",G1ElecPrice);
					
					BigDecimal G2ElecPrice = elecPriceDO.get(i).getG2Elec();
					map.put("G2ElecPrice",G2ElecPrice);
					
					BigDecimal G3ElecPrice = elecPriceDO.get(i).getG3Elec();
					map.put("G3ElecPrice",G3ElecPrice);
					
					BigDecimal WMElecPrice = elecPriceDO.get(i).getWmElec();
					map.put("WMElecPrice",WMElecPrice);
					
					BigDecimal WSElecPrice = elecPriceDO.get(i).getWsElec();
					map.put("WSElecPrice",WSElecPrice);
					
					BigDecimal WGElecPrice = elecPriceDO.get(i).getWgElec();
					map.put("WGElecPrice",WGElecPrice);
					
					BigDecimal MMElecPrice = elecPriceDO.get(i).getMmElec();
					map.put("MMElecPrice",MMElecPrice);
					
					BigDecimal MSElecPrice = elecPriceDO.get(i).getMsElec();
					map.put("MSElecPrice",MSElecPrice);
					
					BigDecimal ZElecPrice = elecPriceDO.get(i).getzElec();
					map.put("ZElecPrice",ZElecPrice);
					
					
					
					
					List<BigDecimal> priceTotalList = new ArrayList<>();
					
					priceTotalList.add(MElecPrice);
					priceTotalList.add(SElecPrice);
					priceTotalList.add(G1ElecPrice);
					priceTotalList.add(G2ElecPrice);
					priceTotalList.add(G3ElecPrice);
					priceTotalList.add(WMElecPrice);
					priceTotalList.add(WSElecPrice);
					priceTotalList.add(WGElecPrice);
					priceTotalList.add(MMElecPrice);
					priceTotalList.add(MSElecPrice);
					priceTotalList.add(ZElecPrice);
					
					for(BigDecimal s:priceTotalList) {	  	 
						priceTotal = priceTotal.add(s);  
					}
					
					
					map.put("PriceTotal",priceTotal);
				}
			}
			
			
			return map;
			
			
			
		}
   	  
	
	
	

	
	
	  @ResponseBody
	  @GetMapping("/exportElecMM")
	  public void exportElecMM(ElecVo elec,ElecLogVo elecLog,HttpServletResponse response){
	  
		  
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				HSSFWorkbook workbook = new HSSFWorkbook();
				OutputStream out = response.getOutputStream();
				
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","用户电价","用户电费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","用户电价","用户电费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","电费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
				
				String createTime = elec.getCreateTime();
				String userId = elec.getUserId();
				
				String userOrg = "";
				if ("2".equals(elec.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(elec.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				
				List<ElecVo> elecPreList = elecService.exprotElecPre(elec);
				List<Object[]> elecPredataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecPreList.size(); i++ ) {
					Object[] arrObj = new Object[headers1.length];
					arrObj[0] = elecPreList.get(i).getId();
					arrObj[1] = elecPreList.get(i).getUserId();
					arrObj[2] = elecPreList.get(i).getUserName();
					arrObj[3] = elecPreList.get(i).getUserOrgName();
					if ("A".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = elecPreList.get(i).getUserTell();
					arrObj[6] = elecPreList.get(i).getWagesId();
					if ("1".equals(elecPreList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(elecPreList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "矿民用";
					} else if ("2".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "矿商业";
					}else if ("3".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "矿工业1";
					}else if ("4".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "矿工业2";
					}else if ("5".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "矿工业3";
					}else if ("6".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "乌民用";
					}else if ("7".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "乌商业";
					}else if ("8".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "乌工业";
					}else if ("9".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "煤田民用";
					}else if ("10".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "煤田商业";
					}else if ("11".equals(elecPreList.get(i).getElecType())) {
						arrObj[5] =   "政府部门";
					}
					arrObj[9] = elecPreList.get(i).getElecPrice();
					arrObj[10] = elecPreList.get(i).getElecCost();
					arrObj[11] = elecPreList.get(i).getElecSum();
					arrObj[12] = elecPreList.get(i).getCreateTime();
					arrObj[13] = elecPreList.get(i).getCreateBy();
					arrObj[14] = formatter.format(elecPreList.get(i).getUpdateTime());
					arrObj[15] = elecPreList.get(i).getUpdateBy();
					arrObj[16] = elecPreList.get(i).getRemark();
					elecPredataList.add(arrObj);
				}
				
				List<ElecVo> elecOweList = elecService.exprotElecOwe(elec);
				List<Object[]> elecOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers2.length];
					arrObj[0] = elecOweList.get(i).getId();
					arrObj[1] = elecOweList.get(i).getUserId();
					arrObj[2] = elecOweList.get(i).getUserName();
					arrObj[3] = elecOweList.get(i).getUserOrgName();
					if ("A".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = elecOweList.get(i).getUserTell();
					arrObj[6] = elecOweList.get(i).getWagesId();
					if ("1".equals(elecOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(elecOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "矿民用";
					} else if ("2".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "矿商业";
					}else if ("3".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "矿工业1";
					}else if ("4".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "矿工业2";
					}else if ("5".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "矿工业3";
					}else if ("6".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "乌民用";
					}else if ("7".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "乌商业";
					}else if ("8".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "乌工业";
					}else if ("9".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "煤田民用";
					}else if ("10".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "煤田商业";
					}else if ("11".equals(elecOweList.get(i).getElecType())) {
						arrObj[5] =   "政府部门";
					}
					arrObj[9] = elecOweList.get(i).getElecPrice();
					arrObj[10] = elecOweList.get(i).getElecCost();
					arrObj[11] = elecOweList.get(i).getElecSum();
					arrObj[12] = elecOweList.get(i).getCreateTime();
					arrObj[13] = elecOweList.get(i).getCreateBy();
					arrObj[14] = formatter.format(elecOweList.get(i).getUpdateTime());
					arrObj[15] = elecOweList.get(i).getUpdateBy();
					arrObj[16] = elecOweList.get(i).getRemark();
					elecOwedataList.add(arrObj);
				}
				
				
				List<ElecLogVo> elecLogList = elecLogService.exprotElecLogMM(elecLog);
				List<Object[]> elecLogdataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecLogList.size(); i++ ) {
					Object[] arrObj = new Object[headers3.length];
					
					arrObj[0] = elecLogList.get(i).getId();
					arrObj[1] = elecLogList.get(i).getUserId();
					arrObj[2] = elecLogList.get(i).getUserName();
					arrObj[3] = elecLogList.get(i).getUserOrgName();
					if ("A".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					if ("1".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿民用";
					} else if ("2".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿商业";
					} else if ("3".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿工业1";
					} else if ("4".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿工业2";
					} else if ("5".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿工业3";
					} else if ("6".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "乌民用";
					} else if ("7".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "乌商业";
					} else if ("8".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "乌工业";
					} else if ("9".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "煤田民用";
					} else if ("10".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "煤田商用";
					} else if ("11".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "政府部门";
					} 
					arrObj[6] = elecLogList.get(i).getElecMoney();
					arrObj[7] = formatter.format(elecLogList.get(i).getMoneyDate());
					arrObj[8] = elecLogList.get(i).getCreateTime();
					arrObj[9] = elecLogList.get(i).getCreateBy();
					arrObj[10] = formatter.format(elecLogList.get(i).getUpdateTime());
					arrObj[11] = elecLogList.get(i).getUpdateBy();
					arrObj[12] = elecLogList.get(i).getRemark();
					
					elecLogdataList.add(arrObj);
				}

				

				String title =  "电费当月报表.xlsx";

				String oneheaders1 = createTime+userOrg+userId+"电费预存表";
				String oneheaders2 = createTime+userOrg+userId+"电费欠费表";
				String oneheaders3 = createTime+userOrg+userId+"电费实收表";
				
				
				String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
				response.setContentType("octets/stream");
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				
				ExportExcelSheet ex1  = new ExportExcelSheet("预存表", oneheaders1, headers1, elecPredataList);
				ExportExcelSheet ex2  = new ExportExcelSheet("欠费表", oneheaders2, headers2, elecOwedataList);
				ExportExcelSheet ex3  = new ExportExcelSheet("实收表", oneheaders3, headers3, elecLogdataList);

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
