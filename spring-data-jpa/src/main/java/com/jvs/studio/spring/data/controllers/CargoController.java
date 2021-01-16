package com.jvs.studio.spring.data.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvs.studio.spring.data.orm.model.Cargo;
import com.jvs.studio.spring.data.services.CargoService;
import com.jvs.studio.spring.data.vo.CargoVO;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<CargoVO> criarNovo(@RequestBody CargoVO unidade) {

		Cargo unidadeTrabalho = unidade.map();
		service.salvar(unidadeTrabalho);
		CargoVO response = new CargoVO(unidadeTrabalho);
		return ResponseEntity.ok(response);
	}

	@GetMapping("{id}")
	public ResponseEntity<CargoVO> buscaPorID(@PathVariable("id") Integer id) {
		CargoVO response = new CargoVO(service.buscarPorId(id));
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<CargoVO>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<CargoVO> atualizar(@PathVariable("id") Integer id, @RequestBody CargoVO cargo) {
		return ResponseEntity.ok(service.atualizar(id, cargo));
	}

}
