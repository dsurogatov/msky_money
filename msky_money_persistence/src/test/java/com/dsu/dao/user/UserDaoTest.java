/**
 * 
 */
package com.dsu.dao.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsu.domain.model.User;
import com.dsu.domain.model.UserTest;

import junit.framework.TestCase;

/**
 * @author nescafe
 *
 */
@Component
public class UserDaoTest {

	@Autowired
	private UserDao dao;
	
	protected void commonTestDao() {
		User user1 = UserTest.createUser();
		
		// create user
		user1 = dao.save(user1);
		TestCase.assertNotNull(user1.getId());
		User user2 = UserTest.createUser();
		TestCase.assertEquals(user1.getName(), user2.getName());
		TestCase.assertEquals(user1.getLogin(), user2.getLogin());
		TestCase.assertEquals(user1.getPassword(), user2.getPassword());
		
		// update user
		user1.setName("test fio 2");
		user1.setLogin("test2");
		user1.setPassword("pass2");
		Long user1Id = user1.getId();
		user1 = dao.save(user1);
		TestCase.assertEquals(user1.getId(), user1Id);
		TestCase.assertEquals(user1.getName(), "test fio 2");
		TestCase.assertEquals(user1.getLogin(), "test2");
		TestCase.assertEquals(user1.getPassword(), "pass2");
		
		// find user
		user1 = dao.findById(user1Id);
		TestCase.assertEquals(user1.getId(), user1Id);
		TestCase.assertEquals(user1.getName(), "test fio 2");
		TestCase.assertEquals(user1.getLogin(), "test2");
		TestCase.assertEquals(user1.getPassword(), "pass2");
		
		// get, count list
		List<User> userList = dao.findAll();
		TestCase.assertEquals(userList.size(), 1);
		TestCase.assertEquals(dao.count(), 1);
		
		// remove user
		dao.delete(user1);
		TestCase.assertEquals(dao.count(), 0);
	}
	
	protected void findColsTestDao() {
		User user1 = UserTest.createUser();
		final String findedValue = "xxxxx";
		user1.setName(findedValue);
		user1.setLogin("aaaaaaaaa");
		user1 = dao.save(user1);
		
		List<User> userList = dao.findByCols(findedValue);
		TestCase.assertFalse(userList.isEmpty());
		
		userList = dao.findByCols("yyyyy");
		TestCase.assertTrue(userList.isEmpty());
		
		dao.delete(user1);
		TestCase.assertEquals(dao.count(), 0);
	}
	
	protected void _nullNameTestDao() {
		User user1 = UserTest.createUser();
		user1.setName(null);
		user1 = dao.save(user1);
	}
	
	protected void _nullLoginTestDao() {
		User user1 = UserTest.createUser();
		user1.setLogin(null);
		user1 = dao.save(user1);
	}
	
	protected void _nullIdTestDao() {
		User user1 = UserTest.createUser();
		user1 = dao.save(user1);
		Long id = user1.getId();
		user1.setId(null);
		//user1.setId(5l);
		user1 = dao.save(user1);
		user1 = dao.findById(id);
		System.out.println(user1);
	}
}
