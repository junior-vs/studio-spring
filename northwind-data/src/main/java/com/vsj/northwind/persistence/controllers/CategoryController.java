package com.vsj.northwind.persistence.controllers;

import com.vsj.northwind.persistence.ddd.vos.CategoryVO;
import com.vsj.northwind.persistence.model.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public ResponseEntity<List<CategoryVO>> listaCategoria(){

        TypedQuery<Category> namedQuery = manager.createNamedQuery("Category.findAll", Category.class);
        List<Category> resultList = namedQuery.getResultList();
        List<CategoryVO> voList = resultList.stream().map(CategoryVO::new).collect(Collectors.toList());

        return ResponseEntity.ok(voList);
    }
}
