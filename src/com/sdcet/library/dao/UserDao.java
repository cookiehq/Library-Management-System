package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Users;

public interface UserDao {

	/**
	 * �û�ע��
	 * @param user
	 */
	public void add(Users user);
	
	/**
	 * �û���Ϣ�޸�
	 * @param user
	 */
	public void update(Users user);
	
	/**
	 * ��ȡ�����û�
	 */
	public List<Users> findAll();
	
	/**
	 * ɾ���û�
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * �����û�������������û�
	 */
	public boolean hasMatchUser(String name, String password);
	
	/**
	 * ���ݵ�½�������û�
	 */
	public Users findByLoginName(String name);
}
