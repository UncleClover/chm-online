package com.clover.test;
import java.sql.Connection;

import com.clover.base.jdbc.ConnTools;
import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.session.Session;
import com.clover.base.jdbc.session.impl.SessionImpl;


public class Main {
	public static void main(String[] args) {
	    Connection conn = ConnTools.getConnect("test");
		Session session = new SessionImpl(conn);
		DataRow data = new DataRow();
		data.set("id", "2");
		data.set("name", "clover");
		session.insert("T_CLOVER_USER", data);
		session.close();
	}
}
