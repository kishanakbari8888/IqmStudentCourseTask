package com.example.StudentCourse.controller;

import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentseservice;
    @PostMapping("/create")
    public String createStudent(@RequestBody Student studentDetail) throws SQLException {
        return studentseservice.createStudent(studentDetail);
    }
    @GetMapping("/get/{id}")
    public Student getbyId(@PathVariable("id") String id) throws SQLException {
        return studentseservice.getStudent(id);
    }

    @GetMapping("/getallStudent")
    public List<Student> getallStudent() throws SQLException {
        return studentseservice.getallStudent();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id ) throws SQLException {
        return studentseservice.deleteStudent(id);
    }
    @PutMapping("/update")
    public String updateStudent(@RequestBody Student studentDetail ) throws SQLException {
        return studentseservice.updateStudent(studentDetail);
    }

//    @GetMapping("/getallcoursebystudentid/{id}")
//    public List<Object[]> getAllCourseByStudentId(@PathVariable("id") String id){
//        return studentseservice.getallCoursebyStudentId(id);
//    }



}
