package com.vsj.northwind.persistence.ddd.vos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.vsj.northwind.persistence.model.Category;
import com.vsj.northwind.persistence.model.Product;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class CategoryVO {

    private String categoryName;

    private String description;

    @JsonCreator
    public CategoryVO(String categoryName, String description) {
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
}
