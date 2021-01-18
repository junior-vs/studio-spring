package com.jvs.studio.spring.data.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvs.studio.spring.data.orm.model.Cargo;
import com.jvs.studio.spring.data.orm.model.Funcionario;
import com.jvs.studio.spring.data.orm.model.UnidadeTrabalho;
import com.jvs.studio.spring.data.repositories.FuncionarioRepository;
import com.jvs.studio.spring.data.vo.FuncionarioVO;
import com.jvs.studio.spring.data.vo.FuncionarioVOResponse;

import javassist.NotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	@PersistenceContext
	private EntityManager manager;

	private static final Logger log = LoggerFactory.getLogger(FuncionarioService.class);

	public FuncionarioVOResponse buscarPorId(Integer id) {
		return new FuncionarioVOResponse(repository.findById(id).orElseThrow());
	}

	public List<FuncionarioVOResponse> listar() {
		return repository.findAll().stream().map(FuncionarioVOResponse::new).collect(Collectors.toList());

	}

	public FuncionarioVOResponse atualizar(Integer id, FuncionarioVO vo) {
		Funcionario found = repository.findById(id).orElseThrow();

		Cargo cargo = manager.find(Cargo.class, vo.getIdCargo());
		UnidadeTrabalho unidadeTrabalho = manager.find(UnidadeTrabalho.class, vo.getIdUnidadeTrabalho());

		if (cargo == null || unidadeTrabalho == null)
			throw new NoSuchElementException("Cargo ou Unidade de Tralbalho não encontado");

		found.setNome(vo.getNome());
		found.setCargo(cargo);
		found.setSalario(vo.getSalario());
		found.setCargo(cargo);
		found.setUnidadeTrabalhos(unidadeTrabalho);

		repository.save(found);
		return new FuncionarioVOResponse(found);

	}

	public FuncionarioVOResponse salvar(FuncionarioVO vo) {
		Cargo cargo = manager.find(Cargo.class, vo.getIdCargo());
		UnidadeTrabalho unidadeTrabalho = manager.find(UnidadeTrabalho.class, vo.getIdUnidadeTrabalho());

		Funcionario novoFuncionario = new Funcionario(vo.getNome(), vo.getCpf(), vo.getSalario(), vo.getDtContratacao(),
				cargo, unidadeTrabalho);

		repository.save(novoFuncionario);

		return new FuncionarioVOResponse(novoFuncionario);
	}
}
