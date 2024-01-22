package com.example.StudentCourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.entities.subentites.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * this class handle database query execution for student table
 */
@Repository
public class StudentDao {

    private final Connection connection;
    @Autowired
    @Qualifier("PostgresConnection")
    JdbcTemplate jdbcTemplate;

    public StudentDao(final Connection connection) {
        this.connection = connection;
    }

    public void save(Student student) throws SQLException, JsonProcessingException {
        String id = student.getId();
        String mobileNo = student.getMobileNo();
        String description = student.getDescription();
        Integer department = student.getDepartmentId();
        Address address = student.getAddress();

        ObjectMapper obj = new ObjectMapper();
        String jsonAddress = obj.writeValueAsString(address);

        String sql = "INSERT INTO student_info (id,mobile_No,description,department,address) VALUES (?,?, ?,?,cast(? as json))";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, mobileNo);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, department);
            preparedStatement.setString(5, jsonAddress);
            return preparedStatement;
        });

    }

    public void saveForUpdate(Student student) throws SQLException {

        String id = student.getId();
        String mobileNo = student.getMobileNo();
        String description = student.getDescription();
        Integer department = student.getDepartmentId();

        String sql = "UPDATE student_info \n" +
                "SET mobile_No = ?, description = ?,department = ? " +
                "WHERE id = ?;";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, mobileNo);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, department);
            preparedStatement.setString(4, id);
            return preparedStatement;
        });
    }

    public void deleteById(String studentId) throws SQLException {
        String id = studentId;
        String sql = "delete from student_info where id = ?;";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            return preparedStatement;
        });
    }

    public Student findById(String studentId) throws SQLException {
        String id = studentId;
        String sql = "SELECT *FROM student_info WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, id);
            }
        }, (resultSet) -> {
            Student student = null;
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getString("id"));
                student.setMobileNo(resultSet.getString("mobile_No"));
                student.setDescription(resultSet.getString("description"));
                student.setDepartmentId(resultSet.getInt("department"));
            }
            return student;
        });

    }

    public List<Map<String, Object>> findAll(Long pageNo, Long size, String field, final String patten) throws SQLException {
        final Long offSet = pageNo * size;

        String sql = "select si.id,si.description,si.mobile_no,si.department,sum(ci.fee) as fee from student_info si inner join studentcourse s on si.id = s.studentid inner join course_info ci on s.courseid=ci.id group by si.id,si.description,si.mobile_no\n" +
                "\n" +
                "\n ORDER BY ? LIMIT ? OFFSET ?";

        if (patten != null) {
            sql = "select si.id,si.description,si.mobile_no,si.department,sum(ci.fee) as fee from student_info si inner join studentcourse s on si.id = s.studentid inner join course_info ci on s.courseid=ci.id group by si.id,si.description,si.mobile_no\n" +
                    "\n" +
                    "\n WHERE id LIKE ? OR mobile_no LIKE ? OR description LIKE ? ORDER BY ? LIMIT ? OFFSET ?";
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
            List<Map<String, Object>> studentList = new LinkedList<>();
            while (resultSet.next()) {
                Map<String, Object> student = new HashMap<>();
                student.put("id", resultSet.getString("id"));
                student.put("mobile No", resultSet.getString("mobile_No"));
                student.put("description", resultSet.getString("description"));
                student.put("department", resultSet.getInt("department"));
                student.put("fee", resultSet.getInt("fee"));
                studentList.add(student);
            }

            return studentList;
        });

    }


}
