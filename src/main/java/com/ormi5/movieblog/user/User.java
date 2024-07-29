package com.ormi5.movieblog.user;

import jakarta.persistence.*;

// EMPTY
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

}