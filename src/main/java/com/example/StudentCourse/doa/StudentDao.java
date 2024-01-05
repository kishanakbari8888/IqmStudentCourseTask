package com.example.StudentCourse.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.entities.Student;

@Repository
public class StudentDao {


    private final Connection connection;
    public StudentDao(final Connection connection){
        this.connection = connection;
    }

    public void save(Student student) throws SQLException {
        String id = student.getId();
        String mobileNo = student.getMobileNo();
        String description = student.getDescription();

        String sql = "INSERT INTO student_info (id,mobile_No,description) VALUES (?, ?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, mobileNo);
            preparedStatement.setString(3,description);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }
    }

    public void saveForUpdate(Student student) throws SQLException{

        String id = student.getId();
        String mobileNo = student.getMobileNo();
        String description = student.getDescription();

        String sql = "UPDATE student_info \n" +
                "SET mobile_No = ?, description = ?\n" +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(3, id);
            preparedStatement.setString(1, mobileNo);
            preparedStatement.setString(2,description);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }
    }

    public void deleteById(String studentId) throws SQLException{
        String id = studentId;
        String sql = "delete from student_info where id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }

    }

    public Student findById(String studentId) throws SQLException{
        String id = studentId;
        String sql = "SELECT *FROM student_info WHERE id = ?;";
        Student student = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    student = new Student();
                    student.setId(resultSet.getString("id"));
                    student.setMobileNo(resultSet.getString("mobile_No"));
                    student.setDescription(resultSet.getString("description"));
                }
            }
        } catch (SQLException e) {
            // Handle exceptions
            e.printStackTrace();
        }
        return student;


    }

    public List<Student> findAll() throws SQLException{

        String sql = "SELECT * FROM student_info;";
        List<Student> studentList = new ArrayList<>();

        try {
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getString("id"));
                student.setMobileNo(resultSet.getString("mobile_No"));
                student.setDescription(resultSet.getString("description"));
                studentList.add(student);
            }

        } catch (SQLException e) {
            // Handle exceptions
            throw e;
        }
        return studentList;

    }


}
