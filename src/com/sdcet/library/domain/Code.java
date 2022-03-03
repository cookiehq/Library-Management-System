package com.sdcet.library.domain;

public class Code {
	private String id;
	private Integer bookid;
	private Integer userid;
	private String ndate;
	private String mdate;

	public Code() {
	}

	
	public Code(String id, Integer bookid, Integer userid, String ndate, String mdate) {
		this.id = id;
		this.bookid = bookid;
		this.userid = userid;
		this.ndate = ndate;
		this.mdate = mdate;
	}


	public String getId() {
		return id;
	}

	public Integer getBookid() {
		return bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getNdate() {
		return ndate;
	}

	public void setNdate(String ndate) {
		this.ndate = ndate;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

}
