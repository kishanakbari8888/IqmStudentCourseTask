package com.example.StudentCourse.Controller;

import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentseservice;
    @PostMapping("/create")
    public String createStudent(@RequestBody Student studentDetail){
        return studentseservice.createStudent(studentDetail);
    }
    @GetMapping("/get/{id}")
    public Student getbyId(@PathVariable("id") String id){
        return studentseservice.getStudent(id);
    }

    @GetMapping("/getallStudent")
    public List<Student> getallStudent(){
        return studentseservice.getallStudent();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id ){
        return studentseservice.deleteStudent(id);
    }
    @PutMapping("/update")
    public String updateStudent(@RequestBody Student studentDetail ){
        return studentseservice.updateStudent(studentDetail);
    }

//    @GetMapping("/getallcoursebystudentid/{id}")
//    public List<Object[]> getAllCourseByStudentId(@PathVariable("id") String id){
//        return studentseservice.getallCoursebyStudentId(id);
//    }



}
