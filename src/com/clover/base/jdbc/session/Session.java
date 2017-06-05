package com.clover.base.jdbc.session;

import java.sql.Connection;
import java.util.List;

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;

/**
 * @desc ���ݿ����ӻỰ�����ӿ�
 * @author zhangdq
 */
public abstract interface Session {
	/**
	 * @desc ��ȡ���ݿ�����
	 * @author zhangdq
	 * @return Connection
	 */
	public abstract Connection connection();

	/**
	 * @desc �����
	 * @param tbName ����
	 * @param DataRow �������
	 * @return int
	 */
	public abstract int insert(String tbName, DataRow data);

	/**
	 * @desc ��������ɾ��������
	 * @author zhangdq
	 * @param tbName ����
	 * @param identify ɾ������������
	 * @param identifyValue ɾ��������ֵ
	 * @return int
	 */
	public abstract int delete(String tbName, String identify, Object identifyValue);

	/**
	 * @desc ����SQL������ɾ��������(��ֱ�ӵ���update����)
	 * @author zhangdq
	 * @time 2017-6-2 ����3:12:25
	 * @param SQL ɾ��SQL���
	 * @param args ɾ������
	 * @return int
	 */
	public abstract int delete(String sql, Object[] args);

	/**
	 * @desc ���±�����
	 * @author zhangdq
	 * @time 2017-6-2 ����6:09:43
	 * @param
	 * @return
	 */
	public abstract int update(String sql);

	/**
	 * @desc �����������±�����
	 * @author zhangdq
	 * @param sql ����SQL���
	 * @param args ����
	 * @return int
	 */
	public abstract int update(String sql, Object[] args);

	/**
	 * @desc ���ݱ�����һ������������ĳЩ������
	 * @author zhangdq
	 * @time 2017-6-2 ����6:15:54
	 * @param tbName ����
	 * @param data ������
	 * @param identify ������������
	 * @param identifyValue ��������ֵ
	 * @return int
	 */
	public abstract int update(String tbName, DataRow data, String identify, Object identifyValue);

	/**
	 * @desc ���ݱ�����һ������������ĳЩ������
	 * @author zhangdq
	 * @time 2017-6-2 ����6:15:54
	 * @param tbName ����
	 * @param data ������
	 * @param identifys ������������
	 * @param identifyValues ��������ֵ
	 * @return int
	 */
	public abstract int update(String tbName, DataRow data, String[] identifys, Object[] identifyValues);

	/**
	 * @desc ��ѯ����
	 * @author zhangdq
	 * @param sql ��ѯSQL
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql);

	/**
	 * @desc ��ѯ���ݡ�
	 * @author zhangdq
	 * @param sql ��ѯSQL
	 * @param args ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] args);

	/**
	 * @desc ��ѯ����ǰ����
	 * @author zhangdq
	 * @time 2017-6-2 ����4:10:08
	 * @param SQL ��ѯSQL
	 * @param rows ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int rows);

	/**
	 * @desc ����������ѯ����ǰ����
	 * @author zhangdq
	 * @time 2017-6-2 ����4:17:45
	 * @param
	 * @return
	 */
	public abstract List<DataRow> query(String sql, Object[] args, int rows);

	/**
	 * @desc ��ĳһ�п�ʼ��ѯһ������������
	 * @author zhangdq
	 * @time 2017-6-2 ����4:12:42
	 * @param SQL ��ѯSQL
	 * @param startRows ��ʼ��ѯλ��
	 * @param rows ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int startRows, int rows);

	/**
	 * @desc ����������ĳһ�п�ʼ��ѯһ������������
	 * @author zhangdq
	 * @time 2017-6-2 ����4:13:49
	 * @param SQL ��ѯSQL
	 * @param args ��ѯ��������
	 * @param startRows ��ʼ��ѯλ��
	 * @param rows ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] args, int startRows, int rows);

	/**
	 * @desc ��ѯint��������
	 * @author zhangdq
	 * @time 2017-6-5 ����5:52:37
	 * @param sql ��ѯSQL
	 * @return int
	 */
	public abstract int queryInt(String sql);

	/**
	 * @desc ����������ѯint��������
	 * @author zhangdq
	 * @time 2017-6-5 ����5:53:13
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return int
	 */
	public abstract int queryInt(String sql, Object[] args);

	/**
	 * @desc ��ѯint����
	 * @author zhangdq
	 * @time 2017-6-5 ����5:53:33
	 * @param sql ��ѯSQL
	 * @return int[]
	 */
	public abstract int[] queryIntArray(String sql);

	/**
	 * @desc ����������ѯint����
	 * @author zhangdq
	 * @time 2017-6-5 ����5:53:56
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return int[]
	 */
	public abstract int[] queryIntArray(String sql, Object[] args);

	/**
	 * @desc ��ѯlong��������
	 * @author zhangdq
	 * @time 2017-6-5 ����5:57:47
	 * @param sql ��ѯSQL
	 * @return long
	 */
	public abstract long queryLong(String sql);

	/**
	 * @desc ����������ѯ��ѯlong��������
	 * @author zhangdq
	 * @time 2017-6-5 ����5:58:17
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return long
	 */
	public abstract long queryLong(String sql, Object[] args);

	/**
	 * @desc ��ѯlong��������
	 * @author zhangdq
	 * @time 2017-6-5 ����5:58:59
	 * @param sql ��ѯSQL
	 * @return long[]
	 */
	public abstract long[] queryLongArray(String sql);

	/**
	 * @desc ����������ѯlong��������
	 * @author zhangdq
	 * @time 2017-6-5 ����5:59:29
	 * @param sql
	 * @param args ��ѯ��������
	 * @return
	 */
	public abstract long[] queryLongArray(String sql, Object[] args);

	/**
	 * @desc ��ѯString��������
	 * @author zhangdq
	 * @time 2017-6-5 ����6:02:39
	 * @param sql ��ѯSQL
	 * @return String
	 */
	public abstract String queryString(String sql);

	/**
	 * @desc ����������ѯString����
	 * @author zhangdq
	 * @time 2017-6-5 ����6:03:09
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return String
	 */
	public abstract String queryString(String sql, Object[] args);

	/**
	 * @desc ��ѯString����
	 * @author zhangdq
	 * @time 2017-6-5 ����6:03:58
	 * @param sql ��ѯSQL
	 * @return String[]
	 */
	public abstract String[] queryStringArray(String sql);

	/**
	 * @desc ����������ѯString����
	 * @author zhangdq
	 * @time 2017-6-5 ����6:04:03
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return String[]
	 */
	public abstract String[] queryStringArray(String sql, Object[] args);

	/**
	 * @desc ��ѯһ������(DataRow)
	 * @author zhangdq
	 * @time 2017-6-5 ����6:04:12
	 * @param sql ��ѯSQL
	 * @return DataRow
	 */
	public abstract DataRow queryMap(String sql);

	/**
	 * @desc ����������ѯһ������(DataRow)
	 * @author zhangdq
	 * @time 2017-6-5 ����6:04:16
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @return DataRow
	 */
	public abstract DataRow queryMap(String sql, Object[] args);

	/**
	 * @desc ���ݷ�ҳ��ѯ
	 * @author zhangdq
	 * @time 2017-6-5 ����6:04:24
	 * @param sql ��ѯSQL
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ��������
	 * @return DBPage
	 */
	public abstract DBPage queryPage(String sql, int curPage, int numPerPage);

	/**
	 * 
	 * @author zhangdq
	 * @time 2017-6-5 ����6:04:27
	 * @param sql ��ѯSQL
	 * @param args ��ѯ��������
	 * @param curPage ��ǰҳ��
	 * @param numPerPage ÿҳ��ʾ��������
	 * @return DBPage
	 */
	public abstract DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage);

	/**
	 * @desc �������ݿ�����
	 * @author zhangdq
	 * @time 2017-05-25 22:57
	 */
	public abstract void beginTrans();

	/**
	 * @desc �ύ���ݿ�����
	 * @author zhangdq
	 */
	public abstract void commitTrans();

	/**
	 * @desc �ع����ݿ��������
	 * @author zhangdq
	 */
	public abstract void rollbackTrans();

	/**
	 * @desc �ر����ݿ����ӣ����ݿ�ر��쳣��������
	 * @author zhangdq
	 */
	public abstract void close();
}
