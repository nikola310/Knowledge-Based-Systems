package com.sbnz.model;
// Generated Jul 21, 2018 12:49:21 PM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "sbnz")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId;
	private String userName;
	private String userSurname;
	private String userUsername;
	private char userType;
	private Set<Patient> patients = new HashSet<Patient>(0);

	public User() {
	}

	public User(long userId, String userName, String userSurname, String userUsername, char userType) {
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userUsername = userUsername;
		this.userType = userType;
	}

	public User(long userId, String userName, String userSurname, String userUsername, char userType,
			Set<Patient> patients) {
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.userUsername = userUsername;
		this.userType = userType;
		this.patients = patients;
	}

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@Column(name = "USER_ID", unique = true, nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", nullable = false, length = 100)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "USER_SURNAME", nullable = false, length = 200)
	public String getUserSurname() {
		return this.userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	@Column(name = "USER_USERNAME", nullable = false, length = 150)
	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	@Column(name = "USER_TYPE", nullable = false, length = 1)
	public char getUserType() {
		return this.userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

}