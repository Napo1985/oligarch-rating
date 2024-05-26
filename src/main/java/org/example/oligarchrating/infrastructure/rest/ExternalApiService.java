package org.example.oligarchrating.infrastructure.rest;

import org.springframework.web.client.RestTemplate;

public class ExternalApiService  implements ExternalApiServiceInterface {

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double evaluateCash(Double amount, String localCurrency) {
        String url = "http://assets-valuation/cash/evaluate?amount=" + amount + "&localCurrency=" + localCurrency;
        return restTemplate.getForObject(url, Double.class);
    }

    public Double getBitcoinValue() {
        String url = "http://assets-valuation/bitcoin/value";
        return restTemplate.getForObject(url, Double.class);
    }

    public Double getOligarchThreshold() {
        String url = "http://oligarch-helper/oligarch-threshold";
        return restTemplate.getForObject(url, Double.class);
    }
}
