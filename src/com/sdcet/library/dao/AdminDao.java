package com.sdcet.library.dao;

import com.sdcet.library.domain.Admins;

public interface AdminDao {

	/**
	 * �����û�������������û�
	 */
	public boolean hasMatchUser(String name, String password);
	
	/**
	 * ���ݵ�½�������û�
	 */
	public Admins findByLoginName(String name);
}
