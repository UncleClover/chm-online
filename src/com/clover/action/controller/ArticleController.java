package com.clover.action.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clover.base.BaseResponse;
import com.clover.base.constants.ChmConstants;
import com.clover.base.constants.TemplateConstants;
import com.clover.base.jdbc.DataRow;
import com.clover.dao.service.ArticleService;

@Controller
@RequestMapping("article")
public class ArticleController {
	
	@Autowired
	private ArticleService service;
	
	@RequestMapping("add")
	public String addArticle() {
		return TemplateConstants.CHM_ARTICLE_ADD;
	}
	
	/**
	 * 保存文章内容
	 * 
	 * @author zhangdq
	 * @time 2017年8月29日 下午11:13:34
	 * @Email qiang900714@126.com
	 * @param title
	 * @param content
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	public BaseResponse saveArticle(String title, String content){
		if(StringUtils.isEmpty(title)){
			return new BaseResponse(ChmConstants.OPER_FAIL_CODE, "chm标题不能为空");
		}
		if(StringUtils.isEmpty(content)){
			return new BaseResponse(ChmConstants.OPER_FAIL_CODE, "chm内容不能为空");
		}
		
		DataRow data = new DataRow();
		data.set("title", title);
		data.set("content", content);
		int row = service.addArticle(data);
		
		return new BaseResponse(ChmConstants.OPER_SUCCESS_CODE, ChmConstants.OPER_SUCCESS_MSG, row);
	}
}
