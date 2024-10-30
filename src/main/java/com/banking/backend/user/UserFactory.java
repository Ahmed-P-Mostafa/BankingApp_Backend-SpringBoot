package com.banking.backend.user;

import com.banking.backend.registration.RegistrationRequest;

public class UserFactory {
    public static User createUser(RegistrationRequest request) {
        return new User(request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                UserRole.USER);
    }
}