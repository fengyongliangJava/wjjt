package com.bootdo.elec.controller;

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
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.service.ElecService;
import com.bootdo.elec.vo.ElecVo;

 
@Controller
@RequestMapping("/elec/elec")
public class ElecController {
	
	
	@Autowired
	private ElecService elecService;
	
	@GetMapping()
	@RequiresPermissions("elec:elec:elec")
	
	String Elec(){
	    return "elec/elec/elec";
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("elec:elec:elec")
	public PageUtils list(@RequestParam Map<String, Object> map,ElecDO elec){
        Query query = new Query(map);
		List<ElecVo> elecList = elecService.list(query);
		int total =  elecService.count(query);
		PageUtils pageUtils = new PageUtils(elecList, total);
		return pageUtils;
	}
	

	
	
	@ResponseBody
	@GetMapping("/exprotElec")
	public void exprotExcel(ElecVo elec,HttpServletResponse response){
		response.reset();
		
		
		try {
			
			String createTime = elec.getCreateTime();
			String userOrg =  elec.getUserOrg();
			String userType = elec.getUserType();
			String elecType = elec.getElecType();
			
			if ("2".equals(elec.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elec.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			if ("A".equals(elec.getUserType())) {
				userType =   "现金缴费";
			} else if ("B".equals(elec.getUserType())) {
				userType =   "工资代扣";	
			} else if ("C".equals(elec.getUserType())) {
				userType =   "微信支付";
			} else if ("D".equals(elec.getUserType())) {
				userType =   "银行转账";
			}
			
			
			if ("1".equals(elec.getElecType())) {
				elecType =   "现金缴费";
			} else if ("2".equals(elec.getElecType())) {
				elecType =   "工资代扣";	
			} else if ("3".equals(elec.getElecType())) {
				elecType =   "微信支付";
			} else if ("4".equals(elec.getElecType())) {
				elecType =   "银行转账";
			} else if ("5".equals(elec.getElecType())) {
				elecType =   "工资代扣";	
			} else if ("6".equals(elec.getElecType())) {
				elecType =   "微信支付";
			} else if ("7".equals(elec.getElecType())) {
				elecType =   "银行转账";
			} else if ("8".equals(elec.getElecType())) {
				elecType =   "工资代扣";	
			} else if ("9".equals(elec.getElecType())) {
				elecType =   "微信支付";
			} else if ("10".equals(elec.getElecType())) {
				elecType =   "银行转账";
			} else if ("11".equals(elec.getElecType())) {
				elecType =   "银行转账";
			}
						
			
			
			String UserId = elec.getUserId();
			
			List<ElecVo> elecList = elecService.exprotElec(elec);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户性质","用户地区","用户电话","用户状态","工资代码","用电类型","用户电价","上月电表","本月电表","用户电量","互感比","用户电费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			
			int dataSize = elecList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = elecList.get(i).getId();
				arrObj[1] = elecList.get(i).getUserId();
				arrObj[2] = elecList.get(i).getUserName();
				arrObj[3] = elecList.get(i).getUserType();
				arrObj[4] = elecList.get(i).getUserOrgName();
				arrObj[5] = elecList.get(i).getUserTell();
				if ("0".equals(elecList.get(i).getUserState())) {
					arrObj[6] =   "未使用";
				} else if ("1".equals(elecList.get(i).getUserState())) {
					arrObj[6] =   "使用中";
				}
				arrObj[7] = elecList.get(i).getWagesId();
				if ("1".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "民用电";
				} else if ("2".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "商业电";
				} else if ("3".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "工业电1";
				} else if ("4".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "工业电2";
				} else if ("5".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "工业电3";
				} else if ("6".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌民用";
				} else if ("7".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌商业";
				} else if ("8".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌工业";
				} else if ("9".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "煤田民用";
				} else if ("10".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "煤田商业";
				} else if ("11".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "政府部门";
				}
				arrObj[9] = elecList.get(i).getElecPrice();
				arrObj[10] = elecList.get(i).getStart();
				arrObj[11] = elecList.get(i).getEnd();
				arrObj[12] = elecList.get(i).getElecAmount();
				arrObj[13] = elecList.get(i).getHu();
				arrObj[14] = elecList.get(i).getElecCost();
				arrObj[15] = elecList.get(i).getElecSum();
				arrObj[16] = elecList.get(i).getCreateTime();
				arrObj[17] = elecList.get(i).getCreateBy();
				if(elecList.get(i).getUpdateTime() != null) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    String UpdateTime = formatter.format(elecList.get(i).getUpdateTime());
				    arrObj[18] = UpdateTime;
				}else {
					 arrObj[18] = "";
				}
				arrObj[19] = elecList.get(i).getUpdateBy();
				arrObj[20] = elecList.get(i).getRemark();
				dataList.add(arrObj);
			}

			
			
			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+elecType+" "+UserId+" "+"导出电费用户明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "电费用户明细";
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
	@RequiresPermissions("elec:elec:add")
	String add(){
	    return "elec/elec/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("elec:elec:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ElecVo elec = elecService.get(id);
		model.addAttribute("elec", elec);
	    return "elec/elec/edit";
	}
	

	
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("elec:elec:add")
	public R save( ElecDO elec){
		
		elec.setUpdateTime(new Date());
		elec.setUpdateBy(ShiroUtils.getUserName());
		elec.setCreateBy(ShiroUtils.getUserName());
		
		if(elecService.save(elec)>0){
			return R.ok();
		}
		
		return R.error();
	}

	
	
	@ResponseBody
	@PostMapping("/update")
	@RequiresPermissions("elec:elec:edit")
	public R update(ElecDO elec){

		elec.setUpdateTime(new Date());
		elec.setCreateBy(ShiroUtils.getUserName());
		elec.setUpdateBy(ShiroUtils.getUserName());
		
		elecService.update(elec);
		if("0".equals(elec.getUserState())){
			elecService.updateState(elec);
		}
		
		return R.ok();
	}
	
	
	

	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("elec:elec:remove")
	public R remove( Long id){
		if(elecService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	

	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("elec:elec:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		elecService.batchRemove(ids);
		return R.ok();
	}
	
}
