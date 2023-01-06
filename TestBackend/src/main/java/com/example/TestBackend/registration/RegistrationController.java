package com.example.TestBackend.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    @CrossOrigin("http://localhost:4200")
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
