package com.bootdo.water.controller;

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
import com.bootdo.water.domain.WaterPriceDO;
import com.bootdo.water.service.WaterPriceService;
import com.bootdo.water.vo.WaterPriceVo;


@Controller
@RequestMapping("/water/waterPrice")
public class WaterPriceController {
	
	
	@Autowired
	private WaterPriceService waterPriceService;
	
	
	@GetMapping()
	@RequiresPermissions("water:waterPrice:waterPrice")
	String WaterPrice(){
	    return "water/waterPrice/waterPrice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("water:waterPrice:waterPrice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<WaterPriceVo> waterPriceList = waterPriceService.list(query);
		int total = waterPriceService.count(query);
		PageUtils pageUtils = new PageUtils(waterPriceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("water:waterPrice:add")
	String add(){
	    return "water/waterPrice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("water:waterPrice:edit")
	String edit(@PathVariable("id") Long id,Model model){
		WaterPriceVo waterPrice = waterPriceService.get(id);
		model.addAttribute("waterPrice", waterPrice);
	    return "water/waterPrice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("water:waterPrice:add")
	public R save( WaterPriceDO waterPrice){
		waterPrice.setCreateBy(ShiroUtils.getUserId());
		waterPrice.setUpdateTime(new Date());
		waterPrice.setUpdateBy(ShiroUtils.getUserId());
		if(waterPriceService.save(waterPrice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("water:waterPrice:edit")
	public R update( WaterPriceDO waterPrice){
		waterPrice.setCreateBy(ShiroUtils.getUserId());
		waterPrice.setUpdateTime(new Date());
		waterPrice.setUpdateBy(ShiroUtils.getUserId());
		if(waterPriceService.update(waterPrice)>0){
			
			waterPriceService.updateMWater(waterPrice);
			waterPriceService.updateS1Water(waterPrice);
			waterPriceService.updateS2Water(waterPrice);
			waterPriceService.updateS3Water(waterPrice);
			
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("water:waterPrice:remove")
	public R remove( Long id){
		if(waterPriceService.remove(id)>0){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	   * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("water:waterPrice:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		waterPriceService.batchRemove(ids);
		return R.ok();
	}
	
}
