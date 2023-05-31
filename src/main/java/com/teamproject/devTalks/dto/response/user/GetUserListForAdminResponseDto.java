package com.teamproject.devTalks.dto.response.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.resultSet.UserListResultSet;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetUserListForAdminResponseDto extends ResponseDto {

    private List<UserSummary> responseUserList;

    public GetUserListForAdminResponseDto(List<UserListResultSet> resultSets){

        super("SU","Success");
        List<UserSummary> responseUserList = new ArrayList<>();
        for(UserListResultSet result : resultSets){
            UserSummary userSummary = new UserSummary(result);
            responseUserList.add(userSummary);
        }

        this.responseUserList = responseUserList;

    }
}

@Getter
class UserSummary{

    private int userNumber;
    private String userEmail;
    private String userNickname;
    private boolean chatAcceptance;
    private String createdAt;
    private int recommendationCount;

    public UserSummary(UserListResultSet resultSet){

        this.userNumber = resultSet.getUserNumber();
        this.userEmail = resultSet.getUserEmail();
        this.userNickname = resultSet.getUserNickname();
        this.chatAcceptance = resultSet.getChatAcceptance() == 1 ? true : false;
        this.createdAt = resultSet.getCreatedAt();
        this.recommendationCount = resultSet.getRecommendationCount();

    }



}
