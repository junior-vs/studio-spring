package com.jvs.studio.spring.data.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.jvs.studio.spring.data.orm.model.Funcionario;

public class FuncionarioVOResponse {

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

	private CargoVO cargo;

	private List<UnidadeTrabalhoVO> unidadesTrabalho;

	public FuncionarioVOResponse(@NotBlank String nome, @NotBlank @CPF String cpf, BigDecimal salario,
			LocalDate dtContratacao, CargoVO cargo, List<UnidadeTrabalhoVO> unidadesTrabalho) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dtContratacao = dtContratacao;
		this.cargo = cargo;
		this.unidadesTrabalho = unidadesTrabalho;
	}
	
	public FuncionarioVOResponse(Funcionario funcionario) {
		
		this.nome = funcionario.getNome();
		this.cpf =  funcionario.getCpf();
		this.salario =  funcionario.getSalario();
		this.dtContratacao =  funcionario.getDtContratacao();
		this.cargo = new CargoVO(funcionario.getCargo());
		this.unidadesTrabalho = funcionario.getUnidadeTrabalhos().stream()
				.map(UnidadeTrabalhoVO::new).collect(Collectors.toList());;
	}

	@Override
	public String toString() {
		return "FuncionarioVOResponse [nome=" + nome + ", cpf=" + cpf + ", salario=" + salario + ", dtContratacao="
				+ dtContratacao + ", cargo=" + cargo + ", unidadesTrabalho=" + unidadesTrabalho + "]";
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

	public CargoVO getCargo() {
		return cargo;
	}

	public List<UnidadeTrabalhoVO> getUnidadesTrabalho() {
		return unidadesTrabalho;
	}

}
