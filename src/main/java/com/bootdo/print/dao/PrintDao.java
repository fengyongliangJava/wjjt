package com.bootdo.print.dao;

import com.bootdo.print.domain.PrintDO;
import com.bootdo.print.vo.PrintVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PrintDao {

	PrintVo get(Long id);
	
	List<PrintVo> list(Map<String,Object> map);
	
	List<PrintVo> exprotPrint(PrintVo print);
	
	List<PrintVo> batchPrint(Long[] ids);
	
	int count(Map<String,Object> map);
	
	int save(PrintDO print);
	
	int update(PrintDO print);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	PrintVo selectByCondition(@Param("createTime") String createTime,@Param("userOrg") String userOrg,@Param("userId") String userId,@Param("moneyDate") Date moneyDate);
}
