package com.teamproject.devTalks.dto.request.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminSignInRequestDto {

    private String adminEmail;
    private String adminPassword;
}
