package com.banking.backend.user;

import com.banking.backend.registration.token.ConfirmationToken;
import com.banking.backend.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("user with email %s not found", email))
        );
    }

    public String signUpUser(User user){
        boolean isExist = repository.findByEmail(user.getEmail()).isPresent();
        if (isExist){
            User u = repository.findByEmail(user.getEmail()).get();
            if (!u.getEnabled()){
                return createToken(user);
            }
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);
        return createToken( user);

    }

    private String createToken(User user){
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public int enableUser(String email) {
        return repository.enableUser(email);
    }
}
