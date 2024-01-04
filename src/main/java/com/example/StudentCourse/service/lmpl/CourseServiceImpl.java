package com.example.StudentCourse.service.lmpl;

import com.example.StudentCourse.doa.CourseDao;
import com.example.StudentCourse.repository.CourseRepository;
import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courserepository;


    @Autowired
    private CourseDao courseDao;

    @Override
    public String createCourse(Course coursedetail) throws SQLException {
        courseDao.save(coursedetail);
        return "Success";
    }

    @Override
    public String updateCourse(Course coursedetail) throws SQLException {
        courseDao.saveForUpdate(coursedetail);
        return "success";
    }

    @Override
    public String deleteCourse(String courseId) throws SQLException {
        courseDao.deleteById(courseId);
        return "success";
    }

    @Override
    public Course getCourse(String courseId) throws SQLException {

        return courseDao.findById(courseId);
    }

    @Override
    public List<Course> getallCourse() throws SQLException {
        try {
            return courseDao.findAll();
        }catch (SQLException e){
            throw e;
        }

    }
}
