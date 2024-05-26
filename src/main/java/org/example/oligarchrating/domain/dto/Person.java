package org.example.oligarchrating.domain.dto;

import lombok.Data;

@Data
public class Person {

    private Long id;

    private String firstName;

    private String lastName;

    private FinancialAssets financialAssets;

}

