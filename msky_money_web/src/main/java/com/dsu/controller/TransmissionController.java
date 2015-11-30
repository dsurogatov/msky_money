/**
 * 
 */
package com.dsu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsu.domain.model.Transmission;
import com.dsu.service.transmission.TransmissionService;

/**
 * @author nescafe
 *
 */
@RestController
public class TransmissionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TransmissionController.class);
	
	@Autowired
	private TransmissionService service;

	@RequestMapping(value = {"/transmission"}, method = RequestMethod.GET)
    public List<Transmission> getTransmissions() {
		LOGGER.debug("Transmission controller");
		
		return service.getTransmissions();
	}
	
}
