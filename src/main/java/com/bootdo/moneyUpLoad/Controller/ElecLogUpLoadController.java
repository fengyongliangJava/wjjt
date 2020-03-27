package com.bootdo.moneyUpLoad.Controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.utils.ExportExcel;
import com.bootdo.common.utils.POIUtil;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.vo.ElecLogVo;

@Controller
@RequestMapping("/moneyUpLoad/ElecLogUpLoad")
public class ElecLogUpLoadController {

	@Autowired
	private ElecLogService elecLogService;
	
	
	@GetMapping()
	@RequiresPermissions("moneyUpLoad:ElecLogUpLoad")
	String ElecLogUpLoad(){
	    return "moneyUpLoad/ElecLogUpLoad";
	}
	

	 @ResponseBody
	 @RequestMapping("/importElecLogExcel")
	 public  String importElecLogExcel(@RequestParam(value = "file") MultipartFile excelFile,String createTime,String userOrg){
			
		 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		//定义一个返回控制
		String data="success";
		try {
		List<String[]> elecLogList = POIUtil.readExcel(excelFile);
		List<ElecLogDO> elecLogDOList = new ArrayList<ElecLogDO>();
		//行数
		int a ;
		for(int i = 1;i<elecLogList.size();i++) {
			 a = i + 2;//行数赋值
			 String[] elecLog = elecLogList.get(i);
			 ElecLogDO elecLogDO = new ElecLogDO();
	
			 //赋值会出现数组长度变化
			 if (elecLog.length >= 5) {
				 //校验数据不能为空
				 if (!"".equals(elecLog[0])
						 && !"".equals(elecLog[1])
						 && !"".equals(elecLog[2])
						 && !"".equals(elecLog[3])
						 && !"".equals(elecLog[4])
						 && !"".equals(elecLog[5]) ) {
		
					 elecLogDO.setUserId(elecLog[0]);//用户编号
					 elecLogDO.setUserName(elecLog[1]);//用户姓名
					 elecLogDO.setUserOrg(userOrg);//用户地区
					 elecLogDO.setUserType(elecLog[2]);//用户性质
					 elecLogDO.setElecType(elecLog[3]);//水费类型
					 elecLogDO.setElecMoney(new BigDecimal(elecLog[4]));
					 
					 try {
						elecLogDO.setMoneyDate(formatter.parse(elecLog[5]));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					 
					 elecLogDO.setCreateTime(createTime);//创建时间
					 elecLogDO.setCreateBy(ShiroUtils.getUserName());//创建人
					 elecLogDO.setUpdateTime(new Date());//更新时间（当前系统时间）
					 elecLogDO.setUpdateBy(ShiroUtils.getUserName());//更新人
					 elecLogDOList.add(elecLogDO);
		
				 } else {
					 if ("".equals(elecLog[0])) {
						 data = "第" + a + "行，用户编号为空，请认真编写";
					 } else if ("".equals(elecLog[1])) {
						 data = "第" + a + "行，用户姓名为空，请认真编写";
					 } else if ("".equals(elecLog[2])) {
						 data = "第" + a + "行，缴费方式为空，请认真编写";
					 } else if ("".equals(elecLog[3])) {
						 data = "第" + a + "行，电费类型为空，请认真编写";
					 } else if ("".equals(elecLog[4])) {
						 data = "第" + a + "行，缴费金额为空，请认真编写";
					 } else if ("".equals(elecLog[5])) {
						 data = "第" + a + "行，缴费时间为空，请认真编写";
					 } 
					 break;
		
				 }
			 } 
		
		}
		
	
		 if( elecLogDOList.size()+1==elecLogList.size()){
			  for(int i = 0;i<elecLogDOList.size();i++){
				  elecLogService.save(elecLogDOList.get(i));
			  }
		 }
		
		
		}  catch (IOException e) {
		 data="fail";
		e.printStackTrace();
		}
		
		return data;
	}
	
	
	

	@ResponseBody
	@GetMapping("/exprotElecLogExcel")
	public void exprotElecLogExcel(ElecLogVo elecLog,HttpServletResponse response,HttpServletRequest request){
		response.reset();
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String createTime =  elecLog.getCreateTime();
	
			
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
			
			List<ElecLogVo> elecLogList = elecLogService.exprotElecLogMM(elecLog);
			
			String[] rowName = {"序号","用户编号","用户姓名","缴费方式","用户地区","电费类型","缴费金额","缴费时间","收取时间","收费人员","更新时间","更新人员","用户备注"};
			
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
			
			ExportExcel excelPort = new ExportExcel(createTime+" "+userOrg+" "+userType+" "+elecType+" "+UserId+" "+"导出电费记录明细Excel",rowName, dataList);
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
	
	
}
