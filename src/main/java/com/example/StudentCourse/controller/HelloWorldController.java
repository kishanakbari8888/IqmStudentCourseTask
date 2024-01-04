package com.example.StudentCourse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(){

        return "HelloWorld";
    }

    @GetMapping("/")
    public String test(){

        return "HelloWorld";
    }

}