package org.example.oligarchrating.web.dto;

import lombok.Data;

@Data
public class PersonInformationDto {
    private String firstName;
    private String lastName;

    public PersonInformationDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
