package com.clover.views.action;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "home/index.ftl") })
public class ChmAction extends ActionSupport {

	public String execute() {
		return SUCCESS;
	}
}