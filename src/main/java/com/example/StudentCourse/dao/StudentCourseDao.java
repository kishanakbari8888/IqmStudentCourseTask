package com.example.StudentCourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.entities.StudentCourse;


/**
 * this class handle database query execution for student_course table
 */
@Repository
public class StudentCourseDao {

    private final Connection connection;
    @Autowired
    @Qualifier("PostgresConnection")
    JdbcTemplate jdbcTemplate;
    private static Logger logger = (Logger) LoggerFactory.getLogger(StudentCourse.class);


    public StudentCourseDao(final Connection connection){
        this.connection = connection;
    }

    public void add(StudentCourse studentCourse) throws SQLException{
        logger.info("we are saving student-course mapping");
        String studentId = studentCourse.getStudentId();
        String courseId = studentCourse.getCourseId();

        String sql = "INSERT INTO studentCourse (studentId, courseId) VALUES (?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseId);
            return preparedStatement;
        });
        logger.info("Successfully saved details into database");
    }

    public List<Course> getAllCourseByStudentId(String studentId) throws SQLException{
        logger.info("we are fetching details of all course by student id" + studentId);
        String sql = "SELECT c.id,c.title,c.description FROM studentCourse s INNER JOIN course_info c ON s.courseId=c.id WHERE s.studentId = ?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1,studentId);
                }
            }, (resultSet) -> {
                List<Course> courseList = new LinkedList<>();
                while(resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getString("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    courseList.add(course);
                }
            return courseList;
        });

    }

}
