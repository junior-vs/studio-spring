package com.vsj.northwind.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsj.northwind.data.model.Product;
import com.vsj.northwind.data.repositories.ProductRepository;
import com.vsj.northwind.vos.ProductVO;

@Service
public class ProductsService {

	@Autowired
	private ProductRepository repository;

	private static final Logger log = LoggerFactory.getLogger(ProductsService.class);

	public List<ProductVO> listAll() {
		List<Product> listAll = (List<Product>) repository.findAll();
		return listAll.stream().map(ProductVO::new).collect(Collectors.toList());
	}

	public ProductVO findByID(Integer id) {
		Optional<Product> findById = repository.findById(id);
		return new ProductVO(findById.orElseThrow());
	}

	public ProductVO findByName(String name) {
		Optional<Product> findByName = repository.findByProductName(name);
		return new ProductVO(findByName.orElseThrow());
	}

	public ProductVO create(@Valid ProductVO vo) {
		log.info("{}", vo);
		Product entity = vo.map();
		repository.save(entity);
		log.info("{}", entity);
		return new ProductVO(entity);
	}

	public ProductVO update(@Valid ProductVO vo, Integer id) {
		Product found = repository.findById(id).orElseThrow();
		return new ProductVO(found);
	}

	public void delete(Integer id) {
		repository.deleteById(id);

	}

}
