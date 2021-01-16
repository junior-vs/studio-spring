package com.vsj.northwind.data.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsj.northwind.data.model.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String name);
}
