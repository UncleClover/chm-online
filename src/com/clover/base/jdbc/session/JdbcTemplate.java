package com.clover.base.jdbc.session;

import java.util.ArrayList;
import java.util.List;

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;
import com.clover.base.utils.StringUtils;

/**
 * @desc ���ݿ������
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-2 ����5:34:58
 */
public class JdbcTemplate {
	private String id;				// ���ݿ�����ID
	private String generatedKeys;	// ���ݿ�����������ID

	public JdbcTemplate() {
	}

	public JdbcTemplate(String id) {
		this.id = id;
	}

	/**
	 * @desc ��ȡ���ݿ�����
	 * @author zhangdq
	 * @time 2017-6-2 ����5:30:30
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
	 * �����
	 * @param tbName ����
	 * @param DataRow �������
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
	 * ��������ɾ��������
	 * @author zhangdq
	 * @param tbName ����
	 * @param identify ɾ������������
	 * @param identifyValue ɾ��������ֵ
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
	 * ����SQL������ɾ��������(��ֱ�ӵ���update����)
	 * @author zhangdq
	 * @date 2017-6-2 ����3:12:25
	 * @param SQL ɾ��SQL���
	 * @param args ɾ������
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
	 * ���±�����
	 * @author zhangdq
	 * @date 2017-6-2 ����6:09:43
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
	 * �����������±�����
	 * @author zhangdq
	 * @param sql ����SQL���
	 * @param args ����
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
	 * ���ݱ�����һ������������ĳЩ������
	 * @author zhangdq
	 * @date 2017-6-2 ����6:15:54
	 * @param tbName ����
	 * @param data ������
	 * @param identify ������������
	 * @param identifyValue ��������ֵ
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
	 * ���ݱ�����һ������������ĳЩ������
	 * @author zhangdq
	 * @date 2017-6-2 ����6:15:54
	 * @param tbName ����
	 * @param data ������
	 * @param identifys ������������
	 * @param identifyValues ��������ֵ
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
	 * ����������
	 * @author zhangdq
	 * @date 2017-6-6 ����4:44:44
	 * @param sql ����SQL������
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
	 * ������������������
	 * @author zhangdq
	 * @date 2017-6-6 ����4:45:14
	 * @param sql ����SQL
	 * @param args ��ѯ�����Ķ�ά����
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
	 * ��ѯ����
	 * @author zhangdq
	 * @param sql ��ѯSQL
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
	 * ��ѯ����
	 * @author zhangdq
	 * @param sql ��ѯSQL
	 * @param args ��ѯ����
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
	 * ��ѯ����ǰ����
	 * @author zhangdq
	 * @date 2017-6-2 ����4:10:08
	 * @param SQL ��ѯSQL
	 * @param rows ��ѯ����
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
	 * ����������ѯ����ǰ����
	 * @author zhangdq
	 * @date 2017-6-2 ����4:17:45
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
	 * ��ĳһ�п�ʼ��ѯһ������������
	 * @author zhangdq
	 * @date 2017-6-2 ����4:12:42
	 * @param SQL ��ѯSQL
	 * @param startRows ��ʼ��ѯλ��
	 * @param rows ��ѯ����
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
	 * ����������ĳһ�п�ʼ��ѯһ������������
	 * @author zhangdq
	 * @date 2017-6-2 ����4:13:49
	 * @param SQL ��ѯSQL
	 * @param args ��ѯ��������
	 * @param startRows ��ʼ��ѯλ��
	 * @param rows ��ѯ����
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
	 * ��ѯint��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:52:37
	 * @param sql ��ѯSQL
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
	 * ����������ѯint��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:53:13
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
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
	 * ��ѯint����
	 * @author zhangdq
	 * @date 2017-6-5 ����5:53:33
	 * @param sql ��ѯSQL
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
	 * ����������ѯint����
	 * @author zhangdq
	 * @date 2017-6-5 ����5:53:56
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
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
	 * ��ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:57:47
	 * @param sql ��ѯSQL
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
	 * ����������ѯ��ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:58:17
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
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
	 * ��ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:58:59
	 * @param sql ��ѯSQL
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
	 * ����������ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:59:29
	 * @param sql
	 * @param args ��ѯ��������
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
	 * ��ѯString��������
	 * @author zhangdq
	 * @date 2017-6-5 ����6:02:39
	 * @param sql ��ѯSQL
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
	 * ����������ѯString����
	 * @author zhangdq
	 * @date 2017-6-5 ����6:03:09
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
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
	 * ��ѯString����
	 * @author zhangdq
	 * @date 2017-6-5 ����6:03:58
	 * @param sql ��ѯSQL
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
	 * ����������ѯString����
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:03
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
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
	 * ��ѯһ������(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:12
	 * @param sql ��ѯSQL
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
	 * ����������ѯһ������(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:16
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
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
	 * ���ݷ�ҳ��ѯ
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:24
	 * @param sql ��ѯSQL
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ��������
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
	 * ����������ҳ��ѯ
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:27
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ��������
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
	 * ��ȡ����ʱ�Զ����ɵ�����
	 * @author zhangdq
	 * @date 2017-6-13 ����4:30:44
	 * @param 
	 * @return
	 */
	public String getgeneratedKeys(){
		return generatedKeys;
	}
}
