package com.example.TestBackend.repo;

import com.example.TestBackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface StudentRepo extends JpaRepository<Student, Long> {

    void deleteStudentById(Long id);

    Optional<Student> findStudentById(Long id);

    Optional<Student> findByEmail(String email);
}
