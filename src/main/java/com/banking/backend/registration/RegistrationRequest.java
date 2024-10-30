package com.banking.backend.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    /*validation on request
    * DTOs on class
    *
    * */

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
}
