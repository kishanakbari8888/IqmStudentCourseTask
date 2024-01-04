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

	@Autowired
	CourseDao courseDao;
	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
//		String sql = "select * from employee";
//		String studentId = "45455";
//		String courseId = "787";

//		jdbcTemplate.update(sql,studentId,courseId);
//
////		String sql = "CREATE TABLE IF NOT EXISTS studentCourse (studentId VARCHAR(255),courseId VARCHAR(255), CONSTRAINT PK_Person PRIMARY KEY (studentId,courseId))";
//
////		jdbcTemplate.execute(sql);
//



//		String sql = "SELECT *FROM studentCourse";
//		List<Map<String, Object>> M = jdbcTemplate.queryForList(sql);
//		for (Map<String, Object> courseMap : M) {
//			String id = (String) courseMap.get("studentId");
//			String title = (String) courseMap.get("CourseId");
////			String description = (String) courseMap.get("description");
//
//			// Print details of each student
//			log.info("Student ID: " + id);
//			log.info("Student ID: " + title);
//
//		}

//		Course course = new Course("CSE302","hello","hello");
//
//		courseDao.insertStudent(course);
//		log.info("Done");


	}
}
