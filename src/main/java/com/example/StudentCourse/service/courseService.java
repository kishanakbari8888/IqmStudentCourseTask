package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.course;

import java.util.List;

public interface courseService {
    public String createCourse();
    public String updateCourse();
    public String deleteCourse(String courseId);
    public course getCourse(String courseId);
    public List<course> getallCourse();
}
