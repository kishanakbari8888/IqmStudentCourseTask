package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.entities.Course;

import java.util.List;

public interface StudentCourseService {
    public String addStudentToCourse(StudentCourse studentcourseenrollment);
    public List<Course> getAllCourseByStudentId(String studentId);
}
