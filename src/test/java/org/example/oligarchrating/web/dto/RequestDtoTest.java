package org.example.oligarchrating.web.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class RequestDtoTest {

    @Test
    void testNoArgsConstructor() {
        RequestDto dto = new RequestDto();
        assertNotNull(dto);
        assertEquals(0, dto.getId());
        assertNull(dto.getPersonInformation());
        assertNull(dto.getFinancialAssets());
    }

    @Test
    void testAllArgsConstructor() {
        long id = 1L;
        PersonInformationDto personInformation = new PersonInformationDto();
        personInformation.setFirstName("John");
        personInformation.setLastName("Doe");
        FinancialAssetsDto financialAssets = new FinancialAssetsDto();
        financialAssets.setCashAmount(new BigDecimal("1000"));
        financialAssets.setCurrency("USD");
        financialAssets.setBitcoinAmount(5);

        RequestDto dto = new RequestDto();
        dto.setId(id);
        dto.setPersonInformation(personInformation);
        dto.setFinancialAssets(financialAssets);

        assertEquals(id, dto.getId());
        assertEquals(personInformation, dto.getPersonInformation());
        assertEquals(financialAssets, dto.getFinancialAssets());
    }

    @Test
    void testEquals() {
        PersonInformationDto personInformation = new PersonInformationDto();
        personInformation.setFirstName("John");
        personInformation.setLastName("Doe");
        FinancialAssetsDto financialAssets = new FinancialAssetsDto();
        financialAssets.setCashAmount(new BigDecimal("1000"));
        financialAssets.setCurrency("USD");
        financialAssets.setBitcoinAmount(5);

        RequestDto dto1 = new RequestDto();
        dto1.setId(1L);
        dto1.setPersonInformation(personInformation);
        dto1.setFinancialAssets(financialAssets);

        RequestDto dto2 = new RequestDto();
        dto2.setId(1L);
        dto2.setPersonInformation(personInformation);
        dto2.setFinancialAssets(financialAssets);

        assertEquals(dto1, dto2);
    }

    @Test
    void testNotEquals() {
        PersonInformationDto personInformation1 = new PersonInformationDto();
        personInformation1.setFirstName("John");
        personInformation1.setLastName("Doe");
        PersonInformationDto personInformation2 = new PersonInformationDto();
        personInformation2.setFirstName("Jane");
        personInformation2.setLastName("Smith");
        FinancialAssetsDto financialAssets = new FinancialAssetsDto();
        financialAssets.setCashAmount(new BigDecimal("1000"));
        financialAssets.setCurrency("USD");
        financialAssets.setBitcoinAmount(5);

        RequestDto dto1 = new RequestDto();
        dto1.setId(1L);
        dto1.setPersonInformation(personInformation1);
        dto1.setFinancialAssets(financialAssets);

        RequestDto dto2 = new RequestDto();
        dto2.setId(2L);
        dto2.setPersonInformation(personInformation2);
        dto2.setFinancialAssets(financialAssets);

        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        PersonInformationDto personInformation = new PersonInformationDto();
        personInformation.setFirstName("John");
        personInformation.setLastName("Doe");
        FinancialAssetsDto financialAssets = new FinancialAssetsDto();
        financialAssets.setCashAmount(new BigDecimal("1000"));
        financialAssets.setCurrency("USD");
        financialAssets.setBitcoinAmount(5);

        RequestDto dto1 = new RequestDto();
        dto1.setId(1L);
        dto1.setPersonInformation(personInformation);
        dto1.setFinancialAssets(financialAssets);

        RequestDto dto2 = new RequestDto();
        dto2.setId(1L);
        dto2.setPersonInformation(personInformation);
        dto2.setFinancialAssets(financialAssets);

        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        PersonInformationDto personInformation1 = new PersonInformationDto();
        personInformation1.setFirstName("John");
        personInformation1.setLastName("Doe");
        PersonInformationDto personInformation2 = new PersonInformationDto();
        personInformation2.setFirstName("Jane");
        personInformation2.setLastName("Smith");
        FinancialAssetsDto financialAssets = new FinancialAssetsDto();
        financialAssets.setCashAmount(new BigDecimal("1000"));
        financialAssets.setCurrency("USD");
        financialAssets.setBitcoinAmount(5);

        RequestDto dto1 = new RequestDto();
        dto1.setId(1L);
        dto1.setPersonInformation(personInformation1);
        dto1.setFinancialAssets(financialAssets);

        RequestDto dto2 = new RequestDto();
        dto2.setId(2L);
        dto2.setPersonInformation(personInformation2);
        dto2.setFinancialAssets(financialAssets);

        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        PersonInformationDto personInformation = new PersonInformationDto();
        personInformation.setFirstName("John");
        personInformation.setLastName("Doe");
        FinancialAssetsDto financialAssets = new FinancialAssetsDto();
        financialAssets.setCashAmount(new BigDecimal("1000"));
        financialAssets.setCurrency("USD");
        financialAssets.setBitcoinAmount(5);

        RequestDto dto = new RequestDto();
        dto.setId(1L);
        dto.setPersonInformation(personInformation);
        dto.setFinancialAssets(financialAssets);

        String expectedString = "RequestDto(id=1, personInformation=PersonInformationDto(firstName=John, lastName=Doe), financialAssets=FinancialAssetsDto(cashAmount=1000, currency=USD, bitcoinAmount=5))";
        assertEquals(expectedString, dto.toString());
    }
}