package com.studio.spring.batch.arquivoposicional.model;

import org.springframework.batch.item.file.transform.Range;

public record Cliente(String nome, String sobrenome, Integer idade, String email) {

    public Cliente(String nome, String sobrenome, Integer idade, String email) {
        this.nome = nome.trim();
        this.sobrenome = sobrenome.trim();
        this.idade = idade;
        this.email = email.trim();
    }


    public static Range[] campos() {
        return new Range[] { new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43) };
    }

    public static String[] headers() {
        return new String[] { "nome", "sobrenome", "idade", "email" };
    }

}
