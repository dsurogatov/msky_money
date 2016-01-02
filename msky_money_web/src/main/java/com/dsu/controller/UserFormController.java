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

	@RequestMapping(value = "/UserForm", method = RequestMethod.GET)
    public String getTransmissionForm() {
        return "user/UserForm";
    }
}
