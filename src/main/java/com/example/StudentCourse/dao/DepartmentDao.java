package com.example.StudentCourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {

    private final Connection connection;

    public DepartmentDao(Connection connection){
        this.connection = connection;
    }

    public List<Map<String, Object>> countStudentCourseByDepartment() throws SQLException{
        String sqlCourseCount = "select di.id,di.title,di.desciption,count(*) from department di inner join course_info ci on di.id=ci.department group by di.id,di.title,di.desciption;";
        List<Map<String, Object>> studentCourseCountbydepartment = new LinkedList<>();

        try{
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sqlCourseCount);
            while(resultSet.next()) {
                Map<String,Object> tmp = new HashMap<>();
                Integer department = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("desciption");
                Integer courseCount = resultSet.getInt("count");

                String SqlStudentCount = "select count(*) from student_info si  where department = ?";
                PreparedStatement preparedStatementForStudent = connection.prepareStatement(SqlStudentCount);
                preparedStatementForStudent.setInt(1,department);
                ResultSet resultSetForStudent = preparedStatementForStudent.executeQuery();
                Integer studentCount;
                if(resultSetForStudent.next())
                    studentCount = resultSetForStudent.getInt("count");
                else
                    throw new RuntimeException("student not found");

                tmp.put("id",department);
                tmp.put("title",title);
                tmp.put("description",description);
                tmp.put("courseCount",courseCount);
                tmp.put("studentCount",studentCount);

                studentCourseCountbydepartment.add(tmp);
            }


        } catch (SQLException e) {
            throw e;
        }
        return studentCourseCountbydepartment;
    }


    public List<Map<String,Object>> getAllCoursewithdepartment() throws SQLException {
        String sqlDepartment = "select di.id,di.title,di.desciption from department di;";
        List<Map<String, Object>> CourseIdsByDepartment = new LinkedList<>();

        try{
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sqlDepartment);
            while(resultSet.next()) {
                Map<String,Object> tmp = new HashMap<>();
                Integer department = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("desciption");

                tmp.put("departmentId",department);
                tmp.put("title",title);
                tmp.put("description",description);

                String SqlStudentCount = "select id from course_info ci where department = ?;";
                PreparedStatement preparedStatementForStudent = connection.prepareStatement(SqlStudentCount);
                preparedStatementForStudent.setInt(1,department);
                ResultSet resultSetForCourse = preparedStatementForStudent.executeQuery();

                List<Object> courseListId = new LinkedList<>();

                while(resultSetForCourse.next()){
                    courseListId.add(resultSetForCourse.getString("id"));
                }
                tmp.put("courseId",courseListId);
                CourseIdsByDepartment.add(tmp);
            }


        } catch (SQLException e) {
            throw e;
        }
        return CourseIdsByDepartment;
    }

    public List<Map<String,Object>> getAllStudentwithdepartment() throws SQLException {
        String sqlDepartment = "select di.id,di.title,di.desciption from department di;";
        List<Map<String, Object>> studentIdsByDepartment = new LinkedList<>();

        try{
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sqlDepartment);
            while(resultSet.next()) {
                Map<String,Object> tmp = new HashMap<>();
                Integer department = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("desciption");

                tmp.put("departmentId",department);
                tmp.put("title",title);
                tmp.put("description",description);

                String SqlStudentCount = "select id from student_info ci where department = ?;";
                PreparedStatement preparedStatementForStudent = connection.prepareStatement(SqlStudentCount);
                preparedStatementForStudent.setInt(1,department);
                ResultSet resultSetForCourse = preparedStatementForStudent.executeQuery();

                List<Object> studentListId = new LinkedList<>();

                while(resultSetForCourse.next()){
                    studentListId.add(resultSetForCourse.getString("id"));
                }
                tmp.put("studentId",studentListId);
                studentIdsByDepartment.add(tmp);
            }


        } catch (SQLException e) {
            throw e;
        }
        return studentIdsByDepartment;
    }

    public List<Map<String,Object>> revenuePerDepartment() throws SQLException{

        String sql = "select department , sum(fee) as revenue from studentcourse sc inner join course_info ci on sc.courseid = ci.id group by department;";
        List<Map<String,Object>> revenueListbyDeparment = new LinkedList<>();

        try{
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while(resultSet.next()){
                Map<String,Object> tmp = new HashMap<>();
                String department = resultSet.getString("department");
                Integer revenue = resultSet.getInt("revenue");
                tmp.put("department",department);
                tmp.put("revenue",revenue);
                revenueListbyDeparment.add(tmp);
            }

        }catch (SQLException e){
            throw e;
        }

        return revenueListbyDeparment;
    }

    public List<Map<String,Object>> feesPerStudent() throws SQLException {
        String sql = "select sc.studentId, sum(fee) as fee from studentcourse sc inner join course_info ci on sc.courseid = ci.id group by studentid;";

        List<Map<String,Object>> feesPerStudent = new LinkedList<>();

        try{
            Statement preparedStatement = connection.createStatement();
            ResultSet resultSet = preparedStatement.executeQuery(sql);

            while(resultSet.next()){
                Map<String,Object> tmp = new HashMap<>();
                String department = resultSet.getString("studentid");
                Integer revenue = resultSet.getInt("fee");
                tmp.put("studentid",department);
                tmp.put("fee",revenue);
                feesPerStudent.add(tmp);
            }

        }catch (SQLException e){
            throw e;
        }
        return feesPerStudent;
    }

}
