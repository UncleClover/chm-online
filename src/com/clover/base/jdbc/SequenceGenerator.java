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
 * 获取序列值
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-11 下午10:06:57
 */
public class SequenceGenerator {
	
	private static Logger logger = Logger.getLogger(SessionImpl.class);
	
	public static SequenceGenerator instance = new SequenceGenerator();

	/**
	 * 获取SequenceGenerator类的单例
	 * @author zhangdq
	 * @date 2017-6-11 下午10:07:39
	 * @return SequenceGenerator
	 */
	public static SequenceGenerator getInstance() {
		return instance;
	}
	
	/**
	 * 获取序列的下一个值
	 * @author zhangdq
	 * @date 2017-6-13 上午9:52:33
	 * @param name 序列名字(默认seq_表名)
	 * @return String
	 */
	public String getNextSequence(String name){
		Connection conn = ConnTools.getConnect();
		return getNextSequence(conn, name);
	}
	/**
	 * 获取序列下一个值
	 * @author zhangdq
	 * @date 2017-6-11 下午10:09:03
	 * @param id 数据库配置ID
	 * @param name 序列名字(默认seq_表名)
	 * @return
	 */
	public String getNextSequence(String id, String name){
		Connection conn = ConnTools.getConnect(id);
		return getNextSequence(conn, name);
	}
	
	/**
	 * 获取序列下一个值
	 * @author zhangdq
	 * @date 2017-6-13 下午5:34:21
	 * @param Connection 数据库连接
	 * @param name 序列名字(默认seq_表名)
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
					throw new SQLException("获取Oracle序列失败~");
				}
				ConnTools.commitTrans(conn);
			} catch (SQLException e) {
				// oracle SQL异常代码：2289-序列不存在
				if (e.getErrorCode() == ChmConstants.SEQUENCE_NOT_EXISTS) {
					// 创建序列
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
	 * 获取数据库连接的类型，默认是Oracle数据库
	 * @author zhangdq
	 * @date 2017-6-13 上午10:45:20
	 * @param Connection 数据库连接
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
	        	throw new Exception("暂不支持该数据库！<<<<<<<<<>>>>>>>>>" + databaseTypeStr);
	        }
        } catch (Exception e) {
        	logger.error("获取数据库连接的类型异常~");
	        e.printStackTrace();
        }
		logger.error("查询数据产商失败~默认返回Oracle数据库类型");
		return DatabaseType.ORACLE;
	}
	
	/**
	 * 根据数据库表名获取序列名字
	 * @author zhangdq
	 * @date 2017-6-13 下午5:40:32
	 * @param tableName 表名
	 * @return String 序列名
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
	 * 创建Oracle序列
	 * @author zhangdq
	 * @date 2017-6-13 下午5:51:35
	 * @param Connection 数据库序列
	 * @param seqName 序列名字
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
			logger.error("创建Oracle序列异常~");
			e.printStackTrace();
		} finally {
			closeStatment(psmt);
		}
	}
	
	/**
	 * 关闭预编译对象
	 * @author zhangdq
	 * @date 2017-6-13 下午6:01:38
	 * @param Statement
	 * @return
	 */
	private static void closeStatment(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			logger.error("关闭预编译对象异常~");
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭数据库结果集
	 * @author zhangdq
	 * @date 2017-6-13 下午6:04:11
	 * @param ResultSet 结果集对象
	 * @return
	 */
	private static void closeResult(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.error("数据库结果集关闭异常~");
			e.printStackTrace();
		}
	}
}
