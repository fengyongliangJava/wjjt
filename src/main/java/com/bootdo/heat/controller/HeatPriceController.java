package com.bootdo.heat.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.heat.domain.HeatPriceDO;
import com.bootdo.heat.service.HeatPriceService;
import com.bootdo.heat.vo.HeatPriceVo;


 
@Controller
@RequestMapping("/heat/heatPrice")
public class HeatPriceController {
	@Autowired
	private HeatPriceService heatPriceService;
	
	@GetMapping()
	@RequiresPermissions("heat:heatPrice:heatPrice")
	String HeatPrice(){
	    return "heat/heatPrice/heatPrice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("heat:heatPrice:heatPrice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HeatPriceVo> heatPriceList = heatPriceService.list(query);
		int total = heatPriceService.count(query);
		PageUtils pageUtils = new PageUtils(heatPriceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("heat:heatPrice:add")
	String add(){
	    return "heat/heatPrice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("heat:heatPrice:edit")
	String edit(@PathVariable("id") Long id,Model model){
		HeatPriceVo heatPrice = heatPriceService.get(id);
		model.addAttribute("heatPrice", heatPrice);
	    return "heat/heatPrice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("heat:heatPrice:add")
	public R save( HeatPriceDO heatPrice){
		heatPrice.setCreateBy(ShiroUtils.getUserId());
		heatPrice.setUpdateTime(new Date());
		heatPrice.setUpdateBy(ShiroUtils.getUserId());
		if(heatPriceService.save(heatPrice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("heat:heatPrice:edit")
	public R update( HeatPriceDO heatPrice){
		heatPrice.setCreateBy(ShiroUtils.getUserId());
		heatPrice.setUpdateTime(new Date());
		heatPrice.setUpdateBy(ShiroUtils.getUserId());
		
		if(heatPriceService.update(heatPrice)>0){
			
			heatPriceService.updateMHeat(heatPrice);
			heatPriceService.updateSHeat(heatPrice);
			heatPriceService.updateGMHeat(heatPrice);
			heatPriceService.updateGSHeat(heatPrice);
			
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("heat:heatPrice:remove")
	public R remove( Long id){
		if(heatPriceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("heat:heatPrice:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		heatPriceService.batchRemove(ids);
		return R.ok();
	}
	
}
