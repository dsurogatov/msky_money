package com.dsu.controller.rest;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dsu.controller.util.UriUtil;
import com.dsu.dto.model.UserDTO;
import com.dsu.service.user.UserService;

@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;


	/** Get all users
	 * @return - list of users
	 */
	@RequestMapping(value = {"/api/user"}, method = RequestMethod.GET)
    public List<UserDTO> getUsers() {
		
		return service.findAll();
	}
	
	/** Find users by string value, finding pass through all fields of the user's class
	 * @param findingValue
	 * @return - list of found users
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = {"/api/user/find/{findingValue}"}, method = RequestMethod.GET)
    public List<UserDTO> getUsersFound(@PathVariable("findingValue") String findingValue) {
		LOGGER.debug("Finding value is '{}'", UriUtil.decode(findingValue));
		return service.findByFields(UriUtil.decode(findingValue));
	}
	
	/** Find user by id
	 * @param id
	 * @return UserDTO inst
	 */
	@RequestMapping(value = {"/api/user/{id}"}, method = RequestMethod.GET)
    public UserDTO getUserById(@PathVariable("id") Long id) {
		LOGGER.debug("Id is '{}'", id);
		return service.findById(id);
	}
	
	/** Create a new user
	 * @param dto
	 * @return - created user
	 */
	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public UserDTO add(@Valid @RequestBody UserDTO dto) {
		UserDTO added = service.create(dto);
        return added;
    }
	
	/** Update user
	 * @param dto
	 * @return - updated dto
	 */
	@RequestMapping(value = "/api/user", method = RequestMethod.PUT)
    public UserDTO update(@Valid @RequestBody UserDTO dto) {
		UserDTO updated = service.update(dto);
        return updated;
    }
	
	/** Delete user 
	 * @param id
	 */
	@RequestMapping(value = {"/api/user/{id}"}, method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") Long id) {
		LOGGER.debug("Id is '{}'", id);
		service.delete(id);
	}
}
