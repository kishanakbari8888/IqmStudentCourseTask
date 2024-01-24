package com.example.StudentCourse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

//    @Column(name = "session_id")
    private UUID sessionId;

//    @Column(name = "user_id")
    private String userId;

    @CreationTimestamp
//    @Column(name = "login_time")
    private Timestamp loginTime;

//    @Column(name = "expiry_time")
    private Timestamp expiryTime;

//    @Column(name = "roles")
    private List<String> roles;

}