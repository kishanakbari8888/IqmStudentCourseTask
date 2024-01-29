package com.example.StudentCourse.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * this class handle database query execution for student table
 */
@Repository
public class UserDao{

    @Autowired
    @Qualifier("PostgresConnection")
    JdbcTemplate jdbcTemplate;
    private static Logger logger = (Logger) LoggerFactory.getLogger(StudentDao.class);

    public void save(User user) throws SQLException, JsonProcessingException {
        logger.info("we are saving user to database");
        String userdetail = user.getUsername();
        String password = user.getPassword();
        String role = user.getRoles();

        String sql = "INSERT INTO userdetails(username,password,role) VALUES (?,?,?);";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userdetail);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            return preparedStatement;
        });

        logger.info("successfully sava data into database");
    }
    public User findById(String username) throws SQLException {
        logger.info("we are finding User by id into UserDetails database");

        String id = username;
        String sql = "SELECT *FROM userdetails WHERE username = ?;";

        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setString(1, username);
                }
            }, (resultSet) -> {
                User user = null;
                if (resultSet.next()) {
                    user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRoles(resultSet.getString("role"));
                }
            return user;
        });

    }

}
