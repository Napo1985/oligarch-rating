package org.example.oligarchrating.domain.entities;

import lombok.Data;

@Data
public class OligarchEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private Long assetsValue;

    public OligarchEntity(Long id, String firstName, String lastName, Long assetsValue) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assetsValue = assetsValue;
    }
}
