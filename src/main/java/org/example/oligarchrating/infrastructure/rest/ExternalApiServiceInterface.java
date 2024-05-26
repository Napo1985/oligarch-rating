package org.example.oligarchrating.infrastructure.rest;

import java.math.BigDecimal;

public interface ExternalApiServiceInterface {
    Double evaluateCash(Double amount, String localCurrency);

    Double getBitcoinValue();

    Double getOligarchThreshold();
}
