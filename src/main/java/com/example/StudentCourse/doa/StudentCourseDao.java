package com.example.StudentCourse.doa;

import jdk.jfr.Registered;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.entities.StudentCourse;


/**
 * this class handle database query execution for student_course table
 */
@Repository
public class StudentCourseDao {

    private final Connection connection;

    public StudentCourseDao(final Connection connection){
        this.connection = connection;
    }

    public void add(StudentCourse studentCourse) throws SQLException{

        String studentId = studentCourse.getStudentId();
        String courseId = studentCourse.getCourseId();

        String sql = "INSERT INTO studentCourse (studentId, courseId) VALUES (?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
            throw e;
        }
    }

    public List<Course> getAllCourseByStudentId(String studentId) throws SQLException{
        String sql = "SELECT c.id,c.title,c.description FROM studentCourse s INNER JOIN course_info c ON s.courseId=c.id WHERE s.studentId = ?";

        List<Course> courseList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getString("id"));
                course.setTitle(resultSet.getString("title"));
                course.setDescription(resultSet.getString("description"));
                courseList.add(course);
            }

        } catch (SQLException e) {
            throw e;
        }
        return courseList;

    }


}
