package com.clover.action.views;


import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.clover.base.action.BaseAction;
import com.clover.base.jdbc.DataRow;
import com.clover.dao.service.ArticleService;
import com.opensymphony.xwork2.ActionSupport;

@Results({ 
	@Result(name = ActionSupport.SUCCESS, location = "/views/template/article/add.ftl"),
	@Result(name = "home", location = "home/index.ftl")
})

public class ArticleAction extends BaseAction {
	public String execute() {
		return SUCCESS;
	}
	
	public String add(){
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		DataRow data = new DataRow();
		data.set("title", title);
		data.set("content", content);
		data.set("user_id", "1");
		
		ArticleService service = (ArticleService) this.getService("articleService");
		service.addArticle(data);
		
		return "home";
	}
}
