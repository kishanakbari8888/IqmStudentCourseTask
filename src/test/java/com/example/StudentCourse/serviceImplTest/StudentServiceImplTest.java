package com.example.StudentCourse.serviceImplTest;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.exceptions.SecurityException;
import com.example.StudentCourse.service.lmpl.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class StudentServiceImplTest {

    @Autowired
    private StudentServiceImpl studentService;
    @Test
    public void parameterExceptionForPageNoTestingForGetAllStudent(){
        Long pageNo = -20L;
        Long size = 10L;
        String filed = "id";
        String patten = null;

        Exception exception = assertThrows(ParameterException.class,()->{studentService.getallStudent(pageNo,size,filed,patten);});
        assertThat(exception.getMessage()).isEqualTo("page No");
    }

    @Test
    public void parameterExceptionForSizeTestingForGetAllStudent(){
        Long pageNo = 1L;
        Long size = -13L;
        String filed = "id";
        String patten = "hello";

        Exception exception = assertThrows(ParameterException.class,()->{studentService.getallStudent(pageNo,size,filed,patten);});
        assertThat(exception.getMessage()).isEqualTo("size");
    }

    @Test
    public void securityExceptionForPattenTesting(){
        Long pageNo = 1L;
        Long size = 1L;
        String filed = "id";
        String patten = "hi\'";

        Exception exception = assertThrows(SecurityException.class,()->{studentService.getallStudent(pageNo,size,filed,patten);});
        System.out.println(exception.getMessage());
        assertThat(exception.getMessage()).isEqualTo("search field");
    }

    @Test
    public void CheckResponseForGetStudent() throws SQLException, JsonProcessingException {
        String studentId = "AU2040155";
        Student student = Student.builder().id("AU2040155").mobileNo("+91855698992").departmentId(1).description("i am kishan").build();

        Student tmp = studentService.getStudent(studentId);
        ObjectMapper obj = new ObjectMapper();
        assertThat(student).isEqualTo(tmp);

    }

}
