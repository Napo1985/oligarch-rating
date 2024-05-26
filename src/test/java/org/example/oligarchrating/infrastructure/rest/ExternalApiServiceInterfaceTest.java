package org.example.oligarchrating.infrastructure.rest;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExternalApiServiceInterfaceTest {

    @Test
    void testEvaluateCash() {
        ExternalApiServiceInterface externalApiService = mock(ExternalApiServiceInterface.class);
        BigDecimal expectedAmount = BigDecimal.valueOf(10000);
        when(externalApiService.evaluateCash(BigDecimal.valueOf(100), "USD")).thenReturn(expectedAmount);
        BigDecimal actualAmount = externalApiService.evaluateCash(BigDecimal.valueOf(100), "USD");
        assertEquals(expectedAmount, actualAmount);
    }

    @Test
    void testGetBitcoinValue() {
        ExternalApiServiceInterface externalApiService = mock(ExternalApiServiceInterface.class);
        BigDecimal expectedBitcoinValue = BigDecimal.valueOf(50000);
        when(externalApiService.getBitcoinValue()).thenReturn(expectedBitcoinValue);
        BigDecimal actualBitcoinValue = externalApiService.getBitcoinValue();
        assertEquals(expectedBitcoinValue, actualBitcoinValue);
    }

    @Test
    void testGetOligarchThreshold() {
        ExternalApiServiceInterface externalApiService = mock(ExternalApiServiceInterface.class);
        BigDecimal expectedThreshold = BigDecimal.valueOf(1000000);
        when(externalApiService.getOligarchThreshold()).thenReturn(expectedThreshold);
        BigDecimal actualThreshold = externalApiService.getOligarchThreshold();
        assertEquals(expectedThreshold, actualThreshold);
    }
}