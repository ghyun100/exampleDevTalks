package com.teamproject.devTalks.dto.response.board.teacher;

import java.util.ArrayList;
import java.util.List;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.resultSet.TeacherBoardListResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class GetTeacherBoardListResponseDto extends ResponseDto {
    private List<TeacherBoardSummary> teacherBoardList;

    public GetTeacherBoardListResponseDto(List<TeacherBoardListResultSet> teacherBoardEntityList) {
        super("SU", "SUCCESS");

        List<TeacherBoardSummary> teacherBoardList = new ArrayList<>();

        for (TeacherBoardListResultSet result : teacherBoardEntityList) {
            TeacherBoardSummary teacherBoardSummary = new TeacherBoardSummary(result);
            teacherBoardList.add(teacherBoardSummary);
        }
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class TeacherBoardSummary {
    private int teacherBoardNumber;
    private String teacherTitle;
    private String teacherContent;
    private String writeDatetime;
    private int viewCount;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImageUrl;

    private String teacherBoardImageUrl;
    private int heartCount;
    private String teacherBoardHashtag;

    public TeacherBoardSummary(TeacherBoardListResultSet teacherBoardEntity) {
        this.teacherBoardNumber = teacherBoardEntity.getteacherBoardNumber();
        this.teacherTitle = teacherBoardEntity.getteacherTitle();
        this.writeDatetime = teacherBoardEntity.getwriteDatetime();
        this.viewCount = teacherBoardEntity.getviewCount();
        this.writerEmail = teacherBoardEntity.getwriterEmail();
        this.writerNickname = teacherBoardEntity.getwriterNickname();
        this.writerProfileImageUrl = teacherBoardEntity.getwriterProfileImageUrl();

        this.heartCount = teacherBoardEntity.getheartCount();
        this.teacherBoardHashtag = teacherBoardEntity.gethashTag();
    }
}