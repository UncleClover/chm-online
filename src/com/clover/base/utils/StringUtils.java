package com.clover.base.utils;

/**
 * @desc 字符串工具类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-5-1 下午7:59:51
 */
public class StringUtils {
	
	/**
	 * @desc 判断字符串是不是为空
	 * @author zhangdq
	 * @time 2017-6-6 下午12:58:27
	 * @param param 需要判断的字符串
	 * @return boolean
	 */
	public static boolean isEmpty(String param) {
		return (param == null || param.trim().length() == 0);
	}

	/**
	 * @desc 判断字符串是不是非空
	 * @author zhangdq
	 * @time 2017-6-6 下午12:59:01
	 * @param param 需要判断的字符串
	 * @return boolean
	 */
	public static boolean isNotEmpty(String param) {
		return !isEmpty(param);
	}
	
	
	/**
	 * @desc 生产一个字符一致的字符串
	 * @author zhangdq
	 * @time 2017-6-6 下午12:59:35
	 * @param args 需要生产的字符串
	 * @param length 需要生成的长度
	 * @return String
	 */
	public static String createSpecifiedString(String args, int length) {
		if (isEmpty(args)) {
			return "";
		}

		if (length <= 0) {
			return args;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(args);
		}
		return sb.toString();
	}
}
