package com.clover.base.jdbc.session;

import java.sql.Connection;
import java.util.List;

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;

/**
 * ���ݿ����ӻỰ�����ӿ�
 * @author zhangdq
 */
public abstract interface Session {
	/**
	 * ��ȡ���ݿ�����
	 * @author zhangdq
	 * @return Connection
	 */
	public abstract Connection connection();

	/**
	 * �����
	 * @param tbName ����
	 * @param DataRow �������
	 * @return int
	 */
	public abstract int insert(String tbName, DataRow data);

	/**
	 * ��������ɾ��������
	 * @author zhangdq
	 * @param tbName ����
	 * @param identify ɾ������������
	 * @param identifyValue ɾ��������ֵ
	 * @return int
	 */
	public abstract int delete(String tbName, String identify, Object identifyValue);

	/**
	 * ����SQL������ɾ��������(��ֱ�ӵ���update����)
	 * @author zhangdq
	 * @date 2017-6-2 ����3:12:25
	 * @param SQL ɾ��SQL���
	 * @param args ɾ������
	 * @return int
	 */
	public abstract int delete(String sql, Object[] args);

	/**
	 * ���±�����
	 * @author zhangdq
	 * @date 2017-6-2 ����6:09:43
	 * @param
	 * @return
	 */
	public abstract int update(String sql);

	/**
	 * �����������±�����
	 * @author zhangdq
	 * @param sql ����SQL���
	 * @param args ����
	 * @return int
	 */
	public abstract int update(String sql, Object[] args);

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
	public abstract int update(String tbName, DataRow data, String identify, Object identifyValue);

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
	public abstract int update(String tbName, DataRow data, String[] identifys, Object[] identifyValues);

	/**
	 * ����������
	 * @author zhangdq
	 * @date 2017-6-6 ����4:44:44
	 * @param sql ����SQL������
	 * @return int[]
	 */
	public abstract int[] batchUpdate(String[] sql);
	
	/**
	 * ������������������
	 * @author zhangdq
	 * @date 2017-6-6 ����4:45:14
	 * @param sql ����SQL
	 * @param args ��ѯ�����Ķ�ά����
	 * @return int[]
	 */
	public abstract int[] batchUpdate(String sql, Object[][] args);
	
	/**
	 * ��ѯ����
	 * @author zhangdq
	 * @param sql ��ѯSQL
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql);

	/**
	 * ��ѯ���ݡ�
	 * @author zhangdq
	 * @param sql ��ѯSQL
	 * @param args ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] args);

	/**
	 * ��ѯ����ǰ����
	 * @author zhangdq
	 * @date 2017-6-2 ����4:10:08
	 * @param SQL ��ѯSQL
	 * @param rows ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int rows);

	/**
	 * ����������ѯ����ǰ����
	 * @author zhangdq
	 * @date 2017-6-2 ����4:17:45
	 * @param
	 * @return
	 */
	public abstract List<DataRow> query(String sql, Object[] args, int rows);

	/**
	 * ��ĳһ�п�ʼ��ѯһ������������
	 * @author zhangdq
	 * @date 2017-6-2 ����4:12:42
	 * @param SQL ��ѯSQL
	 * @param startRows ��ʼ��ѯλ��
	 * @param rows ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int startRows, int rows);

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
	public abstract List<DataRow> query(String sql, Object[] args, int startRows, int rows);

	/**
	 * ��ѯint��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:52:37
	 * @param sql ��ѯSQL
	 * @return int
	 */
	public abstract int queryInt(String sql);

	/**
	 * ����������ѯint��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:53:13
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return int
	 */
	public abstract int queryInt(String sql, Object[] args);

	/**
	 * ��ѯint����
	 * @author zhangdq
	 * @date 2017-6-5 ����5:53:33
	 * @param sql ��ѯSQL
	 * @return int[]
	 */
	public abstract int[] queryIntArray(String sql);

	/**
	 * ����������ѯint����
	 * @author zhangdq
	 * @date 2017-6-5 ����5:53:56
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return int[]
	 */
	public abstract int[] queryIntArray(String sql, Object[] args);

	/**
	 * ��ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:57:47
	 * @param sql ��ѯSQL
	 * @return long
	 */
	public abstract long queryLong(String sql);

	/**
	 * ����������ѯ��ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:58:17
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return long
	 */
	public abstract long queryLong(String sql, Object[] args);

	/**
	 * ��ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:58:59
	 * @param sql ��ѯSQL
	 * @return long[]
	 */
	public abstract long[] queryLongArray(String sql);

	/**
	 * ����������ѯlong��������
	 * @author zhangdq
	 * @date 2017-6-5 ����5:59:29
	 * @param sql
	 * @param args ��ѯ��������
	 * @return
	 */
	public abstract long[] queryLongArray(String sql, Object[] args);

	/**
	 * ��ѯString��������
	 * @author zhangdq
	 * @date 2017-6-5 ����6:02:39
	 * @param sql ��ѯSQL
	 * @return String
	 */
	public abstract String queryString(String sql);

	/**
	 * ����������ѯString����
	 * @author zhangdq
	 * @date 2017-6-5 ����6:03:09
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return String
	 */
	public abstract String queryString(String sql, Object[] args);

	/**
	 * ��ѯString����
	 * @author zhangdq
	 * @date 2017-6-5 ����6:03:58
	 * @param sql ��ѯSQL
	 * @return String[]
	 */
	public abstract String[] queryStringArray(String sql);

	/**
	 * ����������ѯString����
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:03
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return String[]
	 */
	public abstract String[] queryStringArray(String sql, Object[] args);

	/**
	 * ��ѯһ������(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:12
	 * @param sql ��ѯSQL
	 * @return DataRow
	 */
	public abstract DataRow queryMap(String sql);

	/**
	 * ����������ѯһ������(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:16
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return DataRow
	 */
	public abstract DataRow queryMap(String sql, Object[] args);

	/**
	 * ���ݷ�ҳ��ѯ
	 * @author zhangdq
	 * @date 2017-6-5 ����6:04:24
	 * @param sql ��ѯSQL
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ��������
	 * @return DBPage
	 */
	public abstract DBPage queryPage(String sql, int curPage, int numPerPage);

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
	public abstract DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage);

	/**
	 * �������ݿ�����
	 * @author zhangdq
	 * @date 2017-05-25 22:57
	 */
	public abstract void beginTrans();

	/**
	 * �ύ���ݿ�����
	 * @author zhangdq
	 */
	public abstract void commitTrans();

	/**
	 * �ع����ݿ��������
	 * @author zhangdq
	 */
	public abstract void rollbackTrans();

	/**
	 * �ر����ݿ����ӣ����ݿ�ر��쳣��������
	 * @author zhangdq
	 */
	public abstract void close();
	
	/**
	 * ��ȡ����ʽ�Զ����ɵ�����
	 * @author zhangdq
	 * @date 2017-6-13 ����4:30:44
	 * @param 
	 * @return
	 */
	public abstract String getGeneratedKeys();
}
