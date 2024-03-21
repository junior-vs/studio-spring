package com.studio.spring.batch.arquivoposicional.model;

import org.springframework.batch.item.file.transform.Range;

public record Cliente(String nome, String Sobrenome, Integer idade, String email) {

    public static Range[] campos() {
        return new Range[] { new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43) };
    }

    public static String[] headers() {
        return new String[] { "nome", "sobrenome", "idade", "email" };
    }

}
