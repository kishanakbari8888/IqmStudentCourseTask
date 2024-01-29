package com.example.StudentCourse.serviceImplTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.StudentCourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class PasswordEncodeTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Test
    public void getAllCourseByStudentIdTest() throws JsonProcessingException {

        UserDetails userDetails = User.builder().
            username("kishan")
            .password(passwordEncoder().encode("12345678")).roles("ADMIN").
            build();
        System.out.println(userDetails.getPassword());
    }

}
