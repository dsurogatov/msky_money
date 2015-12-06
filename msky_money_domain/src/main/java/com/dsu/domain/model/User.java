/**
 * 
 */
package com.dsu.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.dsu.domain.api.NamedEntity;

/**
 * @author nescafe
 *
 */
@Entity
@Table(name="tbl_user")
public class User extends NamedEntity {

	@Column(name = "s_login", length = 100, unique = true, nullable = false)
	private String login;
	@Column(name = "s_password", length = 32, updatable = false, nullable = false)
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
