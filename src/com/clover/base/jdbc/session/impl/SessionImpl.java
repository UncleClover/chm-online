package com.clover.base.jdbc.session.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.session.Session;

public class SessionImpl implements Session {
	private Connection conn = null;

	public SessionImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Connection connection() {
		return conn;
	}

	@Override
	public int insert(String tbName, DataRow data) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(tbName).append(" ");
		sb.append("(");
		int i = 0;
		List valList = new ArrayList();
		String replaceStr = "";
		for(Iterator it = data.keySet().iterator();it.hasNext();){
			String columnName = (String) it.next();
			valList.add(data.get(columnName));
			if(i < data.size()){
				sb.append(columnName).append(",");
				replaceStr = replaceStr + "?,";
			}else{
				sb.append(columnName).append(") ");
				replaceStr = replaceStr + "?";
			}
		}
		sb.append("values(").append(replaceStr).append(")");
		return 0;
	}

	@Override
	public int delete(String tbName, String identify, Object identifyValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(String sql, Object[] objs) {
		
		return 0;
	}

	@Override
	public List<DataRow> query(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRow> query(String sql, Object[] objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void beginTrans() {
		// TODO Auto-generated method stub

	}

	@Override
	public void commitTrans() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollbackTrans() {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
