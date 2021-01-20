package com.jvs.studio.spring.data.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.jvs.studio.spring.data.orm.model.UnidadeTrabalho;
import com.jvs.studio.spring.data.services.UnidadeTrabalhoService;
import com.jvs.studio.spring.data.vo.UnidadeTrabalhoVO;

@RestController
@RequestMapping("/unidade-trabalho")
public class UnidadeTrabalhoController {

	@Autowired
	private UnidadeTrabalhoService service;

	@PostMapping
	@Transactional
	public ResponseEntity<UnidadeTrabalhoVO> criarNovo(@RequestBody UnidadeTrabalhoVO unidade) {

		UnidadeTrabalho unidadeTrabalho = unidade.map();
		service.salvar(unidadeTrabalho);
		UnidadeTrabalhoVO response = new UnidadeTrabalhoVO(unidadeTrabalho);
		return ResponseEntity.ok(response);
	}

	@GetMapping("{id}")
	public ResponseEntity<UnidadeTrabalhoVO> buscaPorID(@PathVariable("id") Integer id) {
		UnidadeTrabalhoVO response = new UnidadeTrabalhoVO(service.buscaBy(id));
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<UnidadeTrabalhoVO>> listar() {
		List<UnidadeTrabalhoVO> collect = service.listar().stream().map(UnidadeTrabalhoVO::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(collect);
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<UnidadeTrabalhoVO> atualizar(@PathVariable("id") Integer id,@RequestBody UnidadeTrabalhoVO unidade ) {
		UnidadeTrabalhoVO response = new UnidadeTrabalhoVO(service.update(id, unidade));
		return ResponseEntity.ok(response);
	}
	
	
}
