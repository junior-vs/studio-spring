package com.vsj.northwind.data.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id", unique = true, nullable = false)
	@SequenceGenerator(name = "CATEGORY_SEQ_GENERATOR", sequenceName = "CATEGORY_SEQ_GENERATOR0", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ_GENERATOR")
	private Integer categoryId;

	@Column(name = "category_name", nullable = false, length = 15)
	private String categoryName;

	@Column(length = 2147483647)
	private String description;

	private byte[] picture;

	// bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> products;

	public Category() {
	}

	public Category(String categoryName, String description) {
		super();
		this.categoryName = categoryName;
		this.description = description;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

	@Override
	public String toString() {
		return String.format("Category [categoryId=%s, categoryName=%s, description=%s]", categoryId, categoryName,
				description);
	}

}