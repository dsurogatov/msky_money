/**
 * 
 */
package org.dsu.controller.test.user;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang.RandomStringUtils;
import org.dsu.controller.test.TestUtil;
import org.dsu.controller.test.context.TestContext;
import org.dsu.controller.test.context.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dsu.dto.builder.UserDTOBuilder;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.user.UserService;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Test adding a new user
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserControllerAddUpdateMethodTest {
	
	private MockMvc mockMvc;
	 
    @Autowired
    private UserService userServiceMock;
 
    @Autowired
    private WebApplicationContext webApplicationContext;
 
    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(userServiceMock);
 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void add_AllFieldsAreTooLong_ShouldReturnValidationErrorsForFields() throws Exception {
    	// TODO implement for empty fields
    	UserDTO dto = buildUserDTOWithLongLengthFields();
 
        ResultActions ra = mockMvc.perform(post("/api/user")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto))
        		);
        testFieldErrorsResponse(ra);
 
        verifyZeroInteractions(userServiceMock);
    }

	private UserDTO buildUserDTOWithLongLengthFields() {
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

	private void testFieldErrorsResponse(ResultActions ra) throws Exception {
		ra.andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(3)))
                .andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("name", "login", "password")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
                        "Field login should be between 1 - 100 characters.",
                        "Field password should be less or equal 32 characters.",
                        "Field name should be between 1 - 1000 characters."
                )));
	}
    
    @Test
    public void add_NewUserEntry_ShouldAddUserEntryAndReturnAddedEntry() throws Exception {
        UserDTO dto = new UserDTOBuilder()
        		.name(UserControllerGetMethodTest.DTO_NAME)
        		.login(UserControllerGetMethodTest.DTO_LOGIN)
        		.password(UserControllerGetMethodTest.DTO_PASSWORD)
        		.build();
 
        UserDTO added =new UserDTOBuilder().id(1l)
        		.name(UserControllerGetMethodTest.DTO_NAME)
        		.login(UserControllerGetMethodTest.DTO_LOGIN)
        		.password(UserControllerGetMethodTest.DTO_PASSWORD)
        		.build();
 
        when(userServiceMock.create(any(UserDTO.class))).thenReturn(added);
 
        ResultActions ra = mockMvc.perform(post("/api/user")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto))
        		);
        ra.andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(UserControllerGetMethodTest.DTO_NAME)))
                .andExpect(jsonPath("$.login", is(UserControllerGetMethodTest.DTO_LOGIN)))
                .andExpect(jsonPath("$.password", is(UserControllerGetMethodTest.DTO_PASSWORD)));
 
        ArgumentCaptor<UserDTO> dtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userServiceMock, times(1)).create(dtoCaptor.capture());
        verifyNoMoreInteractions(userServiceMock);
 
        UserDTO dtoArgument = dtoCaptor.getValue();
        TestCase.assertNull(dtoArgument.getId());
        assertThat(dtoArgument.getName(), is(UserControllerGetMethodTest.DTO_NAME));
        assertThat(dtoArgument.getLogin(), is(UserControllerGetMethodTest.DTO_LOGIN));
        assertThat(dtoArgument.getPassword(), is(UserControllerGetMethodTest.DTO_PASSWORD));
    }
    
    @Test
    public void update_AllFieldsAreTooLong_ShouldReturnValidationErrorsForFields() throws Exception {
    	UserDTO dto = buildUserDTOWithLongLengthFields();
    	 
        ResultActions ra = mockMvc.perform(put("/api/user")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto))
        		);
        testFieldErrorsResponse(ra);
 
        verifyZeroInteractions(userServiceMock);
    }
    
    @Test
    public void update_ExistUserEntry_ShouldAddUserEntryAndReturnUpdatedEntry() throws Exception {
        UserDTO dto = new UserDTOBuilder().id(1l)
        		.name(UserControllerGetMethodTest.DTO_NAME)
        		.login(UserControllerGetMethodTest.DTO_LOGIN)
        		.password(UserControllerGetMethodTest.DTO_PASSWORD)
        		.build();
 
        when(userServiceMock.update(any(UserDTO.class))).thenReturn(dto);
 
        ResultActions ra = mockMvc.perform(put("/api/user")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto))
        		);
        ra.andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(UserControllerGetMethodTest.DTO_NAME)))
                .andExpect(jsonPath("$.login", is(UserControllerGetMethodTest.DTO_LOGIN)))
                .andExpect(jsonPath("$.password", is(UserControllerGetMethodTest.DTO_PASSWORD)));
 
        ArgumentCaptor<UserDTO> dtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userServiceMock, times(1)).update(dtoCaptor.capture());
        verifyNoMoreInteractions(userServiceMock);
 
        UserDTO dtoArgument = dtoCaptor.getValue();
        TestCase.assertNotNull(dtoArgument.getId());
        assertThat(dtoArgument.getName(), is(UserControllerGetMethodTest.DTO_NAME));
        assertThat(dtoArgument.getLogin(), is(UserControllerGetMethodTest.DTO_LOGIN));
        assertThat(dtoArgument.getPassword(), is(UserControllerGetMethodTest.DTO_PASSWORD));
    }
}
