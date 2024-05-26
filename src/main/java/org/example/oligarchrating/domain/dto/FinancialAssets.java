package org.example.oligarchrating.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialAssets {
    private BigDecimal cashAmount;
    private String currency;
    private int bitcoinAmount;
}