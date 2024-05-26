package org.example.oligarchrating.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class FinancialAssets {
    private Double cashAmount;
    private String currency;
    private Long bitcoinAmount;
}