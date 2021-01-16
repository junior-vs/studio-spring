package com.jvs.studio.spring.data.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jvs.studio.spring.data.orm.model.Cargo;
import com.jvs.studio.spring.data.repositories.CargoRepository;
import com.jvs.studio.spring.data.vo.CargoVO;

@Service
public class CargoService {

	private static final Logger log = LoggerFactory.getLogger(CargoService.class);

	@Autowired
	private CargoRepository repository;

	public Cargo buscarPorId(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	public List<CargoVO> listar() {
		return repository.findAll().stream().map(CargoVO::new).collect(Collectors.toList());
	}

	public CargoVO atualizar(Integer id, CargoVO cargo) {
		Cargo found = buscarPorId(id);
		found.setDescricao(cargo.getDescricao());
		repository.save(found);
		return new CargoVO(found);
	}

	public void salvar(Cargo cargo) {
		repository.save(cargo);
	}

}
