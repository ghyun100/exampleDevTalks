package com.teamproject.devTalks.dto.response.user;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserEmailResponseDto extends ResponseDto{
    
    private String userEmail;

    public FindUserEmailResponseDto(UserEntity userEntity){
        
        super("SU", "Success");

        this.userEmail = userEntity.getUserEmail();
    }  


}
