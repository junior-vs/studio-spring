package com.jvs.studio.spring.data.specification;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.jvs.studio.spring.data.orm.model.Funcionario;

public class FuncionarioSpecification {
	
	private FuncionarioSpecification() {
		// TODO Auto-generated constructor stub
	}

	public static Specification<Funcionario> nome(String nome) {

		return (root, criteriaQuerry, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}

	public static Specification<Funcionario> cpf(String cpf) {

		return (root, criteriaQuerry, criteriaBuilder) -> criteriaBuilder.equal(root.get("cpf"), cpf);
	}

	public static Specification<Funcionario> salario(BigDecimal salario) {

		return (root, criteriaQuerry, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("salario"), salario);
	}

	public static Specification<Funcionario> nome(LocalDate dtContratacao) {

		return (root, criteriaQuerry, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dtContratacao"),
				dtContratacao);
	}

}
