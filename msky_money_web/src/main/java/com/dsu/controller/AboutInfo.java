package com.dsu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutInfo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AboutInfo.class);
		 

	@RequestMapping("/about")
	public ModelAndView about() {
		LOGGER.info("Hello from About controller");
 
		String version = "0.0.1";
		return new ModelAndView("about", "version", version);
	}
}
