package com.bootdo.heat.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bootdo.heat.vo.HeatVo;


public interface HeatIntoService {
	
	public String  importHeatIntoExcel(MultipartFile file,HeatVo heat) throws Exception;
	
	public List<HeatVo> getHeatIntoList(HeatVo heat);
	
	public List<HeatVo> getHeatNumber(HeatVo heat);
}
