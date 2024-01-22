package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.StudentCourse.dao.StudentDao;
import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

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
        return studentDao.findById(studentId);
    }

    /**
     * get students detail form database
     *
     * @return student list
     * @throws SQLException
     */
    @Override
    public List<Map<String, Object>> getallStudent(Long pageNo, Long size, String field, String patten) throws SQLException {
        try {
            return studentDao.findAll(pageNo, size, field, patten);
        }catch (SQLException e){
            throw e;
        }
    }

}
