package org.example.oligarchrating.web.dto;

import lombok.Data;

@Data
public class RequestDto {
    private long id;
    private PersonInformationDto personInformation;
    private FinancialAssetsDto financialAssets;
}
