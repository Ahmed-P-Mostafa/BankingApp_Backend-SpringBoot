package com.banking.backend.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}

/*
    curl --location --request POST 'localhost:8080/api/v1/registration' \
        --header 'Content-Type: application/json' \
            --data-raw '{
            "firstName": "Amigos",
            "lastName": "Code",
            "email": "hellow@amigoscode.com",
            "password": "password"
            }
*/
