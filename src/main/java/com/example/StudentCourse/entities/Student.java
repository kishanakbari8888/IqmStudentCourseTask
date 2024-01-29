package com.example.StudentCourse.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.Valid;

import com.example.StudentCourse.entities.subentites.Address;
import com.fasterxml.jackson.annotation.JsonInclude;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class Student {
    private String id;
    private String mobileNo;
    private String description;
    private Integer departmentId;
    private @Valid Address address;
}
