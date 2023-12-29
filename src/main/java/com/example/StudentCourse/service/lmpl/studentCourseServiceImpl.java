package com.example.StudentCourse.service.lmpl;

import com.example.StudentCourse.entities.StudentCourse;
import com.example.StudentCourse.entities.course;
//import com.example.StudentCourse.service.studentCourseService;
import com.example.StudentCourse.service.studentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.println;


@Service
public class studentCourseServiceImpl implements studentCourseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String addStudentToCourse(StudentCourse studentcourseenrollment) {
        String sql = "INSERT INTO studentCourse (studentId, courseId) VALUES (?,?)";
		jdbcTemplate.update(sql,studentcourseenrollment.getStudentId(),studentcourseenrollment.getCourseId());
        return "Success";
    }


    @Override
    public List<course> getAllCourseByStudentId(String studentId) {
        String sql = "SELECT c.id,c.title,c.description FROM studentCourse s INNER JOIN course_info c ON s.courseId=c.id WHERE s.studentId = ?";
        List<Map<String, Object>> M  = jdbcTemplate.queryForList(sql, new String[]{studentId});

        List<course> obj=new LinkedList<>();

        for (Map<String, Object> courseMap : M) {
			String id = (String) courseMap.get("Id");
			String title = (String) courseMap.get("title");
			String description = (String) courseMap.get("description");

            course C1 = new course(id,title,description);
            obj.add(C1);
		}

        return obj;
    }
}
