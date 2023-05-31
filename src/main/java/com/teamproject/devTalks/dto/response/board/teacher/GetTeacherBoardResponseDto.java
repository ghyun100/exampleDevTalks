package com.teamproject.devTalks.dto.response.board.teacher;

import java.util.List;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.board.TeacherBoardEntity;
import com.teamproject.devTalks.entity.heart.TeacherHeartEntity;
import com.teamproject.devTalks.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class GetTeacherBoardResponseDto extends ResponseDto {
    
    private int teacherBoardNumber;
    private String teacherTitle;
    private String teacherContent;
    private String writeDatetime;
    private int viewCount;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImageUrl;
    
    private String teacherBoardImageUrl;
    private String career;
    private String lectureUrl;
    private String category;

    public GetTeacherBoardResponseDto(
        TeacherBoardEntity teacherBoardEntity, UserEntity userEntity, int teacherHeartCount,
        List<TeacherHeartEntity> teacherHeartEntities,
        List<String> heartList
        
        ){
            super("SU","Success");
            
            this.teacherBoardNumber = teacherBoardEntity.getTeacherBoardNumber();
            this.teacherTitle = teacherBoardEntity.getTeacherTitle();
            this.teacherContent = teacherBoardEntity.getTeacherContent();
            this.writeDatetime = teacherBoardEntity.getWriteDatetime();
            this.viewCount = teacherBoardEntity.getViewCount();
            this.writerEmail = teacherBoardEntity.getWriterEmail();
            this.writerNickname = teacherBoardEntity.getWriterNickname();
            this.writerProfileImageUrl = teacherBoardEntity.getWriterProfileImageUrl();
            
            this.teacherBoardImageUrl = teacherBoardEntity.getTeacherBoardImageUrl();
            this.career = teacherBoardEntity.getCareer();
            this.lectureUrl = teacherBoardEntity.getLectureUrl();
            this.category = teacherBoardEntity.getCategory();
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        class Heart {
    
            private int userNumber;
            private int teacherBoardNumber;
    
            public Heart(TeacherHeartEntity teacherHeartEntity) {
                this.userNumber = teacherHeartEntity.getUserNumber();
                this.teacherBoardNumber = teacherHeartEntity.getTeacherBoardNumber();
            }
        }

        public int getHeartCount(List<TeacherHeartEntity> teacherHeartEntities) {
            return teacherHeartEntities.size();
        }
}
