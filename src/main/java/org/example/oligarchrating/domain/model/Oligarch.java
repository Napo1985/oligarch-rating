package org.example.oligarchrating.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Oligarch {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Long assetsValue;

    public Oligarch() {}

    public Oligarch(Long id , String firstName, String lastName, Long assetsValue) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.assetsValue = assetsValue;
    }
}

