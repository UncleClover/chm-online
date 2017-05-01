package com.clover.base.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @desc 数据库连接工具类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-5-1 下午7:59:34
 */
public class ConnTools {
	private static Logger logger = Logger.getLogger(ConnTools.class);
	private static DataSourceTools datasourceTools = DataSourceTools.getInstance();

	/**
	 * @desc 获取默认数据库连接
	 * @author zhangdq
	 * @time 2017-5-1 下午7:59:05
	 * @param 
	 * @return Connection
	 */
	public static Connection getyConnect() {
		try {
			DataSource datasource = datasourceTools.getDataSource();
			Connection conn = datasource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("获取默认数据库连接异常 <<<<<<<<<<>>>>>>>>>>异常信息：" + e);
		}
		return null;
	}

	/**
	 * @desc 根据数据库ID获取数据连接
	 * @author zhangdq
	 * @time 2017-5-1 下午7:57:18
	 * @param id
	 *            数据库ID
	 * @return Connection
	 */
	public static Connection getConnect(String id) {
		try {
			DataSource datasource = datasourceTools.getDataSource(id);
			Connection conn = datasource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("获取数据库连接异常-数据库ID=" + id + "<<<<<<<<<<>>>>>>>>>>异常信息：" + e);
		}
		return null;
	}
}
