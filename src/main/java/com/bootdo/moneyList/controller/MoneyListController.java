package com.bootdo.moneyList.controller;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.elec.domain.ElecDO;
import com.bootdo.heat.domain.HeatDO;
import com.bootdo.moneyList.damain.MoneyListDO;
import com.bootdo.moneyList.service.MoneyListService;
import com.bootdo.moneyLog.domain.ElecLogDO;
import com.bootdo.moneyLog.domain.HeatLogDO;
import com.bootdo.moneyLog.domain.WaterLogDO;
import com.bootdo.moneyLog.service.ElecLogService;
import com.bootdo.moneyLog.service.HeatLogService;
import com.bootdo.moneyLog.service.WaterLogService;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.service.PrintService;
import com.bootdo.print.vo.PrintVo;
import com.bootdo.water.domain.WaterDO;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
@RequestMapping({"/moneyList/moneyList"})
public class MoneyListController{
   @Autowired
   private MoneyListService moneyListService;
   @Autowired
   private ElecLogService elecLogService;
   @Autowired
   private HeatLogService heatLogService;
   @Autowired
   private WaterLogService waterLogService;
   @Autowired
   private PrintService printService;
  
  @GetMapping
  @RequiresPermissions({"moneyList:moneyList"})
   String MoneyList()  {
    return "moneyList/moneyList";
  }

  
  @ResponseBody
  @PostMapping({"/queryAllUser"})
  public List<MoneyListDO> queryAllUser(MoneyListDO moneyList){
   List<MoneyListDO> moneyLists = this.moneyListService.getAll(moneyList);
   
    for (int i = 0; i < moneyLists.size(); i++){
      if ((!"".equals(((MoneyListDO)moneyLists.get(i)).getUserType())) && (((MoneyListDO)moneyLists.get(i)).getUserType() != null)){
       if (((MoneyListDO)moneyLists.get(i)).getUserType().equals("A")) {
          ((MoneyListDO)moneyLists.get(i)).setUserType("现金缴费");
       }
       if (((MoneyListDO)moneyLists.get(i)).getUserType().equals("B")) {
        ((MoneyListDO)moneyLists.get(i)).setUserType("工资代扣");
       }
       if (((MoneyListDO)moneyLists.get(i)).getUserType().equals("C")) {
           ((MoneyListDO)moneyLists.get(i)).setUserType("微信支付");
        }
       if (((MoneyListDO)moneyLists.get(i)).getUserType().equals("D")) {
          ((MoneyListDO)moneyLists.get(i)).setUserType("银行转账");
        }
      }
      
      
    if ((!"".equals(((MoneyListDO)moneyLists.get(i)).getUserState())) && (((MoneyListDO)moneyLists.get(i)).getUserState() != null)) {
        if (((MoneyListDO)moneyLists.get(i)).getUserType().equals("1")) {
          ((MoneyListDO)moneyLists.get(i)).setUserType("使用中");
         }
        if (((MoneyListDO)moneyLists.get(i)).getUserType().equals("0")) {
          ((MoneyListDO)moneyLists.get(i)).setUserType("已停用");
         }
      }
    
    
    
    if ((!"".equals(((MoneyListDO)moneyLists.get(i)).getMoneyType())) && (((MoneyListDO)moneyLists.get(i)).getMoneyType() != null)){
        if (i == 0) {
        	
        	
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("1")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("矿民用");
           }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("2")) {
           ((MoneyListDO)moneyLists.get(i)).setMoneyType("矿商用");
       }
        
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("3")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("矿工业1");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("4")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("矿工业2");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("5")) {
             ((MoneyListDO)moneyLists.get(i)).setMoneyType("矿工业3");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("6")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("乌民用");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("7")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("乌商业");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("8")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("乌工业");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("9")) {
             ((MoneyListDO)moneyLists.get(i)).setMoneyType("煤田民用");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("10")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("煤田商业");
       }
        if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("11")) {
             ((MoneyListDO)moneyLists.get(i)).setMoneyType("政府部门");
       }
     }
        
        
        if (i == 1){
           if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("1")) {
             ((MoneyListDO)moneyLists.get(i)).setMoneyType("民用暖");
          }
          if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("2")) {
             ((MoneyListDO)moneyLists.get(i)).setMoneyType("商业暖");
           }
          if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("3")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("工业民");
           }
         if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("4")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("工业商");
           }
         }
        
        
       if (i == 2){
    	   
    	   
          if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("1")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("民用水");
          }
          if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("2")) {
            ((MoneyListDO)moneyLists.get(i)).setMoneyType("商业水1");
          }
          if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("3")) {
           ((MoneyListDO)moneyLists.get(i)).setMoneyType("商业水2");
          }
           if (((MoneyListDO)moneyLists.get(i)).getMoneyType().equals("4")) {
             ((MoneyListDO)moneyLists.get(i)).setMoneyType("商业水3");
          }
        }
      }
     }
    
     return moneyLists;
   }
  
   @ResponseBody
   @PostMapping({"/queryAllUserName"})
   public List<MoneyListDO> getAllUserName(MoneyListDO moneyList)
   {
     List<MoneyListDO> getAllUserName = this.moneyListService.getAllUserName(moneyList);
     
     return getAllUserName;
  }
   
@ResponseBody
@PostMapping({"/updateMoney"})
public Long updateMoney(MoneyListDO moneyList, HttpServletRequest rquest){
	
	
    String success = "";
    String id = moneyList.getId();
    List<MoneyListDO> getAll = this.moneyListService.getAll(moneyList);
    String userNameElec = ((MoneyListDO)getAll.get(0)).getUserName();
    String userNameHeat = ((MoneyListDO)getAll.get(1)).getUserName();
    String userNameWater = ((MoneyListDO)getAll.get(2)).getUserName();
  
    
     String userType = moneyList.getUserType();
     String elecType = ((MoneyListDO)getAll.get(0)).getMoneyType();
     String heatType = ((MoneyListDO)getAll.get(1)).getMoneyType();
     String waterType = ((MoneyListDO)getAll.get(2)).getMoneyType();
     String money = moneyList.getMoney();
     BigDecimal decimalMoney = new BigDecimal(money);
     ElecLogDO elecLog = new ElecLogDO();
     HeatLogDO heatLog = new HeatLogDO();
     WaterLogDO waterLog = new WaterLogDO();
     
     
     if (id.equals("0")){
    	 
    	 
       elecLog.setUserId(moneyList.getUserId());
       elecLog.setUserName(userNameElec);
       elecLog.setUserType(userType);
       elecLog.setUserOrg(moneyList.getUserOrg());
       elecLog.setElecType(elecType);
       elecLog.setElecMoney(decimalMoney);
       elecLog.setMoneyDate(new Date());
       moneyList.setMoneyDate(elecLog.getMoneyDate());
       elecLog.setCreateTime(moneyList.getCreateTime());
       elecLog.setCreateBy(ShiroUtils.getUserName());
       elecLog.setUpdateTime(new Date());
       elecLog.setUpdateBy(ShiroUtils.getUserName());
     
       success = id;
     }
     else if (id.equals("1"))
    {
       heatLog.setUserId(moneyList.getUserId());
      
       heatLog.setUserName(userNameHeat);
       heatLog.setUserType(userType);
       heatLog.setUserOrg(moneyList.getUserOrg());
       heatLog.setHeatType(heatType);
       heatLog.setHeatMoney(decimalMoney);
       heatLog.setMoneyDate(new Date());
       moneyList.setMoneyDate(heatLog.getMoneyDate());
       heatLog.setCreateTime(moneyList.getCreateTime());
       heatLog.setCreateBy(ShiroUtils.getUserName());
       heatLog.setUpdateTime(new Date());
       heatLog.setUpdateBy(ShiroUtils.getUserName());
      success = id;
     }
    else if (id.equals("2"))
    {
      waterLog.setUserId(moneyList.getUserId());
     
       waterLog.setUserName(userNameWater);
       waterLog.setUserType(userType);
       waterLog.setUserOrg(moneyList.getUserOrg());
       waterLog.setWaterType(waterType);
       waterLog.setWaterMoney(decimalMoney);
       waterLog.setMoneyDate(new Date());
       moneyList.setMoneyDate(waterLog.getMoneyDate());
       waterLog.setCreateTime(moneyList.getCreateTime());
       waterLog.setCreateBy(ShiroUtils.getUserName());
       waterLog.setUpdateTime(new Date());
       waterLog.setUpdateBy(ShiroUtils.getUserName());
       
     success = id;
   }
    PrintDO print = new PrintDO();
    if (userNameElec != null) {
     print.setUserName(userNameElec);
     }
    if (userNameHeat != null) {
       print.setUserName(userNameHeat);
    }
    if (userNameWater != null) {
      print.setUserName(userNameWater);
    }
    
    
    
     ElecDO elecDO = this.moneyListService.getElecPrint(moneyList);
     if (elecLog.getElecMoney() != null){
         if (elecDO != null) {
             print.setStart(elecDO.getStart());
             print.setEnd(elecDO.getEnd());
             print.setHu(elecDO.getHu());
             print.setElecAmount(elecDO.getElecAmount());
             print.setElecCost(elecDO.getElecCost());
             print.setElecPrice(elecDO.getElecPrice());
             print.setElecSum(elecDO.getElecSum().add(elecLog.getElecMoney()));
             if (heatLog.getHeatMoney() == null) {
              heatLog.setHeatMoney(new BigDecimal(0));
              }
              if (waterLog.getWaterMoney() == null) {
                waterLog.setWaterMoney(new BigDecimal(0));
              }
             print.setPrintMoney(elecLog.getElecMoney().add(heatLog.getHeatMoney()).add(waterLog.getWaterMoney()));
        	 
         }

    }
     
    if (id.equals("0")) {
      print.setElecMoney(decimalMoney);
    }
    HeatDO heatDO = this.moneyListService.getHeatPrint(moneyList);
    if (id.equals("1")) {
      print.setHeatMoney(decimalMoney);
    }
    if (heatLog.getHeatMoney() != null) {
      if (heatDO != null){
       print.setHeatArea(heatDO.getHeatArea());
       print.setHeatCost(heatDO.getHeatCost());
       print.setHeatPrice(heatDO.getHeatPrice());
       print.setHeatSum(heatDO.getHeatSum().add(heatLog.getHeatMoney()));
       if (elecLog.getElecMoney() == null) {
         elecLog.setElecMoney(new BigDecimal(0));
         }
       if (waterLog.getWaterMoney() == null) {
          waterLog.setWaterMoney(new BigDecimal(0));
         }
        print.setPrintMoney(elecLog.getElecMoney().add(heatLog.getHeatMoney()).add(waterLog.getWaterMoney()));
       }
     }
     WaterDO waterDO = this.moneyListService.getWaterPrint(moneyList);
     if (id.equals("2")) {
       print.setWaterMoney(decimalMoney);
     }
    if (waterLog.getWaterMoney() != null){
      if (waterDO != null){
        print.setWaterPrice(waterDO.getWaterPrice());
        print.setWaterCost(waterDO.getWaterCost());
        print.setWaterSum(waterDO.getWaterSum().add(waterLog.getWaterMoney()));
      }
      if (heatLog.getHeatMoney() == null) {
         heatLog.setHeatMoney(new BigDecimal(0));
      }
      if (elecLog.getElecMoney() == null) {
         elecLog.setElecMoney(new BigDecimal(0));
     }
       print.setPrintMoney(elecLog.getElecMoney().add(heatLog.getHeatMoney()).add(waterLog.getWaterMoney()));
     }
     if (success.equals("0"))
     {
      this.moneyListService.updateElec(moneyList);
      this.elecLogService.save(elecLog);
     }
      else if (success.equals("1"))
     {
      this.moneyListService.updateHeat(moneyList);
      this.heatLogService.save(heatLog);
     }
     else if (success.equals("2"))
     {
       this.moneyListService.updateWater(moneyList);
       this.waterLogService.save(waterLog);
     }


PrintVo printRecord = this.printService.selectByCondition(moneyList.getCreateTime(), moneyList.getUserOrg(), moneyList.getUserId(), moneyList.getMoneyDate());
     if (printRecord != null)    {
       print.setId(printRecord.getId());
      this.printService.update(print);
     }
     else  {
    	 
    	 
      print.setUserId(moneyList.getUserId());
      print.setPrintMoney(elecLog.getElecMoney().add(heatLog.getHeatMoney()).add(waterLog.getWaterMoney()));
      print.setPrintDate(new Date());
      print.setCreateBy(ShiroUtils.getUserName());
      print.setCreateTime(moneyList.getCreateTime());
      print.setUpdateBy(ShiroUtils.getUserName());
      print.setUpdateTime(new Date());
      print.setUserType(userType);
      print.setUserOrg(moneyList.getUserOrg());

       this.printService.save(print);
    }
    Long printId = print.getId();
     HttpSession session = rquest.getSession();
    session.setAttribute(printId.toString(), moneyList);
    return printId;
   }
  


@GetMapping({"/moneyPrint/{id}/{userNo}"})
String printEdit(@PathVariable("id") Long id, @PathVariable("userNo") String userNo, Model model, MoneyListDO moneyList, HttpServletRequest request){
	
    PrintVo print = this.printService.get(id);
    HttpSession session = request.getSession();
    moneyList = (MoneyListDO)session.getAttribute(id.toString());
    
    if (print.getWaterMoney() == null) {
      print.setWaterMoney(new BigDecimal(0));
     } else {
      print.setPrintMoney(print.getWaterMoney());
    }
    if (print.getHeatMoney() == null) {
       print.setHeatMoney(new BigDecimal(0));
     } else {
       print.setPrintMoney(print.getHeatMoney());
    }
    if (print.getElecMoney() == null) {
      print.setElecMoney(new BigDecimal(0));
    } else {
       print.setPrintMoney(print.getElecMoney());
    }
    if (sameDate(moneyList.getMoneyDate(), print.getPrintDate())) {
       print.setPrintMoney(print.getWaterMoney().add(print.getHeatMoney().add(print.getElecMoney())));
    }
    print.setUserId(userNo);
    model.addAttribute("print", print);
    return "moneyList/moneyPrint";
   }


   public boolean sameDate(Date moneyDate, Date printDate) {
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
     return fmt.format(moneyDate).equals(fmt.format(printDate));
   }
}



