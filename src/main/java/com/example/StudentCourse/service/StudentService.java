package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.Student;

import java.util.List;

public interface StudentService {
    public String createStudent(Student studentdetail);
    public String updateStudent(Student studentdetail);
    public String deleteStudent(String studentId);
    public Student getStudent(String studentId);
    public List<Student> getallStudent();
}
