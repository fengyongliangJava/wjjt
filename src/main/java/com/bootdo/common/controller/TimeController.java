package com.bootdo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/common/time")
public class TimeController extends BaseController{

	
	@GetMapping()
	String Time() {
		return "common/job/time";
	}



}
