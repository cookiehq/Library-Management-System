package com.sdcet.library.dao;

import com.sdcet.library.domain.Admins;

public interface AdminDao {

	/**
	 * 根据用户名和密码查找用户
	 */
	public boolean hasMatchUser(String name, String password);
	
	/**
	 * 根据登陆名查找用户
	 */
	public Admins findByLoginName(String name);
}
