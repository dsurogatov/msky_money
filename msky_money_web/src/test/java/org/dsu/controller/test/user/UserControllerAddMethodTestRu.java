/**
 * 
 */
package org.dsu.controller.test.user;

import static org.dsu.controller.test.user.UserControllerHelperTest.getEmptyUserFieldErrorMessagesRu;
import static org.dsu.controller.test.user.UserControllerHelperTest.getLongLengthUserFieldErrorMessagesRu;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.io.IOException;
import java.util.Locale;

import org.dsu.controller.test.TestUtil;
import org.dsu.controller.test.context.TestContext;
import org.dsu.controller.test.context.WebAppContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.dsu.dto.model.UserDTO;

/**
 * @author nescafe
 * Test add user in ru locale
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebAppContext.class})
@WebAppConfiguration
public class UserControllerAddMethodTestRu extends UserControllerAddMethodTest {
	
	protected MockHttpServletRequestBuilder postUserDTO(UserDTO dto) throws IOException {
		return post("/api/user")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .locale(new Locale("ru"))
                .content(TestUtil.convertObjectToJsonBytes(dto));
	}
	
	 /* (non-Javadoc)
	 * @see org.dsu.controller.test.user.UserControllerUpdateMethodTest#getLongLengthUserFieldErrorMessages()
	 */
	protected String[] getLongLengthUserFieldErrorMessages() {
	    	return getLongLengthUserFieldErrorMessagesRu();
	 }
	
	/* (non-Javadoc)
	 * @see org.dsu.controller.test.user.UserControllerUpdateMethodTest#getEmptyUserFieldErrorMessages()
	 */
	protected String[] getEmptyUserFieldErrorMessages() {
    	return getEmptyUserFieldErrorMessagesRu();
    }

}
