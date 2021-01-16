package com.jvs.studio.spring.data.orm.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Cargo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String descricao;

  @OneToMany(mappedBy = "cargo")
  private List<Funcionario> funcionarios;

  public Cargo() {
    // TODO Auto-generated constructor stub
  }


  public Cargo(String descricao) {
    super();
    this.descricao = descricao;
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }


  @Override
  public String toString() {
    return String.format("Cargo [id=%s, descricao=%s]", id, descricao);
  }


}
