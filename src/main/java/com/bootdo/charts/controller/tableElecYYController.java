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
@RequestMapping("/charts/tableElecYY")
public class tableElecYYController {
	
	
	
	@Autowired private ElecService elecService;
	@Autowired private ElecLogService elecLogService;
	

	
	@GetMapping()
	@RequiresPermissions("charts:tableYY")
	public String tableElecYY(){
	    return "charts/tableElecYY";
	}
	
	   	  

	@ResponseBody
	@PostMapping("/tableElecYY")
	public  Map<String,Object>  tableElecYY(ElecVo elec,ElecLogVo elecLog){
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		String createTime = elec.getCreateTime();
		int year = Integer.parseInt(createTime) - 1;
		String oldYear = String.valueOf(year);
		String oldcreateTime = oldYear+"-"+"12";
		
		
		
		elec.setElecType("1");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int MPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal MPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int MOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal MOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int MTotalhuOld = MPreElecCountYYOld + MOweElecCountYYOld;
		BigDecimal MTotalyuOld = MPreElecSumYYOld.add(MOweElecSumYYOld);
		
		map.put("MPreElecCountYYOld",MPreElecCountYYOld);
		map.put("MPreElecSumYYOld",MPreElecSumYYOld);
		map.put("MOweElecCountYYOld",MOweElecCountYYOld);
		map.put("MOweElecSumYYOld",MOweElecSumYYOld);

		map.put("MTotalhuOld",MTotalhuOld);
		map.put("MTotalyuOld",MTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int MElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("MElecAmountYY",MElecAmountYY);
		
		int MElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal MElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("MElecCostYYCount",MElecCostYYCount);
		map.put("MElecCostYYSum",MElecCostYYSum);
		
	//累计实收
		int MElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal MElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("MElecLogYYCount",MElecLogYYCount);
		map.put("MElecLogYYSum",MElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		
		int MPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal MPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int MOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal MOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int MTotalhuNew = MPreElecCountYYNew + MOweElecCountYYNew;
		BigDecimal MTotalyuNew = MPreElecSumYYNew.add(MOweElecSumYYNew);

		map.put("MPreElecCountYYNew",MPreElecCountYYNew);
		map.put("MPreElecSumYYNew",MPreElecSumYYNew);
		map.put("MOweElecCountYYNew",MOweElecCountYYNew);
		map.put("MOweElecSumYYNew",MOweElecSumYYNew);

		map.put("MTotalhuNew",MTotalhuNew);
		map.put("MTotalyuNew",MTotalyuNew);		
		
		
		
		
		
		
		
		
		elec.setElecType("2");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int SPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal SPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int SOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal SOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int STotalhuOld = SPreElecCountYYOld + SOweElecCountYYOld;
		BigDecimal STotalyuOld = SPreElecSumYYOld.add(SOweElecSumYYOld);
		
		map.put("SPreElecCountYYOld",SPreElecCountYYOld);
		map.put("SPreElecSumYYOld",SPreElecSumYYOld);
		map.put("SOweElecCountYYOld",SOweElecCountYYOld);
		map.put("SOweElecSumYYOld",SOweElecSumYYOld);

		map.put("STotalhuOld",STotalhuOld);
		map.put("STotalyuOld",STotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int SElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("SElecAmountYY",SElecAmountYY);	
		
		int SElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal SElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("SElecCostYYCount",SElecCostYYCount);
		map.put("SElecCostYYSum",SElecCostYYSum);
		
	//累计实收
		int SElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal SElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("SElecLogYYCount",SElecLogYYCount);
		map.put("SElecLogYYSum",SElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int SPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal SPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int SOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal SOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int STotalhuNew = SPreElecCountYYNew + SOweElecCountYYNew;
		BigDecimal STotalyuNew = SPreElecSumYYNew.add(SOweElecSumYYNew);

		map.put("SPreElecCountYYNew",SPreElecCountYYNew);
		map.put("SPreElecSumYYNew",SPreElecSumYYNew);
		map.put("SOweElecCountYYNew",SOweElecCountYYNew);
		map.put("SOweElecSumYYNew",SOweElecSumYYNew);

		map.put("STotalhuNew",STotalhuNew);
		map.put("STotalyuNew",STotalyuNew);		
		
		
		
	
		
		elec.setElecType("3");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int G1PreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal G1PreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int G1OweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal G1OweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int G1TotalhuOld = G1PreElecCountYYOld + G1OweElecCountYYOld;
		BigDecimal G1TotalyuOld = G1PreElecSumYYOld.add(G1OweElecSumYYOld);
		
		map.put("G1PreElecCountYYOld",G1PreElecCountYYOld);
		map.put("G1PreElecSumYYOld",G1PreElecSumYYOld);
		map.put("G1OweElecCountYYOld",G1OweElecCountYYOld);
		map.put("G1OweElecSumYYOld",G1OweElecSumYYOld);

		map.put("G1TotalhuOld",G1TotalhuOld);
		map.put("G1TotalyuOld",G1TotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int G1ElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("G1ElecAmountYY",G1ElecAmountYY);
		
		int G1ElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal G1ElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("G1ElecCostYYCount",G1ElecCostYYCount);
		map.put("G1ElecCostYYSum",G1ElecCostYYSum);
		
	//累计实收
		int G1ElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal G1ElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("G1ElecLogYYCount",G1ElecLogYYCount);
		map.put("G1ElecLogYYSum",G1ElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int G1PreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal G1PreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int G1OweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal G1OweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int G1TotalhuNew = G1PreElecCountYYNew + G1OweElecCountYYNew;
		BigDecimal G1TotalyuNew = G1PreElecSumYYNew.add(G1OweElecSumYYNew);

		map.put("G1PreElecCountYYNew",G1PreElecCountYYNew);
		map.put("G1PreElecSumYYNew",G1PreElecSumYYNew);
		map.put("G1OweElecCountYYNew",G1OweElecCountYYNew);
		map.put("G1OweElecSumYYNew",G1OweElecSumYYNew);

		map.put("G1TotalhuNew",G1TotalhuNew);
		map.put("G1TotalyuNew",G1TotalyuNew);		
		
		
		
		
		
		
		
		elec.setElecType("4");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int G2PreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal G2PreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int G2OweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal G2OweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int G2TotalhuOld = G2PreElecCountYYOld + G2OweElecCountYYOld;
		BigDecimal G2TotalyuOld = G2PreElecSumYYOld.add(G2OweElecSumYYOld);
		
		map.put("G2PreElecCountYYOld",G2PreElecCountYYOld);
		map.put("G2PreElecSumYYOld",G2PreElecSumYYOld);
		map.put("G2OweElecCountYYOld",G2OweElecCountYYOld);
		map.put("G2OweElecSumYYOld",G2OweElecSumYYOld);

		map.put("G2TotalhuOld",G2TotalhuOld);
		map.put("G2TotalyuOld",G2TotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int G2ElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("G2ElecAmountYY",G2ElecAmountYY);	
		
		int G2ElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal G2ElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("G2ElecCostYYCount",G2ElecCostYYCount);
		map.put("G2ElecCostYYSum",G2ElecCostYYSum);
		
	//累计实收
		int G2ElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal G2ElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("G2ElecLogYYCount",G2ElecLogYYCount);
		map.put("G2ElecLogYYSum",G2ElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int G2PreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal G2PreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int G2OweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal G2OweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int G2TotalhuNew = G2PreElecCountYYNew + G2OweElecCountYYNew;
		BigDecimal G2TotalyuNew = G2PreElecSumYYNew.add(G2OweElecSumYYNew);

		map.put("G2PreElecCountYYNew",G2PreElecCountYYNew);
		map.put("G2PreElecSumYYNew",G2PreElecSumYYNew);
		map.put("G2OweElecCountYYNew",G2OweElecCountYYNew);
		map.put("G2OweElecSumYYNew",G2OweElecSumYYNew);

		map.put("G2TotalhuNew",G2TotalhuNew);
		map.put("G2TotalyuNew",G2TotalyuNew);		
		
		
		
		
		

		elec.setElecType("5");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int G3PreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal G3PreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int G3OweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal G3OweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int G3TotalhuOld = G3PreElecCountYYOld + G3OweElecCountYYOld;
		BigDecimal G3TotalyuOld = G3PreElecSumYYOld.add(G3OweElecSumYYOld);
		
		map.put("G3PreElecCountYYOld",G3PreElecCountYYOld);
		map.put("G3PreElecSumYYOld",G3PreElecSumYYOld);
		map.put("G3OweElecCountYYOld",G3OweElecCountYYOld);
		map.put("G3OweElecSumYYOld",G3OweElecSumYYOld);

		map.put("G3TotalhuOld",G3TotalhuOld);
		map.put("G3TotalyuOld",G3TotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int G3ElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("G3ElecAmountYY",G3ElecAmountYY);
		
		int G3ElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal G3ElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("G3ElecCostYYCount",G3ElecCostYYCount);
		map.put("G3ElecCostYYSum",G3ElecCostYYSum);
		
	//累计实收
		int G3ElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal G3ElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("G3ElecLogYYCount",G3ElecLogYYCount);
		map.put("G3ElecLogYYSum",G3ElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int G3PreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal G3PreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int G3OweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal G3OweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int G3TotalhuNew = G3PreElecCountYYNew + G3OweElecCountYYNew;
		BigDecimal G3TotalyuNew = G3PreElecSumYYNew.add(G3OweElecSumYYNew);

		map.put("G3PreElecCountYYNew",G3PreElecCountYYNew);
		map.put("G3PreElecSumYYNew",G3PreElecSumYYNew);
		map.put("G3OweElecCountYYNew",G3OweElecCountYYNew);
		map.put("G3OweElecSumYYNew",G3OweElecSumYYNew);

		map.put("G3TotalhuNew",G3TotalhuNew);
		map.put("G3TotalyuNew",G3TotalyuNew);		
		
		
		
		
		

		elec.setElecType("6");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int WMPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal WMPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int WMOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal WMOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int WMTotalhuOld = WMPreElecCountYYOld + WMOweElecCountYYOld;
		BigDecimal WMTotalyuOld = WMPreElecSumYYOld.add(WMOweElecSumYYOld);
		
		map.put("WMPreElecCountYYOld",WMPreElecCountYYOld);
		map.put("WMPreElecSumYYOld",WMPreElecSumYYOld);
		map.put("WMOweElecCountYYOld",WMOweElecCountYYOld);
		map.put("WMOweElecSumYYOld",WMOweElecSumYYOld);

		map.put("WMTotalhuOld",WMTotalhuOld);
		map.put("WMTotalyuOld",WMTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int WMElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("WMElecAmountYY",WMElecAmountYY);

		
		int WMElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal WMElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("WMElecCostYYCount",WMElecCostYYCount);
		map.put("WMElecCostYYSum",WMElecCostYYSum);
		
	//累计实收
		int WMElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal WMElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("WMElecLogYYCount",WMElecLogYYCount);
		map.put("WMElecLogYYSum",WMElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int WMPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal WMPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int WMOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal WMOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int WMTotalhuNew = WMPreElecCountYYNew + WMOweElecCountYYNew;
		BigDecimal WMTotalyuNew = WMPreElecSumYYNew.add(WMOweElecSumYYNew);

		map.put("WMPreElecCountYYNew",WMPreElecCountYYNew);
		map.put("WMPreElecSumYYNew",WMPreElecSumYYNew);
		map.put("WMOweElecCountYYNew",WMOweElecCountYYNew);
		map.put("WMOweElecSumYYNew",WMOweElecSumYYNew);

		map.put("WMTotalhuNew",WMTotalhuNew);
		map.put("WMTotalyuNew",WMTotalyuNew);		
		
		
		

		elec.setElecType("7");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int WSPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal WSPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int WSOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal WSOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int WSTotalhuOld = WSPreElecCountYYOld + WSOweElecCountYYOld;
		BigDecimal WSTotalyuOld = WSPreElecSumYYOld.add( WSOweElecSumYYOld);
		
		map.put("WSPreElecCountYYOld",WSPreElecCountYYOld);
		map.put("WSPreElecSumYYOld",WSPreElecSumYYOld);
		map.put("WSOweElecCountYYOld",WSOweElecCountYYOld);
		map.put("WSOweElecSumYYOld",WSOweElecSumYYOld);

		map.put("WSTotalhuOld",WSTotalhuOld);
		map.put("WSTotalyuOld",WSTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int WSElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("WSElecAmountYY",WSElecAmountYY);	
		
		int WSElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal WSElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("WSElecCostYYCount",WSElecCostYYCount);
		map.put("WSElecCostYYSum",WSElecCostYYSum);
		
	//累计实收
		int WSElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal WSElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("WSElecLogYYCount",WSElecLogYYCount);
		map.put("WSElecLogYYSum",WSElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int WSPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal WSPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int WSOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal WSOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int WSTotalhuNew = WSPreElecCountYYNew + WSOweElecCountYYNew;
		BigDecimal WSTotalyuNew = WSPreElecSumYYNew.add( WSOweElecSumYYNew);

		map.put("WSPreElecCountYYNew",WSPreElecCountYYNew);
		map.put("WSPreElecSumYYNew",WSPreElecSumYYNew);
		map.put("WSOweElecCountYYNew",WSOweElecCountYYNew);
		map.put("WSOweElecSumYYNew",WSOweElecSumYYNew);

		map.put("WSTotalhuNew",WSTotalhuNew);
		map.put("WSTotalyuNew",WSTotalyuNew);		
		
		
		
		

		elec.setElecType("8");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int WGPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal WGPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int WGOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal WGOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int WGTotalhuOld = WGPreElecCountYYOld + WGOweElecCountYYOld;
		BigDecimal WGTotalyuOld = WGPreElecSumYYOld.add(WGOweElecSumYYOld);
		
		map.put("WGPreElecCountYYOld",WGPreElecCountYYOld);
		map.put("WGPreElecSumYYOld",WGPreElecSumYYOld);
		map.put("WGOweElecCountYYOld",WGOweElecCountYYOld);
		map.put("WGOweElecSumYYOld",WGOweElecSumYYOld);

		map.put("WGTotalhuOld",WGTotalhuOld);
		map.put("WGTotalyuOld",WGTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int WGElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("WGElecAmountYY",WGElecAmountYY);
		
		int WGElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal WGElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("WGElecCostYYCount",WGElecCostYYCount);
		map.put("WGElecCostYYSum",WGElecCostYYSum);
		
	//累计实收
		int WGElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal WGElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("WGElecLogYYCount",WGElecLogYYCount);
		map.put("WGElecLogYYSum",WGElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int WGPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal WGPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int WGOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal WGOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int WGTotalhuNew = WGPreElecCountYYNew + WGOweElecCountYYNew;
		BigDecimal WGTotalyuNew = WGPreElecSumYYNew.add(WGOweElecSumYYNew);

		map.put("WGPreElecCountYYNew",WGPreElecCountYYNew);
		map.put("WGPreElecSumYYNew",WGPreElecSumYYNew);
		map.put("WGOweElecCountYYNew",WGOweElecCountYYNew);
		map.put("WGOweElecSumYYNew",WGOweElecSumYYNew);

		map.put("WGTotalhuNew",WGTotalhuNew);
		map.put("WGTotalyuNew",WGTotalyuNew);		
		
		
		
		

		elec.setElecType("9");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int MMPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal MMPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int MMOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal MMOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int MMTotalhuOld = MMPreElecCountYYOld + MMOweElecCountYYOld;
		BigDecimal MMTotalyuOld = MMPreElecSumYYOld.add(MMOweElecSumYYOld);
		
		map.put("MMPreElecCountYYOld",MMPreElecCountYYOld);
		map.put("MMPreElecSumYYOld",MMPreElecSumYYOld);
		map.put("MMOweElecCountYYOld",MMOweElecCountYYOld);
		map.put("MMOweElecSumYYOld",MMOweElecSumYYOld);

		map.put("MMTotalhuOld",MMTotalhuOld);
		map.put("MMTotalyuOld",MMTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int MMElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("MMElecAmountYY",MMElecAmountYY);	

		int MMElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal MMElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("MMElecCostYYCount",MMElecCostYYCount);
		map.put("MMElecCostYYSum",MMElecCostYYSum);
		
	//累计实收
		int MMElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal MMElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("MMElecLogYYCount",MMElecLogYYCount);
		map.put("MMElecLogYYSum",MMElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int MMPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal MMPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int MMOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal MMOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int MMTotalhuNew = MMPreElecCountYYNew + MMOweElecCountYYNew;
		BigDecimal MMTotalyuNew = MMPreElecSumYYNew .add(MMOweElecSumYYNew);

		map.put("MMPreElecCountYYNew",MMPreElecCountYYNew);
		map.put("MMPreElecSumYYNew",MMPreElecSumYYNew);
		map.put("MMOweElecCountYYNew",MMOweElecCountYYNew);
		map.put("MMOweElecSumYYNew",MMOweElecSumYYNew);

		map.put("MMTotalhuNew",MMTotalhuNew);
		map.put("MMTotalyuNew",MMTotalyuNew);		
		
		
		
		

		elec.setElecType("10");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int MSPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal MSPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int MSOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal MSOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int MSTotalhuOld = MSPreElecCountYYOld + MSOweElecCountYYOld;
		BigDecimal MSTotalyuOld = MSPreElecSumYYOld.add(MSOweElecSumYYOld);
		
		map.put("MSPreElecCountYYOld",MSPreElecCountYYOld);
		map.put("MSPreElecSumYYOld",MSPreElecSumYYOld);
		map.put("MSOweElecCountYYOld",MSOweElecCountYYOld);
		map.put("MSOweElecSumYYOld",MSOweElecSumYYOld);

		map.put("MSTotalhuOld",MSTotalhuOld);
		map.put("MSTotalyuOld",MSTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int MSElecAmountYY =elecService.ElecAmountYY(elec);	
		map.put("MSElecAmountYY",MSElecAmountYY);

		int MSElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal MSElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("MSElecCostYYCount",MSElecCostYYCount);
		map.put("MSElecCostYYSum",MSElecCostYYSum);
		
	//累计实收
		int MSElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal MSElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("MSElecLogYYCount",MSElecLogYYCount);
		map.put("MSElecLogYYSum",MSElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int MSPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal MSPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int MSOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal MSOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int MSTotalhuNew = MSPreElecCountYYNew + MSOweElecCountYYNew;
		BigDecimal MSTotalyuNew = MSPreElecSumYYNew.add(MSOweElecSumYYNew);

		map.put("MSPreElecCountYYNew",MSPreElecCountYYNew);
		map.put("MSPreElecSumYYNew",MSPreElecSumYYNew);
		map.put("MSOweElecCountYYNew",MSOweElecCountYYNew);
		map.put("MSOweElecSumYYNew",MSOweElecSumYYNew);

		map.put("MSTotalhuNew",MSTotalhuNew);
		map.put("MSTotalyuNew",MSTotalyuNew);		
		
		
		
		
		

		elec.setElecType("11");
		elec.setCreateTime(oldcreateTime);
	//年初余额
		int ZPreElecCountYYOld =elecService.PreElecCountYYOld(elec);
		BigDecimal ZPreElecSumYYOld =elecService.PreElecSumYYOld(elec);
		int ZOweElecCountYYOld =elecService.OweElecCountYYOld(elec);
		BigDecimal ZOweElecSumYYOld =elecService.OweElecSumYYOld(elec);
		
		int ZTotalhuOld = ZPreElecCountYYOld + ZOweElecCountYYOld;
		BigDecimal ZTotalyuOld = ZPreElecSumYYOld.add(ZOweElecSumYYOld);
		
		map.put("ZPreElecCountYYOld",ZPreElecCountYYOld);
		map.put("ZPreElecSumYYOld",ZPreElecSumYYOld);
		map.put("ZOweElecCountYYOld",ZOweElecCountYYOld);
		map.put("ZOweElecSumYYOld",ZOweElecSumYYOld);

		map.put("ZTotalhuOld",ZTotalhuOld);
		map.put("ZTotalyuOld",ZTotalyuOld);
		
	//累计应收
		elec.setCreateTime(createTime);
		
		int ZElecAmountYY =elecService.ElecAmountYY(elec);
		map.put("ZElecAmountYY",ZElecAmountYY);
		
		int ZElecCostYYCount = elecService.ElecCostYYCount(elec);
		BigDecimal ZElecCostYYSum = elecService.ElecCostYYSum(elec);
		map.put("ZElecCostYYCount",ZElecCostYYCount);
		map.put("ZElecCostYYSum",ZElecCostYYSum);
		
	//累计实收
		int ZElecLogYYCount = elecService.ElecLogYYCount(elec);
		BigDecimal ZElecLogYYSum = elecService.ElecLogYYSum(elec);
		map.put("ZElecLogYYCount",ZElecLogYYCount);
		map.put("ZElecLogYYSum",ZElecLogYYSum);

		
   //期末余额	
		elec.setCreateTime(createTime);
		
		int ZPreElecCountYYNew =elecService.PreElecCountYYNew(elec);
		BigDecimal ZPreElecSumYYNew =elecService.PreElecSumYYNew(elec);
		int ZOweElecCountYYNew =elecService.OweElecCountYYNew(elec);
		BigDecimal ZOweElecSumYYNew =elecService.OweElecSumYYNew(elec);

		int ZTotalhuNew = ZPreElecCountYYNew + ZOweElecCountYYNew;
		BigDecimal ZTotalyuNew = ZPreElecSumYYNew.add(ZOweElecSumYYNew);

		map.put("ZPreElecCountYYNew",ZPreElecCountYYNew);
		map.put("ZPreElecSumYYNew",ZPreElecSumYYNew);
		map.put("ZOweElecCountYYNew",ZOweElecCountYYNew);
		map.put("ZOweElecSumYYNew",ZOweElecSumYYNew);

		map.put("ZTotalhuNew",ZTotalhuNew);
		map.put("ZTotalyuNew",ZTotalyuNew);		
		
		
		
		

		//五九合计
		if(elec.getUserOrg().equals("2")) {
			
			int AmountTotalYY = MElecAmountYY + SElecAmountYY + G1ElecAmountYY + G2ElecAmountYY + G3ElecAmountYY + WMElecAmountYY + WSElecAmountYY + WGElecAmountYY + MMElecAmountYY + MSElecAmountYY + ZElecAmountYY;
			map.put("AmountTotalYY", AmountTotalYY);
			
			//合计年初
			int TotalPrehuOld = MPreElecCountYYOld + SPreElecCountYYOld + G1PreElecCountYYOld + G2PreElecCountYYOld + G3PreElecCountYYOld + WMPreElecCountYYOld + WSPreElecCountYYOld + WGPreElecCountYYOld + MMPreElecCountYYOld + MSPreElecCountYYOld + ZPreElecCountYYOld;
			
			List<BigDecimal> TotalPreyuOldList = new ArrayList<>();
			TotalPreyuOldList.add(MPreElecSumYYOld);
			TotalPreyuOldList.add(SPreElecSumYYOld);
			TotalPreyuOldList.add(G1PreElecSumYYOld);
			TotalPreyuOldList.add(G2PreElecSumYYOld);
			TotalPreyuOldList.add(G3PreElecSumYYOld);
			TotalPreyuOldList.add(WMPreElecSumYYOld);
			TotalPreyuOldList.add(WSPreElecSumYYOld);
			TotalPreyuOldList.add(WGPreElecSumYYOld);
			TotalPreyuOldList.add(MMPreElecSumYYOld);
			TotalPreyuOldList.add(MSPreElecSumYYOld);
			TotalPreyuOldList.add(ZPreElecSumYYOld);
			
			
			BigDecimal TotalPreyuOld = new BigDecimal(0);
			for(BigDecimal a:TotalPreyuOldList) {	  	 
				TotalPreyuOld = TotalPreyuOld.add(a);  
			}
			
			int TotalOwehuOld = MOweElecCountYYOld + SOweElecCountYYOld + G1OweElecCountYYOld + G2OweElecCountYYOld + G3OweElecCountYYOld + WMOweElecCountYYOld + WSOweElecCountYYOld + WGOweElecCountYYOld + MMOweElecCountYYOld + MSOweElecCountYYOld + ZOweElecCountYYOld;
			
			
			List<BigDecimal> TotalOweyuOldList = new ArrayList<>();
			TotalOweyuOldList.add(MOweElecSumYYOld);
			TotalOweyuOldList.add(SOweElecSumYYOld);
			TotalOweyuOldList.add(G1OweElecSumYYOld);
			TotalOweyuOldList.add(G2OweElecSumYYOld);
			TotalOweyuOldList.add(G3OweElecSumYYOld);
			TotalOweyuOldList.add(WMOweElecSumYYOld);
			TotalOweyuOldList.add(WSOweElecSumYYOld);
			TotalOweyuOldList.add(WGOweElecSumYYOld);
			TotalOweyuOldList.add(MMOweElecSumYYOld);
			TotalOweyuOldList.add(MSOweElecSumYYOld);
			TotalOweyuOldList.add(ZOweElecSumYYOld);
			
			
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
			int TotalCosthu = MElecCostYYCount + SElecCostYYCount + G1ElecCostYYCount + G2ElecCostYYCount + G3ElecCostYYCount + WMElecCostYYCount + WSElecCostYYCount + WGElecCostYYCount + MMElecCostYYCount + MSElecCostYYCount + ZElecCostYYCount;
			
			List<BigDecimal> TotalCostyuList = new ArrayList<>();
			
			TotalCostyuList.add(MElecCostYYSum);
			TotalCostyuList.add(SElecCostYYSum);
			TotalCostyuList.add(G1ElecCostYYSum);
			TotalCostyuList.add(G2ElecCostYYSum);
			TotalCostyuList.add(G3ElecCostYYSum);
			TotalCostyuList.add(WMElecCostYYSum);
			TotalCostyuList.add(WSElecCostYYSum);
			TotalCostyuList.add(WGElecCostYYSum);
			TotalCostyuList.add(MMElecCostYYSum);
			TotalCostyuList.add(MSElecCostYYSum);
			TotalCostyuList.add(ZElecCostYYSum);
			
			BigDecimal TotalCostyu = new BigDecimal(0);
			for(BigDecimal d:TotalCostyuList) {	  	 
				TotalCostyu = TotalCostyu.add(d);  
			}

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MElecLogYYCount + SElecLogYYCount + G1ElecLogYYCount + G2ElecLogYYCount + G3ElecLogYYCount + WMElecLogYYCount + WSElecLogYYCount + WGElecLogYYCount + MMElecLogYYCount + MSElecLogYYCount + ZElecLogYYCount;
			
			
			List<BigDecimal> TotalLogyuList = new ArrayList<>();
			
			TotalLogyuList.add(MElecLogYYSum);
			TotalLogyuList.add(SElecLogYYSum);
			TotalLogyuList.add(G1ElecLogYYSum);
			TotalLogyuList.add(G2ElecLogYYSum);
			TotalLogyuList.add(G3ElecLogYYSum);
			TotalLogyuList.add(WMElecLogYYSum);
			TotalLogyuList.add(WSElecLogYYSum);
			TotalLogyuList.add(WGElecLogYYSum);
			TotalLogyuList.add(MMElecLogYYSum);
			TotalLogyuList.add(MSElecLogYYSum);
			TotalLogyuList.add(ZElecLogYYSum);
			
			BigDecimal TotalLogyu = new BigDecimal(0);
			for(BigDecimal e:TotalLogyuList) {	  	 
				TotalLogyu = TotalLogyu.add(e);  
			}
			
			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreElecCountYYNew + SPreElecCountYYNew + G1PreElecCountYYNew + G2PreElecCountYYNew + G3PreElecCountYYNew + WMPreElecCountYYNew + WSPreElecCountYYNew + WGPreElecCountYYNew + MMPreElecCountYYNew + MSPreElecCountYYNew + ZPreElecCountYYNew;
			
			
			List<BigDecimal> TotalPreyuNewList = new ArrayList<>();
			
			TotalPreyuNewList.add(MPreElecSumYYNew);
			TotalPreyuNewList.add(SPreElecSumYYNew);
			TotalPreyuNewList.add(G1PreElecSumYYNew);
			TotalPreyuNewList.add(G2PreElecSumYYNew);
			TotalPreyuNewList.add(G3PreElecSumYYNew);
			TotalPreyuNewList.add(WMPreElecSumYYNew);
			TotalPreyuNewList.add(WSPreElecSumYYNew);
			TotalPreyuNewList.add(WGPreElecSumYYNew);
			TotalPreyuNewList.add(MMPreElecSumYYNew);
			TotalPreyuNewList.add(MSPreElecSumYYNew);
			TotalPreyuNewList.add(ZPreElecSumYYNew);
			
			BigDecimal TotalPreyuNew = new BigDecimal(0);
			for(BigDecimal f:TotalPreyuNewList) {	  	 
				TotalPreyuNew = TotalPreyuNew.add(f);  
			}
			
			
			int TotalOwehuNew = MOweElecCountYYNew + SOweElecCountYYNew + G1OweElecCountYYNew + G2OweElecCountYYNew + G3OweElecCountYYNew + WMOweElecCountYYNew + WSOweElecCountYYNew + WGOweElecCountYYNew + MMOweElecCountYYNew + MSOweElecCountYYNew + ZOweElecCountYYNew;
			
			
			List<BigDecimal> TotalOweyuNewList = new ArrayList<>();
			
			TotalOweyuNewList.add(MOweElecSumYYNew);
			TotalOweyuNewList.add(SOweElecSumYYNew);
			TotalOweyuNewList.add(G1OweElecSumYYNew);
			TotalOweyuNewList.add(G2OweElecSumYYNew);
			TotalOweyuNewList.add(G3OweElecSumYYNew);
			TotalOweyuNewList.add(WMOweElecSumYYNew);
			TotalOweyuNewList.add(WSOweElecSumYYNew);
			TotalOweyuNewList.add(WGOweElecSumYYNew);
			TotalOweyuNewList.add(MMOweElecSumYYNew);
			TotalOweyuNewList.add(MSOweElecSumYYNew);
			TotalOweyuNewList.add(ZOweElecSumYYNew);
			
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
			
			
			int AmountTotalYY = MElecAmountYY + SElecAmountYY + G1ElecAmountYY;
			map.put("AmountTotalYY", AmountTotalYY);
			
			//合计年初
			int TotalPrehuOld = MPreElecCountYYOld + SPreElecCountYYOld + G1PreElecCountYYOld;
			
			
			List<BigDecimal> TotalPreyuOldList = new ArrayList<>();
			TotalPreyuOldList.add(MPreElecSumYYOld);
			TotalPreyuOldList.add(SPreElecSumYYOld);
			TotalPreyuOldList.add(G1PreElecSumYYOld);
			
			
			BigDecimal TotalPreyuOld = new BigDecimal(0);
			for(BigDecimal a:TotalPreyuOldList) {	  	 
				TotalPreyuOld = TotalPreyuOld.add(a);  
			}
			
			int TotalOwehuOld = MOweElecCountYYOld + SOweElecCountYYOld + G1OweElecCountYYOld;
			
			List<BigDecimal> TotalOweyuOldList = new ArrayList<>();
			TotalOweyuOldList.add(MOweElecSumYYOld);
			TotalOweyuOldList.add(SOweElecSumYYOld);
			TotalOweyuOldList.add(G1OweElecSumYYOld);
			
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
			int TotalCosthu = MElecCostYYCount + SElecCostYYCount  + G1ElecCostYYCount;
			
			
			List<BigDecimal> TotalCostyuList = new ArrayList<>();
			
			TotalCostyuList.add(MElecCostYYSum);
			TotalCostyuList.add(SElecCostYYSum);
			TotalCostyuList.add(G1ElecCostYYSum);
			
			BigDecimal TotalCostyu = new BigDecimal(0);
			for(BigDecimal d:TotalCostyuList) {	  	 
				TotalCostyu = TotalCostyu.add(d);  
			}


			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MElecLogYYCount + SElecLogYYCount + G1ElecLogYYCount;
			
			
			
			List<BigDecimal> TotalLogyuList = new ArrayList<>();
			
			TotalLogyuList.add(MElecLogYYSum);
			TotalLogyuList.add(SElecLogYYSum);
			TotalLogyuList.add(G1ElecLogYYSum);

			
			BigDecimal TotalLogyu = new BigDecimal(0);
			for(BigDecimal e:TotalLogyuList) {	  	 
				TotalLogyu = TotalLogyu.add(e);  
			}
			

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreElecCountYYNew + SPreElecCountYYNew + G1PreElecCountYYNew;
			
			
			List<BigDecimal> TotalPreyuNewList = new ArrayList<>();
			
			TotalPreyuNewList.add(MPreElecSumYYNew);
			TotalPreyuNewList.add(SPreElecSumYYNew);
			TotalPreyuNewList.add(G1PreElecSumYYNew);
			
			BigDecimal TotalPreyuNew = new BigDecimal(0);
			for(BigDecimal f:TotalPreyuNewList) {	  	 
				TotalPreyuNew = TotalPreyuNew.add(f);  
			}
			
			int TotalOwehuNew = MOweElecCountYYNew + SOweElecCountYYNew + G1OweElecCountYYNew;
			
			
			List<BigDecimal> TotalOweyuNewList = new ArrayList<>();
			
			TotalOweyuNewList.add(MOweElecSumYYNew);
			TotalOweyuNewList.add(SOweElecSumYYNew);
			TotalOweyuNewList.add(G1OweElecSumYYNew);

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
	  @GetMapping("/exportElecYY")
	  public void exportElecYY(ElecVo elec,ElecLogVo elecLog,HttpServletResponse response){
	  
		  
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				HSSFWorkbook workbook = new HSSFWorkbook();
				OutputStream out = response.getOutputStream();
				
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","用户电价","用户电费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","用户电价","用户电费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","电费类型","缴费金额","缴费时间","收费时间","收费人","更新时间","更新人","用户备注"};
				
				String createTime = elec.getCreateTime();
	
				String userOrg = "";
				
				if ("2".equals(elec.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(elec.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				String userType = "";
				if ("A".equals(elec.getUserType())) {
					userType =   "现金缴费";
				} else if ("B".equals(elec.getUserType())) {
					userType =   "工资代扣";
				} else if ("C".equals(elec.getUserType())) {
					userType =   "微信支付";
				} else if ("D".equals(elec.getUserType())) {
					userType =   "银行转账";
				}
				
				String userId = elec.getUserId();
			
				List<ElecVo> elecPreList = elecService.exprotElecPreYY(elec);
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
						arrObj[8] =   "矿民用";
					} else if ("2".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿商业";
					}else if ("3".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿工业1";
					}else if ("4".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿工业2";
					}else if ("5".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿工业3";
					}else if ("6".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "乌民用";
					}else if ("7".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "乌商业";
					}else if ("8".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "乌工业";
					}else if ("9".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "煤田民用";
					}else if ("10".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "煤田商业";
					}else if ("11".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "政府部门";
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
				
				List<ElecVo> elecOweList = elecService.exprotElecOweYY(elec);
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
						arrObj[8] =   "矿民用";
					} else if ("2".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿商业";
					}else if ("3".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿工业1";
					}else if ("4".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿工业2";
					}else if ("5".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿工业3";
					}else if ("6".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "乌民用";
					}else if ("7".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "乌商业";
					}else if ("8".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "乌工业";
					}else if ("9".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "煤田民用";
					}else if ("10".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "煤田商业";
					}else if ("11".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "政府部门";
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
				
				
				List<ElecLogVo> elecLogList = elecLogService.exprotElecLogYY(elecLog);
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

				

				String title =  "电费全年报表.xlsx";

				String oneheaders1 = createTime+userOrg+userType+userId+"电费全年预存表";
				String oneheaders2 = createTime+userOrg+userType+userId+"电费全年欠费表";
				String oneheaders3 = createTime+userOrg+userType+userId+"电费全年实收表";
				
				
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
