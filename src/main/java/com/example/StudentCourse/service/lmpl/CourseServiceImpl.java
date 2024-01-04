package com.example.StudentCourse.service.lmpl;

import com.example.StudentCourse.Repository.CourseRepository;
import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courserepository;

    @Override
    public String createCourse(Course coursedetail) {
        courserepository.save(coursedetail);
        return "Success";
    }

    @Override
    public String updateCourse(Course coursedetail) {
        courserepository.save(coursedetail);
        return "success";
    }

    @Override
    public String deleteCourse(String courseId) {
        courserepository.deleteById(courseId);
        return "success";
    }

    @Override
    public Course getCourse(String courseId) {

        return courserepository.findById(courseId).get() ;
    }

    @Override
    public List<Course> getallCourse() {

        return courserepository.findAll();
    }
}
