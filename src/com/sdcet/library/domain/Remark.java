package com.sdcet.library.domain;

public class Remark {

	private Integer id;
	private String content;
	private String author;
	private String date;

	public Remark() {
	}

	public Remark(String content, String author, String date) {
		this.content = content;
		this.author = author;
		this.date = date;
	}

	public Remark(Integer id, String content, String author, String date) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
