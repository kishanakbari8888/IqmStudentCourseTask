package com.example.StudentCourse.service.lmpl;

//import com.example.StudentCourse.Repository.studentRepository;
import com.example.StudentCourse.doa.StudentDao;
import com.example.StudentCourse.repository.StudentRepository;
import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentrepository;
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentRepository studentrepository, StudentDao studentDao) {
        this.studentrepository = studentrepository;
        this.studentDao = studentDao;
    }

    @Override
    public String createStudent(Student studentdetail) throws SQLException {
        studentDao.save(studentdetail);
        return "success";
    }

    @Override
    public String updateStudent(Student studentdetail) throws SQLException {
        studentDao.saveForUpdate(studentdetail);
        return "success";
    }

    @Override
    public String deleteStudent(String studentId) throws SQLException {
        studentDao.deleteById(studentId);
        return "success";
    }

    @Override
    public Student getStudent(String studentId) throws SQLException {
        return studentDao.findById(studentId);
    }

    @Override
    public List<Student> getallStudent() throws SQLException {
        return studentDao.findAll();
    }

}
