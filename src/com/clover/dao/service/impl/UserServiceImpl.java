package com.clover.dao.service.impl;

import org.springframework.stereotype.Service;

import com.clover.base.jdbc.DataRow;
import com.clover.dao.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public DataRow queryUser(String userAccount, String passWord) {
		return null;
	}
}
