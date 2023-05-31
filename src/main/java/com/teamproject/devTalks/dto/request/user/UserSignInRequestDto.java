package com.teamproject.devTalks.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInRequestDto {

    private String userEmail;
    private String userPassword;
}
