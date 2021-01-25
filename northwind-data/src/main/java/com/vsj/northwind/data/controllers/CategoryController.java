package com.vsj.northwind.data.controllers;

import com.vsj.northwind.data.ddd.vos.CategoryVO;
import com.vsj.northwind.data.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.List;

/**
 * Create[ok]; Reade (All[ok]/One[ok] /Pagination[]) Update[ok] Delele (
 *
 *
 */

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private CategoriesService service;

	@GetMapping
	public ResponseEntity<List<CategoryVO>> listaCategoria() {
		List<CategoryVO> voList = service.listAll();
		return ResponseEntity.ok(voList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryVO> findByid(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.findByID(id));
	}

	@GetMapping("/by")
	public ResponseEntity<CategoryVO> findByid(@RequestParam String name) {
		return ResponseEntity.ok(service.findByName(name));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<CategoryVO> createCategory(@Valid @RequestBody CategoryVO category) {
		return ResponseEntity.ok(service.createCategory(category));
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<CategoryVO> updateCategory(@Valid @RequestBody CategoryVO category,
			@PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.updateCategory(category, id));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
		service.deleteCategory(id);
		return ResponseEntity.ok().build();
	}

}
