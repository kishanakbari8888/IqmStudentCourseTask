package com.example.StudentCourse.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Course;

/**
 * this class handle database query execution for course table
 */
@Repository
public class CourseDao {

    private static Logger logger = (Logger) LoggerFactory.getLogger(CourseDao.class);
    @Autowired
    @Qualifier("PostgresConnection")
    JdbcTemplate jdbcTemplate;

    public void save(Course course) throws SQLException {
        String id = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();
        Integer fee = course.getFee();
        Integer department = course.getDepartmentId();
        String sql = "INSERT INTO course_info (id,title,description,fee,department) VALUES (?,?,?,?,?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, fee);
            preparedStatement.setInt(5, department);
            return preparedStatement;
        });
    }

    public void saveForUpdate(Course course) throws SQLException {

        String id = course.getId();
        String title = course.getTitle();
        String description = course.getDescription();
        Integer fee = course.getFee();
        Integer department = course.getDepartmentId();
        String sql = "UPDATE course_info \n" +
                "SET title = ?, description = ?, fee = ?,department = ? " + "WHERE id = ?;";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, fee);
            preparedStatement.setInt(4, department);
            preparedStatement.setString(5, id);
            return preparedStatement;
        });
    }

    public void deleteById(String courseId) throws SQLException {
        String id = courseId;
        String sql = "delete from course_info where id = ?;";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            return preparedStatement;
        });
    }

    public Course findById(String courseId) throws SQLException {

        logger.info("Here we go to findbyId function for find into database");
        String id = courseId;
        String sql = "SELECT *FROM course_info WHERE id = ?;";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, (resultSet) -> {
            Course course = new Course();
            if (resultSet.next()) {
                course.setId(resultSet.getString("id"));
                course.setTitle(resultSet.getString("title"));
                course.setDescription(resultSet.getString("description"));
                course.setFee(resultSet.getInt("fee"));
                course.setDepartmentId(resultSet.getInt("department"));
            }
            return course;
        });
    }

    public List<Course> findAll(Long pageNo, Long size, String field, final String patten) throws SQLException {
        final Long offSet;
        offSet = pageNo * size;

        String sql = "SELECT * FROM course_info ORDER BY ? LIMIT ? OFFSET ?";
        if (patten != null) {
            sql = "SELECT * FROM course_info WHERE id LIKE ? OR title LIKE ? OR description LIKE ? ORDER BY ? LIMIT ? OFFSET ?";
        }
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                if (patten == null) {
                    preparedStatement.setString(1, field);
                    preparedStatement.setLong(2, size);
                    preparedStatement.setLong(3, offSet);
                } else {
                    String isPatten = "%" + patten + "%";
                    preparedStatement.setString(1, isPatten);
                    preparedStatement.setString(2, isPatten);
                    preparedStatement.setString(3, isPatten);
                    preparedStatement.setString(4, field);
                    preparedStatement.setLong(5, size);
                    preparedStatement.setLong(6, offSet);
                }
            }
        }, (resultSet) -> {
            List<Course> courseList = new LinkedList<>();
            while (resultSet.next()) {
                Course course = new Course();
                course.setId(resultSet.getString("id"));
                course.setTitle(resultSet.getString("title"));
                course.setDescription(resultSet.getString("description"));
                course.setFee(resultSet.getInt("fee"));
                course.setDepartmentId(resultSet.getInt("department"));
                courseList.add(course);
            }

            return courseList;
        });
    }
}
