package com.example.StudentCourse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {

    @Autowired
    @Qualifier("PostgresConnection")
    JdbcTemplate jdbcTemplate;
    private final Connection connection;
    private static Logger logger = (Logger) LoggerFactory.getLogger(DepartmentDao.class);

    public DepartmentDao(Connection connection) {
        this.connection = connection;
    }

    public List<Map<String, Object>> countStudentCourseByDepartment() throws SQLException {
        logger.info("we are getting countStudentCourse");
        String sqlCourseCount = "select di.id,di.title,di.desciption,count(*) from department di inner join course_info ci on di.id=ci.department group by di.id,di.title,di.desciption;";
        return jdbcTemplate.query(sqlCourseCount, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                }
            }, (resultSet) -> {
                List<Map<String, Object>> studentCourseCountbydepartment = new LinkedList<>();
                while (resultSet.next()) {
                    Map<String, Object> tmp = new HashMap<>();
                    Integer department = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("desciption");
                    Integer courseCount = resultSet.getInt("count");

                    String SqlStudentCount = "select count(*) from student_info si  where department = ?";


                    Integer studentCount = jdbcTemplate.query(SqlStudentCount, new PreparedStatementSetter() {
                        @Override
                            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                                preparedStatement.setInt(1, department);
                            }
                        }, (resultSetForStudent) -> {
                            if (resultSetForStudent.next())
                                return resultSetForStudent.getInt("count");
                            else
                                return null;
                    });

                    tmp.put("id", department);
                    tmp.put("title", title);
                    tmp.put("description", description);
                    tmp.put("courseCount", courseCount);
                    tmp.put("studentCount", studentCount);

                    studentCourseCountbydepartment.add(tmp);
                }

            return studentCourseCountbydepartment;
        });

    }

    public List<Map<String, Object>> getAllCoursewithdepartment() throws SQLException {
        String sqlDepartment = "select di.id,di.title,di.desciption from department di;";
        logger.info("now we are at get all course with department");

        return jdbcTemplate.query(sqlDepartment, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                }
            }, (resultSet) -> {
                List<Map<String, Object>> CourseIdsByDepartment = new LinkedList<>();
                while (resultSet.next()) {
                    Map<String, Object> tmp = new HashMap<>();
                    Integer department = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("desciption");

                    tmp.put("departmentId", department);
                    tmp.put("title", title);
                    tmp.put("description", description);

                    String SqlStudentCount = "select id from course_info ci where department = ?;";

                    List<Object> courseListId = jdbcTemplate.query(SqlStudentCount, new PreparedStatementSetter() {
                            @Override
                                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                                    preparedStatement.setInt(1, department);
                                }
                            }, (resultSetForCourse) -> {
                                List<Object> courseListIdtmp = new LinkedList<>();
                                while (resultSetForCourse.next()) {
                                    courseListIdtmp.add(resultSetForCourse.getString("id"));
                                }
                            return courseListIdtmp;
                    });

                    tmp.put("courseId", courseListId);
                    CourseIdsByDepartment.add(tmp);
                }

            return CourseIdsByDepartment;
        });

    }

    public List<Map<String, Object>> getAllStudentwithdepartment() throws SQLException {
        logger.info("now we are at get all student with department");
        String sqlDepartment = "select di.id,di.title,di.desciption from department di;";
        return jdbcTemplate.query(sqlDepartment, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                }
            }, (resultSet) -> {
                List<Map<String, Object>> studentIdsByDepartment = new LinkedList<>();
                while (resultSet.next()) {

                    Map<String, Object> tmp = new HashMap<>();
                    Integer department = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("desciption");

                    tmp.put("departmentId", department);
                    tmp.put("title", title);
                    tmp.put("description", description);

                    String SqlStudentCount = "select id from student_info ci where department = ?;";
                    List<Object> studentListId = jdbcTemplate.query(SqlStudentCount, new PreparedStatementSetter() {
                            @Override
                                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                                    preparedStatement.setInt(1, department);
                                }
                            }, (resultSetForCourse) -> {
                                List<Object> studentListIdtmp = new LinkedList<>();
    //
                                while (resultSetForCourse.next()) {
                                    studentListIdtmp.add(resultSetForCourse.getString("id"));
                                }
                            return studentListIdtmp;
                    });

                    tmp.put("studentId", studentListId);
                    studentIdsByDepartment.add(tmp);
                }

            return studentIdsByDepartment;
        });

    }

    public List<Map<String, Object>> revenuePerDepartment() throws SQLException {
        logger.info("now we are at get revenue per department");
        String sql = "select department , sum(fee) as revenue from studentcourse sc inner join course_info ci on sc.courseid = ci.id group by department;";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                }
            }, (resultSet) -> {
                List<Map<String, Object>> revenueListbyDeparment = new LinkedList<>();
                while (resultSet.next()) {
                    Map<String, Object> tmp = new HashMap<>();
                    String department = resultSet.getString("department");
                    Integer revenue = resultSet.getInt("revenue");
                    tmp.put("department", department);
                    tmp.put("revenue", revenue);
                    revenueListbyDeparment.add(tmp);
                }

            return revenueListbyDeparment;
        });

    }

    public List<Map<String, Object>> feesPerStudent() throws SQLException {
        logger.info("now we at get all fees pre student");
        String sql = "select sc.studentId, sum(fee) as fee from studentcourse sc inner join course_info ci on sc.courseid = ci.id group by studentid;";

        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                }
            }, (resultSet) -> {
                List<Map<String, Object>> feesPerStudent = new LinkedList<>();
                while (resultSet.next()) {
                    Map<String, Object> tmp = new HashMap<>();
                    String department = resultSet.getString("studentid");
                    Integer revenue = resultSet.getInt("fee");
                    tmp.put("studentid", department);
                    tmp.put("fee", revenue);
                    feesPerStudent.add(tmp);
                }

            return feesPerStudent;
        });

    }

}
