package com.clover.dao.service.impl;

import java.util.List;

import com.clover.base.constants.ChmConstants;
import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.session.JdbcTemplate;
import com.clover.base.service.BaseService;
import com.clover.dao.service.ArticleService;

public class ArticleServiceImpl extends BaseService implements ArticleService {

	/**
	 * 获取数据库连接
	 * @author zhangdq
	 * @date 2017-6-27 下午3:03:42
	 * @param 
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.getJdbcTemplate(ChmConstants.DB_CHM);
	}

	@Override
	public DBPage queryAllArticle(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataRow queryArticleById(String id, String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRow> queryArticleList(DataRow data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addArticle(DataRow data) {
		return this.getJdbcTemplate().insert("T_CHM_ARTICLE", data);
	}

	@Override
	public int updateArticle(DataRow data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArticle(String id, String user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteArticle(DataRow data) {
		// TODO Auto-generated method stub
		return 0;
	}

}
