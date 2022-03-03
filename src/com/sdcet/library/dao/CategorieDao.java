package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Categories;
import com.sdcet.library.utils.PageBean;


public interface CategorieDao {

	/**
	 * ��ȡ����ͼ�����
	 */
	public List<Categories> findAll();
	/**
	 * ��ȡ����ͼ�����(��ҳ)
	 */
	public PageBean<Categories> findAllByPage(int page);
	
	/**
	 * �޸ķ�����Ϣ
	 */
	public void upate(Categories categories);
	
	/**
	 * ɾ������
	 */
	public void delete(int id);
	
	/**
	 * ��ӷ���
	 */
	public void add(Categories categories);
	
	/**
	 * ����Id���ҷ���
	 */
	public Categories findById(int id);
	
	/**
	 * ����Id���ҷ���
	 */
	public List<Categories> findByName(String name);
}
