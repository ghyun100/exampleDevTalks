package com.teamproject.devTalks.dto.request.board.information;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class PatchInformationBoardRequestDto {
    @NotNull
    private Integer informationBoardNumber;
    @NotBlank
    private String informationBoardTitle;
    @NotBlank
    private String informationBoardContent;
    private String informationBoardImageUrl;
    @NotBlank
    private String contentSource;
    private List<String> boardHashtag;
    
}
