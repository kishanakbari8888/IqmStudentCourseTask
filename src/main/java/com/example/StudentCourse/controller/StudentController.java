package com.example.StudentCourse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentseservice;

    /**
     * POST request for inserting student detail in database
     * @param studentDetail
     * @return
     * @throws SQLException
     */
    @PostMapping("/create")
    public String createStudent(@RequestBody Student studentDetail) throws SQLException {
        return studentseservice.createStudent(studentDetail);
    }

    /**
     * GET Request for getting student details by id
     * @param id
     * @return
     * @throws SQLException
     */
    @GetMapping("/get/{id}")
    public Student getbyId(@PathVariable("id") String id) throws SQLException {
        return studentseservice.getStudent(id);
    }

    /**
     * GET Request for getting all student details
     * @return
     * @throws SQLException
     */
    @GetMapping("/getallStudent")
    public List<Student> getallStudent() throws SQLException {
        return studentseservice.getallStudent();
    }

    /**
     * DELETE Request for deleting student by id
     * @param id
     * @return
     * @throws SQLException
     */
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") String id ) throws SQLException {
        return studentseservice.deleteStudent(id);
    }

    /**
     * PUT Request for updating course details
     * @param studentDetail
     * @return
     * @throws SQLException
     */
    @PutMapping("/update")
    public String updateStudent(@RequestBody Student studentDetail ) throws SQLException {
        return studentseservice.updateStudent(studentDetail);
    }

}
