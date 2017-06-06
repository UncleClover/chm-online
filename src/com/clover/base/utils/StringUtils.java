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
	
	/**
	 * @desc �ж��ַ����ǲ���Ϊ��
	 * @author zhangdq
	 * @time 2017-6-6 ����12:58:27
	 * @param param ��Ҫ�жϵ��ַ���
	 * @return boolean
	 */
	public static boolean isEmpty(String param) {
		return (param == null || param.trim().length() == 0);
	}

	/**
	 * @desc �ж��ַ����ǲ��Ƿǿ�
	 * @author zhangdq
	 * @time 2017-6-6 ����12:59:01
	 * @param param ��Ҫ�жϵ��ַ���
	 * @return boolean
	 */
	public static boolean isNotEmpty(String param) {
		return !isEmpty(param);
	}
	
	
	/**
	 * @desc ����һ���ַ�һ�µ��ַ���
	 * @author zhangdq
	 * @time 2017-6-6 ����12:59:35
	 * @param args ��Ҫ�������ַ���
	 * @param length ��Ҫ���ɵĳ���
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
