package com.banking.backend.registration;

import com.banking.backend.email.EmailSender;
import com.banking.backend.registration.token.ConfirmationToken;
import com.banking.backend.registration.token.ConfirmationTokenService;
import com.banking.backend.user.User;
import com.banking.backend.user.UserFactory;
import com.banking.backend.user.UserRole;
import com.banking.backend.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.banking.backend.Utils.buildEmail;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        String token = userService.signUpUser(
                UserFactory.createUser(request)
        );
        String link = "http://localhost:8080/api/v1/registration/confirm?token="+token;
        emailSender.send(
                request.getEmail(),
                buildEmail(request.getFirstName(),link)
        );
        return token;
    }

    @Transactional
    public String confirmToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow( () -> new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() !=null){
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);

        return  (userService.enableUser(confirmationToken.getUser().getEmail()) == 1)? "Confirmed":"Something wrong happen";
    }
}
