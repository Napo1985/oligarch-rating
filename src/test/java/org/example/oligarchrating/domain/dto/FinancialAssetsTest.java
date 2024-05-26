package org.example.oligarchrating.domain.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FinancialAssetsTest {

    private FinancialAssets financialAssets;

    @BeforeEach
    public void setUp() {
        financialAssets = new FinancialAssets();
    }

    @Test
    public void testGetSetCashAmount() {
        BigDecimal cashAmount = new BigDecimal("1000.50");
        financialAssets.setCashAmount(cashAmount);
        assertEquals(cashAmount, financialAssets.getCashAmount());
    }

    @Test
    public void testGetSetCurrency() {
        String currency = "USD";
        financialAssets.setCurrency(currency);
        assertEquals(currency, financialAssets.getCurrency());
    }

    @Test
    public void testGetSetBitcoinAmount() {
        int bitcoinAmount = 5;
        financialAssets.setBitcoinAmount(bitcoinAmount);
        assertEquals(bitcoinAmount, financialAssets.getBitcoinAmount());
    }
}