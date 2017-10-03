package com.itheima.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public class UserDaoImpl implements UserDao {
	
	//定义映射文件namespace
	String ns = "test.";
	
	private SqlSessionFactory sqlSessionFactory;
	
	//获取sqlSessionFactory工厂，生产sqlSession
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
		this.sqlSessionFactory=sqlSessionFactory;
	}

	@Override
	public User findUserByID(Integer id) {
		//获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//查询
		User user = sqlSession.selectOne(ns+"findUserByID", id);
		return user;
	}

	@Override
	public List<User> findUserWithLike(String username) {
		//获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//查询
		List<User> list = sqlSession.selectList(ns+"findUserWithLike", username);
		return list;
	}

}
