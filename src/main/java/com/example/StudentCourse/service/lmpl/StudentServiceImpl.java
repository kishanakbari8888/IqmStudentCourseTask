package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.dao.StudentDao;
import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.exceptions.SecurityException;
import com.example.StudentCourse.service.StudentCourseService;
import com.example.StudentCourse.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;
    private static Logger logger = (Logger) LoggerFactory.getLogger(StudentCourseService.class);

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     * add student details in student table
     * @param studentdetail
     * @return message
     * @throws SQLException
     */
    @Override
    public String createStudent(Student studentdetail) throws SQLException, JsonProcessingException {
        studentDao.save(studentdetail);
        return "success";
    }

    /**
     * update student detail base on student id
     * @param studentdetail
     * @return message
     * @throws SQLException
     */
    @Override
    public String updateStudent(Student studentdetail) throws SQLException {
        studentDao.saveForUpdate(studentdetail);
        return "success";
    }

    /**
     * delete student details base on student id
     * @param studentId
     * @return message
     * @throws SQLException
     */
    @Override
    public String deleteStudent(String studentId) throws SQLException {
        studentDao.deleteById(studentId);
        return "success";
    }

    /**
     * get student detail form database
     * @param studentId
     * @return student details
     * @throws SQLException
     */
    @Override
    public Student getStudent(String studentId) throws SQLException {
        Student student = studentDao.findById(studentId);
        logger.info("successfully fetch student data from database");
        return student;
    }

    /**
     * get students detail form database
     *
     * @return student list
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> getallStudent(Long pageNo, Long size, String field, String patten) throws SQLException {

        if(pageNo<0){
            logger.info("Page No field not proper");
            throw new ParameterException("value cann't be less than zero","page No");
        }

        if(size<=0){
            logger.info("size field not proper");
            throw new ParameterException("value cann't be less than zero","size");
        }

        if(patten!=null && (patten.contains("\"") || patten.contains("\'"))){
            logger.info("patten field not proper");
            throw new SecurityException("search field","don't Enter special characters");
        }


        List<Map<String,Object>> studentList = studentDao.findAll(pageNo, size, field, patten);
        logger.info("list of student fetch successfully from database");
        return studentList;
    }

}
