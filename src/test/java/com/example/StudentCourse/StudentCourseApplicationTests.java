package com.example.StudentCourse;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.StudentCourse.dao.DepartmentDao;

@SpringBootTest
class StudentCourseApplicationTests {

	@Autowired
	DepartmentDao departmentDao;

	@Test
	void contextLoads() throws SQLException {

		List<Map<String,Object>> M = departmentDao.feesPerStudent();
		System.out.println(M.toString());
		System.out.print("hello");
	}

}
