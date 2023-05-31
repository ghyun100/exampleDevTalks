package com.teamproject.devTalks.dto.request.board.notice;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class PatchNoticeBoardRequestDto {

    @NotNull
    private int noticeBoardNumber;
    @NotBlank
    private String noticeTitle;
    @NotBlank
    private String noticeContent;
    @NotBlank
    private String boardImageUrl;

    
}
