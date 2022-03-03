package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Books;
import com.sdcet.library.utils.PageBean;


public interface BooksDao {

	/**
	 * 根据书名查找图书
	 */
	public List<Books> findByName(String name);
	
	/**
	 * 根据借阅次数查找图书
	 */
	public List<Books> findByBorrows();

	/**
	 * 根据Id查找图书
	 */
	public Books findById(int id);

	/**
	 * 获取指定分类下的所有图书(分页)
	 */
	public List<Books> findByCategorieId(int categorieId);

	/**
	 * 获取所有图书
	 */
	public List<Books> findAll();
	
	/**
	 * 获取所有图书(分页)
	 */
	public PageBean<Books> findAllByPage(int page);

	/**
	 * 修改图书
	 */
	public void update(Books books);

	/**
	 * 删除图书
	 */
	public void delete(int id);

	/**
	 * 添加图书
	 */
	public void add(Books books);
}
