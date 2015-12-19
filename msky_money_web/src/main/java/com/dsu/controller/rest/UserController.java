package com.dsu.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsu.dto.model.UserDTO;
import com.dsu.service.user.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;


	/** Get all users
	 * @return - list of users
	 */
	@RequestMapping(value = {"/api/user"}, method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
		
		return service.findAll();
	}
}
