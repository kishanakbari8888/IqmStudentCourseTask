package com.example.StudentCourse.service.lmpl;

import com.example.StudentCourse.Repository.studentRepository;
import com.example.StudentCourse.entities.student;
import com.example.StudentCourse.service.studentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentServiceImpl implements studentService {

    @Autowired
    private studentRepository studentrepository;

    @Override
    public String createStudent(student studentdetail) {
        studentrepository.save(studentdetail);
        return "success";
    }

    @Override
    public String updateStudent(student studentdetail) {
        studentrepository.save(studentdetail);
        return "success";
    }

    @Override
    public String deleteStudent(String studentId) {
        studentrepository.deleteById(studentId);
        return "success";
    }

    @Override
    public student getStudent(String studentId) {
        return studentrepository.findById(studentId).get();
    }

    @Override
    public List<student> getallStudent() {
        return studentrepository.findAll();
    }
}
