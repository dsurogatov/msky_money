/**
 * 
 */
package com.dsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nescafe
 * Controller for user jsp
 */
@Controller
public class UserFormController {

	@RequestMapping(value = "/UserListForm", method = RequestMethod.GET)
    public String getUserListForm() {
        return "user/UserListForm";
    }
	
	@RequestMapping(value = "/UserEditForm", method = RequestMethod.GET)
    public String getUserEditForm() {
        return "user/UserEditForm";
    }
}
