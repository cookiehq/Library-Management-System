package com.sdcet.library.dao;

import com.sdcet.library.domain.Code;

public interface CodeDao {

	/**
	 * �жϽ������Ƿ���ڣ�����������
	 */
	public void destroy();
	
	/**
	 * ���ɽ�����
	 */
	public Code add(int bookid,int userid);
	
	/**
	 * ���ҽ������Ƿ����
	 */
	public boolean hasMatchCode(String id);
}
