package com.example.StudentCourse.serviceImplTest;


import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.exceptions.SecurityException;
import com.example.StudentCourse.service.CourseService;
import com.example.StudentCourse.service.lmpl.CourseServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class CourseServiceImplTest {

    @Autowired
    CourseServiceImpl courseService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void contextLoads() throws SQLException, JsonProcessingException {
        List<Course> listOfCourse = courseService.getallCourse(1L,3L,"id",null);
        String resultString = objectMapper.writeValueAsString(listOfCourse);

        System.out.println(resultString);
        assertThat(resultString).isNotEmpty();
    }

    @Test
    public void parameterExceptionForPageNoTesting(){
        Long pageNo = -1L;
        Long size = 3L;
        String filed = "id";
        String patten = null;

        Exception exception = assertThrows(ParameterException.class,()->{courseService.getallCourse(pageNo,size,filed,patten);});
        assertThat(exception.getMessage()).isEqualTo("page No");
    }

    @Test
    public void parameterExceptionForsizeTesting(){
        Long pageNo = 1L;
        Long size = 0L;
        String filed = "id";
        String patten = "hello";

        Exception exception = assertThrows(ParameterException.class,()->{courseService.getallCourse(pageNo,size,filed,patten);});
        assertThat(exception.getMessage()).isEqualTo("size");
    }

    @Test
    public void securityExceptionForPattenTesting(){
        Long pageNo = 1L;
        Long size = 1L;
        String filed = "id";
        String patten = "hell\'o";

        Exception exception = assertThrows(SecurityException.class,()->{courseService.getallCourse(pageNo,size,filed,patten);});
        System.out.println(exception.getMessage());
        assertThat(exception.getMessage()).isEqualTo("search field");
    }
    @Test
    public void sqlExceptionTesting() throws SQLException {
        Long pageNo = 1L;
        Long size = 1L;
        String field = "id";
        String patten = "hello";

        CourseService courseService1 = mock(CourseService.class);

//        when(courseService1.getallCourse(pageNo,size,field,patten)).thenThrow(SQLException.class);
        doThrow(new SQLException("Sql Exception Occur"))
                .when(courseService1)
                .getallCourse(pageNo,size,field,patten);

        Exception exception = assertThrows(SQLException.class,()->{courseService1.getallCourse(pageNo,size,field,patten);});
        System.out.println(exception.getMessage());
    }
}
