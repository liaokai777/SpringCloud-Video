package com.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.mapper.UserMapper;
import com.login.pojo.User;
import com.login.service.UserService;

@Service
public class UserServerImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User selectUserById(Integer id) {
		return userMapper.selectUserById(id);
	}
	
}
