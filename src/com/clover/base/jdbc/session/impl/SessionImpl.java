package com.clover.base.jdbc.session.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.session.Session;

public class SessionImpl implements Session {
	private static Logger logger = Logger.getLogger(SessionImpl.class);
	private Connection conn = null;

	public SessionImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Connection connection() {
		return conn;
	}

	@Override
	public int insert(String tbName, DataRow data) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(tbName).append(" ");
		sb.append("(");
		int i = 0;
		List<Object> valList = new ArrayList<Object>();
		String replaceStr = "";
		for (Iterator<?> it = data.keySet().iterator(); it.hasNext();) {
			String columnName = (String) it.next();
			valList.add(data.get(columnName));
			if (i < data.size() - 1) {
				sb.append(columnName).append(",");
				replaceStr = replaceStr + "?,";
			} else {
				sb.append(columnName).append(") ");
				replaceStr = replaceStr + "?";
			}
			i++;
		}
		sb.append("values(").append(replaceStr).append(")");
		return update(sb.toString(), valList.toArray());
	}

	@Override
	public int delete(String tbName, String identify, Object identifyValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String sql, Object[] objs) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;
		logger.info("开始执行SQL<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>sql = " + sql);
		try {
			psmt = (PreparedStatement) this.conn.prepareStatement(sql);
			if (objs != null && objs.length > 0) {
				for (int i = 1; i <= objs.length; i++) {
					psmt.setObject(i, objs[i - 1]);
				}
			}

			result = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public List<DataRow> query(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRow> query(String sql, Object[] objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beginTrans() {
		// TODO Auto-generated method stub

	}

	@Override
	public void commitTrans() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollbackTrans() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	/**
	 * @desc 关闭数据库结果集，对异常不做处理直接抛出，因为数据库调用出问题是不允许
	 * @author zhangdq
	 * @time 2017-06-01 22:36
	 * @param rs
	 */
	public void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @desc 关闭数据库执行接口，对异常不做处理直接抛出，因为数据库调用出问题是不允许
	 * @author zhangdq
	 * @time 2017-06-01 22:39
	 * @param stmt
	 */
	public void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
