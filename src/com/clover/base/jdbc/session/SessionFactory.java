package com.clover.base.jdbc.session;

import java.sql.Connection;

import com.clover.base.jdbc.ConnTools;
import com.clover.base.jdbc.session.impl.SessionImpl;

/**
 * @desc ��ȡ���ݿ�����ʵ��
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-2 ����5:05:05
 */
public final class SessionFactory {
	
	/**
	 * @desc ��ȡָ��ID�����ݿ�ʵ��
	 * @author zhangdq
	 * @time 2017-6-2 ����5:05:29
	 * @param id ���ݿ�����ID
	 * @return Session
	 */
	public static Session getSession(String id) {
		Connection conn = ConnTools.getConnect(id);
		return new SessionImpl(conn);
	}

	/**
	 * @desc ��ȡĬ�����ݿ�ʵ��
	 * @author zhangdq
	 * @time 2017-6-2 ����5:06:02
	 * @param 
	 * @return Session
	 */
	public static Session getSession() {
		Connection conn = ConnTools.getConnect();
		return new SessionImpl(conn);
	}
}
