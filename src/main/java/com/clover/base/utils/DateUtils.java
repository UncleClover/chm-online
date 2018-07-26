package com.clover.base.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * @author UncleClover
 * @time 2017-7-3 上午10:58:00
 * @Email qiang900714@126.com
 */
public class DateUtils {
	private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	/**
	 * 获取当前时间(日期格式：Mon Jul 03 10:58:55 CST 2017)
	 * @author UncleClover
	 * @time 2017-7-3 上午10:59:50
	 * @Email qiang900714@126.com
	 * @return Date
	 */
	public static Date now(){
		return new GregorianCalendar().getTime();
	}
	
	public static Date FormatedNow(){
		return null;
	}
	
	/**
	 * 格式化日期(yyyy-MM-dd)
	 * @author UncleClover
	 * @time 2017-7-3 上午11:06:24
	 * @Email qiang900714@126.com
	 * @param date
	 * @return String
	 */
	public static String format(Date date){
		return format(date, null);
	}
	
	/**
	 * 格式化日期(默认格式:yyyy-MM-dd)
	 * @author UncleClover
	 * @time 2017-7-3 上午11:07:04
	 * @Email qiang900714@126.com
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern){
		if(StringUtils.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转化为日期(只接受yyyy-MM-dd格式的字符串转化为日期)
	 * @author UncleClover
	 * @time 2017-7-3 上午11:15:11
	 * @Email qiang900714@126.com
	 * @param String date
	 * @return Date
	 */
	public static Date strToDate(String date){
		return strToDate(date, null);
	}
	
	/**
	 * 字符串转化为日期(默认只接受yyyy-MM-dd格式的字符串转化为日期)
	 * @author UncleClover
	 * @time 2017-7-3 上午11:31:17
	 * @Email qiang900714@126.com
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date strToDate(String date, String pattern){
		if(StringUtils.isEmpty(pattern)){
			pattern = "yyyy-MM-dd";
		}
		
		Date parseDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			parseDate = sdf.parse(date);
		} catch (ParseException e) {
			logger.error("字符串转化为日期错误~" + e);
		}
		return parseDate;
	}
	
	/**
	 * 日期转化为字符串
	 * @author UncleClover
	 * @time 2017-7-3 上午11:22:40
	 * @Email qiang900714@126.com
	 * @param Date date
	 * @return String
	 */
	public static String dateToStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat();
		return sdf.format(date);
	}
	
	/**
	 * 获取系统当前时间-SQL时间格式
	 * 
	 * @author UncleClover
	 * @time 2017年9月21日 下午5:57:18
	 * @Email qiang900714@126.com
	 * @return
	 */
	public static Timestamp getSysDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	
	public static void main(String[] args) {
		System.out.println(strToDate("2017-01-21", "yyyy-MM-dd"));
	}
}
