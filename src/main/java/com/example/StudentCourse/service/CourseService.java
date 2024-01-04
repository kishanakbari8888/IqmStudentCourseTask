package com.example.StudentCourse.service;

import com.example.StudentCourse.entities.Course;

import java.util.List;

public interface CourseService {
    public String createCourse(Course coursedetail);
    public String updateCourse(Course coursedetail);
    public String deleteCourse(String courseId);
    public Course getCourse(String courseId);
    public List<Course> getallCourse();
}
