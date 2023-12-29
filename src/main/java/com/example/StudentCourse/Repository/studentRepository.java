package com.example.StudentCourse.Repository;

import com.example.StudentCourse.entities.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<student,String> {
}
