package com.xugu.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.xugu.dao.PersonDao;
import com.xugu.pojo.Person;

/**
 * 测试类(包含基本的数据库DML操作)
 * 
 * @author wsy
 * @date 2019/01/31
 */
@SuppressWarnings("resource")
public class AppTest 
{
	private static PersonDao personDao ;
	private long id = new Random().nextLong();
	
	static {
	    // 加载spring的配置文件
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
		personDao = (PersonDao) ctx.getBean(PersonDao.class);
	}
	 @Before
     public void insert() throws SQLException {
		 Person person = new Person();
		 person.setId(id);
		 person.setName("test");
		 person.setSex("男");
		 System.out.println("插入数据："+personDao.save(person));         
     }
	 
	 @Test
	 public void listAll(){
		 List<Person> persons = personDao.findAll();
         for(Person person1:persons){
        	 System.out.println("查询数据："+person1);
         }
	 }
	 
	 @Test
	 public void update(){
		 Person person = personDao.findById(id);
		 person.setName("1111111");
		 person.setSex("女");
		 System.out.println("更新数据："+personDao.save(person));;
	 }
	 
	 /**
	  * 删除某一条数据
	  */
	 @After
	 public void delete(){
		Person person = personDao.findById(id);
		personDao.delete(person);
		System.out.println("删除数据："+person);
	 }

//	 /**
//	  * 清除所有数据
//	  */
//	 @Test
//	 public void truncate(){
//		 personDao.deleteAll();
//	 }
}
