package com.jvs.studio.spring.data.orm.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private BigDecimal salario;
	private LocalDate dtContratacao;
	@ManyToOne
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;

	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "fk_funcionario") }, inverseJoinColumns = { @JoinColumn(name = "fk_unidade") })
	private List<UnidadeTrabalho> unidadeTrabalhos;

	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, BigDecimal salario, LocalDate dtContratacao) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dtContratacao = dtContratacao;

	}

	public Funcionario(String nome, String cpf, BigDecimal salario, LocalDate dtContratacao, Cargo cargo,
			UnidadeTrabalho unidadeTrabalhos) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.dtContratacao = dtContratacao;
		this.cargo = cargo;
		addUnidadeTrabalhos(unidadeTrabalhos);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public void setDtContratacao(LocalDate dtContratacao) {
		this.dtContratacao = dtContratacao;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
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

	public Cargo getCargo() {
		return cargo;
	}

	public List<UnidadeTrabalho> getUnidadeTrabalhos() {
		return unidadeTrabalhos;
	}

	public void setUnidadeTrabalhos(List<UnidadeTrabalho> unidadeTrabalhos) {
		if (this.unidadeTrabalhos == null)
			this.unidadeTrabalhos = new ArrayList<UnidadeTrabalho>();
		this.unidadeTrabalhos = unidadeTrabalhos;
	}

	public void addUnidadeTrabalhos(UnidadeTrabalho unidadeTrabalho) {
		if (this.unidadeTrabalhos == null)
			this.unidadeTrabalhos = new ArrayList<UnidadeTrabalho>();
		this.unidadeTrabalhos.add(unidadeTrabalho);
	}

	@Override
	public String toString() {
		return String.format(
				"Funcionario [id=%s, nome=%s, cpf=%s, salario=%s, dtContratacao=%s, cargo=%s, unidadeTrabalhos=%s]", id,
				nome, cpf, salario, dtContratacao, cargo, unidadeTrabalhos);
	}

	public void setUnidadeTrabalhos(UnidadeTrabalho unidadeTrabalho) {
		this.unidadeTrabalhos.add(unidadeTrabalho);
	}

}
