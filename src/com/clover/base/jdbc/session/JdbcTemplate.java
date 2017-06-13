package com.clover.base.jdbc.session;

import java.util.ArrayList;
import java.util.List;

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;
import com.clover.base.utils.StringUtils;

/**
 * @desc 数据库操作类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-2 下午5:34:58
 */
public class JdbcTemplate {
	private String id;				// 数据库配置ID
	private String generatedKeys;	// 数据库主键自增长ID

	public JdbcTemplate() {
	}

	public JdbcTemplate(String id) {
		this.id = id;
	}

	/**
	 * @desc 获取数据库连接
	 * @author zhangdq
	 * @time 2017-6-2 下午5:30:30
	 * @param
	 * @return
	 */
	private Session getSession() {
		if (StringUtils.isNotEmpty(id)) {
			return SessionFactory.getSession(id);
		}
		return SessionFactory.getSession();
	}

	public int insert(String tbName, DataRow data) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.insert(tbName, data);
			generatedKeys = session.getGeneratedKeys();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int delete(String tbName, String identify, Object identifyValue) {
		Session session = null;
		int result = 0;

		try {
			session = getSession();
			result = session.delete(tbName, identify, identifyValue);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int delete(String sql, Object[] args) {
		Session session = null;
		int result = 0;

		try {
			session = getSession();
			result = session.delete(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int update(String sql) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.update(sql);
			generatedKeys = session.getGeneratedKeys();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int update(String sql, Object[] objs) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.update(sql, objs);
			generatedKeys = session.getGeneratedKeys();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int update(String tbName, DataRow data, String identify, Object identifyValue) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.update(tbName, data, identify, identifyValue);
			generatedKeys = session.getGeneratedKeys();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int update(String tbName, DataRow data, String[] identifys, Object[] identifyValues) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.update(tbName, data, identifys, identifyValues);
			generatedKeys = session.getGeneratedKeys();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int[] batchUpdate(String[] sql) {
		Session session = null;
		int[] result = null;
		try {
			session = getSession();
			result = session.batchUpdate(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int[] batchUpdate(String sql, Object[][] args) {
		Session session = null;
		int[] result = null;
		try {
			session = getSession();
			result = session.batchUpdate(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public List<DataRow> query(String sql) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	public List<DataRow> query(String sql, Object[] objs) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, objs);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	public List<DataRow> query(String sql, int rows) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, rows);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	public List<DataRow> query(String sql, Object[] objs, int rows) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, objs, rows);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	public List<DataRow> query(String sql, int startRows, int rows) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, startRows, rows);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	public List<DataRow> query(String sql, Object[] objs, int startRows, int rows) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, objs, startRows, rows);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	public int queryInt(String sql) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.queryInt(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int queryInt(String sql, Object[] args) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.queryInt(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int[] queryIntArray(String sql) {
		Session session = null;
		int[] result = null;
		try {
			session = getSession();
			result = session.queryIntArray(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public int[] queryIntArray(String sql, Object[] args) {
		Session session = null;
		int[] result = null;
		try {
			session = getSession();
			result = session.queryIntArray(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public long queryLong(String sql) {
		Session session = null;
		long result = 0L;
		try {
			session = getSession();
			result = session.queryLong(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public long queryLong(String sql, Object[] args) {
		Session session = null;
		long result = 0L;
		try {
			session = getSession();
			result = session.queryLong(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public long[] queryLongArray(String sql) {
		Session session = null;
		long[] result = null;
		try {
			session = getSession();
			result = session.queryLongArray(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public long[] queryLongArray(String sql, Object[] args) {
		Session session = null;
		long[] result = null;
		try {
			session = getSession();
			result = session.queryLongArray(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public String queryString(String sql) {
		Session session = null;
		String result = null;
		try {
			session = getSession();
			result = session.queryString(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public String queryString(String sql, Object[] args) {
		Session session = null;
		String result = null;
		try {
			session = getSession();
			result = session.queryString(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public String[] queryStringArray(String sql) {
		Session session = null;
		String[] result = null;
		try {
			session = getSession();
			result = session.queryStringArray(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public String[] queryStringArray(String sql, Object[] args) {
		Session session = null;
		String[] result = null;
		try {
			session = getSession();
			result = session.queryStringArray(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public DataRow queryMap(String sql) {
		Session session = null;
		DataRow result = new DataRow();
		try {
			session = getSession();
			result = session.queryMap(sql);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public DataRow queryMap(String sql, Object[] args) {
		Session session = null;
		DataRow result = new DataRow();
		try {
			session = getSession();
			result = session.queryMap(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public DBPage queryPage(String sql, int curPage, int numPerPage) {
		Session session = null;
		DBPage result = null;
		try {
			session = getSession();
			result = session.queryPage(sql, curPage, numPerPage);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	public DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage) {
		Session session = null;
		DBPage result = null;
		try {
			session = getSession();
			result = session.queryPage(sql, args, curPage, numPerPage);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}
	
	public String getgeneratedKeys(){
		return generatedKeys;
	}
}
