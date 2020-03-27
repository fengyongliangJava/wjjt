package com.bootdo.water.dao;

import com.bootdo.water.vo.WaterVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface WaterIntoDao {


	public int importWaterIntoExcel(WaterVo water);
	
	
	public List<WaterVo> getWaterIntoList(WaterVo water);
	
	
	public List<WaterVo> getWaterNumber(WaterVo water);
	
	
}
