package com.example.StudentCourse.Dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.StudentCourse.dao.DepartmentDao;
import com.example.StudentCourse.dao.SessionDao;
import com.example.StudentCourse.entities.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
public class SessionTest {

	@Autowired
	SessionDao sessionDao;

	@Test
    public void createSessionTest() {
        String studentId = "AU2040200";
        List<String> roles = new LinkedList<>();
        roles.add("Admin");
        roles.add("Normal");

        Session session = new Session();
        session.setRoles(roles);
        session.setUserId(studentId);
        try{
            UUID SessionUuid = sessionDao.createSession(session);
            System.out.println(SessionUuid);
        }catch (Exception e){
            System.out.println("Error occur -> " + e.getMessage());
        }
	}

    @Test
    public void validateTokenTest() throws JsonProcessingException {
        UUID sessionId = UUID.fromString("64a1edd6-27b8-4bec-ab26-9b734fe83e88");
        Session session = sessionDao.validateToken(sessionId);

        ObjectMapper obj = new ObjectMapper();
        System.out.println(obj.writeValueAsString(session));
    }
}
