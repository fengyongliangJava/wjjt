package com.bootdo.elec.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bootdo.elec.vo.ElecVo;


public interface ElecIntoService {
	
	
	
	public String importElecIntoExcel(MultipartFile file, ElecVo elec) throws Exception;
	
	
	public List<ElecVo> getElecIntoList(ElecVo elec);
	
	public List<ElecVo> getElecNumber(ElecVo elec);
}
