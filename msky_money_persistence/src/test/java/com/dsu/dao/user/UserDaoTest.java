/**
 * 
 */
package com.dsu.dao.user;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsu.dao.RandomString;
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
		User user1 = UserTest.createUser1();

		// create user
		user1 = dao.save(user1);
		TestCase.assertNotNull(user1.getId());
		User user2 = UserTest.createUser1();
		user2.setId(user1.getId());
		UserTest.assertEqualsAllFields(user1, user2);

		// update user
		user1.setName(UserTest.USER_2_NAME);
		user1.setLogin(UserTest.USER_2_LOGIN);
		user1.setPassword(UserTest.USER_2_PASS);
		Long user1Id = user1.getId();
		user1 = dao.save(user1);
		UserTest.assertEqualsAllFields(user1, user1Id, UserTest.USER_2_NAME, UserTest.USER_2_LOGIN, UserTest.USER_2_PASS);

		// find user
		user1 = dao.findById(user1Id);
		UserTest.assertEqualsAllFields(user1, user1Id, UserTest.USER_2_NAME, UserTest.USER_2_LOGIN, UserTest.USER_2_PASS);

		// get, count list
		List<User> userList = dao.findAll();
		TestCase.assertEquals(userList.size(), 1);
		TestCase.assertEquals(dao.count(), 1);

		// remove user
		dao.delete(user1.getId());
		TestCase.assertEquals(dao.count(), 0);
	}

	void findColsTestDao() {
		User user1 = UserTest.createUser1();
		final String findingValue = "xxxxx";
		user1.setName(findingValue);
		user1.setLogin("aaaaaaaaa");
		user1 = dao.save(user1);

		List<User> userList = dao.findByFields(findingValue);
		TestCase.assertFalse(userList.isEmpty());

		userList = dao.findByFields("yyyyy");
		TestCase.assertTrue(userList.isEmpty());

		userList = dao.findByFields("");
		TestCase.assertFalse(userList.isEmpty());

		dao.delete(user1.getId());
		TestCase.assertEquals(dao.count(), 0);

		findNullTestDao();
	}

	void findNullTestDao() {
		try {
			dao.findByFields(null);
		} catch (Exception e) {
			assertThat(e.getCause(), is(instanceOf(IllegalArgumentException.class)));
		}
	}

	void uniqueLoginTestDao() {
		User user1 = UserTest.createUser1();
		dao.save(user1);
		User user2 = UserTest.createUser1();
		dao.save(user2);
		dao.flush();
	}

	void nullNameTestDao() {
		User user1 = UserTest.createUser1();
		user1.setName(null);
		user1 = dao.save(user1);
	}

	void nullLoginTestDao() {
		User user1 = UserTest.createUser1();
		user1.setLogin(null);
		user1 = dao.save(user1);
	}

	void nullPasswordTestDao() {
		User user1 = UserTest.createUser1();
		user1.setPassword(null);
		user1 = dao.save(user1);
	}

	void longLengthNameTestDao() {
		User user1 = UserTest.createUser1();
		user1.setName(RandomString.generateRandomStringByLength(1001));
		user1 = dao.save(user1);
		dao.flush();
	}

	void longLengthLoginTestDao() {
		User user1 = UserTest.createUser1();
		user1.setLogin(RandomString.generateRandomStringByLength(101));
		user1 = dao.save(user1);
		dao.flush();
	}

	void longLengthPasswordTestDao() {
		User user1 = UserTest.createUser1();
		user1.setPassword(RandomString.generateRandomStringByLength(33));
		user1 = dao.save(user1);
		dao.flush();
	}

	void changePersistentIdTestDao() {
		User user1 = UserTest.createUser1();
		user1 = dao.save(user1);
		user1.setId(user1.getId() + 1);
		user1 = dao.save(user1);
		dao.flush();
	}

}
