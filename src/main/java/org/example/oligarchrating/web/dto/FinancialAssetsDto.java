package org.example.oligarchrating.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialAssetsDto {
    private BigDecimal cashAmount;
    private String currency;
    private int bitcoinAmount;
}
