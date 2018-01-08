package com.clover.dao.service;

import java.util.List;

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;

/**
 * chm文件相关操作service
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-27 下午2:35:03
 */
public interface ArticleService {
	
	/**
	 * 查询当前用户所有的CHM列表
	 * @author zhangdq
	 * @date 2017-6-27 下午2:35:48
	 * @param user_id 用户ID
	 * @return DBPage
	 */
	DBPage queryAllArticle(String user_id, int curPage, int numPerPage);
	
	/**
	 * 根据chm ID查询
	 * @author zhangdq
	 * @date 2017-6-27 下午2:37:10
	 * @param id 文章ID
	 * @param user_id 用户ID
	 * @return DataRow
	 */
	DataRow queryArticleById(String id, String user_id);
	
	/**
	 * 查询chm
	 * @author zhangdq
	 * @date 2017-6-27 下午2:37:53
	 * @param DataRow
	 * @return DataRow
	 */
	List<DataRow> queryArticleList(DataRow data);
	
	/**
	 * 增加chm
	 * @author zhangdq
	 * @date 2017-6-27 下午2:39:12
	 * @param 
	 * @return
	 */
	int addArticle(DataRow data);
	
	/**
	 * 更新chm
	 * @author zhangdq
	 * @date 2017-6-27 下午2:39:39
	 * @param 
	 * @return
	 */
	int updateArticle(DataRow data);
	
	/**
	 * 根据chm ID和账户user_id删除chm
	 * @author zhangdq
	 * @date 2017-6-27 下午2:42:43
	 * @param 
	 * @return
	 */
	int deleteArticle(String id, String user_id);
	
	/**
	 * 删除chm
	 * @author zhangdq
	 * @date 2017-6-27 下午2:42:08
	 * @param 
	 * @return
	 */
	int deleteArticle(DataRow data);
	
	/**
	 * 查询首页常用chm列表
	 * 
	 * @author zhangdq
	 * @time 2017年9月18日 下午10:21:21
	 * @Email qiang900714@126.com
	 * @param user_id
	 * @return
	 */
	List<DataRow> queryArticleLimit(String user_id);
}
