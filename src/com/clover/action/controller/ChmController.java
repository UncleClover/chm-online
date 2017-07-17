package com.clover.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clover.base.constants.TemplateConstants;

@Controller
public class ChmController {
	
	@RequestMapping("chm")
	public String execute(){
		return TemplateConstants.CHM_INDEX;
	}
}
