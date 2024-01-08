package com.example.StudentCourse.controller;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
    public List<Course> getallCourse(@RequestParam(name = "page",required = false,defaultValue = "" + Long.MAX_VALUE) Long pageNo, @RequestParam(name = "size",required = false,defaultValue = ""+5) Long size, @RequestParam(name = "sortby",required = false,defaultValue = "id") String field, @RequestParam(name = "search",required = false) String patten) throws SQLException {
        return courseservice.getallCourse(pageNo,size,field,patten);
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
    public String updateCourse(@RequestBody Course courseDetail ) throws SQLException {
        return courseservice.updateCourse(courseDetail);
    }

}
