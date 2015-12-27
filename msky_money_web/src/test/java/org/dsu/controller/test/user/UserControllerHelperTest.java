/**
 * 
 */
package org.dsu.controller.test.user;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang.RandomStringUtils;
import org.dsu.controller.test.TestUtil;
import org.springframework.test.web.servlet.ResultActions;

import com.dsu.dto.builder.UserDTOBuilder;
import com.dsu.dto.model.UserDTO;

/**
 * @author nescafe
 * SOme useful methods for userController test
 */
public final class UserControllerHelperTest {
	
	private UserControllerHelperTest() {}

	/** Create UserDTO with long length all fields
	 * @return - UserDTO instance
	 */
	static UserDTO buildUserDTOWithLongLengthFields() {
		String name = RandomStringUtils.random(1001);
    	String login = RandomStringUtils.random(101);
        String password = RandomStringUtils.random(33);
 
        UserDTO dto = new UserDTOBuilder().id(1l)
        		.name(name)
        		.login(login)
        		.password(password)
        		.build();
		return dto;
	}
	
	/** Create USerDTO with empty values in name, login fields
	 * @param valuesIsNull - if true, than values is set null
	 * @return
	 */
	static UserDTO buildUserDTOWithNullNameLoginFields(boolean valuesIsNull) {
        UserDTO dto = new UserDTOBuilder().id(1l).build();
        if(!valuesIsNull) {
        	dto.setName("");
        	dto.setLogin("");
        }
		return dto;
	}
	
	/** Check json response what it contains errors messages about long length fields 
	 * @param ra - obj, which contains response
	 * @throws Exception
	 */
	static void testLongLengthFieldErrorsResponse(ResultActions ra) throws Exception {
		ra.andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(3)))
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("name", "login", "password")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
                        "The maximum size of the field 'login' is 100 chars.",
                        "The maximum size of the field 'password' is 32 chars.",
                        "The maximum size of the field 'name' is 1,000 chars."
                )));
	}
	
	/**Check json response what it contains errors messages about null values in name and login fields 
	 * @param ra - obj, which contains response
	 * @throws Exception
	 */
	static void testNullNameLoginFieldsErrorsResponse(ResultActions ra) throws Exception {
		//System.out.println(ra.andReturn().getResponse().getContentAsString());
		ra.andExpect(status().isBadRequest())
		        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		        .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
		        .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("name", "login")))
		        .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
		                "The field 'login' can't be empty.",
		                "The field 'name' can't be empty."
		        )));
	}
	
	/** Test UserDTO obj with predefined values
	 * @param ra - obj, which contains response
	 * @throws Exception
	 */
	static void testValidUserDTO(ResultActions ra) throws Exception {
		ra.andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(UserControllerGetMethodTest.DTO_NAME)))
                .andExpect(jsonPath("$.login", is(UserControllerGetMethodTest.DTO_LOGIN)))
                .andExpect(jsonPath("$.password", is(UserControllerGetMethodTest.DTO_PASSWORD)));
	}
}
