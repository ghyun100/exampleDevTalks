package com.teamproject.devTalks.dto.request.comment.qna;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchQnaCommentRequestDto {

    @NotNull // null이 아니어야함
    private int qnaBoardNumber;
    @NotNull
    private int qnaCommentNumber;
    @NotBlank // 값이 공백 문자열이나 null이 아니어야함
    private String commentContent;

}
