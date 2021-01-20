package com.jvs.studio.spring.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.jvs.studio.spring.data.orm.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

	Funcionario findByNome(String nome);

}
