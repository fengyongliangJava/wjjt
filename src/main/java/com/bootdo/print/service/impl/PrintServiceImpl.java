package com.bootdo.print.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.print.dao.PrintDao;
import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.service.PrintService;
import com.bootdo.print.vo.PrintVo;



@Service
public class PrintServiceImpl implements PrintService {
	
	
	@Autowired
	private PrintDao printDao;
	
	
	public PrintVo get(Long id){
		return printDao.get(id);
	}
	
	public List<PrintVo> list(Map<String, Object> map){
		return printDao.list(map);
	}
	
	public List<PrintVo> batchPrint(Long[] ids) {
		return printDao.batchPrint(ids);
	}
	
	public List<PrintVo> exprotPrint(PrintVo print) {
		return printDao.exprotPrint(print);
	}
	
	public int count(Map<String, Object> map){
		return printDao.count(map);
	}
	
	public int save(PrintDO print){
		return printDao.save(print);
	}
	
	public int update(PrintDO print){
		return printDao.update(print);
	}
	
	public int remove(Long id){
		return printDao.remove(id);
	}
	
	public int batchRemove(Long[] ids){
		return printDao.batchRemove(ids);
	}

	@Override
	public PrintVo selectByCondition(String createTime, String userOrg, String userId,Date moneyDate) {
		return printDao.selectByCondition(createTime,userOrg,userId,moneyDate);
	}


}
