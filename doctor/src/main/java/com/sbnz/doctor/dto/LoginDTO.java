/**
 * 
 */
package com.sbnz.doctor.dto;

import javax.validation.constraints.NotNull;

/**
 * @author Nikola
 *
 */
public class LoginDTO {

	@NotNull
	private String username;
	@NotNull
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
