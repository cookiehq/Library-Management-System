package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Users;

public interface UserDao {

	/**
	 * 用户注册
	 * @param user
	 */
	public void add(Users user);
	
	/**
	 * 用户信息修改
	 * @param user
	 */
	public void update(Users user);
	
	/**
	 * 获取所有用户
	 */
	public List<Users> findAll();
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 根据用户名和密码查找用户
	 */
	public boolean hasMatchUser(String name, String password);
	
	/**
	 * 根据登陆名查找用户
	 */
	public Users findByLoginName(String name);
}
