package com.ormi5.movieblog.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 32)
    private String username;

    @Column(name = "email", nullable = false, length = 256)
    private String email;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "is_stop", nullable = false)
    private Boolean isStop;

    @Column(name = "signup_date")
    private Instant signupDate;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "op", nullable = false)
    private Boolean op;
}