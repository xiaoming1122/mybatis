package com.itheima.mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;

public class TestDao {
	private InputStream resourceAsStream;
	private SqlSessionFactory sqlSessionFactory;
	
	@Before
	public void beforeConfig() throws Exception{
		//指定核心配置文件相对路径
				String resource = "sqlMapConfig.xml";
				//读取mybatis核心配置文件
				resourceAsStream = Resources.getResourceAsStream(resource);
				//获取sqlSessionFactory工厂
				sqlSessionFactory = 
						new SqlSessionFactoryBuilder().build(resourceAsStream);
	}
	
	/**
	 * 需求：传统dao开发模式测试
	 */
	@Test
	public void testDao01(){
	//创建dao对象
	UserDao userDao = new UserDaoImpl(sqlSessionFactory);
	//调用dao方法，
	User user = userDao.findUserByID(71);
	System.out.println(user);
	}


}
