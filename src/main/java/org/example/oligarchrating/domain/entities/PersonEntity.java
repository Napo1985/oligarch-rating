package org.example.oligarchrating.domain.entities;

import lombok.Data;

@Data
public class PersonEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private FinancialAssets financialAssets;

    public PersonEntity(Long id, String firstName, String lastName, FinancialAssets financialAssets) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.financialAssets = financialAssets;
    }
}

