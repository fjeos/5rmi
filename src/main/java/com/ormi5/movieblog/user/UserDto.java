package com.ormi5.movieblog.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link User}
 */
@Value
@Builder
@AllArgsConstructor
public class UserDto implements Serializable {
    Integer id;
    String username;
    String email;
    Integer level;
    Boolean isStop;
    Instant signupDate;
    String password;
    Boolean op;

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .level(this.level)
                .isStop(this.isStop)
                .signupDate(this.signupDate)
                .password(this.password)
                .op(this.op)
                .build();
    }

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .level(user.getLevel())
                .isStop(user.getIsStop())
                .signupDate(user.getSignupDate())
                .password(user.getPassword())
                .op(user.getOp())
                .build();
    }
}