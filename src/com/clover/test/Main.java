package com.clover.test;

import java.util.GregorianCalendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clover.base.constants.ChmConstants;
import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.SequenceGenerator;
import com.clover.base.jdbc.session.JdbcTemplate;
import com.clover.dao.service.UserService;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

public class Main {
	public static void main(String[] args) {
		System.out.println(new GregorianCalendar().getTime());
	}
}
