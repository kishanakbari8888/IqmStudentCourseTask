package com.example.StudentCourse.service.lmpl;

//import com.example.StudentCourse.Repository.studentRepository;
import com.example.StudentCourse.Repository.StudentRepository;
import com.example.StudentCourse.entities.Student;
import com.example.StudentCourse.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentrepository;

    @Override
    public String createStudent(Student studentdetail) {
        studentrepository.save(studentdetail);
        return "success";
    }

    @Override
    public String updateStudent(Student studentdetail) {
        studentrepository.save(studentdetail);
        return "success";
    }

    @Override
    public String deleteStudent(String studentId) {
        studentrepository.deleteById(studentId);
        return "success";
    }

    @Override
    public Student getStudent(String studentId) {
        return studentrepository.findById(studentId).get();
    }

    @Override
    public List<Student> getallStudent() {
        return studentrepository.findAll();
    }

}
