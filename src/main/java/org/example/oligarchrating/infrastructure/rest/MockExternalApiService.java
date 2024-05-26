package org.example.oligarchrating.infrastructure.rest;

public class MockExternalApiService implements ExternalApiServiceInterface {

    public Double evaluateCash(Double amount, String localCurrency) {
        return amount;
    }

    public Double getBitcoinValue() {
        return 2D;
    }

    public Double getOligarchThreshold() {
        return 1D;
    }
}
