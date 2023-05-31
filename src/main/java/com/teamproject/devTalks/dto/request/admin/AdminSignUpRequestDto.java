package com.teamproject.devTalks.dto.request.admin;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Getter
@Setter
@NoArgsConstructor
public class AdminSignUpRequestDto {

    @NotBlank @Email
    private String adminEmail;
    @NotBlank
    private String adminPassword;
    @NotBlank
    private String adminNickname;
    @NotBlank @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$")
    private String adminPhoneNumber;
    @NotBlank
    private String adminName;
    private String adminProfileImageUrl;
    @NotNull
    private boolean agreePersonalInformation;



}
