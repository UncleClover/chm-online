package com.clover.base.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springside.modules.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

/**
 * action基类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-27 下午4:11:56
 */
public class BaseAction extends ActionSupport {
	public HttpServletRequest request = Struts2Utils.getRequest();
	public HttpServletResponse response = Struts2Utils.getResponse();
	public HttpSession session = request.getSession();
	
	public Object getService(String bean_id){
		ApplicationContext context = new ClassPathXmlApplicationContext("chm_beans.xml");
		return context.getBean(bean_id);
	}
}
