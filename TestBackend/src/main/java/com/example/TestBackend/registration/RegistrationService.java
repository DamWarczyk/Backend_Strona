package com.example.TestBackend.registration;

import com.example.TestBackend.model.Student;
import com.example.TestBackend.model.userRole;
import com.example.TestBackend.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final StudentService studentService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        return studentService.signUpStudent(new Student(
                request.getName(),
                request.getSurname(),
                request.getEmail(),
                request.getPassword(),
                userRole.USER));
    }
}
