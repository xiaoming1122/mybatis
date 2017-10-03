package com.itheima.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.itheima.domain.User;

public class MyMybatis {
	
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
	 * 需求：查询所有用户
	 * 参数：无
	 * 返回值：List<User>
	 * @throws Exception 
	 */
	@Test
	public void test01() throws Exception{
		//指定核心配置文件相对路径
		String resource = "sqlMapConfig.xml";
		//读取mybatis核心配置文件
		resourceAsStream = Resources.getResourceAsStream(resource);
		//获取sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(resourceAsStream);
		//获取sqlSession对象
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//调用sqlSession接口方法，实现数据库操作
		//参数：唯一定义映射文件中sql语句 
		//定义语法：namespace.sqlId   test.findAllUser
		List<User> list = sqlSession.selectList("test.findAllUser");
		
		//循环打印结果
		for (User user : list) {
			System.out.println(user);
			
		}
		
	}
	
	/**
	 * 需求：根据Id查询用户
	 * 参数：int id
	 * 返回值：User
	 * @throws Exception 
	 */
	@Test
	public void test02() throws Exception{
		//指定核心配置文件路径
		String resource = "sqlMapConfig.xml";
		//读取核心配置文件
		resourceAsStream = Resources.getResourceAsStream(resource);
		//获取工厂
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(resourceAsStream);
		//从工厂中获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//调用sqlSession接口方法，执行sql语句
		//参数1：唯一定位一条映射文件中sql语句 定义语法：namespace.sqlId
		//参数2：传递查询参数，根据Id查询，参数是id
		User user = sqlSession.selectOne("test.findUserByID", 1);
		System.out.println(user);
	}
	
	/**
	 * 需求：根据用户名进行模糊查询
	 * 参数：String username
	 * 返回值：List<user>
	 * @throws Exception 
	 */
	@Test
	public void test03() throws Exception{
		//指定核心配置文件路径
		String resource = "sqlMapConfig.xml";
		//读取核心配置文件
		resourceAsStream = Resources.getResourceAsStream(resource);
		//获取工厂
		SqlSessionFactory sqlSessionFactory = 
				new SqlSessionFactoryBuilder().build(resourceAsStream);
		//从工厂中获取sqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		//调用sqlSession接口方法，执行sql语句，查询
		//参数1:唯一定位一条sql语句 
		List<User> list = sqlSession.selectList("test.findUserWithLike", "奥巴");
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	/**
	 * 需求：保存用户
	 * 参数：User对象
	 * 返回值：无
	 */
	@Test
	public void test04(){
	//从工厂中获取Session	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	//创建User对象
	User user = new User();
	user.setUsername("张无忌and小昭");
	user.setBirthday(new Date());
	user.setSex("男");
	user.setAddress("波斯");
	
	
	
	//调用接口方法，执行sql语句
	//参数1：唯一定位一条sql语句   namespace.sqlId
	//参数2：传递参数
	sqlSession.insert("test.insertUser", user);
	System.out.println("用户Id："+user.getId());
	//事务提交
	sqlSession.commit();
	
	System.out.println("用户Id："+user.getId());
	//关闭
	sqlSession.close();
	}
	/**
	 * 需求：根据Id删除用户
	 * 参数：id
	 * 返回值：无
	 */
	@Test
	public void test05(){
	//从工厂中获取Session	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	//调用接口方法，执行sql语句
	//参数1：唯一定位一条sql语句   namespace.sqlId
	//参数2：传递参数	
	sqlSession.delete("test.deleteUserByID", 114);
	//事务提交
	sqlSession.commit();	
	//关闭
	sqlSession.close();
	}
	/**
	 * 需求：根据Id更新用户
	 * 参数：User
	 * 返回值：无
	 */
	@Test
	public void test06(){
	//从工厂中获取Session	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	//创建User对象
	User user = new User();
	user.setId(113);
	user.setUsername("张无忌and梅绝师太");
	user.setBirthday(new Date());
	user.setSex("男");
	user.setAddress("大都");
	//调用接口方法，执行sql语句
	//参数1：唯一定位一条sql语句   namespace.sqlId
	//参数2：传递参数	
	sqlSession.update("test.updateUserByID", user);
	//事务提交
	sqlSession.commit();	
	//关闭
	sqlSession.close();
	}
}
