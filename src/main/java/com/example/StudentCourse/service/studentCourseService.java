package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.entities.course;

import java.util.List;

public interface studentCourseService {
    public String addStudentToCourse(StudentCourse studentcourseenrollment);
    public List<course> getAllCourseByStudentId(String studentId);
}
