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

import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Student;

/**
 * this class handle database query execution for student table
 */
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
        Integer department = student.getDepartmentId();

        String sql = "INSERT INTO student_info (id,mobile_No,description,department) VALUES (?, ?,?,?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, mobileNo);
            preparedStatement.setString(3,description);
            preparedStatement.setInt(4,department);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void saveForUpdate(Student student) throws SQLException{

        String id = student.getId();
        String mobileNo = student.getMobileNo();
        String description = student.getDescription();
        Integer department = student.getDepartmentId();

        String sql = "UPDATE student_info \n" +
                "SET mobile_No = ?, description = ?,department = ? " +
                "WHERE id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, mobileNo);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,department);
            preparedStatement.setString(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
                    student.setDepartmentId(resultSet.getInt("department"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;


    }

    public List<Map<String,Object>> findAll(Long pageNo, Long size, String field, String patten) throws SQLException{
        Long offSet = 0L;
        offSet = pageNo*size;

        String sql = "select si.id,si.description,si.mobile_no,si.department,sum(ci.fee) as fee from student_info si inner join studentcourse s on si.id = s.studentid inner join course_info ci on s.courseid=ci.id group by si.id,si.description,si.mobile_no\n" +
                "\n" +
                "\n ORDER BY ? LIMIT ? OFFSET ?";

        if(patten!=null){
            sql = "select si.id,si.description,si.mobile_no,si.department,sum(ci.fee) as fee from student_info si inner join studentcourse s on si.id = s.studentid inner join course_info ci on s.courseid=ci.id group by si.id,si.description,si.mobile_no\n" +
                    "\n" +
                    "\n WHERE id LIKE ? OR mobile_no LIKE ? OR description LIKE ? ORDER BY ? LIMIT ? OFFSET ?";
        }

        List<Map<String,Object>> studentList = new LinkedList<>();

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
                Map<String,Object> student = new HashMap<>();
                student.put("id",resultSet.getString("id"));
                student.put("mobile No",resultSet.getString("mobile_No"));
                student.put("description",resultSet.getString("description"));
                student.put("department",resultSet.getInt("department"));
                student.put("fee",resultSet.getInt("fee"));
                studentList.add(student);
            }

        } catch (SQLException e) {
            throw e;
        }
        return studentList;

    }


}
