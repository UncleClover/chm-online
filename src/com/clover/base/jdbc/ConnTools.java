package com.clover.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

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
	public static Connection getConnect() {
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

	/**
	 * @desc ��ȡ���ݿⵥ������
	 * @author zhangdq
	 * @time 2017-5-2 23:42:23
	 * @param id
	 * @return
	 */
	public static Connection getSingleConnect(String id) {
		Map<String, String> map = datasourceTools.getDataSourceParam(id);
		String url = map.get("url");
		String driver = map.get("driver");
		String user = map.get("user");
		String password = map.get("password");
		try {
			Class.forName(driver).newInstance();
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			logger.info("��ȡ���ݿⵥ�������쳣-���ݿ�ID=" + id + "<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>�쳣��Ϣ��" + e);
		}
		return null;
	}
	
	/**
	 * �������ݿ�����
	 * @author zhangdq
	 * @date 2017-6-13 ����5:21:55
	 * @param 
	 * @return
	 */
	public static void beginTrans(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			logger.info("�������ݿ������쳣-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>�쳣��Ϣ��" + e);
			e.printStackTrace();
		}
	}

	/**
	 * �ύ���ݿ�����
	 * @author zhangdq
	 * @date 2017-6-13 ����5:21:45
	 * @param 
	 * @return
	 */
	public static void commitTrans(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			logger.info("�ύ���ݿ������쳣-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>�쳣��Ϣ��" + e);
			e.printStackTrace();
		}
	}

	/**
	 * �ع����ݿ��������
	 * @author zhangdq
	 * @date 2017-6-13 ����5:21:58
	 * @param 
	 * @return
	 */
	public static void rollbackTrans(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			logger.info("�ع����ݿ������쳣-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>�쳣��Ϣ��" + e);
			e.printStackTrace();
		}
	}

	/**
	 * �ر����ݿ�����
	 * @author zhangdq
	 * @date 2017-6-13 ����5:19:09
	 * @param 
	 * @return
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.info("�ر����ݿ�����-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>�쳣��Ϣ��" + e);
			e.printStackTrace();
		}
	}
}
