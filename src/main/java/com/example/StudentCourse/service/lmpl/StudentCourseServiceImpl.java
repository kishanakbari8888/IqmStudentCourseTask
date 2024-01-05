package com.example.StudentCourse.service.lmpl;

import com.example.StudentCourse.doa.StudentCourseDao;
import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.entities.Course;
//import com.example.StudentCourse.service.studentCourseService;
import com.example.StudentCourse.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Override
    public String addStudentToCourse(StudentCourse studentcourseenrollment) {
        try {
            studentCourseDao.add(studentcourseenrollment);
            return "Successful";
        }catch(SQLException e){
            return "UnSuccessful";
        }
    }


    @Override
    public List<Course> getAllCourseByStudentId(String studentId) {
        try {
            return studentCourseDao.getAllCourseByStudentId(studentId);
        } catch (SQLException e) {
            return null;
        }
    }
}
