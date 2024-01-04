package com.example.StudentCourse.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.entities.Course;

@Service
public class CourseDao {

    @Autowired
    private Connection connection;
    public void save(Course course) throws SQLException {
        String id = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();
        String sql = "INSERT INTO course_info (id,title,description) VALUES (?, ?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3,description);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }
    }

    public void saveForUpdate(Course course) throws SQLException{

        String id = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();
        String sql = "UPDATE course_info \n" +
                "SET title = ?, description = ?\n" +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(3, id);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2,description);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }
    }

    public void deleteById(String courseId) throws SQLException{
        String id = courseId;
        String sql = "delete from course_info where id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }

    }

    public Course findById(String courseId) throws SQLException{
        String id = courseId;
        String sql = "SELECT *FROM course_info WHERE id = ?;";
        Course course = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    course.setId(resultSet.getString("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return course;


    }

    public List<Course> findAll() throws SQLException{

        String sql = "SELECT * FROM course_info;";
        List<Course> courseList = new ArrayList<>();

        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
                while(resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getString("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    courseList.add(course);
                }

        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }
        return courseList;

    }


}
