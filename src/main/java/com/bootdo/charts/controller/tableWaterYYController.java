package com.bootdo.charts.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.bootdo.water.domain.WaterPriceDO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.ExportExcelSheet;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.WaterLogVo;
import com.bootdo.water.service.WaterService;
import com.bootdo.water.vo.WaterVo;


 
@Controller
@RequestMapping("/charts/tableWaterYY")
public class tableWaterYYController {
	
	
	
	@Autowired private WaterService waterService;
	@Autowired private WaterLogService waterLogService;
	
	
	@GetMapping()
	@RequiresPermissions("charts:tableYY")
	public String tableWaterYY(){
		
	    return "charts/tableWaterYY";
	}
	
	
	@ResponseBody
	@PostMapping("/tableWaterYY")
	public  Map<String,Integer>  tableWaterYY(WaterVo water,WaterLogVo waterLog){
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		String createTime = water.getCreateTime();
		int year = Integer.parseInt(createTime) - 1;
		String oldYear = String.valueOf(year);
		String oldcreateTime = oldYear+"-"+"12";
		
		
		
		water.setWaterType("1");
		water.setCreateTime(oldcreateTime);
	//年初余额
		int MPreWaterCountYYOld =waterService.PreWaterCountYYOld(water);
		int MPreWaterSumYYOld =waterService.PreWaterSumYYOld(water);
		int MOweWaterCountYYOld =waterService.OweWaterCountYYOld(water);
		int MOweWaterSumYYOld =waterService.OweWaterSumYYOld(water);
		
		int MTotalhuOld = MPreWaterCountYYOld + MOweWaterCountYYOld;
		int MTotalyuOld = MPreWaterSumYYOld + MOweWaterSumYYOld;
		
		map.put("MPreWaterCountYYOld",MPreWaterCountYYOld);
		map.put("MPreWaterSumYYOld",MPreWaterSumYYOld);
		map.put("MOweWaterCountYYOld",MOweWaterCountYYOld);
		map.put("MOweWaterSumYYOld",MOweWaterSumYYOld);

		map.put("MTotalhuOld",MTotalhuOld);
		map.put("MTotalyuOld",MTotalyuOld);
		
	//累计应收
		water.setCreateTime(createTime);
		int MWaterCostYYCount = waterService.WaterCostYYCount(water);
		int MWaterCostYYSum = waterService.WaterCostYYSum(water);
		map.put("MWaterCostYYCount",MWaterCostYYCount);
		map.put("MWaterCostYYSum",MWaterCostYYSum);
		
	//累计实收
		int MWaterLogYYCount = waterService.WaterLogYYCount(water);
		int MWaterLogYYSum = waterService.WaterLogYYSum(water);
		map.put("MWaterLogYYCount",MWaterLogYYCount);
		map.put("MWaterLogYYSum",MWaterLogYYSum);

		
   //期末余额	
		water.setCreateTime(createTime);
		
		int MPreWaterCountYYNew =waterService.PreWaterCountYYNew(water);
		int MPreWaterSumYYNew =waterService.PreWaterSumYYNew(water);
		int MOweWaterCountYYNew =waterService.OweWaterCountYYNew(water);
		int MOweWaterSumYYNew =waterService.OweWaterSumYYNew(water);

		int MTotalhuNew = MPreWaterCountYYNew + MOweWaterCountYYNew;
		int MTotalyuNew = MPreWaterSumYYNew + MOweWaterSumYYNew;

		map.put("MPreWaterCountYYNew",MPreWaterCountYYNew);
		map.put("MPreWaterSumYYNew",MPreWaterSumYYNew);
		map.put("MOweWaterCountYYNew",MOweWaterCountYYNew);
		map.put("MOweWaterSumYYNew",MOweWaterSumYYNew);

		map.put("MTotalhuNew",MTotalhuNew);
		map.put("MTotalyuNew",MTotalyuNew);		
		
		
		
		
		
		
		
		
		water.setWaterType("2");
		water.setCreateTime(oldcreateTime);
	//年初余额
		int S1PreWaterCountYYOld =waterService.PreWaterCountYYOld(water);
		int S1PreWaterSumYYOld =waterService.PreWaterSumYYOld(water);
		int S1OweWaterCountYYOld =waterService.OweWaterCountYYOld(water);
		int S1OweWaterSumYYOld =waterService.OweWaterSumYYOld(water);
		
		int S1TotalhuOld = S1PreWaterCountYYOld + S1OweWaterCountYYOld;
		int S1TotalyuOld = S1PreWaterSumYYOld + S1OweWaterSumYYOld;
		
		map.put("S1PreWaterCountYYOld",S1PreWaterCountYYOld);
		map.put("S1PreWaterSumYYOld",S1PreWaterSumYYOld);
		map.put("S1OweWaterCountYYOld",S1OweWaterCountYYOld);
		map.put("S1OweWaterSumYYOld",S1OweWaterSumYYOld);

		map.put("S1TotalhuOld",S1TotalhuOld);
		map.put("S1TotalyuOld",S1TotalyuOld);
		
	//累计应收
		water.setCreateTime(createTime);
		int S1WaterCostYYCount = waterService.WaterCostYYCount(water);
		int S1WaterCostYYSum = waterService.WaterCostYYSum(water);
		map.put("S1WaterCostYYCount",S1WaterCostYYCount);
		map.put("S1WaterCostYYSum",S1WaterCostYYSum);
		
	//累计实收
		int S1WaterLogYYCount = waterService.WaterLogYYCount(water);
		int S1WaterLogYYSum = waterService.WaterLogYYSum(water);
		map.put("S1WaterLogYYCount",S1WaterLogYYCount);
		map.put("S1WaterLogYYSum",S1WaterLogYYSum);

		
   //期末余额	
		water.setCreateTime(createTime);
		
		int S1PreWaterCountYYNew =waterService.PreWaterCountYYNew(water);
		int S1PreWaterSumYYNew =waterService.PreWaterSumYYNew(water);
		int S1OweWaterCountYYNew =waterService.OweWaterCountYYNew(water);
		int S1OweWaterSumYYNew =waterService.OweWaterSumYYNew(water);

		int S1TotalhuNew = S1PreWaterCountYYNew + S1OweWaterCountYYNew;
		int S1TotalyuNew = S1PreWaterSumYYNew + S1OweWaterSumYYNew;

		map.put("S1PreWaterCountYYNew",S1PreWaterCountYYNew);
		map.put("S1PreWaterSumYYNew",S1PreWaterSumYYNew);
		map.put("S1OweWaterCountYYNew",S1OweWaterCountYYNew);
		map.put("S1OweWaterSumYYNew",S1OweWaterSumYYNew);

		map.put("S1TotalhuNew",S1TotalhuNew);
		map.put("S1TotalyuNew",S1TotalyuNew);		
		
		
		
	
		
		water.setWaterType("3");
		water.setCreateTime(oldcreateTime);
	//年初余额
		int S2PreWaterCountYYOld =waterService.PreWaterCountYYOld(water);
		int S2PreWaterSumYYOld =waterService.PreWaterSumYYOld(water);
		int S2OweWaterCountYYOld =waterService.OweWaterCountYYOld(water);
		int S2OweWaterSumYYOld =waterService.OweWaterSumYYOld(water);
		
		int S2TotalhuOld = S2PreWaterCountYYOld + S2OweWaterCountYYOld;
		int S2TotalyuOld = S2PreWaterSumYYOld + S2OweWaterSumYYOld;
		
		map.put("S2PreWaterCountYYOld",S2PreWaterCountYYOld);
		map.put("S2PreWaterSumYYOld",S2PreWaterSumYYOld);
		map.put("S2OweWaterCountYYOld",S2OweWaterCountYYOld);
		map.put("S2OweWaterSumYYOld",S2OweWaterSumYYOld);

		map.put("S2TotalhuOld",S2TotalhuOld);
		map.put("S2TotalyuOld",S2TotalyuOld);
		
	//累计应收
		water.setCreateTime(createTime);
		int S2WaterCostYYCount = waterService.WaterCostYYCount(water);
		int S2WaterCostYYSum = waterService.WaterCostYYSum(water);
		map.put("S2WaterCostYYCount",S2WaterCostYYCount);
		map.put("S2WaterCostYYSum",S2WaterCostYYSum);
		
	//累计实收
		int S2WaterLogYYCount = waterService.WaterLogYYCount(water);
		int S2WaterLogYYSum = waterService.WaterLogYYSum(water);
		map.put("S2WaterLogYYCount",S2WaterLogYYCount);
		map.put("S2WaterLogYYSum",S2WaterLogYYSum);

		
   //期末余额	
		water.setCreateTime(createTime);
		
		int S2PreWaterCountYYNew =waterService.PreWaterCountYYNew(water);
		int S2PreWaterSumYYNew =waterService.PreWaterSumYYNew(water);
		int S2OweWaterCountYYNew =waterService.OweWaterCountYYNew(water);
		int S2OweWaterSumYYNew =waterService.OweWaterSumYYNew(water);

		int S2TotalhuNew = S2PreWaterCountYYNew + S2OweWaterCountYYNew;
		int S2TotalyuNew = S2PreWaterSumYYNew + S2OweWaterSumYYNew;

		map.put("S2PreWaterCountYYNew",S2PreWaterCountYYNew);
		map.put("S2PreWaterSumYYNew",S2PreWaterSumYYNew);
		map.put("S2OweWaterCountYYNew",S2OweWaterCountYYNew);
		map.put("S2OweWaterSumYYNew",S2OweWaterSumYYNew);

		map.put("S2TotalhuNew",S2TotalhuNew);
		map.put("S2TotalyuNew",S2TotalyuNew);		
		
		
		
		
		
		
		
		water.setWaterType("4");
		water.setCreateTime(oldcreateTime);
	//年初余额
		int S3PreWaterCountYYOld =waterService.PreWaterCountYYOld(water);
		int S3PreWaterSumYYOld =waterService.PreWaterSumYYOld(water);
		int S3OweWaterCountYYOld =waterService.OweWaterCountYYOld(water);
		int S3OweWaterSumYYOld =waterService.OweWaterSumYYOld(water);
		
		int S3TotalhuOld = S3PreWaterCountYYOld + S3OweWaterCountYYOld;
		int S3TotalyuOld = S3PreWaterSumYYOld + S3OweWaterSumYYOld;
		
		map.put("S3PreWaterCountYYOld",S3PreWaterCountYYOld);
		map.put("S3PreWaterSumYYOld",S3PreWaterSumYYOld);
		map.put("S3OweWaterCountYYOld",S3OweWaterCountYYOld);
		map.put("S3OweWaterSumYYOld",S3OweWaterSumYYOld);

		map.put("S3TotalhuOld",S3TotalhuOld);
		map.put("S3TotalyuOld",S3TotalyuOld);
		
	//累计应收
		water.setCreateTime(createTime);
		int S3WaterCostYYCount = waterService.WaterCostYYCount(water);
		int S3WaterCostYYSum = waterService.WaterCostYYSum(water);
		map.put("S3WaterCostYYCount",S3WaterCostYYCount);
		map.put("S3WaterCostYYSum",S3WaterCostYYSum);
		
	//累计实收
		int S3WaterLogYYCount = waterService.WaterLogYYCount(water);
		int S3WaterLogYYSum = waterService.WaterLogYYSum(water);
		map.put("S3WaterLogYYCount",S3WaterLogYYCount);
		map.put("S3WaterLogYYSum",S3WaterLogYYSum);

		
   //期末余额	
		water.setCreateTime(createTime);
		
		int S3PreWaterCountYYNew =waterService.PreWaterCountYYNew(water);
		int S3PreWaterSumYYNew =waterService.PreWaterSumYYNew(water);
		int S3OweWaterCountYYNew =waterService.OweWaterCountYYNew(water);
		int S3OweWaterSumYYNew =waterService.OweWaterSumYYNew(water);

		int S3TotalhuNew = S3PreWaterCountYYNew + S3OweWaterCountYYNew;
		int S3TotalyuNew = S3PreWaterSumYYNew + S3OweWaterSumYYNew;

		map.put("S3PreWaterCountYYNew",S3PreWaterCountYYNew);
		map.put("S3PreWaterSumYYNew",S3PreWaterSumYYNew);
		map.put("S3OweWaterCountYYNew",S3OweWaterCountYYNew);
		map.put("S3OweWaterSumYYNew",S3OweWaterSumYYNew);

		map.put("S3TotalhuNew",S3TotalhuNew);
		map.put("S3TotalyuNew",S3TotalyuNew);		
		
		
		
	


		//59合计
		if(water.getUserOrg().equals("2")) {
			//合计年初
			int TotalPrehuOld = MPreWaterCountYYOld + S1PreWaterCountYYOld;
			int TotalPreyuOld = MPreWaterSumYYOld + S1PreWaterSumYYOld;
			int TotalOwehuOld = MOweWaterCountYYOld + S1OweWaterCountYYOld;
			int TotalOweyuOld = MOweWaterSumYYOld + S1OweWaterSumYYOld;
			
			int TotalAllhuOld = MTotalhuOld + S1TotalhuOld;
			int TotalAllyuOld = MTotalyuOld + S1TotalyuOld;
		
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MWaterCostYYCount + S1WaterCostYYCount;
			int TotalCostyu = MWaterCostYYSum + S1WaterCostYYSum;

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MWaterLogYYCount + S1WaterLogYYCount;
			int TotalLogyu = MWaterLogYYSum + S1WaterLogYYSum;

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreWaterCountYYNew + S1PreWaterCountYYNew;
			int TotalPreyuNew = MPreWaterSumYYNew + S1PreWaterSumYYNew;
			int TotalOwehuNew = MOweWaterCountYYNew + S1OweWaterCountYYNew;
			int TotalOweyuNew = MOweWaterSumYYNew + S1OweWaterSumYYNew;
			
			int TotalAllhuNew = MTotalhuNew + S1TotalhuNew;
			int TotalAllyuNew = MTotalyuNew + S1TotalyuNew;
			
			map.put("TotalPrehuNew", TotalPrehuNew);
			map.put("TotalPreyuNew", TotalPreyuNew);
			map.put("TotalOwehuNew", TotalOwehuNew);
			map.put("TotalOweyuNew", TotalOweyuNew);
			
			map.put("TotalAllhuNew", TotalAllhuNew);
			map.put("TotalAllyuNew", TotalAllyuNew);
			
			map.put("type", 2);
		}

		
		
		//牙星合计
		if(water.getUserOrg().equals("3")) {
			//合计年初
			int TotalPrehuOld = MPreWaterCountYYOld + S1PreWaterCountYYOld + S2PreWaterCountYYOld + S3PreWaterCountYYOld;
			int TotalPreyuOld = MPreWaterSumYYOld + S1PreWaterSumYYOld + S2PreWaterSumYYOld  + S3PreWaterSumYYOld;
			int TotalOwehuOld = MOweWaterCountYYOld + S1OweWaterCountYYOld + S2OweWaterCountYYOld + S3OweWaterCountYYOld;
			int TotalOweyuOld = MOweWaterSumYYOld + S1OweWaterSumYYOld + S2OweWaterSumYYOld + S3OweWaterSumYYOld;
			
			int TotalAllhuOld = MTotalhuOld + S1TotalhuOld + S2TotalhuOld + S3TotalhuOld;
			int TotalAllyuOld = MTotalyuOld + S1TotalyuOld + S2TotalyuOld + S3TotalyuOld;
			
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MWaterCostYYCount + S1WaterCostYYCount + S2WaterCostYYCount + S3WaterCostYYCount;
			int TotalCostyu = MWaterCostYYSum + S1WaterCostYYSum + S2WaterCostYYSum + S3WaterCostYYSum;

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MWaterLogYYCount + S1WaterLogYYCount + S2WaterLogYYCount + S3WaterLogYYCount;
			int TotalLogyu = MWaterLogYYSum + S1WaterLogYYSum + S2WaterLogYYSum + S3WaterLogYYSum;

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreWaterCountYYNew + S1PreWaterCountYYNew + S2PreWaterCountYYNew + S3PreWaterCountYYNew;
			int TotalPreyuNew = MPreWaterSumYYNew + S1PreWaterSumYYNew + S2PreWaterSumYYNew + S3PreWaterSumYYNew;
			int TotalOwehuNew = MOweWaterCountYYNew + S1OweWaterCountYYNew + S2OweWaterCountYYNew + S3OweWaterCountYYNew;
			int TotalOweyuNew = MOweWaterSumYYNew + S1OweWaterSumYYNew + S2OweWaterSumYYNew + S3OweWaterSumYYNew;
			
			int TotalAllhuNew = MTotalhuNew + S1TotalhuNew + S2TotalhuNew + S3TotalhuNew;
			int TotalAllyuNew = MTotalyuNew + S1TotalyuNew + S2TotalyuNew + S3TotalyuNew;
			
			map.put("TotalPrehuNew", TotalPrehuNew);
			map.put("TotalPreyuNew", TotalPreyuNew);
			map.put("TotalOwehuNew", TotalOwehuNew);
			map.put("TotalOweyuNew", TotalOweyuNew);
			
			map.put("TotalAllhuNew", TotalAllhuNew);
			map.put("TotalAllyuNew", TotalAllyuNew);
			
			map.put("type", 3);
		}

		
		
		
		
		//查询单价
		List<WaterPriceDO> waterPriceDO = new ArrayList<>();
		waterPriceDO = waterService.WaterPrice();
		int priceTotal ;
		//判断
		for(int i=0 ; i<waterPriceDO.size();i++){
			if(waterPriceDO.get(i).getWaterOrg().equals("2")&&water.getUserOrg().equals("2")){
				//民用水单价
				BigDecimal MWaterPrice = waterPriceDO.get(i).getmWater();
				map.put("MWaterPrice",MWaterPrice.intValue());
				//商用水单价
				BigDecimal S1WaterPrice = waterPriceDO.get(i).getS1Water();
				map.put("S1WaterPrice",S1WaterPrice.intValue());
				//合计
				priceTotal =MWaterPrice.intValue()+S1WaterPrice.intValue();
				map.put("PriceTotal",priceTotal);
			}
			if(waterPriceDO.get(i).getWaterOrg().equals("3")&&water.getUserOrg().equals("3")){
				//民用水单价
				BigDecimal MWaterPrice = waterPriceDO.get(i).getmWater();
				map.put("MWaterPrice",MWaterPrice.intValue());
				//商用水单价
				BigDecimal S1WaterPrice = waterPriceDO.get(i).getS1Water();
				map.put("S1WaterPrice",S1WaterPrice.intValue());
				//商用水2单价
				BigDecimal S2WaterPrice = waterPriceDO.get(i).getS2Water();
				map.put("S2WaterPrice",S2WaterPrice.intValue());
				//商用水3单价
				BigDecimal S3WaterPrice = waterPriceDO.get(i).getS3Water();
				map.put("S3WaterPrice",S3WaterPrice.intValue());
				//合计
				priceTotal =MWaterPrice.intValue()+S1WaterPrice.intValue()+S2WaterPrice.intValue()+S3WaterPrice.intValue();
				map.put("PriceTotal",priceTotal);
			}
		}
		
		
		return map ;
		
	}
	
	
	
	
	
	       
	
	
	
	
	  @ResponseBody
	  @GetMapping("/exportWaterYY")
	  public void exportWaterYY(WaterVo water,WaterLogVo waterLog,HttpServletResponse response){

			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				HSSFWorkbook workbook = new HSSFWorkbook();
				OutputStream out = response.getOutputStream();
				
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用水类型","用户水价","用户水费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用水类型","用户水价","用户水费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","水费类型","缴费金额","缴费时间","收费时间","收费人","更新时间","更新人","用户备注"};
				
				String createTime = water.getCreateTime();
				
				String userOrg = "";
				if ("2".equals(water.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(water.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				String userId = water.getUserId();
				
				List<WaterVo> waterPreList = waterService.exprotWaterPreYY(water);
				List<Object[]> waterPredataList = new ArrayList<Object[]>();
				for(int i = 0;i < waterPreList.size(); i++ ) {
					Object[] arrObj = new Object[headers1.length];
					arrObj[0] = waterPreList.get(i).getId();
					arrObj[1] = waterPreList.get(i).getUserId();
					arrObj[2] = waterPreList.get(i).getUserName();
					arrObj[3] = waterPreList.get(i).getUserOrgName();
					if ("A".equals(waterPreList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(waterPreList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(waterPreList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(waterPreList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = waterPreList.get(i).getUserTell();
					arrObj[6] = waterPreList.get(i).getWagesId();
					if ("1".equals(waterPreList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(waterPreList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(waterPreList.get(i).getWaterType())) {
						arrObj[8] =   "民用水";
					} else if ("2".equals(waterPreList.get(i).getWaterType())) {
						arrObj[8] =   "商业水";
					} 
					arrObj[9] = waterPreList.get(i).getWaterPrice();
					arrObj[10] = waterPreList.get(i).getWaterCost();
					arrObj[11] = waterPreList.get(i).getWaterSum();
					arrObj[12] = waterPreList.get(i).getCreateTime();
					arrObj[13] = waterPreList.get(i).getCreateBy();
					arrObj[14] = formatter.format(waterPreList.get(i).getUpdateTime());
					arrObj[15] = waterPreList.get(i).getUpdateBy();
					arrObj[16] = waterPreList.get(i).getRemark();
					waterPredataList.add(arrObj);
				}
				
				List<WaterVo> waterOweList = waterService.exprotWaterOweYY(water);
				List<Object[]> waterOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < waterOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers2.length];
					arrObj[0] = waterOweList.get(i).getId();
					arrObj[1] = waterOweList.get(i).getUserId();
					arrObj[2] = waterOweList.get(i).getUserName();
					arrObj[3] = waterOweList.get(i).getUserOrgName();
					if ("A".equals(waterOweList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(waterOweList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(waterOweList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(waterOweList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = waterOweList.get(i).getUserTell();
					arrObj[6] = waterOweList.get(i).getWagesId();
					if ("1".equals(waterOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(waterOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(waterOweList.get(i).getWaterType())) {
						arrObj[8] =   "民用水";
					} else if ("2".equals(waterOweList.get(i).getWaterType())) {
						arrObj[8] =   "商业水";
					} 
					arrObj[9] = waterOweList.get(i).getWaterPrice();
					arrObj[10] = waterOweList.get(i).getWaterCost();
					arrObj[11] = waterOweList.get(i).getWaterSum();
					arrObj[12] = waterOweList.get(i).getCreateTime();
					arrObj[13] = waterOweList.get(i).getCreateBy();
					arrObj[14] = formatter.format(waterOweList.get(i).getUpdateTime());
					arrObj[15] = waterOweList.get(i).getUpdateBy();
					arrObj[16] = waterOweList.get(i).getRemark();
					waterOwedataList.add(arrObj);
				}
				
				List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogYY(waterLog);
				List<Object[]> waterLogdataList = new ArrayList<Object[]>();
				for(int i = 0;i < waterLogList.size(); i++ ) {
					Object[] arrObj = new Object[headers3.length];
					
					arrObj[0] = waterLogList.get(i).getId();
					arrObj[1] = waterLogList.get(i).getUserId();
					arrObj[2] = waterLogList.get(i).getUserName();
					arrObj[3] = waterLogList.get(i).getUserOrgName();
					if ("A".equals(waterLogList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(waterLogList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(waterLogList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(waterLogList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					if ("1".equals(waterLogList.get(i).getWaterType())) {
						arrObj[5] =   "民用水";
					} else if ("2".equals(waterLogList.get(i).getWaterType())) {
						arrObj[5] =   "商业水";
					} 
					arrObj[6] = waterLogList.get(i).getWaterMoney();
					arrObj[7] = formatter.format(waterLogList.get(i).getMoneyDate());
					arrObj[8] = waterLogList.get(i).getCreateTime();
					arrObj[9] = waterLogList.get(i).getCreateBy();
					arrObj[10] = formatter.format(waterLogList.get(i).getUpdateTime());
					arrObj[11] = waterLogList.get(i).getUpdateBy();
					arrObj[12] = waterLogList.get(i).getRemark();
					
					waterLogdataList.add(arrObj);
				}

				

				String title =  "水费全年报表.xlsx";

				String oneheaders1 = createTime+userOrg+userId+"水费全年预存表";
				String oneheaders2 = createTime+userOrg+userId+"水费全年欠费表";
				String oneheaders3 = createTime+userOrg+userId+"水费全年实收表";
				
				
				String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
				response.setContentType("octets/stream");
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				
				ExportExcelSheet ex1  = new ExportExcelSheet("预存表", oneheaders1, headers1, waterPredataList);
				ExportExcelSheet ex2  = new ExportExcelSheet("欠费表", oneheaders2, headers2, waterOwedataList);
				ExportExcelSheet ex3  = new ExportExcelSheet("实收表", oneheaders3, headers3, waterLogdataList);

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