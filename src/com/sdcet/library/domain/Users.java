package com.sdcet.library.domain;

public class Users {

	private Integer id;
	private String loginName;
	private String password;
	private String name;
	private String gender;
	private String phone;

	public Users() {
	}

	public Users(Integer id, String loginName, String name, String gender, String phone) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
	}

	public Users(Integer id, String loginName, String password, String name, String gender, String phone) {
		this.id = id;
		this.loginName = loginName;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
