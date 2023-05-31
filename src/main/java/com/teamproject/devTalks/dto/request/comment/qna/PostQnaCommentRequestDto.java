package com.teamproject.devTalks.dto.request.comment.qna;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostQnaCommentRequestDto {

    @NotNull
    private int qnaBoardNumber;
    @NotBlank
    private String commentContent;

}
