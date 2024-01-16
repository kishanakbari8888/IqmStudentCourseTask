package com.example.StudentCourse;

import lombok.extern.java.Log;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class StudentCourseApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(StudentCourseApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("Application Running");
	}
}
