package com.clover.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "home/index.ftl") })
public class ChmAction extends ActionSupport {
	private HttpServletRequest request = Struts2Utils.getRequest();

	public String execute() {
		request.setAttribute("name", "zhangdq");
		return SUCCESS;
	}
}