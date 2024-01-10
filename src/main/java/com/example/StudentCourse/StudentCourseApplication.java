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
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class PrivateBean {
	private int id;
	private String name;

	public PrivateBean(int i, String myBean) {
		this.id = i;
		this.name = myBean;
	}
}
@SpringBootApplication
@Log

public class StudentCourseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {



		log.info("Application Running");

		PrivateBean bean = new PrivateBean(1, "My bean");

		String result = new ObjectMapper()
				.writeValueAsString(bean);

		log.info(result);
	}
}
