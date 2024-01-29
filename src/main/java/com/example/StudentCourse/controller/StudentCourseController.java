package com.example.StudentCourse.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import com.example.StudentCourse.entities.CommonResponse;
import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.service.lmpl.StudentCourseServiceImpl;

@RestController
@RequestMapping("/api/studentcourse")
public class StudentCourseController {
    @Autowired
    private StudentCourseServiceImpl studentcourseserviceImpl;
    private static Logger logger = (Logger) LoggerFactory.getLogger(StudentCourseController.class);

    /**
     * Post Request for adding student to course
     * @param studentcourse
     * @return
     */
    @PostMapping("/add")
    public CommonResponse addStudentToCourse(@RequestBody StudentCourse studentcourse, HttpServletResponse httpServletResponse){
        logger.info("we are at add student course mapping end-point");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(studentcourseserviceImpl.addStudentToCourse(studentcourse));
        }catch (ParameterException e){
            commonResponse = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }catch (SecurityException e){
            commonResponse = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }catch (Exception e){
            commonResponse = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return commonResponse;
    }

    /**
     * Get request for find all course taken by student by student id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public CommonResponse getAllCourseById(@PathVariable("id") String id,HttpServletResponse httpServletResponse){
        logger.info("get all course by id end-point");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse();
            commonResponse.setData(studentcourseserviceImpl.getAllCourseByStudentId(id));
        }catch (ParameterException e){
            commonResponse = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }catch (SecurityException e){
            commonResponse = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }catch (Exception e){
            commonResponse = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return commonResponse;
    }

}
