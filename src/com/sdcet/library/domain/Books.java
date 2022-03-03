package com.sdcet.library.domain;

/**
 * Õº È¿‡
 */
public class Books {

	private Integer id;
	private String name;
	private String author;
	private String publisher;
	private Integer price;
	private Integer stock;
	private Integer borrows;
	private Integer categorieId;

	private Categories categorie;

	public Books() {
	}

	public Books(Integer id, String name, String author, String publisher, Integer price, Integer stock,
			Integer borrows) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.stock = stock;
		this.borrows = borrows;
	}
	
	

	public Books(Integer id, String name, String author, String publisher, Integer price, Integer stock,
			Integer borrows, Integer categorieId) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.stock = stock;
		this.borrows = borrows;
		this.categorieId = categorieId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getBorrows() {
		return borrows;
	}

	public void setBorrows(Integer borrows) {
		this.borrows = borrows;
	}

	public Categories getCategorie() {
		return categorie;
	}

	public void setCategorie(Categories categorie) {
		this.categorie = categorie;
	}

	public Integer getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(Integer categorieId) {
		this.categorieId = categorieId;
	}

}
