package com.clover.base.jdbc.session;

import java.sql.Connection;
import java.util.List;

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
	 * @param objs ɾ������
	 * @return int
	 */
	public abstract int delete(String sql, Object[] objs);

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
	 * @param objs ����
	 * @return int
	 */
	public abstract int update(String sql, Object[] objs);
	
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
	 * @param objs ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] objs);
	
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
	public abstract List<DataRow> query(String sql, Object[] objs, int rows);
	
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
	 * @param objs ��ѯ��������
	 * @param startRows ��ʼ��ѯλ��
	 * @param rows ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] objs, int startRows, int rows);

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
