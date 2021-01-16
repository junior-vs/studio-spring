package com.vsj.northwind.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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

import com.vsj.northwind.services.ProductsService;
import com.vsj.northwind.vos.ProductVO;

/**
 * Create[ok]; Reade (All[ok]/One[ok] /Pagination[]) Update[ok] Delele (
 *
 *
 */

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductsService service;

	@GetMapping
	public ResponseEntity<List<ProductVO>> listaCategoria() {
		List<ProductVO> voList = service.listAll();
		return ResponseEntity.ok(voList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductVO> findByid(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.findByID(id));
	}

	@GetMapping("/by")
	public ResponseEntity<ProductVO> findByid(@RequestParam String name) {
		return ResponseEntity.ok(service.findByName(name));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ProductVO> createCategory(@Valid @RequestBody ProductVO vo) {
		return ResponseEntity.ok(service.create(vo));
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<ProductVO> updateCategory(@Valid @RequestBody ProductVO vo, @PathVariable("id") Integer id) {
		return ResponseEntity.ok(service.update(vo, id));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
