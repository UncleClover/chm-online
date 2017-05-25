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
	 * @param tbName
	 *            ����
	 * @param DataRow
	 *            �������
	 * @return int
	 */
	public abstract int insert(String tbName, DataRow data);

	/**
	 * @desc ��������ɾ��������
	 * @author zhangdq
	 * @param tbName
	 *            ����
	 * @param identify
	 *            ɾ������������
	 * @param identifyValue
	 *            ɾ��������ֵ
	 * @return int
	 */
	public abstract int delete(String tbName, String identify, Object identifyValue);

	/**
	 * @desc ���±�����
	 * @author zhangdq
	 * @param sql
	 *            ����SQL���
	 * @param objs
	 *            ����
	 * @return int
	 */
	public abstract int update(String sql, Object[] objs);

	/**
	 * @desc ��ѯ����
	 * @author zhangdq
	 * @param sql
	 *            ��ѯSQL
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql);

	/**
	 * @desc ��ѯ���ݡ�
	 * @author zhangdq
	 * @param sql
	 *            ��ѯSQL
	 * @param objs
	 *            ��ѯ����
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] objs);

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
	 * @desc �ر����ݿ�����
	 * @author zhangdq
	 */
	public abstract void close();
}
