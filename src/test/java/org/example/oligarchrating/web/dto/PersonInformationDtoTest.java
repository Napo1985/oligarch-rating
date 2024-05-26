package org.example.oligarchrating.web.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonInformationDtoTest {

    @Test
    void testNoArgsConstructor() {
        PersonInformationDto dto = new PersonInformationDto();
        assertNotNull(dto);
        assertNull(dto.getFirstName());
        assertNull(dto.getLastName());
    }

    @Test
    void testAllArgsConstructor() {
        String firstName = "John";
        String lastName = "Doe";
        PersonInformationDto dto = new PersonInformationDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        assertEquals(firstName, dto.getFirstName());
        assertEquals(lastName, dto.getLastName());
    }

    @Test
    void testEquals() {
        String firstName = "John";
        String lastName = "Doe";
        PersonInformationDto dto1 = new PersonInformationDto();
        dto1.setFirstName(firstName);
        dto1.setLastName(lastName);
        PersonInformationDto dto2 = new PersonInformationDto();
        dto2.setFirstName(firstName);
        dto2.setLastName(lastName);
        assertEquals(dto1, dto2);
    }

    @Test
    void testNotEquals() {
        String firstName1 = "John";
        String lastName1 = "Doe";
        String firstName2 = "Jane";
        String lastName2 = "Smith";
        PersonInformationDto dto1 = new PersonInformationDto();
        dto1.setFirstName(firstName1);
        dto1.setLastName(lastName1);
        PersonInformationDto dto2 = new PersonInformationDto();
        dto2.setFirstName(firstName2);
        dto2.setLastName(lastName2);
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        String firstName = "John";
        String lastName = "Doe";
        PersonInformationDto dto1 = new PersonInformationDto();
        dto1.setFirstName(firstName);
        dto1.setLastName(lastName);
        PersonInformationDto dto2 = new PersonInformationDto();
        dto2.setFirstName(firstName);
        dto2.setLastName(lastName);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        String firstName1 = "John";
        String lastName1 = "Doe";
        String firstName2 = "Jane";
        String lastName2 = "Smith";
        PersonInformationDto dto1 = new PersonInformationDto();
        dto1.setFirstName(firstName1);
        dto1.setLastName(lastName1);
        PersonInformationDto dto2 = new PersonInformationDto();
        dto2.setFirstName(firstName2);
        dto2.setLastName(lastName2);
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        String firstName = "John";
        String lastName = "Doe";
        PersonInformationDto dto = new PersonInformationDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        String expectedString = "PersonInformationDto(firstName=John, lastName=Doe)";
        assertEquals(expectedString, dto.toString());
    }
}