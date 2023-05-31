package com.teamproject.devTalks.dto.response.user;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.user.BlackListEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class GetBlackListResponseDto extends ResponseDto {

    private List<BlackListSummary> responseBlackList;

    public GetBlackListResponseDto(List<BlackListEntity> blackListEntityList) {

        super("SU","Success");
        List<BlackListSummary> responseBlackList = new ArrayList<>();

        for(BlackListEntity blackListEntity : blackListEntityList){
            BlackListSummary blackListSummary = new BlackListSummary(blackListEntity);
            responseBlackList.add(blackListSummary);
        }

        this.responseBlackList = responseBlackList;
    }
}

@Getter
@Setter
@NoArgsConstructor
class BlackListSummary{

    private int userNumber;
    private String reason;
    private String createdAt;

    public BlackListSummary(BlackListEntity blackListEntity){

        this.userNumber = blackListEntity.getUserNumber();
        this.reason = blackListEntity.getReason();
        this.createdAt = blackListEntity.getCreatedAt();

    }

}
