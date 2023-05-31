package com.teamproject.devTalks.dto.response.board.recruit;

import java.util.ArrayList;
import java.util.List;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.board.RecruitBoardEntity;
import com.teamproject.devTalks.entity.comment.RecruitCommentEntity;
import com.teamproject.devTalks.entity.hashTag.RecruitBoardHashTagEntity;
import com.teamproject.devTalks.entity.heart.RecruitHeartEntity;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRecruitBoardResponseDto extends ResponseDto {
    private int recruitBoardNumber;
    private String recruitBoardTitle;
    private String recruitBoardContent;
    private String recruitBoardImageUrl;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImageUrl;
    private int viewCount;
    private List<RecruitComment> commentList;
    private List<RecruitHeart> heartList;
    private List<String> boardHashTagList;
    private boolean recruitmentStatus;

    public GetRecruitBoardResponseDto(
        RecruitBoardEntity recruitBoardEntity, UserEntity userEntity, List<RecruitCommentEntity> recruitCommentEntities, 
        List<RecruitHeartEntity> recruitHeartEntities, List<String> boardHashTagList
    ) {
        super("SU", "Success");

        this.recruitBoardNumber = recruitBoardEntity.getRecruitBoardNumber();
        this.recruitBoardTitle = recruitBoardEntity.getRecruitBoardTitle();
        this.recruitBoardContent = recruitBoardEntity.getRecruitBoardContent();
        this.recruitBoardImageUrl = recruitBoardEntity.getRecruitBoardImageUrl();
        this.recruitmentStatus = recruitBoardEntity.isRecruitmentStatus();
        this.writeDatetime = recruitBoardEntity.getWriteDatetime();
        this.writerEmail = userEntity.getUserEmail();
        this.writerNickname = userEntity.getUserNickname();
        this.writerProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.viewCount = recruitBoardEntity.getViewCount();
        this.recruitmentStatus = recruitBoardEntity.isRecruitmentStatus();
        this.commentList = RecruitComment.createList(recruitCommentEntities);
        this.heartList = RecruitHeart.createList(recruitHeartEntities);
        this.boardHashTagList = boardHashTagList;

    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class RecruitComment {
    private int recruitBoardNumber;
    private int recruitCommentNumber;
    private int userNumber;
    private String recruitCommentContent;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImageUrl;
    private String writeDatetime;

    RecruitComment(RecruitCommentEntity recruitCommentEntity) {
        this.recruitBoardNumber = recruitCommentEntity.getRecruitBoardNumber();
        this.recruitCommentNumber = recruitCommentEntity.getRecruitCommentNumber();
        this.userNumber = recruitCommentEntity.getUserNumber();
        this.recruitCommentContent = recruitCommentEntity.getRecruitCommentContent();
        this.writerEmail = recruitCommentEntity.getWriterEmail();
        this.writerNickname = recruitCommentEntity.getWriterNickname();
        this.writerProfileImageUrl = recruitCommentEntity.getWriterProfileImageUrl();
        this.writeDatetime = recruitCommentEntity.getWriteDatetime();
    }

    static List<RecruitComment> createList(List<RecruitCommentEntity> recruitCommentEntities) {
        List<RecruitComment> recruitCommentList = new ArrayList<>();
        for(RecruitCommentEntity recruitCommentEntity: recruitCommentEntities) {
            RecruitComment recruitComment = new RecruitComment(recruitCommentEntity);
            recruitCommentList.add(recruitComment);
        }
        return recruitCommentList;
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class RecruitHeart {
    private int recruitBoardNumber;
    private int userNumber;
    

    RecruitHeart(RecruitHeartEntity recruitHeartEntity) {
    this.recruitBoardNumber = recruitHeartEntity.getRecruitBoardNumber();
    this.userNumber = recruitHeartEntity.getUserNumber();
    }

    static List<RecruitHeart> createList(List<RecruitHeartEntity> recruitHeartEntities) {
        List<RecruitHeart> recruitHeartList = new ArrayList<>();
        for(RecruitHeartEntity recruitHeartEntity: recruitHeartEntities) {
            RecruitHeart recruitHeart = new RecruitHeart(recruitHeartEntity);
            recruitHeartList.add(recruitHeart);
        }
        return recruitHeartList;
    }

}



