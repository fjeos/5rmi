package com.ormi5.movieblog.user;

import com.ormi5.movieblog.post.ProfilePostResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ProfileResponseDto {

    private String username;
    private int level;
    private int reviewCount;
    private LocalDateTime signUpDate;
    private List<ProfilePostResponseDto> postList;

}
