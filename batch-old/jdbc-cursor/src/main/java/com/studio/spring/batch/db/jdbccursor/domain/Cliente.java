package com.studio.spring.batch.db.jdbccursor.domain;

public class Cliente {
    private String nome;
    private String sobrenome;
    private String idade;
    private String email;

    public Cliente() {
    }

    public Cliente(String nome, String sobrenome, String idade, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cliente{");
        sb.append("nome='").append(nome).append('\'');
        sb.append(", sobrenome='").append(sobrenome).append('\'');
        sb.append(", idade='").append(idade).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
