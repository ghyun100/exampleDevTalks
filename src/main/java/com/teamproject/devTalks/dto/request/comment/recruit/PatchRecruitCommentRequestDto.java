package com.teamproject.devTalks.dto.request.comment.recruit;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchRecruitCommentRequestDto {
    
    @NotNull
    private int recruitBoardNumber;
    @NotNull
    private int recruitCommentNumber;
    @NotBlank
    private String recruitCommentContent;

}
