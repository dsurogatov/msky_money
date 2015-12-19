/**
 * 
 */
package com.dsu.dto.builder;

import com.dsu.dto.model.UserDTO;

/**
 * @author nescafe Builder pattern fro creating UserDTo's. For testing purpose.
 */
public class UserDTOBuilder {

	private Long id;
	private String login;
	private String name;
	private String password;

	public UserDTOBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public UserDTOBuilder login(String login) {
		this.login = login;
		return this;
	}

	public UserDTOBuilder name(String name) {
		this.name = name;
		return this;
	}

	public UserDTOBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public UserDTO build() {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setLogin(login);
		dto.setPassword(password);
		return dto;
	}
}
