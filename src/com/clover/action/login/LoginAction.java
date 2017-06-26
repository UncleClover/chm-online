package com.clover.action.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "login.ftl") })
public class LoginAction extends ActionSupport {
	Logger logger = Logger.getLogger(LoginAction.class);
	HttpServletRequest request = Struts2Utils.getRequest();

	public String execute() {
		return SUCCESS;
	}

	public String doLogin() {
		String account = request.getParameter("account");
		String password = request.getParameter("pwd");
		return "login";
	}
}
