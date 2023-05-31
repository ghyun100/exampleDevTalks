package com.teamproject.devTalks.dto.request.board.qna;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchQnaBoardRequestDto {
    
    @NotNull
    private int qnaBoardNumber;
    @NotBlank
    private String qnaTitle;
    @NotBlank
    private String qnaContent;
    private String qnaBoardImageUrl;
    // 해시태그는?
}
