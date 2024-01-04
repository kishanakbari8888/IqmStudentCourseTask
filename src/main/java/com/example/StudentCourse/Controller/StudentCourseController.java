package com.example.StudentCourse.Controller;

//import com.example.StudentCourse.entities.studentCourseEnrollment;
//import com.example.StudentCourse.service.studentCourseService;
import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.lmpl.StudentCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/studentcourse")
public class StudentCourseController {

    @Autowired
    private StudentCourseServiceImpl studentcourseserviceImpl;

    @PostMapping("/add")
    public String addStudentToCourse(@RequestBody StudentCourse studentcourse){

        return studentcourseserviceImpl.addStudentToCourse(studentcourse);
    }

    @GetMapping("/{id}")
    public List<Course> getAllCourseById(@PathVariable("id") String id){

        return studentcourseserviceImpl.getAllCourseByStudentId(id);
    }


}
