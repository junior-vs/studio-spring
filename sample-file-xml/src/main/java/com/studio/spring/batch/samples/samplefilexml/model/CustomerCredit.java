package com.studio.spring.batch.samples.samplefilexml.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "CUSTOMER")
public class CustomerCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private BigDecimal credit;

    public CustomerCredit() {
    }

    public CustomerCredit(String name, BigDecimal credit) {

        this.name = name;
        this.credit = credit;
    }


}
