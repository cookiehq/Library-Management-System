package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Record;

public interface RecordDao {

	
	/**
	 *��ӽ��ļ�¼
	 * @return
	 */
	public void add(int bookid,int userid);
	
	/**
	 *��ӽ��ļ�¼
	 * @return
	 */
	public void addBorrow(int bookid);
	
	/**
	 *���½��ļ�¼ ����
	 * @return
	 */
	public void returnbook(int bookid,int userid);
	
	/**
	 *��ȡδ���ڵĽ��ļ�¼
	 * @return
	 */
	public List<Record> unexpired();
	
	/**
	 *��ȡ�������ڵĽ��ļ�¼
	 * @return
	 */
	public List<Record> soonToExpire();
	
	/**
	 *��ȡ���ڵĽ��ļ�¼
	 * @return
	 */
	public List<Record> overdue();
	
	/**
	 *��ȡĳ�����ߵĽ��ļ�¼
	 * @return
	 */
	public List<Record> findByUserId(int id);
	
	/**
	 *��ȡȫ�����ļ�¼
	 * @return
	 */
	public List<Record> findAll();
}
