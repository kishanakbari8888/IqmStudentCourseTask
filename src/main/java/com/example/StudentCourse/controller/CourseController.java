package com.example.StudentCourse.controller;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseservice;
    @PostMapping("/create")
    public String createCourse(@RequestBody Course courseDetail) throws SQLException {
        return courseservice.createCourse(courseDetail);
    }
    @GetMapping("/get/{id}")
    public Course getbyId(@PathVariable("id") String id) throws SQLException {
        try {
            return courseservice.getCourse(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getallcourse")
    public List<Course> getallCourse() throws SQLException {

        try {
            return courseservice.getallCourse();
        }catch(SQLException e){
            return null;
//            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") String id ) throws SQLException {
        return courseservice.deleteCourse(id);
    }
    @PutMapping("/update")
    public String updateCourse(@RequestBody Course courseDetail ) throws SQLException {
        return courseservice.updateCourse(courseDetail);
    }

}
