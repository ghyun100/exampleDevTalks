package com.teamproject.devTalks.dto.response.user;

import com.teamproject.devTalks.dto.response.ResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AdminSignInResponseDto extends ResponseDto {

    private String token;
    private int expiredTime;

    public AdminSignInResponseDto(String token){
        super("SU","Success");
        this.token = token;
        this.expiredTime = 3600;
    }
}
