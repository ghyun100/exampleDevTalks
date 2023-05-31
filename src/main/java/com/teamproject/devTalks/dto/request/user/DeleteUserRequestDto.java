package com.teamproject.devTalks.dto.request.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeleteUserRequestDto {

    @NotBlank
    private String userPassword;
}
