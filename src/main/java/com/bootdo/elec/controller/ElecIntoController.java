package com.bootdo.elec.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.ExportExcel;
import com.bootdo.elec.service.ElecIntoService;
import com.bootdo.elec.vo.ElecVo;


@Controller
@RequestMapping("/elec/elecInto")
public class ElecIntoController {
	

	
	@Autowired
	private ElecIntoService elecIntoService;
	

	
	@GetMapping()
	@RequiresPermissions("elec:elecInto:elecInto")
	String ElecInto(){
	    return "elec/elecInto/elecInto";
	}
	

	 @ResponseBody
	 @PostMapping("/importElecIntoExcel")
	 public String importElecIntoExcel(@RequestParam("file") MultipartFile file, ElecVo elec,HttpServletResponse response) throws Exception {
		 try {
			 return elecIntoService.importElecIntoExcel(file, elec);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		 
		}

	
	 
	 
	 
	 
	
	@ResponseBody
	@GetMapping("/exprotElecIntoExcel")
	public void exprotExcel(ElecVo elec,HttpServletResponse response){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime = elec.getCreateTime();
			String userOrg = "";
			if ("2".equals(elec.getUserOrg())) {
				userOrg =   "五九地区";
			} else if ("3".equals(elec.getUserOrg())) {
				userOrg =   "牙星地区";
			}
			
			List<ElecVo> elecList = elecIntoService.getElecIntoList(elec);
			
			String[] rowName = {"序号","用户编号","用户姓名","用户地区","缴费方式","用户电话","工资代码","用户状态","用电类型","暖费单价","上月电表","本月电表","用户电量","互感比","用户电费","用户余额","创建时间","创建人员","更新时间","更新人员","用户备注"};
			
			List<Object[]> dataList = new ArrayList<Object[]>();
			
			
			int dataSize = elecList.size();
			for(int i = 0;i < dataSize ; i++ ) {
				Object[] arrObj = new Object[rowName.length];
				arrObj[0] = elecList.get(i).getId();
				arrObj[1] = elecList.get(i).getUserId();
				arrObj[2] = elecList.get(i).getUserName();
				arrObj[3] = elecList.get(i).getUserOrgName();
				
				if ("A".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "现金缴费";
				} else if ("B".equals(elecList.get(i).getUserType())) {
					arrObj[4]=   "工资代扣";
				} else if ("C".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "微信支付";
				} else if ("D".equals(elecList.get(i).getUserType())) {
					arrObj[4] =   "银行转账";
				}
				arrObj[5] = elecList.get(i).getUserTell();
				arrObj[6] = elecList.get(i).getWagesId();
				if ("1".equals(elecList.get(i).getUserState())) {
					arrObj[7] =   "使用中";
				} else if ("0".equals(elecList.get(i).getUserState())) {
					arrObj[7] =   "未使用";
				} 
				if ("1".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿民用";
				} else if ("2".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿商业";
				}else if ("3".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿工业1";
				}else if ("4".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿工业2";
				}else if ("5".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "矿工业3";
				}else if ("6".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌民用";
				}else if ("7".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌商业";
				}else if ("8".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "乌工业";
				}else if ("9".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "煤田民用";
				}else if ("10".equals(elecList.get(i).getElecType())) {
					arrObj[8] =   "煤田商业";
				}else if ("11".equals(elecList.get(i).getElecType())) {
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
				arrObj[18] = formatter.format(elecList.get(i).getUpdateTime());
				arrObj[19] = elecList.get(i).getUpdateBy();
				arrObj[20] = elecList.get(i).getRemark();
				dataList.add(arrObj);
			}

			ExportExcel excelPort = new ExportExcel(createTime+userOrg+"电费工资代扣明细Excel",rowName, dataList);
			HSSFWorkbook workbook = excelPort.export();
			String fileName = "电费工资代扣明细";
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
	
	
	


}
