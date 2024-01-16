package com.example.StudentCourse.controller;

import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentCourse.entities.CommonResponse;
import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.exceptions.SecurityException;
import com.example.StudentCourse.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseservice;

    /**
     * POST request for inserting course detail in database
     * @param courseDetail
     * @return
     * @throws SQLException
     */
    @PostMapping("/create")
    public String createCourse(@RequestBody Course courseDetail) throws SQLException {
        return courseservice.createCourse(courseDetail);
    }

    /**
     * GET Request for getting course details by id
     * @param id
     * @return
     * @throws SQLException
     */
    @GetMapping("/get/{id}")
    public Course getbyId(@PathVariable("id") String id) throws SQLException {
        return courseservice.getCourse(id);
    }

    /**
     * GET Request for getting all course details
     * @return
     * @throws SQLException
     */
    @GetMapping("/getallcourse")
    public CommonResponse<List<Course>> getallCourse(@RequestParam(name = "page",required = false,defaultValue = "0") Long pageNo, @RequestParam(name = "size",required = false,defaultValue = ""+5) Long size, @RequestParam(name = "sortby",required = false,defaultValue = "id") String field, @RequestParam(name = "search",required = false) String patten, HttpServletResponse httpServletResponse) {

        CommonResponse<List<Course>> courseList;
        try{
            courseList = new CommonResponse<>(courseservice.getallCourse(pageNo,size,field,patten));
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }catch (ParameterException e){
            courseList = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }catch (SecurityException e){
            courseList = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        }catch (Exception e){
            courseList = new CommonResponse<>(e);
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return courseList;
    }

    /**
     * DELETE Request for deleting course by id
     * @param id
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable(value = "id") String id ) throws SQLException {
        return courseservice.deleteCourse(id);
    }

    /**
     * PUT Request for updating course details
     * @param courseDetail
     * @return
     * @throws SQLException
     */
    @PutMapping("/update")
    public String updateCourse(@RequestBody Course courseDetail) throws SQLException {
        return courseservice.updateCourse(courseDetail);
    }



}
