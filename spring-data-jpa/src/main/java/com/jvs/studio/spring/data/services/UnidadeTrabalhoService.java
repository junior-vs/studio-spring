package com.jvs.studio.spring.data.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvs.studio.spring.data.orm.model.UnidadeTrabalho;
import com.jvs.studio.spring.data.repositories.UnidadeTrabalhoRepository;
import com.jvs.studio.spring.data.vo.UnidadeTrabalhoVO;

@Service
public class UnidadeTrabalhoService {

	@Autowired
	private UnidadeTrabalhoRepository repository;

	public List<UnidadeTrabalho> listar() {
		return repository.findAll();

	}

	public void salvar(UnidadeTrabalho unidadeTrabalho) {
		repository.save(unidadeTrabalho);
	}

	public UnidadeTrabalho buscaBy(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	public UnidadeTrabalho update(Integer id, UnidadeTrabalhoVO unidade) {
		UnidadeTrabalho found = buscaBy(id);
		found.setDescricao(unidade.getDescricao());
		found.setEndereco(unidade.getEndereco());
		repository.save(found);
		return found;
	}
}
