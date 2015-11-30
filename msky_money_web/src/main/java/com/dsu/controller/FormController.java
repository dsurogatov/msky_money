/**
 * 
 */
package com.dsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nescafe
 *
 */
@Controller
public class FormController {

	@RequestMapping(value = "/TransmissionForm", method = RequestMethod.GET)
    public String getTransmissionForm() {
        return "TransmissionForm";
    }
}
