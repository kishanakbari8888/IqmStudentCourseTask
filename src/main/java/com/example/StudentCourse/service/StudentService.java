package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    public String createStudent(Student studentdetail) throws SQLException;
    public String updateStudent(Student studentdetail) throws SQLException;
    public String deleteStudent(String studentId) throws SQLException;
    public Student getStudent(String studentId) throws SQLException;
    public List<Student> getallStudent() throws SQLException;
}
