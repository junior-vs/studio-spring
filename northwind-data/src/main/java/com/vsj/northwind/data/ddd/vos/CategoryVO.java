package com.vsj.northwind.data.ddd.vos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vsj.northwind.data.model.Category;

public class CategoryVO {

	@NotBlank
	private String categoryName;

	@NotBlank
	private String description;

	@JsonCreator
	public CategoryVO(@NotBlank @JsonProperty("categoryName") String categoryName,
			@NotBlank @JsonProperty("description") String description) {
		this.categoryName = categoryName;
		this.description = description;
	}

	public CategoryVO(Category category) {
		this.categoryName = category.getCategoryName();
		this.description = category.getDescription();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getDescription() {
		return description;
	}

	public Category map() {
		return new Category(this.categoryName, this.description);
	}

	@Override
	public String toString() {
		return String.format("CategoryVO [categoryName=%s, description=%s]", categoryName, description);
	}

}
