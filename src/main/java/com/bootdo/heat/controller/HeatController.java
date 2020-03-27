package com.bootdo.heat.controller;

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
import com.bootdo.heat.domain.HeatDO;
import com.bootdo.heat.service.HeatService;
import com.bootdo.heat.vo.HeatVo;


 
@Controller
@RequestMapping("/heat/heat")
public class HeatController {
	@Autowired
	private HeatService heatService;
	
	@GetMapping()
	@RequiresPermissions("heat:heat:heat")
	String Heat(){
	    return "heat/heat/heat";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("heat:heat:heat")
	public PageUtils list(@RequestParam Map<String, Object> map){
		//查询列表数据
        Query query = new Query(map);
		List<HeatVo> heatList = heatService.list(query);
		int total =  heatService.count(query);
		PageUtils pageUtils = new PageUtils(heatList, total);
		return pageUtils;
	}
	
	
	
	
	@ResponseBody
	@GetMapping("/exprotHeat")
	public void exprotExcel(HeatVo heat,HttpServletResponse response){
		response.reset();
		
		//1.查询用户列表
		try {
			String createTime = heat.getCreateTime();
			String userOrg =  heat.getUserOrg();
			String userType = heat.getUserType();
			
			if ("2".equals(heat.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(heat.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			if ("A".equals(heat.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(heat.getUserType())) {
				userType =   "工资代扣";
			}
			
			
			String UserId = heat.getUserId();
			
			List<HeatVo> heatList = heatService.exprotHeat(heat);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户性质","用户地区","用户电话","用户状态","工资代码","取暖类型","用户暖价","取暖面积","用户暖费","用户余额","创建时间","更新时间","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			
			int dataSize = heatList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = heatList.get(i).getId();
				arrObj[1] = heatList.get(i).getUserId();
				arrObj[2] = heatList.get(i).getUserName();
				arrObj[3] = heatList.get(i).getUserType();
				arrObj[4] = heatList.get(i).getUserOrgName();
				arrObj[5] = heatList.get(i).getUserTell();
				arrObj[6] = heatList.get(i).getUserState();
				arrObj[7] = heatList.get(i).getWagesId();
				if ("1".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "民用暖";
				} else if ("2".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "商用暖";
				} else if ("3".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "特殊暖费1";
				} else if ("4".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "特殊暖费2";
				} else if ("5".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "特殊暖费3";
				} else if ("6".equals(heatList.get(i).getHeatType())) {
					arrObj[8] =   "特殊暖费4";
				}
				arrObj[9] = heatList.get(i).getHeatPrice();
				arrObj[10] = heatList.get(i).getHeatArea();
				arrObj[11] = heatList.get(i).getHeatCost();
				arrObj[12] = heatList.get(i).getHeatSum();
				arrObj[13] = heatList.get(i).getCreateTime();
				if(heatList.get(i).getUpdateTime() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    String UpdateTime = formatter.format(heatList.get(i).getUpdateTime());
				    arrObj[14] = UpdateTime;
				}else {
					 arrObj[14] = "";
				}
				arrObj[15] = heatList.get(i).getRemark();
				dataList.add(arrObj);
			}

			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+UserId+" "+"导出暖费用户明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "暖费用户明细";
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
	@RequiresPermissions("heat:heat:add")
	String add(){
	    return "heat/heat/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("heat:heat:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		HeatVo heat = heatService.get(id);
		model.addAttribute("heat", heat);
	    return "heat/heat/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("heat:heat:add")
	public R save( HeatDO heat){
		heat.setUpdateTime(new Date());
		heat.setCreateBy(ShiroUtils.getUserName());
		heat.setUpdateBy(ShiroUtils.getUserName());

		if(heatService.save(heat)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("heat:heat:edit")
	public R update( HeatDO heat){
		heat.setUpdateTime(new Date());
		heat.setUpdateBy(ShiroUtils.getUserName());
		heat.setCreateBy(ShiroUtils.getUserName());
		
		heatService.update(heat);
		
		if("0".equals(heat.getUserState())){
			heatService.updateState(heat);
		}
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("heat:heat:remove")
	public R remove( Integer id){
		if(heatService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("heat:heat:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		heatService.batchRemove(ids);
		return R.ok();
	}
	
}
