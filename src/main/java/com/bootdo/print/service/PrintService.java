package com.bootdo.print.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.vo.PrintVo;




public interface PrintService {
	
	PrintVo get(Long id);
	
	List<PrintVo> list(Map<String, Object> map);
	
	List<PrintVo> batchPrint(Long[] ids);
	
	List<PrintVo> exprotPrint(PrintVo print);
	
	int count(Map<String, Object> map);
	
	int save(PrintDO print);
	
	int update(PrintDO print);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	public PrintVo selectByCondition( String createTime,String userOrg,String userId,Date moneyDate);


}
