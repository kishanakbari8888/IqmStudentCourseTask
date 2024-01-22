package com.example.StudentCourse;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.StudentCourse.dao.DepartmentDao;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration

public class StudentCourseApplicationTests {

	@Autowired
	DepartmentDao departmentDao;

	@Test
    public void contextLoads() throws SQLException {
		List<Map<String,Object>> M = departmentDao.feesPerStudent();
		System.out.println(M.toString());
		System.out.print("hello");
	}



}
