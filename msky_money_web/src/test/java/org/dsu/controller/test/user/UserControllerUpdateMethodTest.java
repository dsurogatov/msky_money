/**
 * 
 */
package org.dsu.controller.test.user;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.IOException;

import static org.dsu.controller.test.user.UserControllerHelperTest.*;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.dsu.dto.builder.UserDTOBuilder;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.user.UserService;

import junit.framework.TestCase;

/**
 * @author nescafe
 * Test update users dto instance
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserControllerUpdateMethodTest {

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
    public void update_AllFieldsAreTooLong_ShouldReturnValidationErrorsForFields() throws Exception {
    	UserDTO dto = buildUserDTOWithLongLengthFields();
    	 
        ResultActions ra = mockMvc.perform(putUserDTO(dto));
        testLongLengthFieldErrorsResponse(ra, getLongLengthUserFieldErrorMessages());
 
        verifyZeroInteractions(userServiceMock);
    }
    
    @Test
    public void update_NameLoginFieldsAreNull_ShouldReturnValidationErrorsForFields() throws Exception {
    	UserDTO dto = buildUserDTOWithNullNameLoginFields(true);
 
        ResultActions ra = mockMvc.perform(putUserDTO(dto));
        testNullNameLoginFieldsErrorsResponse(ra, getEmptyUserFieldErrorMessages());
 
        verifyZeroInteractions(userServiceMock);
    }
    
    @Test
    public void update_NameLoginFieldsAreEmpty_ShouldReturnValidationErrorsForFields() throws Exception {
    	UserDTO dto = buildUserDTOWithNullNameLoginFields(false);
 
        ResultActions ra = mockMvc.perform(putUserDTO(dto));
        testNullNameLoginFieldsErrorsResponse(ra, getEmptyUserFieldErrorMessages());
 
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
 
        ResultActions ra = mockMvc.perform(putUserDTO(dto));
        testValidUserDTO(ra);
 
        ArgumentCaptor<UserDTO> dtoCaptor = ArgumentCaptor.forClass(UserDTO.class);
        verify(userServiceMock, times(1)).update(dtoCaptor.capture());
        verifyNoMoreInteractions(userServiceMock);
 
        UserDTO dtoArgument = dtoCaptor.getValue();
        TestCase.assertNotNull(dtoArgument.getId());
        assertThat(dtoArgument.getName(), is(UserControllerGetMethodTest.DTO_NAME));
        assertThat(dtoArgument.getLogin(), is(UserControllerGetMethodTest.DTO_LOGIN));
        assertThat(dtoArgument.getPassword(), is(UserControllerGetMethodTest.DTO_PASSWORD));
    }

    protected MockHttpServletRequestBuilder putUserDTO(UserDTO dto) throws IOException {
		return put("/api/user")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto));
	}
    
    /** Get array of error messages about long length fields in User class
	 * @return arrays of messages
	 */
    protected String[] getLongLengthUserFieldErrorMessages() {
    	return getLongLengthUserFieldErrorMessagesEn();
    }
    
    /** Get array of error messages about empty fields in User class
     * @return - arrays of messages
     */
    protected String[] getEmptyUserFieldErrorMessages() {
    	return getEmptyUserFieldErrorMessagesEn();
    }
}
