package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface StudentService {
    public String createStudent(Student studentdetail) throws SQLException;
    public String updateStudent(Student studentdetail) throws SQLException;
    public String deleteStudent(String studentId) throws SQLException;
    public Student getStudent(String studentId) throws SQLException;
    public List<Map<String, Object>> getallStudent(Long pageNo, Long size, String field, String patten) throws SQLException;
}

