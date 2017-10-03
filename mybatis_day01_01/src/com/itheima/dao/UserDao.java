package com.itheima.dao;

import java.util.List;

import com.itheima.domain.User;

public interface UserDao {
	
	//根据Id查询用户
	public User findUserByID(Integer id);
	//根据用户名模糊查询
	public List<User> findUserWithLike(String username);

}
