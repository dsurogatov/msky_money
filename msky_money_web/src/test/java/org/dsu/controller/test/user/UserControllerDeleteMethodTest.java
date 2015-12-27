/**
 * 
 */
package org.dsu.controller.test.user;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dsu.service.exception.ExceptionType;
import com.dsu.service.exception.MskyMoneyException;
import com.dsu.service.user.UserService;

/**
 * @author nescafe
 * Set of test to delete user entry
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserControllerDeleteMethodTest {
	
	private MockMvc mockMvc;
	 
    @Autowired
    private UserService userServiceMock;
 
    @Autowired
    private WebApplicationContext webApplicationContext;
 
    @Before
    public void setUp() {
        Mockito.reset(userServiceMock);
 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void delete_UserEntry_ShouldReturnOk() throws Exception {
    	//when(userServiceMock.delete(any(UserDTO.class))).thenReturn(dto);
    	 
        ResultActions ra = mockMvc.perform(deleteUserDTO(1l));
        ra.andExpect(status().isOk());
 
        verify(userServiceMock, times(1)).delete(1L);
        verifyNoMoreInteractions(userServiceMock);
    }
    
    @Test
    public void delete_UserEntryWithNullId_ShouldThrowException() throws Exception {
    	MskyMoneyException ex = new MskyMoneyException(ExceptionType.INTERNAL_ERROR);
    	doThrow(ex).when(userServiceMock).delete(1l);
    	 
        ResultActions ra = mockMvc.perform(deleteUserDTO(1l));
        ra.andExpect(status().isInternalServerError());
 
        verify(userServiceMock, times(1)).delete(1L);
        verifyNoMoreInteractions(userServiceMock);
    }
    
    MockHttpServletRequestBuilder deleteUserDTO(Long id) throws IOException {
		return delete("/api/user/{id}", id);
	}
}
