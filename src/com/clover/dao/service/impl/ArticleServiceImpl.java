package com.clover.dao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.clover.base.constants.ChmConstants;
import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.session.JdbcTemplate;
import com.clover.base.service.BaseService;
import com.clover.base.utils.DateUtils;
import com.clover.dao.service.ArticleService;

@Service
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
	public DBPage queryAllArticle(String user_id, int curPage, int numPerPage) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM T_CHM_ARTICLE");
		return this.getJdbcTemplate().queryPage(sql.toString(), curPage, numPerPage);
	}

	@Override
	public DataRow queryArticleById(String id, String user_id) {
		String sql = "SELECT * FROM T_CHM_ARTICLE WHERE ID=?";
		return this.getJdbcTemplate().queryMap(sql, new String[]{id});
	}

	@Override
	public List<DataRow> queryArticleList(DataRow data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addArticle(DataRow data) {
		data.set("create_time", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return this.getJdbcTemplate().insert("T_CHM_ARTICLE", data);
	}

	@Override
	public int updateArticle(DataRow data) {
		data.set("update_time", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		return this.getJdbcTemplate().update("T_CHM_ARTICLE", data, "id", data.get("id"));
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

	@Override
	public List<DataRow> queryArticleLimit(String user_id) {
		String sql = "SELECT t.* FROM t_chm_article t limit 0, " + ChmConstants.HOME_PAGE_HOT_LIST_NUM;
		return this.getJdbcTemplate().query(sql);
	}
}
