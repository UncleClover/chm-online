package com.clover.base.jdbc.session;

import java.sql.Connection;
import java.util.List;

import com.clover.base.jdbc.DataRow;

/**
 * @desc 数据库连接会话操作接口
 * @author zhangdq
 */
public abstract interface Session {
	/**
	 * @desc 获取数据库连接
	 * @author zhangdq
	 * @return Connection
	 */
	public abstract Connection connection();

	/**
	 * @desc 插入表
	 * @param tbName
	 *            表名
	 * @param DataRow
	 *            插入的列
	 * @return int
	 */
	public abstract int insert(String tbName, DataRow data);

	/**
	 * @desc 根据条件删除表数据
	 * @author zhangdq
	 * @param tbName
	 *            表名
	 * @param identify
	 *            删除条件的列名
	 * @param identifyValue
	 *            删除条件的值
	 * @return int
	 */
	public abstract int delete(String tbName, String identify, Object identifyValue);

	/**
	 * @desc 更新表数据
	 * @author zhangdq
	 * @param sql
	 *            更新SQL语句
	 * @param objs
	 *            参数
	 * @return int
	 */
	public abstract int update(String sql, Object[] objs);

	/**
	 * @desc 查询数据
	 * @author zhangdq
	 * @param sql
	 *            查询SQL
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql);

	/**
	 * @desc 查询数据、
	 * @author zhangdq
	 * @param sql
	 *            查询SQL
	 * @param objs
	 *            查询参数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] objs);

	/**
	 * @desc 开启数据库事务
	 * @author zhangdq
	 * @time 2017-05-25 22:57
	 */
	public abstract void beginTrans();

	/**
	 * @desc 提交数据库事务
	 * @author zhangdq
	 */
	public abstract void commitTrans();

	/**
	 * @desc 回滚数据库事务操作
	 * @author zhangdq
	 */
	public abstract void rollbackTrans();

	/**
	 * @desc 关闭数据库连接
	 * @author zhangdq
	 */
	public abstract void close();
}
