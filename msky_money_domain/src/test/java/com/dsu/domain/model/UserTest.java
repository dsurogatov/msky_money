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

	public static User createUser() {
		User user = new User();
		user.setName("test fio");
		user.setLogin("test");
		user.setPassword("pass");
		return user;
	}

	@Test
	public void testUser() {
		User user1 = UserTest.createUser();
		TestCase.assertNull(user1.getId());
		
		User user2 = UserTest.createUser();
		TestCase.assertEquals(user1.getId(), user2.getId());
		TestCase.assertEquals(user1.getName(), user2.getName());
		TestCase.assertEquals(user1.getLogin(), user2.getLogin());
		TestCase.assertEquals(user1.getPassword(), user2.getPassword());
		
		user1.setId(1l);
		user1.setName("test fio 2");
		user1.setLogin("test2");
		user1.setPassword("pass2");
		TestCase.assertEquals(user1.getId(), Long.valueOf(1l));
		TestCase.assertEquals(user1.getName(), "test fio 2");
		TestCase.assertEquals(user1.getLogin(), "test2");
		TestCase.assertEquals(user1.getPassword(), "pass2");
		
	}
}
