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
	 * @param tbName 表名
	 * @param DataRow 插入的列
	 * @return int
	 */
	public abstract int insert(String tbName, DataRow data);

	/**
	 * @desc 根据条件删除表数据
	 * @author zhangdq
	 * @param tbName 表名
	 * @param identify 删除条件的列名
	 * @param identifyValue 删除条件的值
	 * @return int
	 */
	public abstract int delete(String tbName, String identify, Object identifyValue);
	
	/**
	 * @desc 根据SQL和条件删除表数据(可直接调用update方法)
	 * @author zhangdq
	 * @time 2017-6-2 下午3:12:25
	 * @param SQL 删除SQL语句
	 * @param objs 删除参数
	 * @return int
	 */
	public abstract int delete(String sql, Object[] objs);

	/**
	 * @desc 更新表数据
	 * @author zhangdq
	 * @time 2017-6-2 下午6:09:43
	 * @param 
	 * @return
	 */
	public abstract int update(String sql);
	/**
	 * @desc 根据条件更新表数据
	 * @author zhangdq
	 * @param sql 更新SQL语句
	 * @param objs 参数
	 * @return int
	 */
	public abstract int update(String sql, Object[] objs);
	
	/**
	 * @desc 根据表名和一定的条件更新某些列数据
	 * @author zhangdq
	 * @time 2017-6-2 下午6:15:54
	 * @param tbName 表名
	 * @param data 列数据
	 * @param identify 更新条件列名
	 * @param identifyValue 更新条件值
	 * @return int
	 */
	public abstract int update(String tbName, DataRow data, String identify, Object identifyValue);
	
	/**
	 * @desc 根据表名和一定的条件更新某些列数据
	 * @author zhangdq
	 * @time 2017-6-2 下午6:15:54
	 * @param tbName 表名
	 * @param data 列数据
	 * @param identifys 更新条件列名
	 * @param identifyValues 更新条件值
	 * @return int
	 */
	public abstract int update(String tbName, DataRow data, String[] identifys, Object[] identifyValues);

	/**
	 * @desc 查询数据
	 * @author zhangdq
	 * @param sql 查询SQL
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql);

	/**
	 * @desc 查询数据、
	 * @author zhangdq
	 * @param sql 查询SQL
	 * @param objs 查询参数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] objs);
	
	/**
	 * @desc 查询数据前几行
	 * @author zhangdq
	 * @time 2017-6-2 下午4:10:08
	 * @param SQL 查询SQL
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int rows);
	
	
	/**
	 * @desc 根据条件查询数据前几行
	 * @author zhangdq
	 * @time 2017-6-2 下午4:17:45
	 * @param 
	 * @return
	 */
	public abstract List<DataRow> query(String sql, Object[] objs, int rows);
	
	/**
	 * @desc 从某一行开始查询一定行数的数据
	 * @author zhangdq
	 * @time 2017-6-2 下午4:12:42
	 * @param SQL 查询SQL
	 * @param startRows 开始查询位置
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int startRows, int rows);
	
	/**
	 * @desc 根据条件从某一行开始查询一定行数的数据
	 * @author zhangdq
	 * @time 2017-6-2 下午4:13:49
	 * @param SQL 查询SQL
	 * @param objs 查询条件参数
	 * @param startRows 开始查询位置
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] objs, int startRows, int rows);

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
	 * @desc 关闭数据库连接，数据库关闭异常不做处理
	 * @author zhangdq
	 */
	public abstract void close();
}
