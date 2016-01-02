/**
 * 
 */
package com.dsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nescafe
 * Cotroller for getting main jsp for project
 */
@Controller
public class MainFormController {

	@RequestMapping(value = "/MainForm", method = RequestMethod.GET)
    public String getTransmissionForm() {
        return "MainForm";
    }
}
