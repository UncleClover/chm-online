package com.clover.action.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clover.base.BaseResponse;
import com.clover.base.constants.ChmConstants;
import com.clover.base.constants.TemplateConstants;
import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;
import com.clover.base.utils.StringUtils;
import com.clover.dao.service.ArticleService;

@Controller
@RequestMapping("article")
public class ArticleController {
	private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ArticleService service;

	@RequestMapping("/add")
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
	@RequestMapping("/save")
	@ResponseBody
	public BaseResponse saveArticle(String title, String content, String id, String updateTimes) {
		if (StringUtils.isEmpty(title)) {
			return new BaseResponse(ChmConstants.OPER_FAIL_CODE, "chm标题不能为空");
		}
		if (StringUtils.isEmpty(content)) {
			return new BaseResponse(ChmConstants.OPER_FAIL_CODE, "chm内容不能为空");
		}

		DataRow data = new DataRow();
		data.set("title", title);
		data.set("content", content);
		data.set("update_times", updateTimes);

		int row = 0;
		if (StringUtils.isEmpty(id)) {
			row = service.addArticle(data);
		} else {
			data.set("id", id);
			row = service.updateArticle(data);
		}

		return new BaseResponse(ChmConstants.OPER_SUCCESS_CODE, ChmConstants.OPER_SUCCESS_MSG, row);
	}

	/**
	 * 分页查询chm列表
	 * 
	 * @author zhangdq
	 * @time 2017年8月31日 上午12:26:18
	 * @Email qiang900714@126.com
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse queryArticlePageResult(int curPage, int numPerPage) {
		DBPage dbPage = service.queryAllArticle(null, curPage, numPerPage);
		return new BaseResponse(ChmConstants.OPER_SUCCESS_CODE, ChmConstants.OPER_SUCCESS_MSG, dbPage);
	}

	/**
	 * 查询文章详情
	 * 
	 * @author zhangdq
	 * @time 2017年9月3日 下午10:23:03
	 * @Email qiang900714@126.com
	 * @param articleId
	 * @return
	 */
	@RequestMapping("/detail")
	public String queryArticleDetailById(String articleId, Model model) {
		DataRow data = service.queryArticleById(articleId, null);
		model.addAttribute("article", data);

		return TemplateConstants.CHM_ARTICLE_DETAIL;
	}

	/**
	 * 更新chm内容
	 * 
	 * @author zhangdq
	 * @time 2017年9月18日 下午9:46:48
	 * @Email qiang900714@126.com
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String updateArticleById(String articleId, Model model) {
		logger.info("更新ID{}", articleId);
		DataRow data = service.queryArticleById(articleId, null);
		model.addAttribute("article", data);

		return TemplateConstants.CHM_ARTICLE_ADD;
	}

	/**
	 * 查询常用chm列表
	 * 
	 * @author zhangdq
	 * @time 2017年8月31日 上午12:26:18
	 * @Email qiang900714@126.com
	 * @return
	 */
	@RequestMapping(value = "/hotList", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse queryArticleHomeList() {
		List<DataRow> articleList = service.queryArticleLimit(null);
		return new BaseResponse(ChmConstants.OPER_SUCCESS_CODE, ChmConstants.OPER_SUCCESS_MSG, articleList);
	}
}
