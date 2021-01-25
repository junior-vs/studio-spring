package com.vsj.northwind.data.repositories;

import com.vsj.northwind.data.model.Category;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String name);
}
