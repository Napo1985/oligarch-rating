package org.example.oligarchrating.infrastructure.rest;

import java.math.BigDecimal;

public interface ExternalApiServiceInterface {
    BigDecimal evaluateCash(BigDecimal amount, String localCurrency);

    BigDecimal getBitcoinValue();

    BigDecimal getOligarchThreshold();
}
