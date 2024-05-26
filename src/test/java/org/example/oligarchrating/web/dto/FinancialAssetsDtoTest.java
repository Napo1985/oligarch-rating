package org.example.oligarchrating.web.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class FinancialAssetsDtoTest {

    @Test
    void testNoArgsConstructor() {
        FinancialAssetsDto dto = new FinancialAssetsDto();
        assertNotNull(dto);
        assertNull(dto.getCashAmount());
        assertNull(dto.getCurrency());
        assertEquals(0, dto.getBitcoinAmount());
    }

    @Test
    void testSetterMethods() {
        BigDecimal cashAmount = new BigDecimal("1000");
        String currency = "USD";
        int bitcoinAmount = 5;
        FinancialAssetsDto dto = new FinancialAssetsDto();

        dto.setCashAmount(cashAmount);
        dto.setCurrency(currency);
        dto.setBitcoinAmount(bitcoinAmount);

        assertEquals(cashAmount, dto.getCashAmount());
        assertEquals(currency, dto.getCurrency());
        assertEquals(bitcoinAmount, dto.getBitcoinAmount());
    }

    @Test
    void testEquals() {
        BigDecimal cashAmount = new BigDecimal("1000");
        String currency = "USD";
        int bitcoinAmount = 5;
        FinancialAssetsDto dto1 = new FinancialAssetsDto();
        dto1.setCashAmount(cashAmount);
        dto1.setCurrency(currency);
        dto1.setBitcoinAmount(bitcoinAmount);

        FinancialAssetsDto dto2 = new FinancialAssetsDto();
        dto2.setCashAmount(cashAmount);
        dto2.setCurrency(currency);
        dto2.setBitcoinAmount(bitcoinAmount);

        assertEquals(dto1, dto2);
    }

    @Test
    void testNotEquals() {
        BigDecimal cashAmount1 = new BigDecimal("1000");
        BigDecimal cashAmount2 = new BigDecimal("2000");
        String currency = "USD";
        int bitcoinAmount = 5;

        FinancialAssetsDto dto1 = new FinancialAssetsDto();
        dto1.setCashAmount(cashAmount1);
        dto1.setCurrency(currency);
        dto1.setBitcoinAmount(bitcoinAmount);

        FinancialAssetsDto dto2 = new FinancialAssetsDto();
        dto2.setCashAmount(cashAmount2);
        dto2.setCurrency(currency);
        dto2.setBitcoinAmount(bitcoinAmount);

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        BigDecimal cashAmount = new BigDecimal("1000");
        String currency = "USD";
        int bitcoinAmount = 5;

        FinancialAssetsDto dto1 = new FinancialAssetsDto();
        dto1.setCashAmount(cashAmount);
        dto1.setCurrency(currency);
        dto1.setBitcoinAmount(bitcoinAmount);

        FinancialAssetsDto dto2 = new FinancialAssetsDto();
        dto2.setCashAmount(cashAmount);
        dto2.setCurrency(currency);
        dto2.setBitcoinAmount(bitcoinAmount);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        BigDecimal cashAmount1 = new BigDecimal("1000");
        BigDecimal cashAmount2 = new BigDecimal("2000");
        String currency = "USD";
        int bitcoinAmount = 5;

        FinancialAssetsDto dto1 = new FinancialAssetsDto();
        dto1.setCashAmount(cashAmount1);
        dto1.setCurrency(currency);
        dto1.setBitcoinAmount(bitcoinAmount);

        FinancialAssetsDto dto2 = new FinancialAssetsDto();
        dto2.setCashAmount(cashAmount2);
        dto2.setCurrency(currency);
        dto2.setBitcoinAmount(bitcoinAmount);

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        BigDecimal cashAmount = new BigDecimal("1000");
        String currency = "USD";
        int bitcoinAmount = 5;

        FinancialAssetsDto dto = new FinancialAssetsDto();
        dto.setCashAmount(cashAmount);
        dto.setCurrency(currency);
        dto.setBitcoinAmount(bitcoinAmount);

        String expectedString = "FinancialAssetsDto(cashAmount=1000, currency=USD, bitcoinAmount=5)";
        assertEquals(expectedString, dto.toString());
    }
}