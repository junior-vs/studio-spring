package com.jvs.studio.spring.data.vo.requests;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jvs.studio.spring.data.orm.model.UnidadeTrabalho;

public class UnidadeTrabalhoVO {

	@NotBlank
	private String descricao;
	@NotBlank
	private String endereco;

	@JsonCreator
	public UnidadeTrabalhoVO(@NotBlank String descricao, @NotBlank String endereco) {
		super();
		this.descricao = descricao;
		this.endereco = endereco;
	}

	public UnidadeTrabalhoVO(UnidadeTrabalho unidadeTrabalho) {
		this(unidadeTrabalho.getDescricao(), unidadeTrabalho.getEndereco());
	}

	public String getDescricao() {
		return descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return "UnidadeTrabalhoRequestForm [descricao=" + descricao + ", endereco=" + endereco + "]";
	}

	public UnidadeTrabalho map() {
		return new UnidadeTrabalho(descricao, endereco);
	}

}
