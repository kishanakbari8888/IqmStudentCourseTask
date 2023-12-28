package com.example.StudentCourse.service.lmpl;

import com.example.StudentCourse.Repository.courseRepository;
import com.example.StudentCourse.entities.course;
import com.example.StudentCourse.service.courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class courseServiceImpl implements courseService {

    @Autowired
    private courseRepository courserepository;

    @Override
    public String createCourse(course coursedetail) {
        courserepository.save(coursedetail);
        return "Success";
    }

    @Override
    public String updateCourse() {
        return null;
    }

    @Override
    public String deleteCourse(String courseId) {
        return null;
    }

    @Override
    public course getCourse(String courseId) {

        return courserepository.findById(courseId).get() ;
    }

    @Override
    public List<course> getallCourse() {

        return courserepository.findAll();
    }
}
