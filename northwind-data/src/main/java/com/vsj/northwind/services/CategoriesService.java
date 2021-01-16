package com.vsj.northwind.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsj.northwind.data.model.Category;
import com.vsj.northwind.data.repositories.CategoriesRepository;
import com.vsj.northwind.vos.CategoryVO;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository repository;

	private static final Logger log = LoggerFactory.getLogger(CategoriesService.class);

	public List<CategoryVO> listAll() {

		List<Category> listAll = (List<Category>) repository.findAll();
		return listAll.stream().map(CategoryVO::new).collect(Collectors.toList());

	}

	public CategoryVO findByID(final Integer id) {
		Optional<Category> findById = repository.findById(id);
		return new CategoryVO(findById.orElseThrow());
	}

	public CategoryVO findByName(final String name) {
		Optional<Category> findByName = repository.findByCategoryName(name);
		return new CategoryVO(findByName.orElseThrow());
	}

	public CategoryVO createCategory(@Valid final CategoryVO category) {
		log.info("{}", category);
		Category entity = category.map();
		repository.save(entity);
		log.info("{}", entity);
		return new CategoryVO(entity);
	}

	public CategoryVO updateCategory(@Valid final CategoryVO category, final Integer id) {

		Category found = repository.findById(id).orElseThrow();
		found.setCategoryName(category.getCategoryName());
		found.setDescription(category.getDescription());
		return new CategoryVO(found);
	}

	public void deleteCategory(Integer id) {
		repository.deleteById(id);

	}

}
