package com.clover.base.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @desc ���ݿ����ӹ�����
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-5-1 ����7:59:34
 */
public class ConnTools {
	private static Logger logger = Logger.getLogger(ConnTools.class);
	private static DataSourceTools datasourceTools = DataSourceTools.getInstance();

	/**
	 * @desc ��ȡĬ�����ݿ�����
	 * @author zhangdq
	 * @time 2017-5-1 ����7:59:05
	 * @param 
	 * @return Connection
	 */
	public static Connection getyConnect() {
		try {
			DataSource datasource = datasourceTools.getDataSource();
			Connection conn = datasource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("��ȡĬ�����ݿ������쳣 <<<<<<<<<<>>>>>>>>>>�쳣��Ϣ��" + e);
		}
		return null;
	}

	/**
	 * @desc �������ݿ�ID��ȡ��������
	 * @author zhangdq
	 * @time 2017-5-1 ����7:57:18
	 * @param id
	 *            ���ݿ�ID
	 * @return Connection
	 */
	public static Connection getConnect(String id) {
		try {
			DataSource datasource = datasourceTools.getDataSource(id);
			Connection conn = datasource.getConnection();
			return conn;
		} catch (SQLException e) {
			logger.error("��ȡ���ݿ������쳣-���ݿ�ID=" + id + "<<<<<<<<<<>>>>>>>>>>�쳣��Ϣ��" + e);
		}
		return null;
	}
}
