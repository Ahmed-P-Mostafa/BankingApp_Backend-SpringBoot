package com.banking.backend.registration.token;

import com.banking.backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.banking.backend.Constants.TOKEN_SEQUENCE_NAME;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @SequenceGenerator(
            name = TOKEN_SEQUENCE_NAME,
            sequenceName = TOKEN_SEQUENCE_NAME,
            allocationSize = 1)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = TOKEN_SEQUENCE_NAME
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false,
    name = "user_id")
    private User user;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             User user) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
