package com.sdcet.library.dao;

import com.sdcet.library.domain.Code;

public interface CodeDao {

	/**
	 * 判断借阅码是否过期，过期则销毁
	 */
	public void destroy();
	
	/**
	 * 生成借阅码
	 */
	public Code add(int bookid,int userid);
	
	/**
	 * 查找借阅码是否存在
	 */
	public boolean hasMatchCode(String id);
}
