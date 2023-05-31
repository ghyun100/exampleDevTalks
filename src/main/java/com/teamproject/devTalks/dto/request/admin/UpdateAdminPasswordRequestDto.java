package com.teamproject.devTalks.dto.request.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UpdateAdminPasswordRequestDto {
    @NotBlank
    private String currentPassword;
    @NotBlank
    private String changePassword;
}
