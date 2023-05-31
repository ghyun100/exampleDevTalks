package com.teamproject.devTalks.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostBlackListRequestDto {

    private int userNumber;
    private String reason;
    
}
