package com.teamproject.devTalks.dto.request.board.qna;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostQnaBoardRequestDto {
    
    @NotBlank
    private String qnaTitle;
    @NotBlank
    private String qnaContent;
    private String qnaBoardImageUrl;
    private List<String> boardHashtag;

}
