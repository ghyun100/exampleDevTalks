package com.teamproject.devTalks.dto.request.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAdminRequestDto {

    @NotBlank
    private String password;
    @NotBlank
    private String adminNickname;
    @NotBlank @Pattern(regexp="^\\d{3}-\\d{3,4}-\\d{4}$")
    private String adminPhoneNumber;
    @NotBlank
    private String adminProfileImageUrl;

}
