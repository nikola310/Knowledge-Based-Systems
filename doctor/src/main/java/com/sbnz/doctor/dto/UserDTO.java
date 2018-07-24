/**
 * 
 */
package com.sbnz.doctor.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Nikola
 *
 */
public class UserDTO {
	private long userId;
	@NotNull
	@Size(max = 100)
	private String userName;
	@NotNull
	@Size(max = 200)
	private String userSurname;
	@NotNull
	@Size(max = 150)
	private String userUsername;
	@NotNull
	private char userType;
	@NotNull
	private String userPassword;

	public UserDTO() {
	}

	public UserDTO(long userId, String userName, String userSurname, String userUsername, char userType) {
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userUsername = userUsername;
		this.userType = userType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
