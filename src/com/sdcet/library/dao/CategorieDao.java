package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Categories;
import com.sdcet.library.utils.PageBean;


public interface CategorieDao {

	/**
	 * 获取所有图书分类
	 */
	public List<Categories> findAll();
	/**
	 * 获取所有图书分类(分页)
	 */
	public PageBean<Categories> findAllByPage(int page);
	
	/**
	 * 修改分类信息
	 */
	public void upate(Categories categories);
	
	/**
	 * 删除分类
	 */
	public void delete(int id);
	
	/**
	 * 添加分类
	 */
	public void add(Categories categories);
	
	/**
	 * 根据Id查找分类
	 */
	public Categories findById(int id);
	
	/**
	 * 根据Id查找分类
	 */
	public List<Categories> findByName(String name);
}
