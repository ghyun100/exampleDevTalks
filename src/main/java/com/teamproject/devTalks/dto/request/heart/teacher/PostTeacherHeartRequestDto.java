package com.teamproject.devTalks.dto.request.heart.teacher;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTeacherHeartRequestDto {
    @NotBlank
    private int teacherBoardNumber;
}
