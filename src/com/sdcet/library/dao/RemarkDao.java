package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Remark;

public interface RemarkDao {

	/**
	 * 获取全部评论
	 * @param bookid
	 * @return
	 */
	public List<Remark> findAll();
	
	/**
	 * 获取全部评论用于后台
	 * @param bookid
	 * @return
	 */
	public List<Remark> findAll2();
	
	/**
	 * 添加评论
	 * @param bookid
	 */
	public void add(Remark remark);
	
	
	/**
	 * 根据评论编号删除评论
	 * @param bookid
	 */
	public void delete(int id);
}
