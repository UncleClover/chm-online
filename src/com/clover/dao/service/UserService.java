package com.clover.dao.service;

import java.util.HashMap;

/**
 * @desc �û��ӿ���
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-4-9 ����1:06:39
 */
public interface UserService {
	/**
	 * @desc ��¼��ѯ
	 * @author zhangdq
	 * @time 2017-4-9 ����1:12:00
	 * @param userAccount ��¼�˻�
	 * @param passWord ��¼����
	 * @return HashMap<String, String>
	 */
	HashMap<String, String> queryUser(String userAccount, String passWord);
}
