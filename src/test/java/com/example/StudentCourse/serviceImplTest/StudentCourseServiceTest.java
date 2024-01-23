package com.example.StudentCourse.serviceImplTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.StudentCourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class StudentCourseServiceTest {

    @Autowired
    StudentCourseService studentCourseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllCourseByStudentIdTest() throws JsonProcessingException {
        String studentId = "AU2040155";
        List<Course> courseList = studentCourseService.getAllCourseByStudentId(studentId);
        String courseListJson = objectMapper.writeValueAsString(courseList);

        System.out.println(courseListJson);
    }

}
