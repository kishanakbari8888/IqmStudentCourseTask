package com.example.StudentCourse.Controller;

import com.example.StudentCourse.entities.student;
import com.example.StudentCourse.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class studentController {
    @Autowired
    private studentService studentseservice;
    @PostMapping("/create")
    public String createStudent(@RequestBody student studentDetail){
        return studentseservice.createStudent(studentDetail);
    }
    @GetMapping("/get/{id}")
    public student getbyId(@PathVariable("id") String id){
        return studentseservice.getStudent(id);
    }

    @GetMapping("/getallStudent")
    public List<student> getallStudent(){
        return studentseservice.getallStudent();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id ){
        return studentseservice.deleteStudent(id);
    }
    @PutMapping("/update")
    public String updateStudent(@RequestBody student studentDetail ){
        return studentseservice.updateStudent(studentDetail);
    }

//    @GetMapping("/getallcoursebystudentid/{id}")
//    public List<Object[]> getAllCourseByStudentId(@PathVariable("id") String id){
//        return studentseservice.getallCoursebyStudentId(id);
//    }



}
