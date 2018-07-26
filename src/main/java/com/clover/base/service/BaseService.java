package com.clover.base.service;

import com.clover.base.jdbc.session.JdbcTemplate;

/**
 * service基类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-27 下午2:54:57
 */
public class BaseService {
	/**
	 * 根据DataSource id获取数据库连接
	 * @author UncleClover
	 * @date 2017-6-27 下午2:55:40
	 * @param id
	 * @return JdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate(String id) {
		return new JdbcTemplate(id);
	}
}
