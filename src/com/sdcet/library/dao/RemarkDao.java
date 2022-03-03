package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Remark;

public interface RemarkDao {

	/**
	 * ��ȡȫ������
	 * @param bookid
	 * @return
	 */
	public List<Remark> findAll();
	
	/**
	 * ��ȡȫ���������ں�̨
	 * @param bookid
	 * @return
	 */
	public List<Remark> findAll2();
	
	/**
	 * �������
	 * @param bookid
	 */
	public void add(Remark remark);
	
	
	/**
	 * �������۱��ɾ������
	 * @param bookid
	 */
	public void delete(int id);
}
