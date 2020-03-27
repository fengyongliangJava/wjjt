package com.bootdo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/common/long")
public class LongController extends BaseController{

	
	@GetMapping()
	String Long() {
		return "common/job/Long";
	}



}
