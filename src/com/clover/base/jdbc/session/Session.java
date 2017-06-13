package com.clover.base.jdbc.session;

import java.sql.Connection;
import java.util.List;

import com.clover.base.jdbc.DBPage;
import com.clover.base.jdbc.DataRow;

/**
 * 数据库连接会话操作接口
 * @author zhangdq
 */
public abstract interface Session {
	/**
	 * 获取数据库连接
	 * @author zhangdq
	 * @return Connection
	 */
	public abstract Connection connection();

	/**
	 * 插入表
	 * @param tbName 表名
	 * @param DataRow 插入的列
	 * @return int
	 */
	public abstract int insert(String tbName, DataRow data);

	/**
	 * 根据条件删除表数据
	 * @author zhangdq
	 * @param tbName 表名
	 * @param identify 删除条件的列名
	 * @param identifyValue 删除条件的值
	 * @return int
	 */
	public abstract int delete(String tbName, String identify, Object identifyValue);

	/**
	 * 根据SQL和条件删除表数据(可直接调用update方法)
	 * @author zhangdq
	 * @date 2017-6-2 下午3:12:25
	 * @param SQL 删除SQL语句
	 * @param args 删除参数
	 * @return int
	 */
	public abstract int delete(String sql, Object[] args);

	/**
	 * 更新表数据
	 * @author zhangdq
	 * @date 2017-6-2 下午6:09:43
	 * @param
	 * @return
	 */
	public abstract int update(String sql);

	/**
	 * 根据条件更新表数据
	 * @author zhangdq
	 * @param sql 更新SQL语句
	 * @param args 参数
	 * @return int
	 */
	public abstract int update(String sql, Object[] args);

	/**
	 * 根据表名和一定的条件更新某些列数据
	 * @author zhangdq
	 * @date 2017-6-2 下午6:15:54
	 * @param tbName 表名
	 * @param data 列数据
	 * @param identify 更新条件列名
	 * @param identifyValue 更新条件值
	 * @return int
	 */
	public abstract int update(String tbName, DataRow data, String identify, Object identifyValue);

	/**
	 * 根据表名和一定的条件更新某些列数据
	 * @author zhangdq
	 * @date 2017-6-2 下午6:15:54
	 * @param tbName 表名
	 * @param data 列数据
	 * @param identifys 更新条件列名
	 * @param identifyValues 更新条件值
	 * @return int
	 */
	public abstract int update(String tbName, DataRow data, String[] identifys, Object[] identifyValues);

	/**
	 * 批更新数据
	 * @author zhangdq
	 * @date 2017-6-6 下午4:44:44
	 * @param sql 更新SQL的数组
	 * @return int[]
	 */
	public abstract int[] batchUpdate(String[] sql);
	
	/**
	 * 根据条件批更新数据
	 * @author zhangdq
	 * @date 2017-6-6 下午4:45:14
	 * @param sql 更新SQL
	 * @param args 查询条件的二维数组
	 * @return int[]
	 */
	public abstract int[] batchUpdate(String sql, Object[][] args);
	
	/**
	 * 查询数据
	 * @author zhangdq
	 * @param sql 查询SQL
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql);

	/**
	 * 查询数据、
	 * @author zhangdq
	 * @param sql 查询SQL
	 * @param args 查询参数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] args);

	/**
	 * 查询数据前几行
	 * @author zhangdq
	 * @date 2017-6-2 下午4:10:08
	 * @param SQL 查询SQL
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int rows);

	/**
	 * 根据条件查询数据前几行
	 * @author zhangdq
	 * @date 2017-6-2 下午4:17:45
	 * @param
	 * @return
	 */
	public abstract List<DataRow> query(String sql, Object[] args, int rows);

	/**
	 * 从某一行开始查询一定行数的数据
	 * @author zhangdq
	 * @date 2017-6-2 下午4:12:42
	 * @param SQL 查询SQL
	 * @param startRows 开始查询位置
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, int startRows, int rows);

	/**
	 * 根据条件从某一行开始查询一定行数的数据
	 * @author zhangdq
	 * @date 2017-6-2 下午4:13:49
	 * @param SQL 查询SQL
	 * @param args 查询条件参数
	 * @param startRows 开始查询位置
	 * @param rows 查询行数
	 * @return List<DataRow>
	 */
	public abstract List<DataRow> query(String sql, Object[] args, int startRows, int rows);

	/**
	 * 查询int类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:52:37
	 * @param sql 查询SQL
	 * @return int
	 */
	public abstract int queryInt(String sql);

	/**
	 * 根据条件查询int类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:53:13
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return int
	 */
	public abstract int queryInt(String sql, Object[] args);

	/**
	 * 查询int数组
	 * @author zhangdq
	 * @date 2017-6-5 下午5:53:33
	 * @param sql 查询SQL
	 * @return int[]
	 */
	public abstract int[] queryIntArray(String sql);

	/**
	 * 根据条件查询int数组
	 * @author zhangdq
	 * @date 2017-6-5 下午5:53:56
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return int[]
	 */
	public abstract int[] queryIntArray(String sql, Object[] args);

	/**
	 * 查询long类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:57:47
	 * @param sql 查询SQL
	 * @return long
	 */
	public abstract long queryLong(String sql);

	/**
	 * 根据条件查询查询long类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:58:17
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return long
	 */
	public abstract long queryLong(String sql, Object[] args);

	/**
	 * 查询long数组数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:58:59
	 * @param sql 查询SQL
	 * @return long[]
	 */
	public abstract long[] queryLongArray(String sql);

	/**
	 * 根据条件查询long数组数据
	 * @author zhangdq
	 * @date 2017-6-5 下午5:59:29
	 * @param sql
	 * @param args 查询条件参数
	 * @return
	 */
	public abstract long[] queryLongArray(String sql, Object[] args);

	/**
	 * 查询String类型数据
	 * @author zhangdq
	 * @date 2017-6-5 下午6:02:39
	 * @param sql 查询SQL
	 * @return String
	 */
	public abstract String queryString(String sql);

	/**
	 * 根据条件查询String类型
	 * @author zhangdq
	 * @date 2017-6-5 下午6:03:09
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return String
	 */
	public abstract String queryString(String sql, Object[] args);

	/**
	 * 查询String数组
	 * @author zhangdq
	 * @date 2017-6-5 下午6:03:58
	 * @param sql 查询SQL
	 * @return String[]
	 */
	public abstract String[] queryStringArray(String sql);

	/**
	 * 根据条件查询String数组
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:03
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return String[]
	 */
	public abstract String[] queryStringArray(String sql, Object[] args);

	/**
	 * 查询一条数据(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:12
	 * @param sql 查询SQL
	 * @return DataRow
	 */
	public abstract DataRow queryMap(String sql);

	/**
	 * 根据条件查询一条数据(DataRow)
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:16
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @return DataRow
	 */
	public abstract DataRow queryMap(String sql, Object[] args);

	/**
	 * 数据分页查询
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:24
	 * @param sql 查询SQL
	 * @param curPage 当前页数
	 * @param numPerPage 每页显示数据条数
	 * @return DBPage
	 */
	public abstract DBPage queryPage(String sql, int curPage, int numPerPage);

	/**
	 * 根据条件分页查询
	 * @author zhangdq
	 * @date 2017-6-5 下午6:04:27
	 * @param sql 查询SQL
	 * @param args 查询条件参数
	 * @param curPage 当前页数
	 * @param numPerPage 每页显示数据条数
	 * @return DBPage
	 */
	public abstract DBPage queryPage(String sql, Object[] args, int curPage, int numPerPage);

	/**
	 * 开启数据库事务
	 * @author zhangdq
	 * @date 2017-05-25 22:57
	 */
	public abstract void beginTrans();

	/**
	 * 提交数据库事务
	 * @author zhangdq
	 */
	public abstract void commitTrans();

	/**
	 * 回滚数据库事务操作
	 * @author zhangdq
	 */
	public abstract void rollbackTrans();

	/**
	 * 关闭数据库连接，数据库关闭异常不做处理
	 * @author zhangdq
	 */
	public abstract void close();
	
	/**
	 * 获取插入式自动生成的主键
	 * @author zhangdq
	 * @date 2017-6-13 下午4:30:44
	 * @param 
	 * @return
	 */
	public abstract String getGeneratedKeys();
}
