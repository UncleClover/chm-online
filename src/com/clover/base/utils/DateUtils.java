package com.clover.base.utils;

import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static Date now(){
		return new GregorianCalendar().getTime();
	}
}
