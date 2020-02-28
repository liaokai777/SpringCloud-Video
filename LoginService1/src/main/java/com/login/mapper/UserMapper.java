package com.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.login.pojo.User;

@Mapper
public interface UserMapper {
	 public User selectUserById(Integer id);
}
