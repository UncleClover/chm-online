package com.clover.test;

import com.clover.base.constants.ChmConstants;
import com.clover.base.jdbc.DataRow;
import com.clover.base.jdbc.SequenceGenerator;
import com.clover.base.jdbc.session.JdbcTemplate;


public class Main {
	public static void main(String[] args) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ChmConstants.DB_TEST);
		DataRow data = new DataRow();
		data.set("id", SequenceGenerator.getInstance().getNextSequence(ChmConstants.DB_TEST,"T_CLOVER_USER"));
		data.set("name", "clover");
		jdbcTemplate.insert("T_CLOVER_USER", data);
		jdbcTemplate.getgeneratedKeys();
	}
}
