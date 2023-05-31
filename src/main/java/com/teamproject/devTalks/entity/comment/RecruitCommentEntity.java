package com.teamproject.devTalks.entity.comment;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.teamproject.devTalks.dto.request.comment.recruit.PostRecruitCommentRequestDto;
import com.teamproject.devTalks.entity.board.RecruitBoardEntity;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="RecruitComment")
@Table(name="RecruitComment")
public class RecruitCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recruitBoardNumber;
    private int recruitCommentNumber;
    private int userNumber;
    private String recruitCommentContent;
    private String writerNickname;
    private String writerEmail;
    private String writerProfileImageUrl;
    private String writeDatetime;
    
    public RecruitCommentEntity(UserEntity userEntity, RecruitBoardEntity recruitBoardEntity, PostRecruitCommentRequestDto dto) {
        
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.recruitBoardNumber = recruitBoardEntity.getRecruitBoardNumber();
        this.userNumber = userEntity.getUserNumber();
        this.recruitCommentContent = dto.getRecruitCommentContent();
        this.writerNickname = userEntity.getUserNickname();
        this.writerEmail = userEntity.getUserEmail();
        this.writerProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.writeDatetime = writeDatetime;
    }

}
