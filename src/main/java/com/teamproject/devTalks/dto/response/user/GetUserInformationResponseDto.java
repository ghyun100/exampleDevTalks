package com.teamproject.devTalks.dto.response.user;


import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.user.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class GetUserInformationResponseDto extends ResponseDto {

    private int userNumber;
    private String userProfileImageUrl;
    private String userNickname;
    private boolean chatAcceptance;
    private String userIntroduction;
    private List<String>userHashTag;
    private int recommendationCount;


    public GetUserInformationResponseDto(
            List<String> hashtagList,
            UserEntity userEntity,
            int recommendationCount

    ) {
        super("SU","Success");
        this.userNumber = userEntity.getUserNumber();
        this.userProfileImageUrl= userEntity.getUserProfileImageUrl();
        this.userNickname = userEntity.getUserNickname();
        this.chatAcceptance = userEntity.isChatAcceptance();
        this.userIntroduction = userEntity.getUserIntroduction();
        this.userHashTag = hashtagList;
        this.recommendationCount = recommendationCount;



    }
}
