package org.example.oligarchrating.domain.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testPersonConstructor() {
        Person person = new Person();
        assertNotNull(person);
    }

    @Test
    public void testPersonGettersAndSetters() {
        Person person = new Person();
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        FinancialAssets financialAssets = new FinancialAssets();

        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setFinancialAssets(financialAssets);

        assertEquals(id, person.getId());
        assertEquals(firstName, person.getFirstName());
        assertEquals(lastName, person.getLastName());
        assertEquals(financialAssets, person.getFinancialAssets());
    }

    @Test
    public void testPersonEquality() {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setFinancialAssets(new FinancialAssets());

        Person person2 = new Person();
        person2.setId(1L);
        person2.setFirstName("John");
        person2.setLastName("Doe");
        person2.setFinancialAssets(new FinancialAssets());

        assertEquals(person1, person2);
    }
}