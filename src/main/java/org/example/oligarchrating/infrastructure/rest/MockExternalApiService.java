package org.example.oligarchrating.infrastructure.rest;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

public class MockExternalApiService implements ExternalApiServiceInterface {

    public BigDecimal evaluateCash(BigDecimal amount, String localCurrency) {
        return amount;
    }

    public BigDecimal getBitcoinValue() {
        return BigDecimal.valueOf(2); //
    }

    public BigDecimal getOligarchThreshold() {
        return BigDecimal.valueOf(1);
    }
}
