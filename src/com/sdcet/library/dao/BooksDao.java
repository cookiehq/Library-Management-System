package com.sdcet.library.dao;

import java.util.List;

import com.sdcet.library.domain.Books;
import com.sdcet.library.utils.PageBean;


public interface BooksDao {

	/**
	 * ������������ͼ��
	 */
	public List<Books> findByName(String name);
	
	/**
	 * ���ݽ��Ĵ�������ͼ��
	 */
	public List<Books> findByBorrows();

	/**
	 * ����Id����ͼ��
	 */
	public Books findById(int id);

	/**
	 * ��ȡָ�������µ�����ͼ��(��ҳ)
	 */
	public List<Books> findByCategorieId(int categorieId);

	/**
	 * ��ȡ����ͼ��
	 */
	public List<Books> findAll();
	
	/**
	 * ��ȡ����ͼ��(��ҳ)
	 */
	public PageBean<Books> findAllByPage(int page);

	/**
	 * �޸�ͼ��
	 */
	public void update(Books books);

	/**
	 * ɾ��ͼ��
	 */
	public void delete(int id);

	/**
	 * ���ͼ��
	 */
	public void add(Books books);
}
