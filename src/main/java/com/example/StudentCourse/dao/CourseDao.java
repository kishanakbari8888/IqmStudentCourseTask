package com.example.StudentCourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Course;

/**
 * this class handle database query execution for course table
 */
@Repository
public class CourseDao {

    private static Logger logger = (Logger) LoggerFactory.getLogger(CourseDao.class);

    private final Connection connection;

    public CourseDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Course course) throws SQLException {
        String id = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();
        Integer fee = course.getFee();
        Integer department = course.getDepartmentId();
        String sql = "INSERT INTO course_info (id,title,description,fee,department) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3,description);
            preparedStatement.setInt(4,fee);
            preparedStatement.setInt(5,department);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void saveForUpdate(Course course) throws SQLException{

        String id = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();
        Integer fee = course.getFee();
        Integer department = course.getDepartmentId();
        String sql = "UPDATE course_info \n" +
                "SET title = ?, description = ?, fee = ?,department = ? " +"WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, title);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,fee);
            preparedStatement.setInt(4, department);
            preparedStatement.setString(5, id);
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

        logger.info("Here we go to findbyId function for find into database");
        String id = courseId;
        String sql = "SELECT *FROM course_info WHERE id = ?;";
        Course course = null;

        try {
            logger.info("hitting sql query in databse");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    course = new Course();
                    course.setId(resultSet.getString("id"));
                    course.setTitle(resultSet.getString("title"));
                    course.setDescription(resultSet.getString("description"));
                    course.setFee(resultSet.getInt("fee"));
                    course.setDepartmentId(resultSet.getInt("department"));
                }
            }
            logger.info("successfully find data form database");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("oops some error occur while querying into database for record");
        }
        return course;
    }


    public List<Course> findAll(Long pageNo,Long size,String field,String patten) throws SQLException{
        Long offSet = 0L;
        offSet = pageNo*size;

        String sql = "SELECT * FROM course_info ORDER BY ? LIMIT ? OFFSET ?";

        if(patten!=null){
            sql = "SELECT * FROM course_info WHERE id LIKE ? OR title LIKE ? OR description LIKE ? ORDER BY ? LIMIT ? OFFSET ?";
        }
        List<Course> courseList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if(patten==null){
                preparedStatement.setString(1,field);
                preparedStatement.setLong(2,size);
                preparedStatement.setLong(3,offSet);
            }
            else{
                patten = "%"+patten+"%";
                preparedStatement.setString(1,patten);
                preparedStatement.setString(2,patten);
                preparedStatement.setString(3,patten);
                preparedStatement.setString(4,field);
                preparedStatement.setLong(5,size);
                preparedStatement.setLong(6,offSet);
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getString("id"));
                course.setTitle(resultSet.getString("title"));
                course.setDescription(resultSet.getString("description"));
                course.setFee(resultSet.getInt("fee"));
                course.setDepartmentId(resultSet.getInt("department"));
                courseList.add(course);
            }

        } catch (SQLException e) {
            throw e;
        }
        return courseList;
    }

}
