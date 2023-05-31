package com.teamproject.devTalks.dto.request.board.notice;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PostNoticeBoardRequestDto {
    @NotBlank
    private String noticeTitle;
    @NotBlank
    private String noticeContent;
    private String noticeImageUrl;
    
}
