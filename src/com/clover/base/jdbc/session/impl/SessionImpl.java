package com.clover.base.jdbc.session.impl;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		String sql = "DELETE FROM " + tbName + " WHERE " + identify + "=?";
		return update(sql, new Object[] { identify });
	}

	@Override
	public int delete(String sql, Object[] objs) {
		return update(sql, objs);
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
		return query(sql, null);
	}

	@Override
	public List<DataRow> query(String sql, Object[] objs) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<DataRow> list = new ArrayList<DataRow>();
		logger.info("开始执行SQL<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>sql = " + sql);
		try {
			psmt = conn.prepareStatement(sql);
			if (objs != null && objs.length > 0) {
				for (int i = 1; i <= objs.length; i++) {
					psmt.setObject(i, objs[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData(); // 获得列集
			while (rs.next()) {
				list.add(toDataRow(rs, rsm));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<DataRow> query(String sql, int rows) {
		return query(sql, null, rows);
	}

	@Override
	public List<DataRow> query(String sql, Object[] objs, int rows) {
		return query(sql, objs, 0, rows);
	}

	@Override
	public List<DataRow> query(String sql, int startRows, int rows) {
		return query(sql, null, startRows, rows);
	}

	@Override
	public List<DataRow> query(String sql, Object[] objs, int startRows, int rows) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<DataRow> list = new ArrayList<DataRow>();

		// 组装SQL语句
		StringBuilder sb = new StringBuilder();
		sb.append("select * from (select row_.*,rownum rownum_ from (");
		sb.append(sql);
		sb.append(") row_ where rownum <=" + (startRows + rows) + ") where rownum_ > " + startRows);

		logger.info("开始执行SQL<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>sql = " + sb.toString());

		try {
			psmt = conn.prepareStatement(sb.toString());
			psmt.setFetchSize(50);
			if (objs != null && objs.length > 0) {
				for (int i = 1; i <= objs.length; i++) {
					psmt.setObject(i, objs[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData(); // 获得列集
			while (rs.next()) {
				list.add(toDataRow(rs, rsm));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void beginTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void commitTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollbackTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
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

	/**
	 * @desc 结果集转化为DataRow格式
	 * @author zhangdq
	 * @time 2017-6-2 下午3:42:58
	 * @param
	 * @return
	 */
	public DataRow toDataRow(ResultSet rs, ResultSetMetaData rsmd) {
		DataRow data = new DataRow();
		try {
			// 获得列的个数
			int col = rsmd.getColumnCount();
			for (int i = 0; i < col; i++) {
				String colmunName = rsmd.getColumnName(i + 1);
				Object value = rs.getObject(colmunName);
				if (value instanceof Clob) {
					value = rs.getString(colmunName);
				} else if (value instanceof Blob) {
					value = rs.getByte(colmunName);
				} else if (value instanceof Date) {
					value = rs.getTimestamp(colmunName);
				}
				data.set(colmunName, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
}
