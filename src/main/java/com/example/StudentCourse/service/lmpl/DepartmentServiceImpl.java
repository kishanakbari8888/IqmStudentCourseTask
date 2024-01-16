package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.dao.CourseDao;
import com.example.StudentCourse.dao.DepartmentDao;
import com.example.StudentCourse.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao department;
    private static Logger logger = (Logger) LoggerFactory.getLogger(DepartmentServiceImpl.class);


    @Override
    public List<Map<String, Object>> countStudentCourseByDepartment() throws SQLException{
        logger.info("we are at service layer");
        return department.countStudentCourseByDepartment();
    }

    @Override
    public List<Map<String, Object>> courseListByDepartmentWise() throws SQLException {
        logger.info("we are at service layer");
        return department.getAllCoursewithdepartment();
    }

    @Override
    public List<Map<String, Object>> studentListByDepartmentWise() throws SQLException {
        logger.info("we are at service layer");
        return department.getAllStudentwithdepartment();
    }

    @Override
    public List<Map<String, Object>> revenuePerDepartment() throws SQLException {
        logger.info("we are at service layer");
        return department.revenuePerDepartment();
    }

    @Override
    public List<Map<String, Object>> feesPerStudent() throws SQLException {
        logger.info("we are at service layer");
        return department.feesPerStudent();
    }

}
