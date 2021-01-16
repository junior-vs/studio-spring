package com.jvs.studio.spring.data.orm.model;

import static javax.persistence.GenerationType.IDENTITY;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class UnidadeTrabalho {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Integer id;
  private String descricao;
  private String endereco;

  @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
  private List<Funcionario> funcionarios;

  public UnidadeTrabalho() {
    //  Auto-generated constructor stub
  }

  public UnidadeTrabalho(String descricao, String endereco) {
    super();
    this.descricao = descricao;
    this.endereco = endereco;
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

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }

  public void setFuncionarios(List<Funcionario> funcionarios) {
    this.funcionarios = funcionarios;
  }



}
