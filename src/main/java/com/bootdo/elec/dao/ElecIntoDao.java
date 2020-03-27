package com.bootdo.elec.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bootdo.elec.vo.ElecVo;



@Mapper
public interface ElecIntoDao {

	 int importElecIntoExcel(ElecVo elec);
	
	 List<ElecVo> getElecIntoList(ElecVo elec);
	
	 List<ElecVo> getElecNumber(ElecVo elec);
	
}
