package com.example.StudentCourse.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/countbydepartment")
    public CommonResponse<List<Map<String, Object>>> getCountBydeparment(){
        try{
            return new CommonResponse<>(departmentService.countStudentCourseByDepartment());
        } catch (ParameterException e) {
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            return new CommonResponse<>(e);
        }catch (Exception e){
            return new CommonResponse<>(e);
        }
    }

    @GetMapping("/getcourselistbydepartmentwise")
    public CommonResponse<List<Map<String, Object>>> courseListByDepartmentWise(){
        try{
            return new CommonResponse<>(departmentService.courseListByDepartmentWise());
        } catch (ParameterException e) {
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            return new CommonResponse<>(e);
        }catch (Exception e){
            return new CommonResponse<>(e);
        }
    }

    @GetMapping("/getstudentlistbydepartmentwise")
    public CommonResponse<List<Map<String, Object>>> studentListByDepartmentWise(){
        try{
            return new CommonResponse<>(departmentService.studentListByDepartmentWise());
        } catch (ParameterException e) {
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            return new CommonResponse<>(e);
        }catch (Exception e){
            return new CommonResponse<>(e);
        }
    }

    @GetMapping("/revenueperdepartment")
    public CommonResponse<List<Map<String, Object>>> revenuePerDepartment(){
        try{
            return new CommonResponse<>(departmentService.revenuePerDepartment());
        } catch (ParameterException e) {
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            return new CommonResponse<>(e);
        }catch (Exception e){
            return new CommonResponse<>(e);
        }
    }

    @GetMapping("/feesperstudent")
    public CommonResponse<List<Map<String, Object>>> feesPerStudent(){
        try{
            return new CommonResponse<>(departmentService.feesPerStudent());
        } catch (ParameterException e) {
            return new CommonResponse<>(e);
        } catch (SecurityException e){
            return new CommonResponse<>(e);
        }catch (Exception e){
            return new CommonResponse<>(e);
        }
    }

}
