package com.example.StudentCourse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
import javax.validation.Valid;

import com.example.StudentCourse.entities.CommonResponse;
import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentseservice;
    private static Logger logger = (Logger) LoggerFactory.getLogger(StudentController.class);



    /**
     * POST request for inserting student detail in database
     * @param studentDetail
     * @return
     * @throws SQLException
     */
    @PostMapping("/create")
    public CommonResponse createStudent(@RequestBody @Valid Student studentDetail, HttpServletResponse httpServletResponse) throws SQLException, JsonProcessingException {
        logger.info("we are at create student Controller");
        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(studentseservice.createStudent(studentDetail));
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
     * GET Request for getting student details by id
     * @param id
     * @return
     * @throws SQLException
     */
    @GetMapping("/get/{id}")
    public CommonResponse getbyId(@PathVariable("id") String id,HttpServletResponse httpServletResponse) throws SQLException {
        logger.info("we are at get student by id Controller");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(studentseservice.getStudent(id));
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
     * GET Request for getting all student details
     * @return
     * @throws SQLException
     */
    @GetMapping("/getallStudent")
    public CommonResponse<List<Map<String,Object>>>  getallStudent(@RequestParam(name = "page",required = false,defaultValue = "0") Long pageNo, @RequestParam(name = "size",required = false,defaultValue = ""+5) Long size, @RequestParam(name = "sortby",required = false,defaultValue = "id") String field, @RequestParam(name = "search",required = false) String patten,HttpServletResponse httpServletResponse) throws SQLException  {
        logger.info("we are at get all student Controller");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse();
            commonResponse.setData(studentseservice.getallStudent(pageNo, size, field,patten));
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
     * DELETE Request for deleting student by id
     * @param id
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/delete/{id}")
    public CommonResponse deleteStudent(@PathVariable("id") String id,HttpServletResponse httpServletResponse) throws SQLException {
        logger.info("we are at delete all student Controller");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(studentseservice.deleteStudent(id));
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
     * @param studentDetail
     * @return
     * @throws SQLException
     */
    @PutMapping("/update")
    public CommonResponse updateStudent(@RequestBody Student studentDetail,HttpServletResponse httpServletResponse ) throws SQLException {
        logger.info("we are at update student Controller");

        CommonResponse commonResponse;
        try{
            commonResponse = new CommonResponse(studentseservice.updateStudent(studentDetail));
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