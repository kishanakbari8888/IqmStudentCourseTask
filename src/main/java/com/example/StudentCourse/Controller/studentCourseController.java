package com.example.StudentCourse.Controller;

//import com.example.StudentCourse.entities.studentCourseEnrollment;
//import com.example.StudentCourse.service.studentCourseService;
import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.entities.course;
import com.example.StudentCourse.service.lmpl.studentCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/studentcourse")
public class studentCourseController {

    @Autowired
    private studentCourseServiceImpl studentcourseserviceImpl;

    @PostMapping("/add")
    public String addStudentToCourse(@RequestBody StudentCourse studentcourse){

        return studentcourseserviceImpl.addStudentToCourse(studentcourse);
    }

    @GetMapping("/{id}")
    public List<course> getAllCourseById(@PathVariable("id") String id){

        return studentcourseserviceImpl.getAllCourseByStudentId(id);
    }


}
