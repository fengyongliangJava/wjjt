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
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.WaterLogVo;
import com.bootdo.water.domain.WaterPriceDO;
import com.bootdo.water.service.WaterService;
import com.bootdo.water.vo.WaterVo;


 
@Controller
@RequestMapping("/charts/tableWaterMM")
public class tableWaterMMController {
	
	
	@Autowired private WaterService waterService;
	@Autowired private WaterLogService waterLogService;
	
	@GetMapping()
	public String tableWaterMM(){
	    return "charts/tableWaterMM";
	}

	
	
	@ResponseBody
	@PostMapping("/tableWaterMM")
	public  Map<String,Object>  tableWaterMM(WaterVo water,WaterLogVo waterLog){
		
		Map<String,Object> map = new HashMap<>();
		
		
		water.setWaterType("1");
		
		int MPreWaterCountMMOld =waterService.PreWaterCountMMOld(water);
		int MPreWaterSumMMOld =waterService.PreWaterSumMMOld(water);
		int MOweWaterCountMMOld =waterService.OweWaterCountMMOld(water);
		int MOweWaterSumMMOld =waterService.OweWaterSumMMOld(water);
		
		int MTotalhuOld = MPreWaterCountMMOld + MOweWaterCountMMOld;
		int MTotalyuOld = MPreWaterSumMMOld+ MOweWaterSumMMOld;
		
		map.put("MPreWaterCountMMOld",MPreWaterCountMMOld);
		map.put("MPreWaterSumMMOld",MPreWaterSumMMOld);
		map.put("MOweWaterCountMMOld",MOweWaterCountMMOld);
		map.put("MOweWaterSumMMOld",MOweWaterSumMMOld);

		map.put("MTotalhuOld",MTotalhuOld);
		map.put("MTotalyuOld",MTotalyuOld);
		
		
	//累计应收
		int MWaterCostMMCount = waterService.WaterCostMMCount(water);
		int MWaterCostMMSum = waterService.WaterCostMMSum(water);
		map.put("MWaterCostMMCount",MWaterCostMMCount);
		map.put("MWaterCostMMSum",MWaterCostMMSum);
		
	//累计实收
		int MWaterLogMMCount = waterService.WaterLogMMCount(water);
		int MWaterLogMMSum = waterService.WaterLogMMSum(water);
		map.put("MWaterLogMMCount",MWaterLogMMCount);
		map.put("MWaterLogMMSum",MWaterLogMMSum);

		
   //期末余额	
		int MPreWaterCountMMNew =waterService.PreWaterCountMMNew(water);
		int MPreWaterSumMMNew =waterService.PreWaterSumMMNew(water);
		int MOweWaterCountMMNew =waterService.OweWaterCountMMNew(water);
		int MOweWaterSumMMNew =waterService.OweWaterSumMMNew(water);

		int MTotalhuNew = MPreWaterCountMMNew + MOweWaterCountMMNew;
		int MTotalyuNew = MPreWaterSumMMNew + MOweWaterSumMMNew;

		map.put("MPreWaterCountMMNew",MPreWaterCountMMNew);
		map.put("MPreWaterSumMMNew",MPreWaterSumMMNew);
		map.put("MOweWaterCountMMNew",MOweWaterCountMMNew);
		map.put("MOweWaterSumMMNew",MOweWaterSumMMNew);

		map.put("MTotalhuNew",MTotalhuNew);
		map.put("MTotalyuNew",MTotalyuNew);		
		
		
		
		
		
		
		
		
		water.setWaterType("2");
	//年初余额
		int S1PreWaterCountMMOld =waterService.PreWaterCountMMOld(water);
		int S1PreWaterSumMMOld =waterService.PreWaterSumMMOld(water);
		int S1OweWaterCountMMOld =waterService.OweWaterCountMMOld(water);
		int S1OweWaterSumMMOld =waterService.OweWaterSumMMOld(water);
		
		int S1TotalhuOld = S1PreWaterCountMMOld + S1OweWaterCountMMOld;
		int S1TotalyuOld = S1PreWaterSumMMOld + S1OweWaterSumMMOld;
		
		map.put("S1PreWaterCountMMOld",S1PreWaterCountMMOld);
		map.put("S1PreWaterSumMMOld",S1PreWaterSumMMOld);
		map.put("S1OweWaterCountMMOld",S1OweWaterCountMMOld);
		map.put("S1OweWaterSumMMOld",S1OweWaterSumMMOld);

		map.put("S1TotalhuOld",S1TotalhuOld);
		map.put("S1TotalyuOld",S1TotalyuOld);
		
	//累计应收
		int S1WaterCostMMCount = waterService.WaterCostMMCount(water);
		int S1WaterCostMMSum = waterService.WaterCostMMSum(water);
		map.put("S1WaterCostMMCount",S1WaterCostMMCount);
		map.put("S1WaterCostMMSum",S1WaterCostMMSum);
		
	//累计实收
		int S1WaterLogMMCount = waterService.WaterLogMMCount(water);
		int S1WaterLogMMSum = waterService.WaterLogMMSum(water);
		map.put("S1WaterLogMMCount",S1WaterLogMMCount);
		map.put("S1WaterLogMMSum",S1WaterLogMMSum);

		
   //期末余额	
		
		int S1PreWaterCountMMNew =waterService.PreWaterCountMMNew(water);
		int S1PreWaterSumMMNew =waterService.PreWaterSumMMNew(water);
		int S1OweWaterCountMMNew =waterService.OweWaterCountMMNew(water);
		int S1OweWaterSumMMNew =waterService.OweWaterSumMMNew(water);

		int S1TotalhuNew = S1PreWaterCountMMNew + S1OweWaterCountMMNew;
		int S1TotalyuNew = S1PreWaterSumMMNew + S1OweWaterSumMMNew;

		map.put("S1PreWaterCountMMNew",S1PreWaterCountMMNew);
		map.put("S1PreWaterSumMMNew",S1PreWaterSumMMNew);
		map.put("S1OweWaterCountMMNew",S1OweWaterCountMMNew);
		map.put("S1OweWaterSumMMNew",S1OweWaterSumMMNew);

		map.put("S1TotalhuNew",S1TotalhuNew);
		map.put("S1TotalyuNew",S1TotalyuNew);		
		
		
		
	
		
		water.setWaterType("3");
	//年初余额
		int S2PreWaterCountMMOld =waterService.PreWaterCountMMOld(water);
		int S2PreWaterSumMMOld =waterService.PreWaterSumMMOld(water);
		int S2OweWaterCountMMOld =waterService.OweWaterCountMMOld(water);
		int S2OweWaterSumMMOld =waterService.OweWaterSumMMOld(water);
		
		int S2TotalhuOld = S2PreWaterCountMMOld + S2OweWaterCountMMOld;
		int S2TotalyuOld = S2PreWaterSumMMOld + S2OweWaterSumMMOld;
		
		map.put("S2PreWaterCountMMOld",S2PreWaterCountMMOld);
		map.put("S2PreWaterSumMMOld",S2PreWaterSumMMOld);
		map.put("S2OweWaterCountMMOld",S2OweWaterCountMMOld);
		map.put("S2OweWaterSumMMOld",S2OweWaterSumMMOld);

		map.put("S2TotalhuOld",S2TotalhuOld);
		map.put("S2TotalyuOld",S2TotalyuOld);
		
	//累计应收
		int S2WaterCostMMCount = waterService.WaterCostMMCount(water);
		int S2WaterCostMMSum = waterService.WaterCostMMSum(water);
		map.put("S2WaterCostMMCount",S2WaterCostMMCount);
		map.put("S2WaterCostMMSum",S2WaterCostMMSum);
		
	//累计实收
		int S2WaterLogMMCount = waterService.WaterLogMMCount(water);
		int S2WaterLogMMSum = waterService.WaterLogMMSum(water);
		map.put("S2WaterLogMMCount",S2WaterLogMMCount);
		map.put("S2WaterLogMMSum",S2WaterLogMMSum);

		
   //期末余额	
		
		int S2PreWaterCountMMNew =waterService.PreWaterCountMMNew(water);
		int S2PreWaterSumMMNew =waterService.PreWaterSumMMNew(water);
		int S2OweWaterCountMMNew =waterService.OweWaterCountMMNew(water);
		int S2OweWaterSumMMNew =waterService.OweWaterSumMMNew(water);

		int S2TotalhuNew = S2PreWaterCountMMNew + S2OweWaterCountMMNew;
		int S2TotalyuNew = S2PreWaterSumMMNew + S2OweWaterSumMMNew;

		map.put("S2PreWaterCountMMNew",S2PreWaterCountMMNew);
		map.put("S2PreWaterSumMMNew",S2PreWaterSumMMNew);
		map.put("S2OweWaterCountMMNew",S2OweWaterCountMMNew);
		map.put("S2OweWaterSumMMNew",S2OweWaterSumMMNew);

		map.put("S2TotalhuNew",S2TotalhuNew);
		map.put("S2TotalyuNew",S2TotalyuNew);		
		
		
		
		
		
		
		
		water.setWaterType("4");
	//年初余额
		int S3PreWaterCountMMOld =waterService.PreWaterCountMMOld(water);
		int S3PreWaterSumMMOld =waterService.PreWaterSumMMOld(water);
		int S3OweWaterCountMMOld =waterService.OweWaterCountMMOld(water);
		int S3OweWaterSumMMOld =waterService.OweWaterSumMMOld(water);
		
		int S3TotalhuOld = S3PreWaterCountMMOld + S3OweWaterCountMMOld;
		int S3TotalyuOld = S3PreWaterSumMMOld + S3OweWaterSumMMOld;
		
		map.put("S3PreWaterCountMMOld",S3PreWaterCountMMOld);
		map.put("S3PreWaterSumMMOld",S3PreWaterSumMMOld);
		map.put("S3OweWaterCountMMOld",S3OweWaterCountMMOld);
		map.put("S3OweWaterSumMMOld",S3OweWaterSumMMOld);

		map.put("S3TotalhuOld",S3TotalhuOld);
		map.put("S3TotalyuOld",S3TotalyuOld);
		
	//累计应收
		int S3WaterCostMMCount = waterService.WaterCostMMCount(water);
		int S3WaterCostMMSum = waterService.WaterCostMMSum(water);
		map.put("S3WaterCostMMCount",S3WaterCostMMCount);
		map.put("S3WaterCostMMSum",S3WaterCostMMSum);
		
	//累计实收
		int S3WaterLogMMCount = waterService.WaterLogMMCount(water);
		int S3WaterLogMMSum = waterService.WaterLogMMSum(water);
		map.put("S3WaterLogMMCount",S3WaterLogMMCount);
		map.put("S3WaterLogMMSum",S3WaterLogMMSum);

		
   //期末余额	
		int S3PreWaterCountMMNew =waterService.PreWaterCountMMNew(water);
		int S3PreWaterSumMMNew =waterService.PreWaterSumMMNew(water);
		int S3OweWaterCountMMNew =waterService.OweWaterCountMMNew(water);
		int S3OweWaterSumMMNew =waterService.OweWaterSumMMNew(water);

		int S3TotalhuNew = S3PreWaterCountMMNew + S3OweWaterCountMMNew;
		int S3TotalyuNew = S3PreWaterSumMMNew + S3OweWaterSumMMNew;

		map.put("S3PreWaterCountMMNew",S3PreWaterCountMMNew);
		map.put("S3PreWaterSumMMNew",S3PreWaterSumMMNew);
		map.put("S3OweWaterCountMMNew",S3OweWaterCountMMNew);
		map.put("S3OweWaterSumMMNew",S3OweWaterSumMMNew);

		map.put("S3TotalhuNew",S3TotalhuNew);
		map.put("S3TotalyuNew",S3TotalyuNew);		
		
		
	


		//59合计
		if(water.getUserOrg().equals("2")) {
			//合计年初
			int TotalPrehuOld = MPreWaterCountMMOld + S1PreWaterCountMMOld;
			int TotalPreyuOld = MPreWaterSumMMOld + S1PreWaterSumMMOld;
			int TotalOwehuOld = MOweWaterCountMMOld + S1OweWaterCountMMOld;
			int TotalOweyuOld = MOweWaterSumMMOld + S1OweWaterSumMMOld;
			
			int TotalAllhuOld = MTotalhuOld + S1TotalhuOld;
			int TotalAllyuOld = MTotalyuOld + S1TotalyuOld;
		
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MWaterCostMMCount + S1WaterCostMMCount;
			int TotalCostyu = MWaterCostMMSum + S1WaterCostMMSum;

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MWaterLogMMCount + S1WaterLogMMCount;
			int TotalLogyu = MWaterLogMMSum + S1WaterLogMMSum;

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreWaterCountMMNew + S1PreWaterCountMMNew;
			int TotalPreyuNew = MPreWaterSumMMNew + S1PreWaterSumMMNew;
			int TotalOwehuNew = MOweWaterCountMMNew + S1OweWaterCountMMNew;
			int TotalOweyuNew = MOweWaterSumMMNew + S1OweWaterSumMMNew;
			
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
			int TotalPrehuOld = MPreWaterCountMMOld + S1PreWaterCountMMOld + S2PreWaterCountMMOld + S3PreWaterCountMMOld;
			
			

			int TotalPreyuOld = MPreWaterSumMMOld + S1PreWaterSumMMOld + S2PreWaterSumMMOld  + S3PreWaterSumMMOld;
			int TotalOwehuOld = MOweWaterCountMMOld + S1OweWaterCountMMOld + S2OweWaterCountMMOld + S3OweWaterCountMMOld;
			int TotalOweyuOld = MOweWaterSumMMOld + S1OweWaterSumMMOld + S2OweWaterSumMMOld + S3OweWaterSumMMOld;
			
			int TotalAllhuOld = MTotalhuOld + S1TotalhuOld + S2TotalhuOld + S3TotalhuOld;
			int TotalAllyuOld = MTotalyuOld + S1TotalyuOld + S2TotalyuOld + S3TotalyuOld;
			
			map.put("TotalPrehuOld", TotalPrehuOld);
			map.put("TotalPreyuOld", TotalPreyuOld);
			map.put("TotalOwehuOld", TotalOwehuOld);
			map.put("TotalOweyuOld", TotalOweyuOld);
			
			map.put("TotalAllhuOld", TotalAllhuOld);
			map.put("TotalAllyuOld", TotalAllyuOld);

		  //累计应收
			int TotalCosthu = MWaterCostMMCount + S1WaterCostMMCount + S2WaterCostMMCount + S3WaterCostMMCount;
			int TotalCostyu = MWaterCostMMSum + S1WaterCostMMSum + S2WaterCostMMSum + S3WaterCostMMSum;

			map.put("TotalCosthu", TotalCosthu);
			map.put("TotalCostyu", TotalCostyu);
			
		  //累计实收
			int TotalLoghu = MWaterLogMMCount + S1WaterLogMMCount + S2WaterLogMMCount + S3WaterLogMMCount;
			int TotalLogyu = MWaterLogMMSum + S1WaterLogMMSum + S2WaterLogMMSum + S3WaterLogMMSum;

			map.put("TotalLoghu", TotalLoghu);
			map.put("TotalLogyu", TotalLogyu);

		  //合计年末
			int TotalPrehuNew = MPreWaterCountMMNew + S1PreWaterCountMMNew + S2PreWaterCountMMNew + S3PreWaterCountMMNew;
			int TotalPreyuNew = MPreWaterSumMMNew + S1PreWaterSumMMNew + S2PreWaterSumMMNew + S3PreWaterSumMMNew;
			int TotalOwehuNew = MOweWaterCountMMNew + S1OweWaterCountMMNew + S2OweWaterCountMMNew + S3OweWaterCountMMNew;
			int TotalOweyuNew = MOweWaterSumMMNew + S1OweWaterSumMMNew + S2OweWaterSumMMNew + S3OweWaterSumMMNew;
			
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
	@GetMapping("/exportWaterMM")
	public void exportWaterMM(WaterVo water, WaterLogVo waterLog, HttpServletResponse response) {


		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			HSSFWorkbook workbook = new HSSFWorkbook();
			OutputStream out = response.getOutputStream();

			String[] headers1 = new String[]{"序号", "用户编号", "用户姓名", "用户地区", "缴费方式", "用户电话", "工资代码", "用户状态", "用水类型", "用户水价", "用户水费", "用户余额", "创建时间", "更新时间", "用户备注"};
			String[] headers2 = new String[]{"序号", "用户编号", "用户姓名", "用户地区", "缴费方式", "用户电话", "工资代码", "用户状态", "用水类型", "用户水价", "用户水费", "用户余额", "创建时间", "更新时间", "用户备注"};
			String[] headers3 = new String[]{"序号", "用户编号", "用户姓名", "用户地区", "缴费方式", "水费类型", "缴费金额", "缴费时间", "收费时间", "收费人", "更新时间", "更新人", "用户备注"};

			String createTime = water.getCreateTime();
			String userId = water.getUserId();

			String userOrg = "";
			if ("2".equals(water.getUserOrg())) {
				userOrg = "五九地区";
			} else if ("3".equals(water.getUserOrg())) {
				userOrg = "牙星地区";
			}


			List<WaterVo> waterPreList = waterService.exprotWaterPre(water);
			List<Object[]> waterPredataList = new ArrayList<Object[]>();
			for (int i = 0; i < waterPreList.size(); i++) {
				Object[] arrObj = new Object[headers1.length];
				arrObj[0] = waterPreList.get(i).getId();
				arrObj[1] = waterPreList.get(i).getUserId();
				arrObj[2] = waterPreList.get(i).getUserName();
				arrObj[3] = waterPreList.get(i).getUserOrgName();
				if ("A".equals(waterPreList.get(i).getUserType())) {
					arrObj[4] = "现金缴费";
				} else if ("B".equals(waterPreList.get(i).getUserType())) {
					arrObj[4] = "工资代扣";
				} else if ("C".equals(waterPreList.get(i).getUserType())) {
					arrObj[4] = "微信支付";
				} else if ("D".equals(waterPreList.get(i).getUserType())) {
					arrObj[4] = "银行转账";
				}
				arrObj[5] = waterPreList.get(i).getUserTell();
				arrObj[6] = waterPreList.get(i).getWagesId();
				if ("1".equals(waterPreList.get(i).getUserState())) {
					arrObj[7] = "使用中";
				} else if ("0".equals(waterPreList.get(i).getUserState())) {
					arrObj[7] = "未使用";
				}
				if ("1".equals(waterPreList.get(i).getWaterType())) {
					arrObj[8] = "民用水";
				} else if ("2".equals(waterPreList.get(i).getWaterType())) {
					arrObj[8] = "商业水";
				}
				arrObj[9] = waterPreList.get(i).getWaterPrice();
				arrObj[10] = waterPreList.get(i).getWaterCost();
				arrObj[11] = waterPreList.get(i).getWaterSum();
				arrObj[12] = waterPreList.get(i).getCreateTime();
				arrObj[13] = formatter.format(waterPreList.get(i).getUpdateTime());
				arrObj[14] = waterPreList.get(i).getRemark();
				waterPredataList.add(arrObj);
			}

			List<WaterVo> waterOweList = waterService.exprotWaterOwe(water);
			List<Object[]> waterOwedataList = new ArrayList<Object[]>();
			for (int i = 0; i < waterOweList.size(); i++) {
				Object[] arrObj = new Object[headers2.length];
				arrObj[0] = waterOweList.get(i).getId();
				arrObj[1] = waterOweList.get(i).getUserId();
				arrObj[2] = waterOweList.get(i).getUserName();
				arrObj[3] = waterOweList.get(i).getUserOrgName();
				if ("A".equals(waterOweList.get(i).getUserType())) {
					arrObj[4] = "现金缴费";
				} else if ("B".equals(waterOweList.get(i).getUserType())) {
					arrObj[4] = "工资代扣";
				} else if ("C".equals(waterOweList.get(i).getUserType())) {
					arrObj[4] = "微信支付";
				} else if ("D".equals(waterOweList.get(i).getUserType())) {
					arrObj[4] = "银行转账";
				}
				arrObj[5] = waterOweList.get(i).getUserTell();
				arrObj[6] = waterOweList.get(i).getWagesId();
				if ("1".equals(waterOweList.get(i).getUserState())) {
					arrObj[7] = "使用中";
				} else if ("0".equals(waterOweList.get(i).getUserState())) {
					arrObj[7] = "未使用";
				}
				if ("1".equals(waterOweList.get(i).getWaterType())) {
					arrObj[8] = "民用水";
				} else if ("2".equals(waterOweList.get(i).getWaterType())) {
					arrObj[8] = "商业水";
				}
				arrObj[9] = waterOweList.get(i).getWaterPrice();
				arrObj[10] = waterOweList.get(i).getWaterCost();
				arrObj[11] = waterOweList.get(i).getWaterSum();
				arrObj[12] = waterOweList.get(i).getCreateTime();
				arrObj[13] = formatter.format(waterOweList.get(i).getUpdateTime());
				arrObj[14] = waterPreList.get(i).getRemark();
				waterOwedataList.add(arrObj);
			}


			List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogMM(waterLog);
			List<Object[]> waterLogdataList = new ArrayList<Object[]>();
			for (int i = 0; i < waterLogList.size(); i++) {
				Object[] arrObj = new Object[headers3.length];

				arrObj[0] = waterLogList.get(i).getId();
				arrObj[1] = waterLogList.get(i).getUserId();
				arrObj[2] = waterLogList.get(i).getUserName();
				arrObj[3] = waterLogList.get(i).getUserOrgName();
				if ("A".equals(waterLogList.get(i).getUserType())) {
					arrObj[4] = "现金缴费";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[4] = "工资代扣";
				} else if ("C".equals(waterLogList.get(i).getUserType())) {
					arrObj[4] = "微信支付";
				} else if ("D".equals(waterLogList.get(i).getUserType())) {
					arrObj[4] = "银行转账";
				}
				if ("1".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5] = "民用水";
				} else if ("2".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5] = "商业水";
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


			String title = "水费当月报表.xlsx";

			String oneheaders1 = createTime + userOrg + userId + "水费预存表";
			String oneheaders2 = createTime + userOrg + userId + "水费欠费表";
			String oneheaders3 = createTime + userOrg + userId + "水费实收表";


			String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "ISO8859-1") + "\"";
			response.setContentType("octets/stream");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", headStr);

			ExportExcelSheet ex1 = new ExportExcelSheet("预存表", oneheaders1, headers1, waterPredataList);
			ExportExcelSheet ex2 = new ExportExcelSheet("欠费表", oneheaders2, headers2, waterOwedataList);
			ExportExcelSheet ex3 = new ExportExcelSheet("实收表", oneheaders3, headers3, waterLogdataList);

			ex1.export(workbook, out);
			ex2.export(workbook, out);
			ex3.export(workbook, out);

			workbook.write(out);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
