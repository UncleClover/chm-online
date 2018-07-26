package com.clover.dao.service;


import com.clover.base.jdbc.DataRow;

/**
 * 用户接口类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-4-9 上午1:06:39
 */
public interface UserService {
	/**
	 * 登录查询
	 * @author UncleClover
	 * @time 2017-4-9 上午1:12:00
	 * @param userAccount 登录账户
	 * @param passWord 登录密码
	 * @return DataRow
	 */
	DataRow queryUser(String userAccount, String passWord);
}
