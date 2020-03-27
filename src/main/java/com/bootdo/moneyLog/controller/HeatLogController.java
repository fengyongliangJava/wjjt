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
import com.bootdo.moneyLog.domain.HeatLogDO;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.vo.HeatLogVo;

@Controller
@RequestMapping("/moneyLog/heatLog")
public class HeatLogController {

	@Autowired
	private HeatLogService heatLogService;
	
	@GetMapping()
	@RequiresPermissions("moneyLog:elecLog")
	String HeatLog(){
	    return "moneyLog/heatLog/heatLog";
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
        Query query = new Query(map);
		List<HeatLogVo> heatLog = heatLogService.list(query);
		int total = heatLogService.count(query);
		PageUtils pageUtils = new PageUtils(heatLog, total);
		return pageUtils;
	}
	
	
	
	@ResponseBody
	@GetMapping("/exprotHeatLog")
	public void exprotHeatLog(HeatLogVo heatLog,HttpServletResponse response,HttpServletRequest request){
		response.reset();
		
	
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String moneyDate = request.getParameter("moneyDate");
			
			if(!Util.isNullOrEmpty(moneyDate)) {
				heatLog.setBeginDate(moneyDate+" 00:00:00");
				heatLog.setEndDate(moneyDate + " 23:59:59");
			}
			
			String userOrg = "";
			if ("2".equals(heatLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(heatLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = "";
			if ("A".equals(heatLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(heatLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(heatLog.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(heatLog.getUserType())) {
				userType =   "银行转账";
			}
			
			
			String heatType = "";
			if ("1".equals(heatLog.getHeatType())) {
				heatType  =   "民用暖";
			} else if ("2".equals(heatLog.getHeatType())) {
				heatType  =   "商业暖";
			}else if ("3".equals(heatLog.getHeatType())) {
				heatType  =   "工业民";
			}else if ("4".equals(heatLog.getHeatType())) {
				heatType  =   "工业商";
			}

			
			String UserId = heatLog.getUserId();
			
			List<HeatLogVo> heatLogList = heatLogService.exprotHeatLogDD(heatLog);
			
			String[] rowName = {"序号","用户编号","用户姓名","缴费方式","用户地区","暖费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			int dataSize = heatLogList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = heatLogList.get(i).getId();
				arrObj[1] = heatLogList.get(i).getUserId();
				arrObj[2] = heatLogList.get(i).getUserName();
				if ("A".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "现金缴费";
				} else if ("B".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "工资代扣";
				} else if ("c".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "微信支付";
				} else if ("D".equals(heatLogList.get(i).getUserType())) {
					arrObj[3] =   "银行转账";
				}
				arrObj[4] = heatLogList.get(i).getUserOrgName();
				if ("1".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "民用暖";
				} else if ("2".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "商业暖";
				}else if ("3".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "工业民";
				}else if ("4".equals(heatLogList.get(i).getHeatType())) {
					arrObj[5]  =   "工业商";
				}
				arrObj[6] = heatLogList.get(i).getHeatMoney();
				arrObj[7] = formatter.format(heatLogList.get(i).getMoneyDate());
				arrObj[8] = heatLogList.get(i).getCreateTime();
				arrObj[9] = heatLogList.get(i).getCreateBy();
				arrObj[10] = formatter.format(heatLogList.get(i).getUpdateTime());
				arrObj[11] = heatLogList.get(i).getUpdateBy();
				arrObj[12] = heatLogList.get(i).getRemark();
				
				dataList.add(arrObj);
			}
			
			
			ExportExcel excelPort = new ExportExcel(moneyDate+" "+userOrg+" "+userType+" "+heatType+" "+UserId+" "+"导出暖费记录明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "暖费记录明细";
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
	    return "moneyLog/heatLog/add";
	}

	
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("moneyLog:elecLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		HeatLogVo heatLog = heatLogService.get(id);
		model.addAttribute("heatLog", heatLog);
	    return "moneyLog/heatLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("moneyLog:elecLog:save")
	public R save( HeatLogDO heatLog){
		
		heatLog.setUpdateTime(new Date());
		heatLog.setUpdateBy(ShiroUtils.getUserName());
		heatLog.setCreateBy(ShiroUtils.getUserName());
		if(heatLogService.save(heatLog)>0){
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
	public R update(@RequestParam Map<String, Object> map,HeatLogDO heatLog){
		
		Object moneyDate = map.get("moneyDate");
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		try {
			@SuppressWarnings("unused")
			Date heat = sdf.parse((String) moneyDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		map.put("heat",null);
		heatLog.setUpdateTime(new Date());
		heatLog.setUpdateBy(ShiroUtils.getUserName());
		
		if(heatLogService.update(heatLog)>0){
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
		if(heatLogService.remove(id)>0){
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
		heatLogService.batchRemove(ids);
		return R.ok();
	}
	
}
