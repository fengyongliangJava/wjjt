package com.bootdo.print.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.service.PrintService;
import com.bootdo.print.vo.PrintVo;

 
@Controller
@RequestMapping("/print/print")
public class PrintController {
	@Autowired
	private PrintService printService;
	
	@GetMapping()
	@RequiresPermissions("print:print:print")
	String Print(){
	    return "print/print/print";
	}
	
	
	@GetMapping("/printMoney")
	String printMoney(){
	    return "print/print/printEdit2";
	}
	
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("print:print:print")
	public PageUtils list(@RequestParam Map<String, Object> map){
		
		if(map.get("createTime") == null || "".equals(map.get("createTime"))){
			map.put("beginDate",null);
		}else{
			String beginStr  = (String) map.get("createTime");
			beginStr = beginStr + " 00:00:00";
			map.put("beginDate",beginStr);
		}
		if(map.get("createTime") == null || "".equals(map.get("createTime"))){
			map.put("endDate",null);
		}else{
			String endStr  = (String) map.get("createTime");
			endStr = endStr + " 23:59:59";
			map.put("endDate",endStr);
		}
		//查询列表数据
        Query query = new Query(map);
		List<PrintVo> printList = printService.list(query);
		int total = printService.count(query);
		PageUtils pageUtils = new PageUtils(printList, total);
		return pageUtils;
	}
	
	
	
	@ResponseBody
	@GetMapping("/exprotPrint")
	public void exprotPrint(PrintVo print,HttpServletResponse response){
		response.reset();
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime = print.getCreateTime();
			String userOrg =  print.getUserOrg();
			String userType = print.getUserType();
			
			if(!Util.isNullOrEmpty(createTime)) {
				print.setBeginDate(createTime+" 00:00:00");
				print.setEndDate(createTime + " 23:59:59");
			}
			
			if ("2".equals(print.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(print.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			if ("A".equals(print.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(print.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(print.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(print.getUserType())) {
				userType =   "银行转账";
			}
			
			
			String UserId = print.getUserId();
			
			List<PrintVo> printList = printService.exprotPrint(print);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户地区","缴费方式","上月电字","本月电字","互感比","用户电量","电费单价","用户电费","电费余额","电缴费金额","暖费单价","暖费面积","用户暖费","暖费余额","暖缴费金额","水费单价","用户水费","水费余额","水缴费金额","打印日期","收费人员","收取日期","更新人员","更新日期","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			
			int dataSize = printList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = printList.get(i).getId();
				arrObj[1] = printList.get(i).getUserId();
				arrObj[2] = printList.get(i).getUserName();
				arrObj[3] = printList.get(i).getUserOrgName();
				
				if ("A".equals(printList.get(i).getUserType())) {
					arrObj[4] =   "现金缴费";
				} else if ("B".equals(printList.get(i).getUserType())) {
					arrObj[4] =   "工资代扣";
				} else if ("C".equals(printList.get(i).getUserType())) {
					arrObj[4] =   "微信支付";
				} else if ("D".equals(printList.get(i).getUserType())) {
					arrObj[4] =   "银行转账";
				}
				
				arrObj[5] = printList.get(i).getStart();
				arrObj[6] = printList.get(i).getEnd();
				arrObj[7] = printList.get(i).getHu();
				arrObj[8] = printList.get(i).getElecAmount();
				arrObj[9] = printList.get(i).getElecPrice();
				arrObj[10] = printList.get(i).getElecCost();
				arrObj[11] = printList.get(i).getElecSum();
				arrObj[12] = printList.get(i).getElecMoney();
				arrObj[13] = printList.get(i).getHeatPrice();
				arrObj[14] = printList.get(i).getHeatArea();
				arrObj[15] = printList.get(i).getHeatCost();
				arrObj[16] = printList.get(i).getHeatSum();
				arrObj[17] = printList.get(i).getHeatMoney();
				arrObj[18] = printList.get(i).getWaterPrice();
				arrObj[19] = printList.get(i).getWaterCost();
				arrObj[20] = printList.get(i).getWaterSum();
				arrObj[21] = printList.get(i).getWaterMoney();
				arrObj[22] = formatter.format(printList.get(i).getPrintDate());
				arrObj[23] = printList.get(i).getCreateBy();
				arrObj[24] = printList.get(i).getCreateTime();
				arrObj[25] = printList.get(i).getUpdateBy();
				arrObj[26] = formatter.format(printList.get(i).getUpdateTime());
				arrObj[27] = printList.get(i).getRemark();
				dataList.add(arrObj);
			}
			
			
			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+UserId+" "+"导出票据打印记录明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "票据打印记录明细";
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
	@RequiresPermissions("print:print:add")
	String add(){
	    return "print/print/add";
	}

	
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("print:print:edit")
	String edit(@PathVariable("id") Long id,Model model){
		PrintVo print = printService.get(id);
		
		if(print.getWaterMoney() != null) {
			if(print.getWaterMoney() == null) {print.setWaterMoney(new BigDecimal(0));}
			if(print.getHeatMoney() == null)  {print.setHeatMoney(new BigDecimal(0));}
			if(print.getElecMoney() == null)  {print.setElecMoney(new BigDecimal(0));}
			
			print.setPrintMoney(print.getWaterMoney().add(print.getElecMoney().add(print.getHeatMoney())));
		} 
		
		if(print.getHeatMoney() != null) {
			if(print.getWaterMoney() == null) {print.setWaterMoney(new BigDecimal(0));}
			if(print.getHeatMoney() == null)  {print.setHeatMoney(new BigDecimal(0));}
			if(print.getElecMoney() == null)  {print.setElecMoney(new BigDecimal(0));}
			
			print.setPrintMoney(print.getHeatMoney().add(print.getElecMoney().add(print.getWaterMoney())));
		} 
		
		if(print.getElecMoney() != null) {
			if(print.getWaterMoney() == null) {print.setWaterMoney(new BigDecimal(0));}
			if(print.getHeatMoney() == null)  {print.setHeatMoney(new BigDecimal(0));}
			if(print.getElecMoney() == null)  {print.setElecMoney(new BigDecimal(0));}
			
			print.setPrintMoney(print.getElecMoney().add(print.getHeatMoney().add(print.getWaterMoney())));
		} 
		
		
		model.addAttribute("print", print);
	    return "print/print/edit";
	}
	
	
	
	@GetMapping("/printEdit/{id}")
	String printEdit(@PathVariable("id") Long id,Model model){
		PrintVo print = printService.get(id);
		
		if(print.getWaterMoney() != null) {
			
			if(print.getWaterMoney() == null) {print.setWaterMoney(new BigDecimal(0));}
			if(print.getHeatMoney() == null)  {print.setHeatMoney(new BigDecimal(0));}
			if(print.getElecMoney() == null)  {print.setElecMoney(new BigDecimal(0));}
			
			print.setPrintMoney(print.getWaterMoney().add(print.getElecMoney().add(print.getHeatMoney())));
		} 
		
		if(print.getHeatMoney() != null) {
			
			if(print.getWaterMoney() == null) {print.setWaterMoney(new BigDecimal(0));}
			if(print.getHeatMoney() == null)  {print.setHeatMoney(new BigDecimal(0));}
			if(print.getElecMoney() == null)  {print.setElecMoney(new BigDecimal(0));}
			
			print.setPrintMoney(print.getHeatMoney().add(print.getElecMoney().add(print.getWaterMoney())));
		} 
		
		if(print.getElecMoney() != null) {
			
			if(print.getWaterMoney() == null) {print.setWaterMoney(new BigDecimal(0));}
			if(print.getHeatMoney() == null)  {print.setHeatMoney(new BigDecimal(0));}
			if(print.getElecMoney() == null)  {print.setElecMoney(new BigDecimal(0));}
			
			print.setPrintMoney(print.getElecMoney().add(print.getHeatMoney().add(print.getWaterMoney())));
		} 
		
		model.addAttribute("print", print);
	    return "print/print/printEdit";
	}

	
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("print:print:add")
	public R save(@RequestParam(value="printDate") String printDate,PrintDO print ){
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			print.setPrintDate(sdf.parse(printDate));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		print.setUpdateTime(new Date());
		print.setCreateBy(ShiroUtils.getUserName());
		print.setUpdateBy(ShiroUtils.getUserName());
		if(printService.save(print)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("print:print:edit")
	public R update( PrintDO print){
		print.setUpdateTime(new Date());
		print.setUpdateBy(ShiroUtils.getUserName());
		printService.update(print);
		return R.ok();
	}
	
	/**
  	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("print:print:remove")
	public R remove( Long id){
		if(printService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("print:print:batchRemove")
	public R batchRemove(@RequestParam("ids[]") Long[] ids){
		printService.batchRemove(ids);
		return R.ok();
	}
	

	
	
	/**
	 * 批量打印 
	 */
	@PostMapping("/batchPrint")
	@ResponseBody
	@RequiresPermissions("print:print:batchRemove")
	public List<PrintVo> batchPrint(@RequestParam("ids[]") Long[] ids){
		
		List<PrintVo> PrintList	= printService.batchPrint(ids);
		
		return PrintList;
		
	}
	
	
	
	
	
}
