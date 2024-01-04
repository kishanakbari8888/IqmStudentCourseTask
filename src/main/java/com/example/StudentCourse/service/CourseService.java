package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseService {
    public String createCourse(Course coursedetail) throws SQLException;
    public String updateCourse(Course coursedetail) throws SQLException;
    public String deleteCourse(String courseId) throws SQLException;
    public Course getCourse(String courseId) throws SQLException;
    public List<Course> getallCourse() throws SQLException;
}
