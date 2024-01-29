package com.example.StudentCourse.entities.subentites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.security.PublicKey;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    @NotEmpty(message = "Field 'street' cannot be empty")
    public String street;

    @NotEmpty(message = "Field 'city' cannot be empty")
    public String city;

    @NotEmpty(message = "Field 'state' cannot be empty")
    public String state;
}
