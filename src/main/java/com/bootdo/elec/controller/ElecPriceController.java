package com.bootdo.elec.controller;

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
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.domain.ElecPriceDO;
import com.bootdo.elec.service.ElecPriceService;
import com.bootdo.elec.vo.ElecPriceVo;

 
@Controller
@RequestMapping("/elec/elecPrice")
public class ElecPriceController {
	
	@Autowired
	private ElecPriceService elecPriceService;
	
	@GetMapping()
	@RequiresPermissions("elec:elecPrice:elecPrice")
	String ElecPrice(){
	    return "elec/elecPrice/elecPrice";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("elec:elecPrice:elecPrice")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ElecPriceVo> elecPriceList = elecPriceService.list(query);
		int total = elecPriceService.count(query);
		PageUtils pageUtils = new PageUtils(elecPriceList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("elec:elecPrice:add")
	String add(){
	    return "elec/elecPrice/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("elec:elecPrice:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ElecPriceVo elecPrice = elecPriceService.get(id);
		model.addAttribute("elecPrice", elecPrice);
	    return "elec/elecPrice/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("elec:elecPrice:add")
	public R save( ElecPriceDO elecPrice,ElecDO elec){
		elecPrice.setUpdateTime(new Date());
		elecPrice.setCreateBy(ShiroUtils.getUserId());
		if(elecPriceService.save(elecPrice)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("elec:elecPrice:edit")
	public R update( ElecPriceDO elecPrice){
		elecPrice.setCreateBy(ShiroUtils.getUserId());
		elecPrice.setUpdateTime(new Date());
		elecPrice.setUpdateBy(ShiroUtils.getUserId());
		
		if(elecPriceService.update(elecPrice)>0){
			
			elecPriceService.updateMElec(elecPrice);
			elecPriceService.updateSElec(elecPrice);
			elecPriceService.updateG1Elec(elecPrice);
			elecPriceService.updateG2Elec(elecPrice);
			elecPriceService.updateG3Elec(elecPrice);
			elecPriceService.updateWMElec(elecPrice);
			elecPriceService.updateWSElec(elecPrice);
			elecPriceService.updateWGElec(elecPrice);
			elecPriceService.updateMMElec(elecPrice);
			elecPriceService.updateMSElec(elecPrice);
			elecPriceService.updateZElec(elecPrice);
			
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("elec:elecPrice:remove")
	public R remove( Long id){
		if(elecPriceService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("elec:elecPrice:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		elecPriceService.batchRemove(ids);
		return R.ok();
	}
	
}
