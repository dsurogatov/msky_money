/**
 * 
 */
package com.dsu.domain.model;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Helper class for users test
 */
public class UserTest {
	
	public final static String USER_1_NAME = "name 1";
	public final static String USER_2_NAME = "name 2";
	public final static String USER_1_LOGIN = "login 1";
	public final static String USER_2_LOGIN = "login 2";
	public final static String USER_1_PASS = "pass 1";
	public final static String USER_2_PASS = "pass 2";

	public static User createUser(String name, String login, String password) {
		User user = new User();
		user.setName(name);
		user.setLogin(login);
		user.setPassword(password);
		return user;
	}
	
	public static User createUser1() {
		return createUser(USER_1_NAME, USER_1_LOGIN, USER_1_PASS);
	}
	
	public static void assertEqualsAllFields(User user, Long id, String name, String login, String password){
		TestCase.assertEquals(user.getId(), id);
		TestCase.assertEquals(user.getName(), name);
		TestCase.assertEquals(user.getLogin(), login);
		TestCase.assertEquals(user.getPassword(), password);
	}
	
	public static void assertEqualsAllFields(User user1, User user2) {
		assertEqualsAllFields(user1, user2.getId(), user2.getName(), user2.getLogin(), user2.getPassword());
	}

	@Test
	public void testUser() {
		User user1 = UserTest.createUser1();
		TestCase.assertNull(user1.getId());
		
		User user2 = UserTest.createUser1();
		assertEqualsAllFields(user1, user2);
		
		user1.setId(1l);
		user1.setName(USER_2_NAME);
		user1.setLogin(USER_2_LOGIN);
		user1.setPassword(USER_2_PASS);
		assertEqualsAllFields(user1, 1l, USER_2_NAME, USER_2_LOGIN, USER_2_PASS);
	}
}
