/**
 * 
 */
package com.dsu.dto;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

import com.dsu.dao.RandomString;
import com.dsu.domain.model.User;
import com.dsu.domain.model.UserTest;
import com.dsu.dto.converter.ConverterUtils;
import com.dsu.dto.model.UserDTO;

/**
 * @author nescafe Test for UserDTO
 */
public class UserDTOTest {

	public static final String DTO_NAME = "dto name";
	public static final String DTO_LOGIN = "dto login";
	public static final String DTO_PASSWORD = "dto pass";

	/**
	 * Create dummy UserDTO
	 * 
	 * @return instance of UserDTO
	 */
	public static UserDTO createUserDTO() {
		UserDTO dto = new UserDTO();
		dto.setName(DTO_NAME);
		dto.setLogin(DTO_LOGIN);
		dto.setPassword(DTO_PASSWORD);
		return dto;
	}

	/**
	 * Compare UserDTO inst with values of fields
	 * 
	 * @param userDTO
	 * @param id
	 * @param name
	 * @param login
	 * @param password
	 */
	public static void assertEqualsAllFields(UserDTO userDTO, Long id, String name, String login, String password) {
		assertEquals(userDTO.getId(), id);
		assertEquals(userDTO.getName(), name);
		assertEquals(userDTO.getLogin(), login);
		assertEquals(userDTO.getPassword(), password);
	}

	/**
	 * Test create and update UserDTO
	 */
	@Test
	public void testUserDTO() {
		UserDTO dto = createUserDTO();
		dto.setId(1l);
		assertEqualsAllFields(dto, Long.valueOf(1l), DTO_NAME, DTO_LOGIN, DTO_PASSWORD);
	}

	/**
	 * Test validation rules on UserDTO fields
	 */
	@Test
	public void testValidationFieldUserDTO() {
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();

		// all fields are not empty
		UserDTO dto = createUserDTO();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(dto);
		assertEquals(0, constraintViolations.size());

		// set name and login as null
		dto.setName(null);
		dto.setLogin(null);
		dto.setPassword(null);
		constraintViolations = validator.validate(dto);
		assertEquals(2, constraintViolations.size());
		
		// set name and login to empty string
		dto.setName("");
		dto.setLogin("");
		dto.setPassword("");
		constraintViolations = validator.validate(dto);
		assertEquals(2, constraintViolations.size());
		
		// set long length fileds values 
		dto.setName(RandomString.generateRandomStringByLength(1001));
		dto.setLogin(RandomString.generateRandomStringByLength(101));
		dto.setPassword(RandomString.generateRandomStringByLength(33));
		constraintViolations = validator.validate(dto);
		assertEquals(3, constraintViolations.size());
		
//		for (ConstraintViolation<Object> cv : constraintViolations)
//			System.out.println(
//			        String.format("Внимание, ошибка! property: [%s], value: [%s], message: [%s]", cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
	}
	
	/**
	 * Test UserConverter to conver User to UserDTO and vise versa
	 */
	@Test
	public void testUserConverter() {
		UserDTO dto = createUserDTO();
		final Long id = 1l;
		dto.setId(id);
		
		// test convert to entity
		User entity = ConverterUtils.toEntity(dto);
		UserTest.assertEqualsAllFields(entity, id, dto.getName(), dto.getLogin(), dto.getPassword());
		
		// test convert to dto
		entity.setName(UserTest.USER_1_NAME);
		entity.setLogin(UserTest.USER_1_LOGIN);
		entity.setPassword(UserTest.USER_1_PASS);
		UserDTO dto2 = ConverterUtils.toDTO(entity);
		assertEqualsAllFields(dto2, id, entity.getName(), entity.getLogin(), entity.getPassword());
		
		// convert null
		entity.setId(null);
		entity.setName(null);
		entity.setLogin(null);
		entity.setPassword(null);
		dto2 = ConverterUtils.toDTO(entity);
		assertEqualsAllFields(dto2, null, null, null, null);
		entity = ConverterUtils.toEntity(dto2);
		UserTest.assertEqualsAllFields(entity, null, null, null, null);
	}
}
