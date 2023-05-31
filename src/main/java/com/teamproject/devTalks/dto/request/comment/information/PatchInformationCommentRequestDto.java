package com.teamproject.devTalks.dto.request.comment.information;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchInformationCommentRequestDto {
    @NotNull
    private Integer informationCommentNumber;
    @NotBlank
    private String informationCommentContent;

    public PatchInformationCommentRequestDto(PatchInformationCommentRequestDto dto) {
        this.informationCommentNumber = dto.getInformationCommentNumber();
        this.informationCommentContent = dto.getInformationCommentContent();
    }
}
