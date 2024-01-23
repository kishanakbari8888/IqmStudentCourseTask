package com.example.StudentCourse.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    private static Logger logger = (Logger) LoggerFactory.getLogger(CourseController.class);


    /**
     * POST request for inserting course detail in database
     * @param courseDetail
     * @return
     * @throws SQLException
     */
    @PostMapping("/create")
    public CommonResponse<String> createCourse(@RequestBody Course courseDetail, HttpServletResponse httpServletResponse, HttpSession session){
        session.setAttribute("demo","cdkcjdcdkcjldcjoidcdjcnkjdchdcnjdbcjdc");
        CommonResponse commonResponse;
        logger.info("we are at create course end-point");
        try{
            commonResponse = new CommonResponse(courseservice.createCourse(courseDetail));
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
     * GET Request for getting course details by id
     * @param id
     * @return
     * @throws SQLException
     */
    @GetMapping("/get/{id}")
    public CommonResponse<Course> getbyId(@PathVariable("id") String id,HttpServletResponse httpServletResponse) throws SQLException {
        logger.info("we are at getbyId course end-point");
        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(courseservice.getCourse(id));
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
     * GET Request for getting all course details
     * @return
     * @throws SQLException
     */
    @GetMapping("/getallcourse")
    public CommonResponse<List<Course>> getallCourse(@RequestParam(name = "page",required = false,defaultValue = "0") Long pageNo, @RequestParam(name = "size",required = false,defaultValue = ""+5) Long size, @RequestParam(name = "sortby",required = false,defaultValue = "id") String field, @RequestParam(name = "search",required = false) String patten, HttpServletResponse httpServletResponse) {
        logger.info("we are at getAllCourse end-point");

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
    public CommonResponse<String> deleteCourse(@PathVariable(value = "id") String id,HttpServletResponse httpServletResponse) throws SQLException {
        logger.info("we are at delete Course end-point");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(courseservice.deleteCourse(id));
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
     * PUT Request for updating course details
     * @param courseDetail
     * @return
     * @throws SQLException
     */
    @PutMapping("/update")
    public CommonResponse<String> updateCourse(@RequestBody Course courseDetail,HttpServletResponse httpServletResponse) throws SQLException {
        logger.info("we are at update Course end-point");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(courseservice.updateCourse(courseDetail));
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
