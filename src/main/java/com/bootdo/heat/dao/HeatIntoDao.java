package com.bootdo.heat.dao;


import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.bootdo.heat.vo.HeatVo;


@Mapper
public interface HeatIntoDao {


	public int importHeatIntoExcel(HeatVo heat);
	
	public List<HeatVo> getHeatIntoList(HeatVo heat);
	
	public List<HeatVo> getHeatNumber(HeatVo heat);
	
}
