package com.example.StudentCourse.Controller;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class courseController {
    @Autowired
    private CourseService courseservice;
    @PostMapping("/create")
    public String createCourse(@RequestBody Course courseDetail){
        return courseservice.createCourse(courseDetail);
    }
    @GetMapping("/get/{id}")
    public Course getbyId(@PathVariable("id") String id){
        return courseservice.getCourse(id);
    }

    @GetMapping("/getallcourse")
    public List<Course> getallCourse(){
        return courseservice.getallCourse();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") String id ){
        return courseservice.deleteCourse(id);
    }
    @PutMapping("/update")
    public String updateCourse(@RequestBody Course courseDetail ){
        return courseservice.updateCourse(courseDetail);
    }

}
