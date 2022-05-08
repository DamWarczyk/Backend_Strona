package com.example.TestBackend.service;

import com.example.TestBackend.exepction.UsernNotFoundException;
import com.example.TestBackend.model.Student;
import com.example.TestBackend.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private  final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Student addStudent(Student student){
        return studentRepo.save(student);
    }

    public List<Student> findAllStudents(){
        return studentRepo.findAll();
    }

    public Student updateStudent(Student student){
        return studentRepo.save(student);
    }

    public Student findStudentById(Long id){
        return studentRepo.findStudentById(id).orElseThrow(() -> new UsernNotFoundException("User by id: " + id + "Not found"));
    }

    public  void deleteStudent(Long id){
        studentRepo.deleteStudentById(id);
    }
}
