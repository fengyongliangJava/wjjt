package com.bootdo.moneyList.controller;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.bootdo.common.utils.ExportExcelSheet;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.elec.service.ElecService;
import com.bootdo.elec.vo.ElecVo;
import com.bootdo.moneyList.service.MoneyWZService;
import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.service.PrintService;


@Controller
@RequestMapping("/moneyList/moneyWZ")
public class MoneyListWZController {

	@Autowired
	private MoneyWZService moneyWZService;
	
	@Autowired
	private ElecService  elecService;
	
	@Autowired
	private ElecLogService  elecLogService;
	
	@Autowired
	private PrintService  printService;
	
	@GetMapping()
	@RequiresPermissions("moneyList:moneyWZ")
	String MoneyWZ(){
	    return "moneyList/moneyWZ";
	}

	
	
	@ResponseBody
	@GetMapping("/list")
	public PageUtils list(@RequestParam Map<String, Object> map,ElecDO elec){
        Query query = new Query(map);
		List<ElecVo> WZList = moneyWZService.list(query);
		int total =  moneyWZService.count(query);
		PageUtils pageUtils = new PageUtils(WZList, total);
		return pageUtils;
	}
	

	
	@GetMapping("/add")
	String add(){
	    return "elec/elec/add";
	}

	
	
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id,Model model){
		ElecVo elec = elecService.get(id);
		model.addAttribute("elec", elec);
	    return "elec/elec/edit";
	}
	
	
	
	
	@ResponseBody
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
	public R remove( Long id){
		if(elecService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	

	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] ids){
		elecService.batchRemove(ids);
		return R.ok();
	}
	
	
	
	
	@PostMapping("/batchMoney")
	@ResponseBody
	public R batchMoney(@RequestParam("ids[]") Long[] ids){
		int count = 0;
		List<ElecVo> MoenyList = moneyWZService.WZElecList(ids);
	
		for(int i= 0; i<MoenyList.size(); i++){
			
			//往缴费记录表里插入数据
			ElecLogDO elecLog = new ElecLogDO();
			
			elecLog.setUserId(MoenyList.get(i).getUserId());
			elecLog.setUserName(MoenyList.get(i).getUserName());
			elecLog.setUserType(MoenyList.get(i).getUserType());
			elecLog.setUserOrg(MoenyList.get(i).getUserOrg());
			elecLog.setElecType(MoenyList.get(i).getElecType());
			
			BigDecimal s = (BigDecimal) MoenyList.get(i).getElecSum(); 
			elecLog.setElecMoney(s.abs());
			
			elecLog.setMoneyDate(new Date());
			elecLog.setCreateTime(MoenyList.get(i).getCreateTime());
			elecLog.setCreateBy(ShiroUtils.getUserName());
			elecLog.setUpdateTime(new Date());
			elecLog.setUpdateBy(ShiroUtils.getUserName());
			
			elecLogService.save(elecLog);
			
			
			//往打印表里插入数据
			PrintDO print = new PrintDO();
			
			print.setUserId(MoenyList.get(i).getUserId());
			print.setUserName(MoenyList.get(i).getUserName());
			print.setUserType(MoenyList.get(i).getUserType());
			print.setUserOrg(MoenyList.get(i).getUserOrg());
			print.setStart(MoenyList.get(i).getStart());
			print.setEnd(MoenyList.get(i).getStart());
			print.setHu(MoenyList.get(i).getHu());
			print.setElecAmount(MoenyList.get(i).getElecAmount());
			print.setElecPrice(MoenyList.get(i).getElecPrice());
			print.setElecCost(MoenyList.get(i).getElecCost());
			//缴费金额  =  余额变成 正数
			print.setElecMoney(elecLog.getElecMoney());
			//缴玩费以后  余额 0
			print.setElecSum(new BigDecimal(0));
			print.setPrintDate(new Date());
			print.setPrintMoney(elecLog.getElecMoney());
			print.setCreateTime(MoenyList.get(i).getCreateTime());
			print.setCreateBy(ShiroUtils.getUserName());
			print.setUpdateTime(new Date());
			print.setUpdateBy(ShiroUtils.getUserName());
			
			printService.save(print);
			
			//批量缴费以后  余额 清0
			MoenyList.get(i).setElecSum(new BigDecimal(0));
			int index = elecService.update(MoenyList.get(i));
			count = count + index;
			
		   }
	
		
			if(ids !=null && count == ids.length) {
				return R.ok();
			}else {
				return R.error();
			} 
			
	}
	
	
	
	
	
	
	  @ResponseBody
	  @GetMapping("/exprotWZ")
	  public void exportElecYY(ElecVo elec,ElecLogVo elecLog,HttpServletResponse response){
		  
		  
		  
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				HSSFWorkbook workbook = new HSSFWorkbook();
				OutputStream out = response.getOutputStream();
				
				String[] headers1 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","用户电价","用户电费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers2 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","用户电价","用户电费","用户余额","创建时间","创建人","更新时间","更新人","用户备注"};
				String[] headers3 = new String[] {"序号","用户编号","用户姓名","用户地区","缴费方式","电费类型","缴费金额","缴费时间","收费时间","收费人","更新时间","更新人","用户备注"};
				
				String createTime = elec.getCreateTime();
	
				
				String userType = "";
				if ("A".equals(elec.getUserType())) {
					userType =   "现金缴费";
				} else if ("B".equals(elec.getUserType())) {
					userType =   "工资代扣";
				} else if ("C".equals(elec.getUserType())) {
					userType =   "微信支付";
				} else if ("D".equals(elec.getUserType())) {
					userType =   "银行转账";
				}
				
				String userId = elec.getUserId();
			
				List<ElecVo> elecPreList = moneyWZService.exprotWZPre(elec);
				List<Object[]> elecPredataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecPreList.size(); i++ ) {
					Object[] arrObj = new Object[headers1.length];
					arrObj[0] = elecPreList.get(i).getId();
					arrObj[1] = elecPreList.get(i).getUserId();
					arrObj[2] = elecPreList.get(i).getUserName();
					arrObj[3] = elecPreList.get(i).getUserOrgName();
					if ("A".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(elecPreList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = elecPreList.get(i).getUserTell();
					arrObj[6] = elecPreList.get(i).getWagesId();
					if ("1".equals(elecPreList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(elecPreList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					
					if ("1".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿民用";
					} else if ("2".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿商业";
					}else if ("3".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿工业1";
					}else if ("4".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿工业2";
					}else if ("5".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "矿工业3";
					}else if ("6".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "乌民用";
					}else if ("7".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "乌商业";
					}else if ("8".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "乌工业";
					}else if ("9".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "煤田民用";
					}else if ("10".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "煤田商业";
					}else if ("11".equals(elecPreList.get(i).getElecType())) {
						arrObj[8] =   "政府部门";
					}
					arrObj[9] = elecPreList.get(i).getElecPrice();
					arrObj[10] = elecPreList.get(i).getElecCost();
					arrObj[11] = elecPreList.get(i).getElecSum();
					arrObj[12] = elecPreList.get(i).getCreateTime();
					arrObj[13] = elecPreList.get(i).getCreateBy();
					arrObj[14] = formatter.format(elecPreList.get(i).getUpdateTime());
					arrObj[15] = elecPreList.get(i).getUpdateBy();
					arrObj[16] = elecPreList.get(i).getRemark();
					elecPredataList.add(arrObj);
				}
				
				List<ElecVo> elecOweList = moneyWZService.exprotWZOwe(elec);
				List<Object[]> elecOwedataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecOweList.size(); i++ ) {
					Object[] arrObj = new Object[headers2.length];
					arrObj[0] = elecOweList.get(i).getId();
					arrObj[1] = elecOweList.get(i).getUserId();
					arrObj[2] = elecOweList.get(i).getUserName();
					arrObj[3] = elecOweList.get(i).getUserOrgName();
					if ("A".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(elecOweList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					arrObj[5] = elecOweList.get(i).getUserTell();
					arrObj[6] = elecOweList.get(i).getWagesId();
					if ("1".equals(elecOweList.get(i).getUserState())) {
						arrObj[7] =   "使用中";
					} else if ("0".equals(elecOweList.get(i).getUserState())) {
						arrObj[7] =   "未使用";
					} 
					if ("1".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿民用";
					} else if ("2".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿商业";
					}else if ("3".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿工业1";
					}else if ("4".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿工业2";
					}else if ("5".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "矿工业3";
					}else if ("6".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "乌民用";
					}else if ("7".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "乌商业";
					}else if ("8".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "乌工业";
					}else if ("9".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "煤田民用";
					}else if ("10".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "煤田商业";
					}else if ("11".equals(elecOweList.get(i).getElecType())) {
						arrObj[8] =   "政府部门";
					}
					arrObj[9] = elecOweList.get(i).getElecPrice();
					arrObj[10] = elecOweList.get(i).getElecCost();
					arrObj[11] = elecOweList.get(i).getElecSum();
					arrObj[12] = elecOweList.get(i).getCreateTime();
					arrObj[13] = elecOweList.get(i).getCreateBy();
					arrObj[14] = formatter.format(elecOweList.get(i).getUpdateTime());
					arrObj[15] = elecOweList.get(i).getUpdateBy();
					arrObj[16] = elecOweList.get(i).getRemark();
					elecOwedataList.add(arrObj);
				}
				
				
				List<ElecLogVo> elecLogList = moneyWZService.exprotWZLog(elecLog);
				List<Object[]> elecLogdataList = new ArrayList<Object[]>();
				for(int i = 0;i < elecLogList.size(); i++ ) {
					Object[] arrObj = new Object[headers3.length];
					
					arrObj[0] = elecLogList.get(i).getId();
					arrObj[1] = elecLogList.get(i).getUserId();
					arrObj[2] = elecLogList.get(i).getUserName();
					arrObj[3] = elecLogList.get(i).getUserOrgName();
					if ("A".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "现金缴费";
					} else if ("B".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "工资代扣";
					} else if ("C".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "微信支付";
					} else if ("D".equals(elecLogList.get(i).getUserType())) {
						arrObj[4] =   "银行转账";
					}
					if ("1".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿民用";
					} else if ("2".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿商业";
					} else if ("3".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿工业1";
					} else if ("4".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿工业2";
					} else if ("5".equals(elecLogList.get(i).getElecType())) {
						arrObj[5] =   "矿工业3";
					} else if ("6".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "乌民用";
					} else if ("7".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "乌商业";
					} else if ("8".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "乌工业";
					} else if ("9".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "煤田民用";
					} else if ("10".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "煤田商用";
					} else if ("11".equals(elecLogList.get(i).getElecType())) {
					    arrObj[5] =   "政府部门";
					} 
					arrObj[6] = elecLogList.get(i).getElecMoney();
					arrObj[7] = formatter.format(elecLogList.get(i).getMoneyDate());
					arrObj[8] = elecLogList.get(i).getCreateTime();
					arrObj[9] = elecLogList.get(i).getCreateBy();
					arrObj[10] = formatter.format(elecLogList.get(i).getUpdateTime());
					arrObj[11] = elecLogList.get(i).getUpdateBy();
					arrObj[12] = elecLogList.get(i).getRemark();
					
					elecLogdataList.add(arrObj);
				}

				

				String title =  "乌镇电费报表.xlsx";

				String oneheaders1 = createTime+"乌镇"+userType+userId+"电费预存表";
				String oneheaders2 = createTime+"乌镇"+userType+userId+"电费欠费表";
				String oneheaders3 = createTime+"乌镇"+userType+userId+"电费实收表";
				
				
				String headStr = "attachment; filename=\"" + new String( title.getBytes("gb2312"), "ISO8859-1" ) + "\"";
				response.setContentType("octets/stream");
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				
				ExportExcelSheet ex1  = new ExportExcelSheet("预存表", oneheaders1, headers1, elecPredataList);
				ExportExcelSheet ex2  = new ExportExcelSheet("欠费表", oneheaders2, headers2, elecOwedataList);
				ExportExcelSheet ex3  = new ExportExcelSheet("实收表", oneheaders3, headers3, elecLogdataList);

				ex1.export(workbook,out);
				ex2.export(workbook,out);
				ex3.export(workbook,out);
		
				workbook.write(out);  
				out.close();  

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		


}