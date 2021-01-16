package com.jvs.studio.spring.data.vo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jvs.studio.spring.data.orm.model.Cargo;

public class CargoVO {

	@NotBlank
	private String descricao;

	@JsonCreator
	public CargoVO(@NotBlank String descricao) {
		super();
		this.descricao = descricao;
	}

	public CargoVO(Cargo unidadeTrabalho) {
		this(unidadeTrabalho.getDescricao());
	}

	@Override
	public String toString() {
		return "CargoVO [descricao=" + descricao + "]";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Cargo map() {
		return new Cargo(descricao);
	}

}
