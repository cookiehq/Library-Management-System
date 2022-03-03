package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Record;

public interface RecordDao {

	
	/**
	 *添加借阅记录
	 * @return
	 */
	public void add(int bookid,int userid);
	
	/**
	 *添加借阅记录
	 * @return
	 */
	public void addBorrow(int bookid);
	
	/**
	 *更新借阅记录 还书
	 * @return
	 */
	public void returnbook(int bookid,int userid);
	
	/**
	 *获取未过期的借阅记录
	 * @return
	 */
	public List<Record> unexpired();
	
	/**
	 *获取即将过期的借阅记录
	 * @return
	 */
	public List<Record> soonToExpire();
	
	/**
	 *获取过期的借阅记录
	 * @return
	 */
	public List<Record> overdue();
	
	/**
	 *获取某个读者的借阅记录
	 * @return
	 */
	public List<Record> findByUserId(int id);
	
	/**
	 *获取全部借阅记录
	 * @return
	 */
	public List<Record> findAll();
}
