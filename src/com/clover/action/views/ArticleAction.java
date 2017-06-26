package com.clover.action.views;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.web.struts2.Struts2Utils;

import com.clover.action.login.LoginAction;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = ActionSupport.SUCCESS, location = "/views/template/article/add.ftl") })
public class ArticleAction extends ActionSupport {
	Logger logger = Logger.getLogger(LoginAction.class);
	HttpServletRequest request = Struts2Utils.getRequest();

	public String execute() {
		return SUCCESS;
	}
}
