package com.clover.action.controller;

import org.springframework.web.bind.annotation.RequestMapping;


public class GisController {
	@RequestMapping("/hegis")
	public String execute() {
		return "/home/cmgisdemo";
	}

	@RequestMapping("/baidu")
	public String baidu() {
		return "/home/baidu";
	}
}
