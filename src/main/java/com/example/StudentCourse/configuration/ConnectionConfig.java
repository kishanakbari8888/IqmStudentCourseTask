package com.example.StudentCourse.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConnectionConfig {
    private static final String DB_URL_Postgres = "jdbc:postgresql://pg-1b9c0afa-kishanakbari5555-1a15.a.aivencloud.com:18137/defaultdb";
    private static final String DB_USER_Postgres = "avnadmin";
    private static final String DB_PASSWORD_Postgres = "AVNS_C5tHw7AQEGB0oO9dm1o";

    private static final String DB_URL_MYSQL = "jdbc:mysql://mysql-3e74fe9a-kishanakbari5555-1a15.a.aivencloud.com:18137/defaultdb";
    private static final String DB_USER_MYSQL = "avnadmin";
    private static final String DB_PASSWORD_MYSQL = "AVNS_vKWd94Um7HTNGRH7lKW";


    @Primary
    @Bean
    public Connection connectionConfigForPostgres() throws SQLException {
        Connection temp = null;
        try{
            temp =  DriverManager.getConnection(DB_URL_Postgres, DB_USER_Postgres, DB_PASSWORD_Postgres);
        }catch (SQLException e){
            throw e;
        }
        return temp;
    }

    @Bean(name = "MysqlConnector")
    public Connection connectionConfigForMYsql() throws SQLException {
        Connection temp = null;
        try{
            temp =  DriverManager.getConnection(DB_URL_MYSQL, DB_USER_MYSQL, DB_PASSWORD_MYSQL);
        }catch (SQLException e){
            throw e;
        }
        return temp;
    }

    @Bean(name = "postgresDriver")
    public DataSource dataSourcePostgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(DB_URL_Postgres);
        dataSource.setUsername(DB_USER_Postgres);
        dataSource.setPassword(DB_PASSWORD_Postgres);
        return dataSource;
    }

    @Bean(name = "MysqlDriver")
    public DataSource dataSourceMysql() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(DB_URL_MYSQL);
        dataSource.setUsername(DB_USER_MYSQL);
        dataSource.setPassword(DB_PASSWORD_MYSQL);
        return dataSource;
    }

    @Bean(name = "PostgresConnection")
    public JdbcTemplate jdbcTemplateConfigPostgres(@Qualifier("postgresDriver")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "MysqlConnection")
    public JdbcTemplate jdbcTemplateConfigMysql(@Qualifier("MysqlDriver")DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

}
