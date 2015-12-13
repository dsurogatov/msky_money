/**
 * 
 */
package com.dsu.dto.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dsu.dto.api.NamedDTO;

/**
 * @author nescafe DTO for User entity
 */
public class UserDTO extends NamedDTO {

	@NotNull(message="Field login can't be empty")
	@Size(min=1, max=100, message="Field login should be between 1 - 100 characters.")
	private String login;

	@Size(max=32, message="Field password should be less or equal 32 characters.")
	private String password;

	/**
	 * @return login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTO [getName()=" + getName() + ", getId()=" + getId() + ", login=" + login + ", password=" + password + "]";
	}
}
