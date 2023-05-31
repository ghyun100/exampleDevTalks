package com.teamproject.devTalks.entity.comment;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.teamproject.devTalks.dto.request.comment.qna.PatchQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PostQnaCommentRequestDto;
import com.teamproject.devTalks.entity.board.QnaBoardEntity;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "qnaComment")
@Table(name = "qnaComment")
public class QnaCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qnaCommentNumber;
    private String commentContent;
    private String writerProfileImageUrl;
    private String writerNickname;
    private String writerEmail;
    private String writeDatetime;
    private int userNumber;
    private int qnaBoardNumber;

    public QnaCommentEntity(UserEntity userEntity, QnaBoardEntity qnaBoardEntity, PostQnaCommentRequestDto dto) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        this.commentContent = dto.getCommentContent();
        this.writerProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.writerNickname = userEntity.getUserNickname();
        this.writerEmail = userEntity.getUserEmail();
        this.writeDatetime = dateFormat.format(now);
        this.userNumber = userEntity.getUserNumber();
        this.qnaBoardNumber = qnaBoardEntity.getQnaBoardNumber();

    }

    public QnaCommentEntity(UserEntity userEntity, QnaBoardEntity qnaBoardEntity, PatchQnaCommentRequestDto dto) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        this.qnaCommentNumber = dto.getQnaCommentNumber();
        this.commentContent = dto.getCommentContent();
        this.writerProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.writerNickname = userEntity.getUserNickname();
        this.writerEmail = userEntity.getUserEmail();
        this.writeDatetime = dateFormat.format(now);
        this.userNumber = userEntity.getUserNumber();
        this.qnaBoardNumber = dto.getQnaBoardNumber();

    }

}
