package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.doa.StudentCourseDao;
import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.service.StudentCourseService;


@Service
public class StudentCourseServiceImpl implements StudentCourseService {

//    @Autowired
//    private StudentCourseDao studentCourseDao;
//
//    /**
//     * add student with corresponding course
//     * @param studentcourseenrollment
//     * @return
//     */
//    @Override
//    public String addStudentToCourse(StudentCourse studentcourseenrollment) {
//        try {
//            studentCourseDao.add(studentcourseenrollment);
//            return "Successful";
//        }catch(SQLException e){
//            return "UnSuccessful";
//        }
//    }
//
//    /**
//     * find all courses in which student enrolled by student id
//     * @param studentId
//     * @return
//     */
//    @Override
//    public List<Course> getAllCourseByStudentId(String studentId) {
//        try {
//            return studentCourseDao.getAllCourseByStudentId(studentId);
//        } catch (SQLException e) {
//            return null;
//        }
//    }
}
