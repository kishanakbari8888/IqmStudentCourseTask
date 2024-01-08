package com.example.StudentCourse.Controller;

import com.example.StudentCourse.entities.course;
import com.example.StudentCourse.service.courseService;
import com.example.StudentCourse.service.lmpl.courseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class courseController {
    @Autowired
    private courseService courseservice;
    @PostMapping("/create")
    public String createCourse(@RequestBody course courseDetail){
        return courseservice.createCourse(courseDetail);
    }
    @GetMapping("/get/{id}")
    public course getbyId(@PathVariable("id") String id){
        return courseservice.getCourse(id);
    }

    @GetMapping("/getallcourse")
    public List<course> getallCourse(){
        return courseservice.getallCourse();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") String id ){
        return courseservice.deleteCourse(id);
    }
    @PutMapping("/update")
    public String updateCourse(@RequestBody course courseDetail ){
        return courseservice.updateCourse(courseDetail);
    }

}
