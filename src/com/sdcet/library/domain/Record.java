package com.sdcet.library.domain;

public class Record {
	private Integer bookid;
	private Integer userid;
	private String ndate;
	private String mdate;
	private String state;

	public Record() {
	}

	public Record(Integer bookid, Integer userid, String ndate, String mdate, String state) {
		this.bookid = bookid;
		this.userid = userid;
		this.ndate = ndate;
		this.mdate = mdate;
		this.state = state;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}