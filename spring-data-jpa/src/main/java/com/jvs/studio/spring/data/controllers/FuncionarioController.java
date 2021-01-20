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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jvs.studio.spring.data.services.FuncionarioService;
import com.jvs.studio.spring.data.vo.FuncionarioVO;
import com.jvs.studio.spring.data.vo.FuncionarioVOResponse;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@PostMapping
	@Transactional
	public ResponseEntity<FuncionarioVOResponse> criarNovo(@RequestBody FuncionarioVO funcionarioVO) {

		FuncionarioVOResponse response = service.salvar(funcionarioVO);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioVOResponse> buscaPorID(@PathVariable("id") Integer id) {
		FuncionarioVOResponse response = service.buscarPorId(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/busca")
	public ResponseEntity<List<FuncionarioVOResponse>> buscaPorNome(@RequestParam String nome) {
		List<FuncionarioVOResponse> response = service.buscarPorNome(nome);
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<FuncionarioVOResponse>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<FuncionarioVOResponse> atualizar(@PathVariable("id") Integer id,
			@RequestBody FuncionarioVO cargo) {
		return ResponseEntity.ok(service.atualizar(id, cargo));
	}

}
