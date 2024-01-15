package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.dao.DepartmentDao;
import com.example.StudentCourse.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao department;

    @Override
    public List<Map<String, Object>> countStudentCourseByDepartment() throws SQLException{
        return department.countStudentCourseByDepartment();
    }

    @Override
    public List<Map<String, Object>> courseListByDepartmentWise() throws SQLException {
        return department.getAllCoursewithdepartment();
    }

    @Override
    public List<Map<String, Object>> studentListByDepartmentWise() throws SQLException {
        return department.getAllStudentwithdepartment();
    }

    @Override
    public List<Map<String, Object>> revenuePerDepartment() throws SQLException {
        return department.revenuePerDepartment();
    }

    @Override
    public List<Map<String, Object>> feesPerStudent() throws SQLException {
        return department.feesPerStudent();
    }

}
