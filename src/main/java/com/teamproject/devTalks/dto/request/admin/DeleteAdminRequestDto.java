package com.teamproject.devTalks.dto.request.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class DeleteAdminRequestDto {

    @NotBlank
    private String adminPassword;
}
