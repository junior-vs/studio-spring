package com.jvs.studio.spring.data.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jvs.studio.spring.data.orm.model.Funcionario;

public class FuncionarioVO {

	@NotBlank
	private String nome;

	@NotBlank
	@CPF
	private String cpf;

	@NonNull
	private BigDecimal salario;

	@NonNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dtContratacao;

	private Integer idCargo;

	private Integer idUnidadeTrabalho;

	public FuncionarioVO(@NotBlank String nome, @NotBlank @CPF String cpf, BigDecimal salario, LocalDate dtContratacao,
			Integer idCargo, Integer idUnidadeTrabalho) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dtContratacao = dtContratacao;
		this.idCargo = idCargo;
		this.idUnidadeTrabalho = idUnidadeTrabalho;
	}

	@Override
	public String toString() {
		return "FuncionarioVO [nome=" + nome + ", cpf=" + cpf + ", salario=" + salario + ", dtContratacao="
				+ dtContratacao + ", idCargo=" + idCargo + ", idUnidadeTrabalho=" + idUnidadeTrabalho + "]";
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public LocalDate getDtContratacao() {
		return dtContratacao;
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public Integer getIdUnidadeTrabalho() {
		return idUnidadeTrabalho;
	}

	public Funcionario map() {
		return new Funcionario(nome, cpf, salario, dtContratacao);
	}

}
