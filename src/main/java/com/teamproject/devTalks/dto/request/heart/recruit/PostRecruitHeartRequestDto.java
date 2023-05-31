package com.teamproject.devTalks.dto.request.heart.recruit;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRecruitHeartRequestDto {

    @NotNull
    private int recruitBoardNumber;

}
