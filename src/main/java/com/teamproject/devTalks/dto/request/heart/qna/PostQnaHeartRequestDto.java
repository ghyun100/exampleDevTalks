package com.teamproject.devTalks.dto.request.heart.qna;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostQnaHeartRequestDto {

    @NotNull
    private int qnaBoardNumber;

}
