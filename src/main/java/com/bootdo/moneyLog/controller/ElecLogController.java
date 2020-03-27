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
import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;


@Controller
@RequestMapping("/moneyLog/elecLog")
public class ElecLogController {

	@Autowired
	private ElecLogService elecLogService;
	
	@GetMapping()
	@RequiresPermissions("moneyLog:elecLog")
	String moneyLog(){
	    return "moneyLog/elecLog/elecLog";
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
		List<ElecLogVo> elecLog = elecLogService.list(query);
		int total = elecLogService.count(query);
		PageUtils pageUtils = new PageUtils(elecLog, total);
		return pageUtils;
	}
	
	
	

	@ResponseBody
	@GetMapping("/exprotElecLog")
	public void exprotElecLog(ElecLogVo elecLog,HttpServletResponse response,HttpServletRequest request){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String moneyDate = request.getParameter("moneyDate");
			
			if(!Util.isNullOrEmpty(moneyDate)) {
				elecLog.setBeginDate(moneyDate+" 00:00:00");
				elecLog.setEndDate(moneyDate + " 23:59:59");
			}
			
			String userOrg = "";
			if ("2".equals(elecLog.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elecLog.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			String userType = "";
			if ("A".equals(elecLog.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(elecLog.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(elecLog.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(elecLog.getUserType())) {
				userType =   "银行转账";
			}
			
			
			String elecType = "";
			if ("1".equals(elecLog.getElecType())) {
				elecType  =   "矿民用";
			} else if ("2".equals(elecLog.getElecType())) {
				elecType  =   "矿商业";
			}else if ("3".equals(elecLog.getElecType())) {
				elecType  =   "矿工业1";
			}else if ("4".equals(elecLog.getElecType())) {
				elecType  =   "矿工业2";
			}else if ("5".equals(elecLog.getElecType())) {
				elecType  =   "矿工业3";
			}else if ("6".equals(elecLog.getElecType())) {
				elecType  =   "乌民用";
			}else if ("7".equals(elecLog.getElecType())) {
				elecType  =   "乌商业";
			}else if ("8".equals(elecLog.getElecType())) {
				elecType  =   "乌工业";
			}else if ("9".equals(elecLog.getElecType())) {
				elecType  =   "煤田民用";
			}else if ("10".equals(elecLog.getElecType())) {
				elecType  =   "煤田商业";
			}else if ("11".equals(elecLog.getElecType())) {
				elecType  =   "政府部门";
			}
			
			
			String UserId = elecLog.getUserId();
			
			List<ElecLogVo> elecLogList = elecLogService.exprotElecLogDD(elecLog);
			
			String[] rowName = {"序号","用户编号","用户姓名","缴费方式","用户地区","电费类型","缴费金额","缴费时间","收费时间","收费人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			int dataSize = elecLogList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
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
				
				dataList.add(arrObj);
			}
			
			ExportExcel excelPort = new ExportExcel(moneyDate+" "+userOrg+" "+userType+" "+elecType+" "+UserId+" "+"导出电费记录明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "电费记录明细";
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
	    return "moneyLog/elecLog/add";
	}

	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("moneyLog:elecLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ElecLogVo elecLog = elecLogService.get(id);
		model.addAttribute("elecLog", elecLog);
	    return "moneyLog/elecLog/edit";
	}
	

	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("moneyLog:elecLog:save")
	public R save( ElecLogDO elecLog){
		
		elecLog.setUpdateTime((new Date()));
		elecLog.setCreateBy(ShiroUtils.getUserName());
		elecLog.setUpdateBy(ShiroUtils.getUserName());
		if(elecLogService.save(elecLog)>0){
			return R.ok();
		}
		return R.error();
	}

	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("moneyLog:elecLog:edit")
	public R update(@RequestParam Map<String, Object> map,ElecLogDO elecLog){
		
		Object moneyDate = map.get("moneyDate");
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		try {
	
			@SuppressWarnings("unused")
			Date elec = sdf.parse((String) moneyDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		map.put("elec",null);
		elecLog.setUpdateTime(new Date());
		elecLog.setUpdateBy(ShiroUtils.getUserName());
		
		if(elecLogService.update(elecLog)>0){
		return R.ok();
		}
		
		return R.error();
	
	}
	

	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("moneyLog:elecLog:remove")
	public R remove( Long id){
		if(elecLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	

	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("moneyLog:elecLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		elecLogService.batchRemove(ids);
		return R.ok();
	}
	
}
