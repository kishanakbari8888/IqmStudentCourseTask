package com.example.StudentCourse.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Course;

/**
 * this class handle database query execution for course table
 */
@Repository
public class CourseDao {

    private final Connection connection;

    public CourseDao(Connection connection) {
        this.connection = connection;
    }

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
        }catch(SQLException e){
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
            e.printStackTrace();
        }
        return course;


    }

    public List<Course> findAll(Long pageNo,Long size,String field,String patten) throws SQLException{
        Long offSet = 0L;
        Long limit = 0L;
        String sql;

        if(pageNo!=Long.MAX_VALUE){
            offSet = (pageNo)*size;
            limit = size;
        }
        else{
            limit = pageNo;
        }

        sql = "SELECT * FROM course_info ORDER BY ? LIMIT ? OFFSET ?";

        if(patten!=null){
            sql = "SELECT * FROM course_info WHERE id LIKE "+ "\'%" +patten+"%\'" +" OR title LIKE "+ "\'%" +patten+"%\'" +" OR description LIKE "+ "\'%" +patten+"%\'" +" ORDER BY ? LIMIT ? OFFSET ?";
        }
        List<Course> courseList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,field);
            preparedStatement.setLong(2,limit);
            preparedStatement.setLong(3,offSet);
            ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    Course course = new Course();
                    course.setId(resultSet.getString("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    courseList.add(course);
                }

        } catch (SQLException e) {
            System.out.println("SQL Exception");
            throw e;
        }
        return courseList;

    }


}
