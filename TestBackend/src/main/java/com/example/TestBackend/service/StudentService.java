package com.example.TestBackend.service;

import com.example.TestBackend.exepction.UsernNotFoundException;
import com.example.TestBackend.model.Student;
import com.example.TestBackend.repo.StudentRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements UserDetailsService {
    private final StudentRepo studentRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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


    public void deleteStudent(Long id){
        studentRepo.deleteStudentById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public String signUpStudent(Student student){
        boolean studentExists = studentRepo.findByEmail(student.getEmail()).isPresent();

        if (studentExists){
            throw new IllegalStateException("Email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(student.getPassword());

        student.setPassword(encodedPassword);

        studentRepo.save(student);

        //TODO: SEND CONFIMRATION TOKEN

        return "it works";
    }
}
