package com.example.StudentCourse.service.lmpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StudentCourse.doa.CourseDao;
import com.example.StudentCourse.entities.Course;
import com.example.StudentCourse.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    /**
     * Add course detail to postgres database
     * @param coursedetail
     * @return message
     * @throws SQLException
     */
    @Override
    public String createCourse(Course coursedetail) throws SQLException {
        courseDao.save(coursedetail);
        return "Success";
    }

    /**
     * update course base on id in database
     * @param coursedetail
     * @return message
     * @throws SQLException
     */
    @Override
    public String updateCourse(Course coursedetail) throws SQLException {
        courseDao.saveForUpdate(coursedetail);
        return "success";
    }

    /**
     * delete course base on id
     * @param courseId
     * @return
     * @throws SQLException
     */
    @Override
    public String deleteCourse(String courseId) throws SQLException {
        courseDao.deleteById(courseId);
        return "success";
    }

    /**
     * find course base on id
     * @param courseId
     * @return course
     * @throws SQLException
     */
    @Override
    public Course getCourse(String courseId) throws SQLException {
        return courseDao.findById(courseId);
    }

    /**
     * find all course present in database
     * @return Courses
     * @throws SQLException
     */
    @Override
    public List<Course> getallCourse(Long pageNo,Long size,String field,String patten) throws SQLException {
        try {
            return courseDao.findAll(pageNo,size,field,patten);
        }catch (SQLException e){
            throw e;
        }

    }
}
