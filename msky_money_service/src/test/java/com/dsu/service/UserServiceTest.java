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

import com.dsu.domain.model.UserTest;
import com.dsu.dto.converter.ConverterUtils;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;
import com.dsu.service.user.UserService;

import junit.framework.TestCase;

/**
 * @author nescafe Test case for UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/service-test-context.xml")
public class UserServiceTest {

	public static void assertEqualsAllFields(UserDTO user, Long id, String name, String login, String password) {
		TestCase.assertEquals(user.getId(), id);
		TestCase.assertEquals(user.getName(), name);
		TestCase.assertEquals(user.getLogin(), login);
		TestCase.assertEquals(user.getPassword(), password);
	}

	@Autowired
	private UserService service;

	@Test
	public void allMethodsUserServiceTest() {

		// test creating
		UserDTO user1 = ConverterUtils.toDTO(UserTest.createUser1());
		user1 = service.create(user1);
		TestCase.assertNotNull(user1.getId());
		assertEqualsAllFields(user1, user1.getId(), UserTest.USER_1_NAME, UserTest.USER_1_LOGIN, UserTest.USER_1_PASS);

		// test update user
		user1.setName(UserTest.USER_2_NAME);
		user1.setLogin(UserTest.USER_2_LOGIN);
		user1.setPassword(UserTest.USER_2_PASS);
		Long user1Id = user1.getId();
		user1 = service.update(user1);
		assertEqualsAllFields(user1, user1Id, UserTest.USER_2_NAME, UserTest.USER_2_LOGIN, UserTest.USER_2_PASS);

		// find user
		user1 = service.findById(user1Id);
		assertEqualsAllFields(user1, user1Id, UserTest.USER_2_NAME, UserTest.USER_2_LOGIN, UserTest.USER_2_PASS);

		// test getting all users
		UserDTO user2 = ConverterUtils.toDTO(UserTest.createUser1());
		user2 = service.create(user2);
		List<UserDTO> userList = service.findAll();
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
		service.delete(user1.getId());
		service.delete(user2.getId());
		userList = service.findAll();
		TestCase.assertEquals(userList.size(), 0);

	}

	@Test
	public void userNotFindedTest() {
		try {
			UserDTO user = service.findById(1l);
			TestCase.fail("User founded! " + user);
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.ENTITY_NOT_FINDED){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
		
		try {
			UserDTO user = service.findById(null);
			TestCase.fail("User founded! " + user);
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.INTERNAL_ERROR && !(e.getCause() instanceof IllegalArgumentException)){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
	}
	
	@Test
	public void createNullTest() {
		try {
			UserDTO user = service.create(null);
			TestCase.fail("User has been created! " + user);
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.INTERNAL_ERROR && !(e.getCause() instanceof IllegalArgumentException)){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
		
		// test: id must be null
		try {
			UserDTO user1 = new UserDTO();
			user1.setId(1l);
			UserDTO user = service.create(user1);
			TestCase.fail("User has been created! " + user);
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.INTERNAL_ERROR && !(e.getCause() instanceof IllegalArgumentException)){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
	}
	
	@Test
	public void updateNullTest() {
		try {
			UserDTO user = service.update(null);
			TestCase.fail("User has been updated! " + user);
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.INTERNAL_ERROR && !(e.getCause() instanceof IllegalArgumentException)){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
		
		// test: id mustn't be null
		try {
			UserDTO user = service.update(new UserDTO());
			TestCase.fail("User has been updated! " + user);
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.INTERNAL_ERROR && !(e.getCause() instanceof IllegalArgumentException)){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
	}
	
	/**
	 * Test to delete a null entity, an entity with null id, and delete not existing entity 
	 */
	@Test
	public void deleteNullTest() {
		
		// test: id mustn't be null
		try {
			service.delete(null);
			TestCase.fail("Null user has been deleted! ");
		} catch (MskyMoneyException e) {
			if(e.getType() != ExceptionType.INTERNAL_ERROR && !(e.getCause() instanceof IllegalArgumentException)){
				TestCase.fail("Wrong type of the exception! " + e.getType());
			}
		}
		
		// delete not exicting user
		service.delete(100l);
	}
}
