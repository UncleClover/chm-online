package com.clover.action.login;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "login/login.ftl") })
public class LoginAction extends ActionSupport {
	
	public String execute() {
		
		return SUCCESS;
	}
}
