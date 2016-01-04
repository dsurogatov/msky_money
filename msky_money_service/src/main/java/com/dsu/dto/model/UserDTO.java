/**
 * 
 */
package com.dsu.dto.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.dsu.dto.api.BaseNamedDTO;

/**
 * @author nescafe DTO for User entity
 */
public class UserDTO extends BaseNamedDTO {

	@NotEmpty(message="validation.notempty.field")
	@Size(max=100, message="validation.maxsize.field")
	private String login;

	@Size(max=100, message="validation.maxsize.field")
	private String password;
	private String hashedPassword;

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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
}
