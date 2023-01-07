package com.example.TestBackend;

import com.example.TestBackend.model.Role;
import com.example.TestBackend.model.Student;
import com.example.TestBackend.service.StudentService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentResource {
    private final StudentService studentService;

    @GetMapping("/all")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Student> getStudentsById(@PathVariable("id") Long id){
        Student students = studentService.findStudentById(id);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/add")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Student> postStudent( @RequestBody Student student){
        Student student1 = studentService.saveStudent(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @PostMapping("/role/add")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Role> postRole(@RequestBody Role role){
        Role role1 = studentService.saveRole(role);
        return new ResponseEntity<>(role1, HttpStatus.CREATED);
    }

    @PostMapping("/role/addtouser")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<?> roleToUser(@RequestBody RoleToUserForm form){
        studentService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<Student> updateStudent( @RequestBody Student student){
        Student updateStudent = studentService.updateStudent(student);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<?> deleteStudent( @PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

@Data
@Getter
class RoleToUserForm{
    private String email;
    private String roleName;
}