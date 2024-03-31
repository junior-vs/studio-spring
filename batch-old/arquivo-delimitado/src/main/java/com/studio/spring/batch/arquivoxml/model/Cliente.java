package com.studio.spring.batch.arquivoxml.model;

import org.springframework.batch.item.file.transform.Range;

public record Cliente(String nome, String sobrenome, Integer idade, String email) {

    public static Range[] campos() {
        return new Range[] { new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43) };
    }

    public static String[] headers() {
        return new String[] { "nome", "sobrenome", "idade", "email" };
    }

}
