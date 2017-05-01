package com.clover.base.utils;

/**
 * @desc �ַ���������
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-5-1 ����7:59:51
 */
public class StringUtils {
	public static boolean isEmpty(String param) {
		return (param == null || param.trim().length() == 0);
	}

	public static boolean isNotEmpty(String param) {
		return !isEmpty(param);
	}
}
