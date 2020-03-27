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
import com.bootdo.elec.service.ElecService;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.heat.service.HeatService;
import com.bootdo.heat.vo.HeatVo;
import com.bootdo.water.service.WaterService;
import com.bootdo.water.vo.WaterVo;



@Controller
@RequestMapping("/charts/countOwe")
public class countOweController {
	
	
	
	  @Autowired private ElecService elecService;

	  @Autowired private HeatService heatService;

	  @Autowired private WaterService waterService;

	
	
	@GetMapping()
	@RequiresPermissions("charts:countOwe")
	String countOwe(){
	    return "charts/countOwe";
	}
	

	  @ResponseBody
	  @PostMapping("/getCountOwe")
	  public Map<String,Object> getCountOwe(ElecVo elec, WaterVo water, HeatVo heat) {
		  
		    Map<String,Object> map = new HashMap<>();
		  
			int elecCount = elecService.ElecCountOwe(elec);
			BigDecimal elecSum = elecService.ElecSumOwe(elec);
			int waterCount = waterService.WaterCountOwe(water);
			BigDecimal waterSum =  waterService.WaterSumOwe(water);
			int heatCount = heatService.HeatCountOwe(heat);
			BigDecimal heatSum = heatService.HeatSumOwe(heat);
			
			int totalCount = elecCount + heatCount + waterCount;
			

			List<BigDecimal>  totalSumList = new ArrayList<>();
			
			totalSumList.add(elecSum);
			totalSumList.add(heatSum);
			totalSumList.add(waterSum);
			
			BigDecimal totalSum = new BigDecimal(0);
			for(BigDecimal a:totalSumList) {
				totalSum = totalSum.add(a);
			}
		
			map.put("elecCount",elecCount);
			map.put("elecSum",elecSum);
			map.put("waterCount",waterCount);
			map.put("waterSum",waterSum);
			map.put("heatCount",heatCount);
			map.put("heatSum",heatSum);
			
			map.put("totalCount", totalCount);
			map.put("totalSum", totalSum);
			
			
		    return map;
				
	  }
	
	  
	
	    @ResponseBody
		@GetMapping("/exportOwe")
		public void exportOwe(ElecVo elec,HeatVo heat,WaterVo water,HttpServletResponse response){

	    	
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				OutputStream out = response.getOutputStream();
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","用户电话","工资代码","用户状态","用电类型","用户电价","上月电表","本月电表","用户电量","互感比","用户电费","用户余额","创建时间","更新时间","用户备注"};				
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","用户电话","工资代码","用户状态","用暖类型","用户暖价","取暖面积","用户暖费","用户余额","创建时间","更新时间","用户备注"};	
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","缴费方式","用户地区","用户电话","工资代码","用户状态","用水类型","用户水价","用户水费","用户余额","创建时间","更新时间","用户备注"};
				
				
				String createTime = elec.getCreateTime();
				String userId = elec.getUserId();
				String userOrg = "";
				if ("2".equals(elec.getUserOrg())) {
					userOrg =   "五九地区";
				} else if ("3".equals(elec.getUserOrg())) {
					userOrg =   "牙星地区";
				}
				
				
				
				
				List<ElecVo> elecOweList = elecService.exprotElecOwe(elec);
				List<Object[]> elecOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers1.length];
					arrObj[0] = elecOweList.get(i).getId();
					arrObj[1] = elecOweList.get(i).getUserId();
					arrObj[2] = elecOweList.get(i).getUserName();
					if ("A".equals(elecOweList.get(i).getUserType())) {
						arrObj[3] =   "现金缴费";
					} else if ("B".equals(elecOweList.get(i).getUserType())) {
						arrObj[3]=   "工资代扣";
					} else if ("C".equals(elecOweList.get(i).getUserType())) {
						arrObj[3] =   "微信支付";
					} else if ("D".equals(elecOweList.get(i).getUserType())) {
						arrObj[3] =   "银行转账";
					}
					arrObj[4] = elecOweList.get(i).getUserOrgName();
					arrObj[5] = elecOweList.get(i).getUserTell();
					arrObj[6] = elecOweList.get(i).getWagesId();
					
					if ("0".equals(elecOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} else if ("1".equals(elecOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
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
					arrObj[10] = elecOweList.get(i).getStart();
					arrObj[11] = elecOweList.get(i).getEnd();
					arrObj[12] = elecOweList.get(i).getElecAmount();
					arrObj[13] = elecOweList.get(i).getHu();
					arrObj[14] = elecOweList.get(i).getElecCost();
					arrObj[15] = elecOweList.get(i).getElecSum();
					arrObj[16] = elecOweList.get(i).getCreateTime();
					arrObj[17] = formatter.format(elecOweList.get(i).getUpdateTime());
					arrObj[18] = elecOweList.get(i).getRemark();
					
					elecOwedataList.add(arrObj);
				}
				
				
				
				List<HeatVo> heatOweList = heatService.exprotHeatOwe(heat);
				List<Object[]> heatOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < heatOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers2.length];
					arrObj[0] = heatOweList.get(i).getId();
					arrObj[1] = heatOweList.get(i).getUserId();
					arrObj[2] = heatOweList.get(i).getUserName();
					if ("A".equals(heatOweList.get(i).getUserType())) {
						arrObj[3] =   "现金缴费";
					} else if ("B".equals(heatOweList.get(i).getUserType())) {
						arrObj[3]=   "工资代扣";
					} else if ("C".equals(heatOweList.get(i).getUserType())) {
						arrObj[3] =   "微信支付";
					} else if ("D".equals(heatOweList.get(i).getUserType())) {
						arrObj[3] =   "银行转账";
					}
					arrObj[4] = heatOweList.get(i).getUserOrgName();
					arrObj[5] = heatOweList.get(i).getUserTell();
					arrObj[6] = heatOweList.get(i).getWagesId();
					if ("0".equals(heatOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} else if ("1".equals(heatOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					}
					if ("1".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "民用暖";
					} else if ("2".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "矿商暖";
					}else if ("3".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "工业民";
					}else if ("4".equals(heatOweList.get(i).getHeatType())) {
						arrObj[8] =   "工业商";
					}
					arrObj[9] = heatOweList.get(i).getHeatPrice();
					arrObj[10] = heatOweList.get(i).getHeatArea();
					arrObj[11] = heatOweList.get(i).getHeatCost();
					arrObj[12] = heatOweList.get(i).getHeatSum();
					arrObj[13] = heatOweList.get(i).getCreateTime();
					arrObj[14] = formatter.format(heatOweList.get(i).getUpdateTime());
					arrObj[15] = heatOweList.get(i).getRemark();
					
					heatOwedataList.add(arrObj);
				}
				
				
				
				List<WaterVo> waterOweList = waterService.exprotWaterOwe(water);
				List<Object[]> waterOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < waterOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers3.length];
					arrObj[0] = waterOweList.get(i).getId();
					arrObj[1] = waterOweList.get(i).getUserId();
					arrObj[2] = waterOweList.get(i).getUserName();
					if ("A".equals(waterOweList.get(i).getUserType())) {
						arrObj[3] =   "现金缴费";
					} else if ("B".equals(waterOweList.get(i).getUserType())) {
						arrObj[3]=   "工资代扣";
					} else if ("C".equals(waterOweList.get(i).getUserType())) {
						arrObj[3] =   "微信支付";
					} else if ("D".equals(waterOweList.get(i).getUserType())) {
						arrObj[3] =   "银行转账";
					}
					arrObj[4] = waterOweList.get(i).getUserOrgName();
					arrObj[5] = waterOweList.get(i).getUserTell();
					arrObj[6] = waterOweList.get(i).getWagesId();
					if ("0".equals(waterOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} else if ("1".equals(waterOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
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
					arrObj[13] = formatter.format(waterOweList.get(i).getUpdateTime());
					arrObj[14] = waterOweList.get(i).getRemark();
					
					waterOwedataList.add(arrObj);
				}
				

				String title =  "水电暖当月欠费报表.xlsx";
				
				String oneheaders1 = createTime+userOrg+userId+"电费欠费表";
				String oneheaders2 = createTime+userOrg+userId+"暖费欠费表";
				String oneheaders3 = createTime+userOrg+userId+"水费欠费表";
				
		
				
				String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
				response.setContentType("octets/stream");
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				
				ExportExcelSheet ex1  = new ExportExcelSheet("电费", oneheaders1, headers1, elecOwedataList);
				ExportExcelSheet ex2  = new ExportExcelSheet("暖费", oneheaders2, headers2, heatOwedataList);
				ExportExcelSheet ex3  = new ExportExcelSheet("水费", oneheaders3, headers3, waterOwedataList);

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
