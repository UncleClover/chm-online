package com.clover.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clover.base.constants.TemplateConstants;

@Controller
@RequestMapping("article")
public class ArticleController {
	
	@RequestMapping("add")
	public String addArticle() {
		return TemplateConstants.CHM_ARTICLE_ADD;
	}
}
