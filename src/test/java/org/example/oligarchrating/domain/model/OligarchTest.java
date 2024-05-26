package org.example.oligarchrating.domain.model;

import org.example.oligarchrating.infrastructure.repository.model.Oligarch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OligarchTest {

    @Test
    public void testOligarchConstructor() {
        Oligarch oligarch = new Oligarch();
        assertNotNull(oligarch);
    }

    @Test
    public void testOligarchGettersAndSetters() {
        Oligarch oligarch = new Oligarch();
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        Long assetsValue = 1000000000L;

        oligarch.setId(id);
        oligarch.setFirstName(firstName);
        oligarch.setLastName(lastName);
        oligarch.setAssetsValue(assetsValue);

        assertEquals(id, oligarch.getId());
        assertEquals(firstName, oligarch.getFirstName());
        assertEquals(lastName, oligarch.getLastName());
        assertEquals(assetsValue, oligarch.getAssetsValue());
    }

    @Test
    public void testOligarchEquality() {
        Oligarch oligarch1 = new Oligarch(1L, "John", "Doe", 1000L);
        Oligarch oligarch2 = new Oligarch(1L, "John", "Doe", 1000L);

        assertNotEquals(oligarch1, oligarch2);
    }
}