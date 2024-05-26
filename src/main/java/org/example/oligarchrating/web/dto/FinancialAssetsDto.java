package org.example.oligarchrating.web.dto;

import lombok.Data;

@Data
public class FinancialAssetsDto {
    private Double cashAmount;
    private String currency;
    private Long bitcoinAmount;

    public FinancialAssetsDto(Double cashAmount, String currency, Long bitcoinAmount) {
        this.cashAmount = cashAmount;
        this.currency = currency;
        this.bitcoinAmount = bitcoinAmount;
    }
}

