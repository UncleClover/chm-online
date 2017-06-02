package com.clover.base.jdbc.session;

import java.sql.Connection;

import com.clover.base.jdbc.ConnTools;
import com.clover.base.jdbc.session.impl.SessionImpl;

/**
 * @desc 获取数据库连接实例
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-2 下午5:05:05
 */
public final class SessionFactory {
	
	/**
	 * @desc 获取指定ID的数据库实例
	 * @author zhangdq
	 * @time 2017-6-2 下午5:05:29
	 * @param id 数据库配置ID
	 * @return Session
	 */
	public static Session getSession(String id) {
		Connection conn = ConnTools.getConnect(id);
		return new SessionImpl(conn);
	}

	/**
	 * @desc 获取默认数据库实例
	 * @author zhangdq
	 * @time 2017-6-2 下午5:06:02
	 * @param 
	 * @return Session
	 */
	public static Session getSession() {
		Connection conn = ConnTools.getConnect();
		return new SessionImpl(conn);
	}
}
