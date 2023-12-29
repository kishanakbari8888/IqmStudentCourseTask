package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.student;

import java.util.List;
import java.util.Optional;

public interface studentService {
    public String createStudent(student studentdetail);
    public String updateStudent(student studentdetail);
    public String deleteStudent(String studentId);
    public student getStudent(String studentId);
    public List<student> getallStudent();
}
