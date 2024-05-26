package org.example.oligarchrating.infrastructure.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class ExternalApiService  implements ExternalApiServiceInterface {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BigDecimal evaluateCash(BigDecimal amount, String localCurrency) {
        String url = "http://assets-valuation/cash/evaluate?amount=" + amount + "&localCurrency=" + localCurrency;
        return restTemplate.getForObject(url, BigDecimal.class);
    }

    public BigDecimal getBitcoinValue() {
        String url = "http://assets-valuation/bitcoin/value";
        return restTemplate.getForObject(url, BigDecimal.class);
    }

    public BigDecimal getOligarchThreshold() {
        String url = "http://oligarch-helper/oligarch-threshold";
        return restTemplate.getForObject(url, BigDecimal.class);
    }
}
