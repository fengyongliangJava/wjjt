package com.bootdo.water.controller;

import java.io.OutputStream;
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
import com.bootdo.water.domain.WaterDO;
import com.bootdo.water.service.WaterService;
import com.bootdo.water.vo.WaterVo;



@Controller
@RequestMapping("/water/water")
public class WaterController {
	@Autowired
	private WaterService waterService;
	
	@GetMapping()
	@RequiresPermissions("water:water:water")
	String Water(){
	    return "water/water/water";
	}
	
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("water:water:water")
	public PageUtils list(@RequestParam Map<String, Object> map){
		
        Query query = new Query(map);
		List<WaterVo> waterList = waterService.list(query);
		int total =  waterService.count(query);
		PageUtils pageUtils = new PageUtils(waterList, total);
		return pageUtils;
	}
	
	

	
	@ResponseBody
	@GetMapping("/exprotWater")
	public void exprotExcel(WaterVo water,HttpServletResponse response){
		response.reset();
		
		//1.查询用户列表
		try {
			String createTime = water.getCreateTime();
			String userOrg =  water.getUserOrg();
			String userType = water.getUserType();
			String waterType = water.getWaterType();
			
			
			if ("2".equals(water.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(water.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			if ("A".equals(water.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(water.getUserType())) {
				userType =   "工资代扣";
			} else if ("C".equals(water.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(water.getUserType())) {
				userType =   "银行转账";
			}
			
			if ("1".equals(water.getWaterType())) {
				waterType =   "现金缴费";
			} else if ("2".equals(water.getWaterType())) {
				waterType =   "工资代扣";
			} else if ("3".equals(water.getWaterType())) {
				waterType =   "微信支付";
			} else if ("4".equals(water.getWaterType())) {
				waterType =   "银行转账";
			}
			
			String UserId = water.getUserId();
			
			List<WaterVo> waterList = waterService.exprotWater(water);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户性质","用户地区","用户电话","用户状态","工资代码","用水类型","用户水价","用户水费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			
			int dataSize = waterList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = waterList.get(i).getId();
				arrObj[1] = waterList.get(i).getUserId();
				arrObj[2] = waterList.get(i).getUserName();
				arrObj[3] = waterList.get(i).getUserType();
				arrObj[4] = waterList.get(i).getUserOrgName();
				arrObj[5] = waterList.get(i).getUserTell();
				if ("1".equals(waterList.get(i).getUserState())) {
					arrObj[6] =   "使用中";
				} else if ("0".equals(waterList.get(i).getUserState())) {
					arrObj[6] =   "未使用";
				}  
				arrObj[7] = waterList.get(i).getWagesId();
				if ("1".equals(waterList.get(i).getWaterType())) {
					arrObj[8] =   "民用水";
				} else if ("2".equals(waterList.get(i).getWaterType())) {
					arrObj[8] =   "商业水1";
				} else if ("3".equals(waterList.get(i).getWaterType())) {
					arrObj[8] =   "商业水2";
				} else if ("4".equals(waterList.get(i).getWaterType())) {
					arrObj[8] =   "商业水3";
				} 
				arrObj[9] = waterList.get(i).getWaterPrice();
				arrObj[10] = waterList.get(i).getWaterCost();
				arrObj[11] = waterList.get(i).getWaterSum();
				arrObj[12] = waterList.get(i).getCreateTime();
				arrObj[13] = waterList.get(i).getCreateBy();
				if(waterList.get(i).getUpdateTime() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    String UpdateTime = formatter.format(waterList.get(i).getUpdateTime());
				    arrObj[14] = UpdateTime;
				}else {
					 arrObj[14] = "";
				}
				arrObj[15] = waterList.get(i).getUpdateBy();
				arrObj[16] = waterList.get(i).getRemark();
				dataList.add(arrObj);
			}

			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+waterType+" "+UserId+" "+"导出水费用户明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "水费用户明细";
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
	@RequiresPermissions("water:water:add")
	String add(){
	    return "water/water/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("water:water:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WaterDO water = waterService.get(id);
		model.addAttribute("water", water);
	    return "water/water/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("water:water:add")
	public R save( WaterDO water){

		water.setUpdateTime(new Date());
		water.setUpdateBy(ShiroUtils.getUserName());
		water.setCreateBy(ShiroUtils.getUserName());
	

		if(waterService.save(water)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("water:water:edit")
	public R update( WaterDO water){
		
		water.setUpdateTime(new Date());
		water.setUpdateBy(ShiroUtils.getUserName());
		water.setCreateBy(ShiroUtils.getUserName());

		waterService.update(water);
		
		if("0".equals(water.getUserState())){
			waterService.updateState(water);
		}
		
		
		return R.ok();
	}
	
	/**
	    * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("water:water:remove")
	public R remove( Long id){
		if(waterService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("water:water:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		waterService.batchRemove(ids);
		return R.ok();
	}
	
}
