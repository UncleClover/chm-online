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

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.DatabaseType;
import com.clover.base.jdbc.session.Session;

public class SessionImpl implements Session {
	private static Logger logger = Logger.getLogger(SessionImpl.class);
	private Connection conn = null;
	private String generatedKeys = "";
	private int databaseType = DatabaseType.ORACLE;

	public SessionImpl(Connection conn) {
		this.conn = conn;
		setDababaseType(conn);
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
	public int delete(String sql, Object[] args) {
		return update(sql, args);
	}

	@Override
	public int update(String sql) {
		return update(sql, null);
	}

	@Override
	public int update(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			// mysql数据库插入时会返回主键自动增长的数值
			if (DatabaseType.MYSQL == databaseType && sql.toLowerCase().startsWith("insert")) {
				psmt = (PreparedStatement) this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			} else {
				psmt = (PreparedStatement) this.conn.prepareStatement(sql);
			}
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[i - 1]);
				}
			}

			result = psmt.executeUpdate();
			if (DatabaseType.MYSQL == databaseType && sql.toLowerCase().startsWith("insert")) {
				rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					setGeneratedKeys(rs.getString(1));
				}
			}
		} catch (SQLException e) {
			logger.info("数据更新异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public int[] batchUpdate(String[] sql) {
		Statement stmt = null;
		int[] result = null;
		try {
			stmt = conn.createStatement();
			for (int i = 0; i < sql.length; i++) {
				logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql[i]);

				stmt.addBatch(sql[i]);
			}

			result = stmt.executeBatch();
		} catch (SQLException e) {
			logger.info("数据批量更新异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeStatement(stmt);
		}
		return result;
	}

	@Override
	public int[] batchUpdate(String sql, Object[][] args) {
		PreparedStatement psmt = null;
		int[] result = null;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				Object[] ojbs = args[i];
				for (int j = 1; j < ojbs.length; j++) {
					psmt.setObject(j, ojbs[(j - 1)]);
				}
				psmt.addBatch();
			}
			result = psmt.executeBatch();
		} catch (SQLException e) {
			logger.info("数据批量更新异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public int update(String tbName, DataRow data, String identify, Object identifyValue) {
		return update(tbName, data, new String[] { identify }, new Object[] { identifyValue });
	}

	@Override
	public int update(String tbName, DataRow data, String[] identifys, Object[] identifyValues) {
		// 组装更新SQL语句
		StringBuilder sb = new StringBuilder();
		sb.append("update ").append(tbName).append(" set ");

		// 如果更新的字段是条件之一，则从更新字段中删除
		for (int i = 0; i < identifys.length; i++) {
			data.remove(identifyValues[i]);
		}
		int i = 0;
		List<Object> valueList = new ArrayList<Object>();
		for (Iterator<?> it = data.keySet().iterator(); it.hasNext();) {
			i++;
			String key = (String) it.next();
			valueList.add(data.get(key));
			if (i < data.size()) {
				sb.append(key + "=?, ");
			} else {
				sb.append(key + "=? ");
			}
		}
		for (int j = 0; j < identifys.length; j++) {
			System.out.println(identifys[j]);
			sb.append((j == 0 ? " where " : " and ") + identifys[j] + "=?");
			valueList.add(identifyValues[j]);
		}
		return update(sb.toString(), valueList.toArray());
	}

	@Override
	public List<DataRow> query(String sql) {
		return query(sql, null);
	}

	@Override
	public List<DataRow> query(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<DataRow> list = new ArrayList<DataRow>();
		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);
		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData(); // 获得列集
			while (rs.next()) {
				list.add(toDataRow(rs, rsm));
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return list;
	}

	@Override
	public List<DataRow> query(String sql, int rows) {
		return query(sql, null, rows);
	}

	@Override
	public List<DataRow> query(String sql, Object[] args, int rows) {
		return query(sql, args, 0, rows);
	}

	@Override
	public List<DataRow> query(String sql, int startRows, int rows) {
		return query(sql, null, startRows, rows);
	}

	@Override
	public List<DataRow> query(String sql, Object[] args, int startRows, int rows) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<DataRow> list = new ArrayList<DataRow>();

		// 组装SQL语句
		StringBuilder sb = new StringBuilder();
		if(DatabaseType.ORACLE == databaseType){
			logger.info("Oracle数据库查询SQL组装~");
			sb.append("select * from (select row_.*,rownum rownum_ from (");
			sb.append(sql);
			sb.append(") row_ where rownum <=" + (startRows + rows) + ") where rownum_ > " + startRows);

		}else if(DatabaseType.MYSQL == databaseType){
			logger.info("mysql数据库查询SQL组装~");
			sb.append(sql);
			sb.append("limit" + startRows + "," + rows);
		}
		
		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sb.toString());

		try {
			psmt = conn.prepareStatement(sb.toString());
			psmt.setFetchSize(50);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData(); // 获得列集
			while (rs.next()) {
				list.add(toDataRow(rs, rsm));
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return list;
	}

	@Override
	public int queryInt(String sql) {
		return queryInt(sql, null);
	}

	@Override
	public int queryInt(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int result = 0;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public int[] queryIntArray(String sql) {
		return queryIntArray(sql, null);
	}

	@Override
	public int[] queryIntArray(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int[] result = null;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();

			List<Integer> list = new ArrayList<Integer>();
			while (rs.next()) {
				list.add(Integer.valueOf(rs.getInt(1)));
			}

			if (list != null && list.size() > 0) {
				result = new int[list.size()];
				for (int i = 0; i < list.size(); i++) {
					result[i] = list.get(i);
				}
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public long queryLong(String sql) {
		return queryLong(sql, null);
	}

	@Override
	public long queryLong(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		long result = 0;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public long[] queryLongArray(String sql) {
		return queryLongArray(sql, null);
	}

	@Override
	public long[] queryLongArray(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		long[] result = null;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();

			List<Long> list = new ArrayList<Long>();
			while (rs.next()) {
				list.add(Long.valueOf(rs.getLong(1)));
			}

			if (list != null && list.size() > 0) {
				result = new long[list.size()];
				for (int i = 0; i < list.size(); i++) {
					result[i] = list.get(i);
				}
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public String queryString(String sql) {
		return queryString(sql, null);
	}

	@Override
	public String queryString(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String result = null;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public String[] queryStringArray(String sql) {
		return queryStringArray(sql, null);
	}

	@Override
	public String[] queryStringArray(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String[] result = null;

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();

			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}

			if (list != null && list.size() > 0) {
				result = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					result[i] = list.get(i);
				}
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return result;
	}

	@Override
	public DataRow queryMap(String sql) {
		return queryMap(sql, null);
	}

	@Override
	public DataRow queryMap(String sql, Object[] args) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		DataRow data = new DataRow();

		logger.info("开始执行SQL<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>sql = " + sql);

		try {
			psmt = conn.prepareStatement(sql);
			if (args != null && args.length > 0) {
				for (int i = 1; i <= args.length; i++) {
					psmt.setObject(i, args[(i - 1)]);
				}
			}
			rs = psmt.executeQuery();
			ResultSetMetaData rsm = rs.getMetaData(); // 获得列集
			while (rs.next()) {
				data = toDataRow(rs, rsm);
			}
		} catch (SQLException e) {
			logger.info("数据查询异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(psmt);
		}
		return data;
	}

	@Override
	public DBPage queryPage(String sql, int curPage, int numPerPage) {
		return queryPage(sql, null, curPage, numPerPage);
	}

	@Override
	public DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage) {
		String queryTotalRowsSql = sql;
		int orderIndex = sql.toLowerCase().indexOf(" order ");
		if (orderIndex != -1) {
			queryTotalRowsSql = sql.substring(0, orderIndex);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT COUNT(1) FROM ( ");
		sb.append(queryTotalRowsSql);
		sb.append(") TOTALROWS");

		int totalRows = queryInt(sb.toString(), args);
		DBPage page = new DBPage(curPage, numPerPage);
		page.setTotalRows(totalRows);

		int startIndex = page.getStartIndex();
		int endIndex = page.getLastIndex();
		int rows = endIndex - startIndex;
		rows = rows < 0 ? 0 : rows;

		List<DataRow> list = query(sql, args, startIndex, rows);
		page.setData(list);

		return page;
	}

	@Override
	public void beginTrans() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			logger.info("数据事务开启异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
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
			logger.info("数据事务提交异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
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
			logger.info("数据事务回滚异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
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
			logger.info("数据关闭异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		}
	}

	public String getGeneratedKeys() {
		return generatedKeys;
	}

	private void setGeneratedKeys(String generatedKeys) {
		this.generatedKeys = generatedKeys;
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
			logger.info("数据结果集关闭异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
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
			logger.info("数据库预编译对象关闭异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
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
			logger.info("数据库结果集转化为DataRow格式异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * 设置数据库连接类型
	 * @author zhangdq
	 * @date 2017-6-13 下午4:44:59
	 * @param Connection 数据库连接
	 * @return
	 */
	public void setDababaseType(Connection conn) {
		try {
			String databaseTypeStr = conn.getMetaData().getDatabaseProductName();
			if (databaseTypeStr.equalsIgnoreCase("oracle")) {
				databaseType = DatabaseType.ORACLE;
			} else if (databaseTypeStr.equalsIgnoreCase("MySQL")) {
				databaseType = DatabaseType.MYSQL;
			} else {
				logger.error("查询数据产商失败~默认返回Oracle数据库类型");
				throw new Exception("暂不支持该数据库！<<<<<<<<<>>>>>>>>>" + databaseTypeStr);
			}
		} catch (Exception e) {
			logger.info("设置数据库连接类型异常-<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>异常信息：" + e);
			e.printStackTrace();
		}
	}
}
