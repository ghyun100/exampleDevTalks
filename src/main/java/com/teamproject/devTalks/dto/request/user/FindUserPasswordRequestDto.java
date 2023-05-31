package com.teamproject.devTalks.dto.request.user;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserPasswordRequestDto {
    
    @NotBlank
    private String userEmail;

}
