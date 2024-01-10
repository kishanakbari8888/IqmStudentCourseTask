package com.example.StudentCourse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonList<T extends Serializable> implements Serializable {

    private List<T> data;
    private long totalRecords;

}
