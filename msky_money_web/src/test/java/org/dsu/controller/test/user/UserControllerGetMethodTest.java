/**
 * 
 */
package org.dsu.controller.test.user;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dsu.controller.test.TestUtil;
import org.dsu.controller.test.context.TestContext;
import org.dsu.controller.test.context.WebAppContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriUtils;

import com.dsu.dto.builder.UserDTOBuilder;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;
import com.dsu.service.user.UserService;

/**
 * @author nescafe
 * Test UserController with WebApplicationContext (WAC)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserControllerGetMethodTest {
	
	public static final String DTO_NAME = "dto name";
	public static final String DTO_NAME_RUS = "dto русский?";
	public static final String DTO_LOGIN = "dto login";
	public static final String DTO_PASSWORD = "dto pass";
	
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
    
    private List<UserDTO> getUserTestInsts() {
    	UserDTO first = new UserDTOBuilder().id(1l)
        		.name(DTO_NAME)
        		.login(DTO_LOGIN)
        		.password(DTO_PASSWORD)
        		.build();
        UserDTO second =  new UserDTOBuilder().id(2l)
        		.name(DTO_NAME)
        		.login(DTO_LOGIN)
        		.password(DTO_PASSWORD)
        		.build();
        return Arrays.asList(first, second);
    }
    
    @Test
    public void getUsers_UsersFound_ShouldReturnFoundUserEntries() throws Exception {
        when(userServiceMock.findAll()).thenReturn(getUserTestInsts());
 
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(DTO_NAME)))
                .andExpect(jsonPath("$[0].login", is(DTO_LOGIN)))
                .andExpect(jsonPath("$[0].password", is(DTO_PASSWORD)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is(DTO_NAME)))
                .andExpect(jsonPath("$[1].login", is(DTO_LOGIN)))
                .andExpect(jsonPath("$[1].password", is(DTO_PASSWORD)));
 
        verify(userServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(userServiceMock);
    }
    
    @Test
    public void getUsersFound_UsersFound_ShouldReturnFoundUserEntries() throws Exception {
        when(userServiceMock.findByFields(DTO_NAME)).thenReturn(getUserTestInsts());
 
        mockMvc.perform(get("/api/user/find/" + DTO_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(DTO_NAME)))
                .andExpect(jsonPath("$[0].login", is(DTO_LOGIN)))
                .andExpect(jsonPath("$[0].password", is(DTO_PASSWORD)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is(DTO_NAME)))
                .andExpect(jsonPath("$[1].login", is(DTO_LOGIN)))
                .andExpect(jsonPath("$[1].password", is(DTO_PASSWORD)));
 
        verify(userServiceMock, times(1)).findByFields(DTO_NAME);
        verifyNoMoreInteractions(userServiceMock);
    }
    
    @Test
    public void getUsersFound_UsersNotFound_ShouldReturnEmptyListEntries() throws Exception {
        when(userServiceMock.findByFields(DTO_NAME_RUS)).thenReturn(new ArrayList<>());
        
        String uri = "/api/user/find/" + UriUtils.encode(DTO_NAME_RUS, "utf-8");
        //System.out.println(uri);
        mockMvc.perform(get(uri))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		        .andExpect(jsonPath("$", hasSize(0)));

        verify(userServiceMock, times(1)).findByFields(DTO_NAME_RUS);
        verifyNoMoreInteractions(userServiceMock);
    }
    
    @Test
    public void getUserById_UserEntryNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(userServiceMock.findById(1L)).thenThrow(new MskyMoneyException(ExceptionType.ENTITY_NOT_FINDED));
 
        mockMvc.perform(get("/api/user/{id}", 1L))
                .andExpect(status().isNotFound());
 
        verify(userServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(userServiceMock);
    }
    
    @Test
    public void getUserById_UserEntryFound_ShouldReturnFoundUserEntry() throws Exception {
        when(userServiceMock.findById(1L)).thenReturn(getUserTestInsts().get(0));
 
        mockMvc.perform(get("/api/user/{id}", 1L))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		        .andExpect(jsonPath("$.id", is(1)))
		        .andExpect(jsonPath("$.name", is(DTO_NAME)))
		        .andExpect(jsonPath("$.login", is(DTO_LOGIN)))
		        .andExpect(jsonPath("$.password", is(DTO_PASSWORD)));
 
        verify(userServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(userServiceMock);
    }
}