package com.example.StudentCourse.controller;

//import com.example.StudentCourse.entities.studentCourseEnrollment;
//import com.example.StudentCourse.service.studentCourseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.service.lmpl.StudentCourseServiceImpl;

@RestController
@RequestMapping("/api/studentcourse")
public class StudentCourseController {

//    @Autowired
//    private StudentCourseServiceImpl studentcourseserviceImpl;
//
//    /**
//     * Post Request for adding student to course
//     * @param studentcourse
//     * @return
//     */
//    @PostMapping("/add")
//    public String addStudentToCourse(@RequestBody StudentCourse studentcourse){
//
//        return studentcourseserviceImpl.addStudentToCourse(studentcourse);
//    }
//
//    /**
//     * Get request for find all course taken by student by student id
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public List<Course> getAllCourseById(@PathVariable("id") String id){
//
//        return studentcourseserviceImpl.getAllCourseByStudentId(id);
//    }

}
