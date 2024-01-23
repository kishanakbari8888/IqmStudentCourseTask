package com.example.StudentCourse.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    private String id;
    private String title;
    private String description;
    private Integer fee;
    private Integer departmentId;

}
