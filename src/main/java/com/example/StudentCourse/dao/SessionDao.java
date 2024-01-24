package com.example.StudentCourse.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.StudentCourse.entities.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Repository
public class SessionDao {
    @Autowired
    @Qualifier("PostgresConnection")
    JdbcTemplate jdbcTemplate;

    private ObjectMapper obj = new ObjectMapper();

    private static final int sessionTime = 3000;
    private static Logger logger = (Logger) LoggerFactory.getLogger(DepartmentDao.class);

    private Timestamp calculateExpiryTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, sessionTime);
        return new Timestamp(calendar.getTimeInMillis());
    }
    public UUID createSession(Session session) throws JsonProcessingException {

        String userId = session.getUserId();
        List<String> roles = session.getRoles();

        Timestamp expiryTime = calculateExpiryTime();
        String sql = "INSERT INTO sessions (user_id, login_time, expiry_time, roles) VALUES (?, CURRENT_TIMESTAMP, ?, cast(? as jsonb)) RETURNING session_id";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String jsonRoles = obj.writeValueAsString(roles);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"session_id"});
                ps.setString(1, userId);
                ps.setTimestamp(2, expiryTime);
                ps.setString(3, jsonRoles); // Convert List<String> to array
                return ps;
            }
        }, keyHolder);

        return (UUID) keyHolder.getKeyList().get(0).get("session_id");
    }

    public Session validateToken(final UUID sessionId){

        String sql = "SELECT * FROM sessions WHERE session_id = ?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1, sessionId);
            }
        }, (resultSet) -> {
            Session session = new Session();
            if (resultSet.next()) {
                session.setSessionId((UUID) resultSet.getObject("session_id"));
                String jsonRoles = (String) resultSet.getString("roles");
                try {
                    session.setRoles((Arrays.asList(obj.readValue(jsonRoles, String[].class))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                session.setExpiryTime((Timestamp) resultSet.getObject("expiry_time"));
                session.setLoginTime((Timestamp) resultSet.getObject("login_time"));
                session.setUserId(resultSet.getString("user_id"));
            }
            return session;
        });





    }

}
