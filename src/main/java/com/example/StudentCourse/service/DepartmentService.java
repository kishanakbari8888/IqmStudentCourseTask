package com.example.StudentCourse.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public List<Map<String, Object>> getDetailByField(String field) throws SQLException;

    public List<Map<String,Object>> revenuePerDepartment() throws SQLException;

    public List<Map<String,Object>> feesPerStudent() throws SQLException;
}
