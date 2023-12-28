package com.example.StudentCourse.Controller;

import com.example.StudentCourse.entities.course;
import com.example.StudentCourse.service.courseService;
import com.example.StudentCourse.service.lmpl.courseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class courseController {

    @Autowired
    private courseService courseservice;
    @PostMapping("/create")
    public String createCourse(@RequestBody course courseDetail){
        return courseservice.createCourse(courseDetail);
    }
    @PostMapping("/{id}")
    public course getbyId(@PathVariable("id") String id){
        return courseservice.getCourse(id);
    }

}
