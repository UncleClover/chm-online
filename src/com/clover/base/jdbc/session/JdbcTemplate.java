package com.clover.base.jdbc.session;

import java.util.ArrayList;
import java.util.List;

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
	private String id;// 数据库配置ID

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
}
