package com.example.StudentCourse.Repository;

import com.example.StudentCourse.entities.course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface courseRepository extends JpaRepository<course,String> {
}
