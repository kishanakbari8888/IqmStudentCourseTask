package com.example.StudentCourse.dataBaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfig {
    private static final String DB_URL = "jdbc:postgresql://pg-1b9c0afa-kishanakbari5555-1a15.a.aivencloud.com:18137/defaultdb";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    @Bean
    public Connection connectionConfi() throws SQLException {
        Connection temp = null;
        try{
            temp =  DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }catch (SQLException e){
            throw e;
        }
        return temp;
    }

}
