package com.example.StudentCourse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentCourse.entities.CommonResponse;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.exceptions.SecurityException;
import com.example.StudentCourse.service.lmpl.DepartmentServiceImpl;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentService;
    public static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
//
    @GetMapping("/getdetailbyfield/{field}")
    public CommonResponse<List<Map<String, Object>>> getDetailByField(@PathVariable(name = "field") String field){
        logger.info("we are at conntroller layer");
        try{
            return new CommonResponse<>(departmentService.getDetailByField(field));
        } catch (ParameterException e) {
            logger.info("ParameterException exception occur");
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            logger.info("SecurityException exception occur");
            return new CommonResponse<>(e);
        }catch (Exception e){
            logger.info("exception occur");
            return new CommonResponse<>(e);
        }
    }

    @GetMapping("/revenueperdepartment")
    public CommonResponse<List<Map<String, Object>>> revenuePerDepartment(){
        logger.info("we are at conntroller layer");
        try{
            return new CommonResponse<>(departmentService.revenuePerDepartment());
        } catch (ParameterException e) {
            logger.info("ParameterException exception occur");
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            logger.info("SecurityException exception occur");
            return new CommonResponse<>(e);
        }catch (Exception e){
            logger.info("exception occur");
            return new CommonResponse<>(e);
        }
    }

    @GetMapping("/feesperstudent")
    public CommonResponse<List<Map<String, Object>>> feesPerStudent(){
        logger.info("we are at conntroller layer");
        try{
            return new CommonResponse<>(departmentService.feesPerStudent());
        } catch (ParameterException e) {
            logger.info("ParameterException exception occur");
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            logger.info("SecurityException exception occur");
            return new CommonResponse<>(e);
        }catch (Exception e){
            logger.info("exception occur");
            return new CommonResponse<>(e);
        }
    }

}
