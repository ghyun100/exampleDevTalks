package com.teamproject.devTalks.dto.request.comment.recruit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRecruitCommentRequestDto {
    
    @NotNull
    private int recruitBoardNumber;
    @NotBlank
    private String recruitCommentContent;

}
