package com.clover.base.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.clover.base.constants.ChmConstants;
import com.clover.base.jdbc.session.impl.SessionImpl;
import com.clover.base.utils.StringUtils;

/**
 * ��ȡ����ֵ
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-11 ����10:06:57
 */
public class SequenceGenerator {
	
	private static Logger logger = Logger.getLogger(SessionImpl.class);
	
	public static SequenceGenerator instance = new SequenceGenerator();

	/**
	 * ��ȡSequenceGenerator��ĵ���
	 * @author zhangdq
	 * @date 2017-6-11 ����10:07:39
	 * @return SequenceGenerator
	 */
	public static SequenceGenerator getInstance() {
		return instance;
	}
	
	/**
	 * ��ȡ���е���һ��ֵ
	 * @author zhangdq
	 * @date 2017-6-13 ����9:52:33
	 * @param name ��������(Ĭ��seq_����)
	 * @return String
	 */
	public String getNextSequence(String name){
		Connection conn = ConnTools.getConnect();
		return getNextSequence(conn, name);
	}
	/**
	 * ��ȡ������һ��ֵ
	 * @author zhangdq
	 * @date 2017-6-11 ����10:09:03
	 * @param id ���ݿ�����ID
	 * @param name ��������(Ĭ��seq_����)
	 * @return
	 */
	public String getNextSequence(String id, String name){
		Connection conn = ConnTools.getConnect(id);
		return getNextSequence(conn, name);
	}
	
	/**
	 * ��ȡ������һ��ֵ
	 * @author zhangdq
	 * @date 2017-6-13 ����5:34:21
	 * @param Connection ���ݿ�����
	 * @param name ��������(Ĭ��seq_����)
	 * @return
	 */
	private String getNextSequence(Connection conn, String name) {
		int databaseType = getDatabaseType(conn);
		PreparedStatement psmt = null;
		ResultSet result = null;
		String nextVal = null;
		String seqName = "";
		if (databaseType == DatabaseType.ORACLE) {
			try {
				seqName = getSeqNameByTableName(name);
				String sql = "select " + seqName + ".nextval from dual";

				ConnTools.beginTrans(conn);
				psmt = conn.prepareStatement(sql);
				result = psmt.executeQuery();
				if (result != null && result.next()) {
					nextVal = result.getString("nextval");
				} else {
					throw new SQLException("��ȡOracle����ʧ��~");
				}
				ConnTools.commitTrans(conn);
			} catch (SQLException e) {
				// oracle SQL�쳣���룺2289-���в�����
				if (e.getErrorCode() == ChmConstants.SEQUENCE_NOT_EXISTS) {
					// ��������
					createSequence(conn, seqName);
					ConnTools.commitTrans(conn);
					return "1";
				}
			} finally {
				closeResult(result);
				closeStatment(psmt);
				ConnTools.close(conn);
			}
		}
		return nextVal;
	}

	/**
	 * ��ȡ���ݿ����ӵ����ͣ�Ĭ����Oracle���ݿ�
	 * @author zhangdq
	 * @date 2017-6-13 ����10:45:20
	 * @param Connection ���ݿ�����
	 * @return int
	 */
	public int getDatabaseType(Connection conn){
		try {
	        String databaseTypeStr = conn.getMetaData().getDatabaseProductName();
	        if(databaseTypeStr.equalsIgnoreCase("oracle")){
	        	return DatabaseType.ORACLE;
	        }else if(databaseTypeStr.equalsIgnoreCase("MySQL")){
	        	return DatabaseType.MYSQL;
	        }else{
	        	throw new Exception("�ݲ�֧�ָ����ݿ⣡<<<<<<<<<>>>>>>>>>" + databaseTypeStr);
	        }
        } catch (Exception e) {
        	logger.error("��ȡ���ݿ����ӵ������쳣~");
	        e.printStackTrace();
        }
		logger.error("��ѯ���ݲ���ʧ��~Ĭ�Ϸ���Oracle���ݿ�����");
		return DatabaseType.ORACLE;
	}
	
	/**
	 * �������ݿ������ȡ��������
	 * @author zhangdq
	 * @date 2017-6-13 ����5:40:32
	 * @param tableName ����
	 * @return String ������
	 */
	public static String getSeqNameByTableName(String tableName) {
		String seqName = "";
		if (StringUtils.isNotEmpty(tableName)) {
			seqName = tableName.toLowerCase();
			if (seqName.startsWith("t_")) {
				seqName = seqName.replaceFirst("^t", "seq");
			} else {
				seqName = "seq_" + seqName;
			}
		}
		return seqName.length() < 30 ? seqName : seqName.substring(0, 30);
	}

	/**
	 * ����Oracle����
	 * @author zhangdq
	 * @date 2017-6-13 ����5:51:35
	 * @param Connection ���ݿ�����
	 * @param seqName ��������
	 * @return
	 */
	private void createSequence(Connection conn, String seqName) {
		PreparedStatement psmt = null;
		StringBuffer sb = new StringBuffer();
		sb.append("create sequence " + seqName + " ");
		sb.append("minvalue 1 ");
		sb.append("maxvalue 99999999999999999999 ");
		sb.append("start with 1 ");
		sb.append("increment by 1 ");
		sb.append("nocache");
		try {
			psmt = conn.prepareStatement(sb.toString());
			psmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("����Oracle�����쳣~");
			e.printStackTrace();
		} finally {
			closeStatment(psmt);
		}
	}
	
	/**
	 * �ر�Ԥ�������
	 * @author zhangdq
	 * @date 2017-6-13 ����6:01:38
	 * @param Statement
	 * @return
	 */
	private static void closeStatment(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.error("�ر�Ԥ��������쳣~");
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر����ݿ�����
	 * @author zhangdq
	 * @date 2017-6-13 ����6:04:11
	 * @param ResultSet ���������
	 * @return
	 */
	private static void closeResult(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("���ݿ������ر��쳣~");
			e.printStackTrace();
		}
	}
}
