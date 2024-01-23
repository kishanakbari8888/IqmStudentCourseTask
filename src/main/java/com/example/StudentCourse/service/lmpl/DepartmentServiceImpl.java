package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.dao.DepartmentDao;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao department;
    private static Logger logger = (Logger) LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Override
    public List<Map<String, Object>> getDetailByField(String field) throws SQLException{
        logger.info("we are at service layer of department");
        if(field.equals("department"))
            return department.countStudentCourseByDepartment();
        else if(field.equals("course"))
            return department.getAllCoursewithdepartment();
        else if(field.equals("student"))
            return department.getAllStudentwithdepartment();

        throw new ParameterException("you have wrong parameter");
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
