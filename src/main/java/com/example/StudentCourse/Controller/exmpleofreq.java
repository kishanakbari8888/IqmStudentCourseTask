package com.example.StudentCourse.Controller;

import com.example.StudentCourse.entities.course;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class exmpleofreq{

    @PostMapping("/course")
    public course getpostreq(@RequestBody course c1){
        return c1;
    }

}
