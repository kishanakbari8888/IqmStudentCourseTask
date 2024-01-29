package com.example.StudentCourse.service;

import java.util.List;
import java.util.UUID;

import com.example.StudentCourse.entities.Session;

public interface SessionService {
    public UUID createToken(Session session);
    public List<String> getListOfRoles(UUID sessionId);
}
