package com.teamproject.devTalks.dto.request.board.teacher;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchTeacherBoardRequestDto {
    @NotNull
    private int teacherBoardNumber;
    @NotBlank
    private String teacherTitle;
    private String category;
    private String lectureUrl;
    private String career;
    @NotBlank
    private String teacherContent;
    private String teacherBoardImageUrl;
    private List<String> boardHashtag;
    private boolean recruitmentStatus;

    public PatchTeacherBoardRequestDto(PatchTeacherBoardRequestDto dto){

        this.teacherBoardNumber = dto.getTeacherBoardNumber();
        this.teacherTitle = dto.getTeacherTitle();
        this.category = dto.getCategory();
        this.lectureUrl = dto.getLectureUrl();
        this.career = dto.getCareer();
        this.teacherContent = dto.getTeacherContent();
        this.teacherBoardImageUrl = dto.getTeacherBoardImageUrl();
        this.recruitmentStatus = dto.isRecruitmentStatus();
    }
}
