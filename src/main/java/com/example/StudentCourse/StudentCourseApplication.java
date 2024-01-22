package com.example.StudentCourse;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

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
