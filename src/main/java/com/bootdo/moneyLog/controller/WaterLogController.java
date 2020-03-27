package com.bootdo.moneyLog.controller;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.utils.ExportExcel;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.Util;
import com.bootdo.moneyLog.domain.WaterLogDO;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.moneyLog.vo.WaterLogVo;

@Controller
@RequestMapping("/moneyLog/waterLog")
public class WaterLogController {

	@Autowired
	private WaterLogService waterLogService;
	
	@GetMapping()
	@RequiresPermissions("moneyLog:elecLog")
	String WaterLog(){
	    return "moneyLog/waterLog/waterLog";
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("moneyLog:elecLog")
	public PageUtils list(@RequestParam Map<String, Object> map){
		
		if(map.get("moneyDate") == null || "".equals(map.get("moneyDate"))){
			map.put("beginDate",null);
		}else{
			String beginStr  = (String) map.get("moneyDate");
			beginStr = beginStr + " 00:00:00";
			map.put("beginDate",beginStr);
		}
		if(map.get("moneyDate") == null || "".equals(map.get("moneyDate"))){
			map.put("endDate",null);
		}else{
			String endStr  = (String) map.get("moneyDate");
			endStr = endStr + " 23:59:59";
			map.put("endDate",endStr);
		}
		//查询列表数据
        Query query = new Query(map);
		List<WaterLogVo> waterLog = waterLogService.list(query);
		int total = waterLogService.count(query);
		PageUtils pageUtils = new PageUtils(waterLog, total);
		return pageUtils;
	}
	

	
	@ResponseBody
	@GetMapping("/exprotWaterLog")
	public void exprotWaterLog(WaterLogVo waterLog,HttpServletResponse response,HttpServletRequest request){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String moneyDate = request.getParameter("moneyDate");
			
			if(!Util.isNullOrEmpty(moneyDate)) {
				waterLog.setBeginDate(moneyDate+" 00:00:00");
				waterLog.setEndDate(moneyDate + " 23:59:59");
			}
			
			String userOrg = "";
			if ("2".equals(waterLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(waterLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = "";
			if ("A".equals(waterLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(waterLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(waterLog.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(waterLog.getUserType())) {
				userType =   "银行转账";
			}
			
			String waterType = "";
			if ("1".equals(waterLog.getWaterType())) {
				waterType  =   "民用水";
			} else if ("2".equals(waterLog.getWaterType())) {
				waterType  =   "商业水1";
			} else if ("3".equals(waterLog.getWaterType())) {
				waterType  =   "商业水2";
			} else if ("4".equals(waterLog.getWaterType())) {
				waterType  =   "商业水3";
			}
			
			String UserId = waterLog.getUserId();
			
			List<WaterLogVo> waterLogList = waterLogService.exprotWaterLogDD(waterLog);
			
			String[] rowName = {"序号","用户编号","用户姓名","缴费方式","用户地区","水费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			int dataSize = waterLogList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = waterLogList.get(i).getId();
				arrObj[1] = waterLogList.get(i).getUserId();
				arrObj[2] = waterLogList.get(i).getUserName();
				if ("A".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				} else if ("B".equals(waterLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				}
				arrObj[4] = waterLogList.get(i).getUserOrgName();
				if ("1".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "民用水";
				} else if ("2".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "商业水1";
				} else if ("3".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "商业水2";
				} else if ("4".equals(waterLogList.get(i).getWaterType())) {
					arrObj[5]  =   "商业水3";
				}
				arrObj[6] = waterLogList.get(i).getWaterMoney();
				arrObj[7] = formatter.format(waterLogList.get(i).getMoneyDate());
				arrObj[8] = waterLogList.get(i).getCreateTime();
				arrObj[9] = waterLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(waterLogList.get(i).getUpdateTime());
				arrObj[11] = waterLogList.get(i).getUpdateBy();
				arrObj[12] = waterLogList.get(i).getRemark();
				
				dataList.add(arrObj);
			}
			
			
			ExportExcel excelPort = new ExportExcel(moneyDate+" "+userOrg+" "+userType+" "+waterType+" "+UserId+" "+"导出水费记录明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "水费记录明细";
			fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1"); 
			fileName = StringUtils.replace(fileName, " ", "%20"); 
			response.setContentType("application/ms-excel;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName+".xlsx");
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expries", 0);
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@GetMapping("/add")
	@RequiresPermissions("moneyLog:elecLog:add")
	String add(){
	    return "moneyLog/waterLog/add";
	}

	
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("moneyLog:elecLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WaterLogVo waterLog = waterLogService.get(id);
		model.addAttribute("waterLog", waterLog);
	    return "moneyLog/waterLog/edit";
	}
	
	/**
	 * 保存
	 * @param moneyDate 
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("moneyLog:elecLog:save")
	public R save(WaterLogDO waterLog){
		
		waterLog.setUpdateTime(new Date());
		waterLog.setUpdateBy(ShiroUtils.getUserName());
		waterLog.setCreateBy(ShiroUtils.getUserName());
		if(waterLogService.save(waterLog)>0){
			return R.ok();
		}
		return R.error();
		
	}
	
	/**
                * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("moneyLog:elecLog:edit")
	public R update(@RequestParam Map<String, Object> map,WaterLogDO waterLog){
		
		Object moneyDate = map.get("moneyDate");
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		try {
			@SuppressWarnings("unused")
			Date water = sdf.parse((String) moneyDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		map.put("water",null);
		waterLog.setUpdateTime(new Date());
		waterLog.setUpdateBy(ShiroUtils.getUserName());
		
		if(waterLogService.update(waterLog)>0){
		return R.ok();
		}
		
		return R.error();
	
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("moneyLog:elecLog:remove")
	public R remove( Long id){
		if(waterLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("moneyLog:elecLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		waterLogService.batchRemove(ids);
		return R.ok();
	}
	
}
