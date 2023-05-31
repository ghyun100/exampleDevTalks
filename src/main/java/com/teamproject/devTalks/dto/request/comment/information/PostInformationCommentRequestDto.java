package com.teamproject.devTalks.dto.request.comment.information;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostInformationCommentRequestDto {
    @NotNull
    private Integer informationBoardNumber;
    @NotBlank
    private String informationCommentContent;

}