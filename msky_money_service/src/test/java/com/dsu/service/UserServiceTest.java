/**
 * 
 */
package com.dsu.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dsu.domain.model.User;
import com.dsu.domain.model.UserTest;
import com.dsu.service.user.UserService;

import junit.framework.TestCase;

/**
 * @author nescafe Test case for UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/service-test-context.xml")
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Test
	public void createUserTest() {

		// test creating
		User user1 = UserTest.createUser1();
		user1 = service.create(user1);
		TestCase.assertNotNull(user1.getId());
		UserTest.assertEqualsAllFields(user1, user1.getId(), UserTest.USER_1_NAME, UserTest.USER_1_LOGIN, UserTest.USER_1_PASS);

		// test update user
		user1.setName(UserTest.USER_2_NAME);
		user1.setLogin(UserTest.USER_2_LOGIN);
		user1.setPassword(UserTest.USER_2_PASS);
		Long user1Id = user1.getId();
		user1 = service.update(user1);
		UserTest.assertEqualsAllFields(user1, user1Id, UserTest.USER_2_NAME, UserTest.USER_2_LOGIN, UserTest.USER_2_PASS);

		// find user
		user1 = service.findById(user1Id);
		UserTest.assertEqualsAllFields(user1, user1Id, UserTest.USER_2_NAME, UserTest.USER_2_LOGIN, UserTest.USER_2_PASS);

		// test getting all users
		User user2 = UserTest.createUser1();
		user2 = service.create(user2);
		List<User> userList = service.findAll();
		TestCase.assertEquals(userList.size(), 2);
		
		// test find user by substring
		userList = service.findByFields(UserTest.USER_2_NAME);
		TestCase.assertEquals(userList.size(), 1);
		userList = service.findByFields(UserTest.USER_2_NAME + "1234567890");
		TestCase.assertEquals(userList.size(), 0);
		userList = service.findByFields("");
		TestCase.assertEquals(userList.size(), 2);
		userList = service.findByFields(null);
		TestCase.assertEquals(userList.size(), 2);
		
		// test delete user
		service.delete(user1);
		service.delete(user2);
		userList = service.findAll();
		TestCase.assertEquals(userList.size(), 0);

	}
}
