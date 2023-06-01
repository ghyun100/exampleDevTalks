package com.teamproject.devTalks.dto.request.heart.information;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostInformationHeartRequestDto {
    
    @NotNull
    private Integer informationBoardNumber;
}
