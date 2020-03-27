package com.bootdo.water.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bootdo.water.vo.WaterVo;

public interface WaterIntoService {
	
	
	public String importWaterIntoExcel(MultipartFile file,WaterVo water) throws Exception;
	
	
	public List<WaterVo> getWaterIntoList(WaterVo water);
	
	public List<WaterVo> getWaterNumber(WaterVo water);
	
}
