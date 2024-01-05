package com.example.StudentCourse;

import lombok.extern.java.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import com.example.StudentCourse.doa.CourseDao;
import com.example.StudentCourse.entities.Course;

@SpringBootApplication
@Log
public class StudentCourseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application Running");
	}
}
