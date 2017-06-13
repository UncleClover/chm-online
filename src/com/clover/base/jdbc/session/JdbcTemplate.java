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

	/**
	 * 插入表
	 * @param tbName 表名
	 * @param DataRow 插入的列
	 * @return int
	 */
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

	/**
	 * 根据条件删除表数据
	 * @author zhangdq
	 * @param tbName 表名
	 * @param identify 删除条件的列名
	 * @param identifyValue 删除条件的值
	 * @return int
	 */
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

	/**
	 * 根据SQL和条件删除表数据(可直接调用update方法)
	 * @author zhangdq
	 * @date 2017-6-2 下午3:12:25
	 * @param SQL 删除SQL语句
	 * @param args 删除参数
	 * @return int
	 */
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

	/**
	 * 更新表数据
	 * @author zhangdq
	 * @date 2017-6-2 下午6:09:43
	 * @param
	 * @return
	 */
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

	/**
	 * 根据条件更新表数据
	 * @author zhangdq
	 * @param sql 更新SQL语句
	 * @param args 参数
	 * @return int
	 */
	public int update(String sql, Object[] args) {
		Session session = null;
		int result = 0;
		try {
			session = getSession();
			result = session.update(sql, args);
			generatedKeys = session.getGeneratedKeys();
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return result;
	}

	/**
	 * 根据表名和一定的条件更新某些列数据
	 * @author zhangdq
	 * @date 2017-6-2 下午6:15:54
	 * @param tbName 表名
	 * @param data 列数据
	 * @param identify 更新条件列名
	 * @param identifyValue 更新条件值
	 * @return int
	 */
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

	/**
	 * 根据表名和一定的条件更新某些列数据
	 * @author zhangdq
	 * @date 2017-6-2 下午6:15:54
	 * @param tbName 表名
	 * @param data 列数据
	 * @param identifys 更新条件列名
	 * @param identifyValues 更新条件值
	 * @return int
	 */
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

	/**
	 * 批更新数据
	 * @author zhangdq
	 * @date 2017-6-6 下午4:44:44
	 * @param sql 更新SQL的数组
	 * @return int[]
	 */
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

	/**
	 * 根据条件批更新数据
	 * @author zhangdq
	 * @date 2017-6-6 下午4:45:14
	 * @param sql 更新SQL
	 * @param args 查询条件的二维数组
	 * @return int[]
	 */
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

	/**
	 * 查询数据
	 * @author zhangdq
	 * @param sql 查询SQL
	 * @return List<DataRow>
	 */
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

	/**
	 * 查询数据
	 * @author zhangdq
	 * @param sql 查询SQL
	 * @param args 查询参数
	 * @return List<DataRow>
	 */
	public List<DataRow> query(String sql, Object[] args) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, args);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	/**
	 * 查询数据前几行
	 * @author zhangdq
	 * @date 2017-6-2 下午4:10:08
	 * @param SQL 查询SQL
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
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

	/**
	 * 根据条件查询数据前几行
	 * @author zhangdq
	 * @date 2017-6-2 下午4:17:45
	 * @param
	 * @return
	 */
	public List<DataRow> query(String sql, Object[] args, int rows) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, args, rows);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	/**
	 * 从某一行开始查询一定行数的数据
	 * @author zhangdq
	 * @date 2017-6-2 下午4:12:42
	 * @param SQL 查询SQL
	 * @param startRows 开始查询位置
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
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

	/**
	 * 根据条件从某一行开始查询一定行数的数据
	 * @author zhangdq
	 * @date 2017-6-2 下午4:13:49
	 * @param SQL 查询SQL
	 * @param args 查询条件参数
	 * @param startRows 开始查询位置
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public List<DataRow> query(String sql, Object[] args, int startRows, int rows) {
		Session session = null;
		List<DataRow> list = new ArrayList<DataRow>();
		try {
			session = getSession();
			list = session.query(sql, args, startRows, rows);
		} finally {
			if (session != null) {
				session.close();
				session = null;
			}
		}
		return list;
	}

	/**
	 * 查询int类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:52:37
	 * @param sql 查询SQL
	 * @return int
	 */
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

	/**
	 * 根据条件查询int类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:53:13
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return int
	 */
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

	/**
	 * 查询int数组
	 * @author zhangdq
	 * @date 2017-6-5 下午5:53:33
	 * @param sql 查询SQL
	 * @return int[]
	 */
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

	/**
	 * 根据条件查询int数组
	 * @author zhangdq
	 * @date 2017-6-5 下午5:53:56
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return int[]
	 */
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

	/**
	 * 查询long类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:57:47
	 * @param sql 查询SQL
	 * @return long
	 */
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

	/**
	 * 根据条件查询查询long类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:58:17
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return long
	 */
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

	/**
	 * 查询long数组数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:58:59
	 * @param sql 查询SQL
	 * @return long[]
	 */
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

	/**
	 * 根据条件查询long数组数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:59:29
	 * @param sql
	 * @param args 查询条件参数
	 * @return
	 */
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

	/**
	 * 查询String类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午6:02:39
	 * @param sql 查询SQL
	 * @return String
	 */
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

	/**
	 * 根据条件查询String类型
	 * @author zhangdq
	 * @date 2017-6-5 下午6:03:09
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return String
	 */
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

	/**
	 * 查询String数组
	 * @author zhangdq
	 * @date 2017-6-5 下午6:03:58
	 * @param sql 查询SQL
	 * @return String[]
	 */
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

	/**
	 * 根据条件查询String数组
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:03
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return String[]
	 */
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

	/**
	 * 查询一条数据(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:12
	 * @param sql 查询SQL
	 * @return DataRow
	 */
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

	/**
	 * 根据条件查询一条数据(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:16
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return DataRow
	 */
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

	/**
	 * 数据分页查询
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:24
	 * @param sql 查询SQL
	 * @param curPage 当前页数
	 * @param numPerPage 每页显示数据条数
	 * @return DBPage
	 */
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

	/**
	 * 根据条件分页查询
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:27
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @param curPage 当前页数
	 * @param numPerPage 每页显示数据条数
	 * @return DBPage
	 */
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
	
	/**
	 * 获取插入时自动生成的主键
	 * @author zhangdq
	 * @date 2017-6-13 下午4:30:44
	 * @param 
	 * @return
	 */
	public String getgeneratedKeys(){
		return generatedKeys;
	}
}
