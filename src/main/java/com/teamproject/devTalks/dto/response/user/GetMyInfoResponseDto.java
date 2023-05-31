package com.teamproject.devTalks.dto.response.user;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetMyInfoResponseDto extends ResponseDto {

    private int userNumber;
    private String userEmail;
    private String userNickname;
    private String userPhoneNumber;
    private String userIntroduction;
    private String userProfileImageUrl;
    private boolean chatAcceptance;
    private List<String> userHashTag;
    private int recommendationCount;

    public GetMyInfoResponseDto(

            List<String> hashtagList ,
            UserEntity userEntity,
            int recommendationCount

    ){
        super("SU","Success");
        this.userNumber = userEntity.getUserNumber();
        this.userEmail = userEntity.getUserEmail();
        this.userNickname = userEntity.getUserNickname();
        this.userPhoneNumber = userEntity.getUserPhoneNumber();
        this.userIntroduction = userEntity.getUserIntroduction();
        this.userProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.chatAcceptance = userEntity.isChatAcceptance();
        this.userHashTag = hashtagList;
        this.recommendationCount = recommendationCount;

    }

}
