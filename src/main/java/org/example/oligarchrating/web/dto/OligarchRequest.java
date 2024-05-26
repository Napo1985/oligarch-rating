package org.example.oligarchrating.web.dto;

import lombok.Data;

@Data
public class OligarchRequest {
    private Long id;
    private PersonInformationDto personInformation;
    private FinancialAssetsDto financialAssets;

    public OligarchRequest(Long id, PersonInformationDto personInformation, FinancialAssetsDto financialAssets) {
        this.id = id;
        this.personInformation = personInformation;
        this.financialAssets = financialAssets;
    }
}
