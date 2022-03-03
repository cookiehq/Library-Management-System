package com.sdcet.library.utils;

/**
 * 常量类
 */
public class Constans {

	/**
	 * 分页中每页显示的记录数
	 */
	public static final int PAGE_SIZE = 10;
	
	/**
	 * 生成随机数的个数
	 */
	public static final int RANDOM_SIZE = 12;
	
	/** 加密盐 */
	public static final String SALT = "library.salt"; 
	
	/** 邮件内容 */
	public static final String HTMLCONS = "Email地址验证<br/>"+ 
			"这封邮件是由【图书馆】发送的。<br/>"+
			"你收到这封邮件是【图书馆】进行新用户注册使用。<br/>"+
			"账号激活声明<br/>"+
			"请将下面的验证码输入到提示框即可：";
}
