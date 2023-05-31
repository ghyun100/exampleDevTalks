package com.teamproject.devTalks.entity.comment;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.teamproject.devTalks.dto.request.comment.information.PostInformationCommentRequestDto;
import com.teamproject.devTalks.entity.board.InformationBoardEntity;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="information_comment")
@Table(name="information_comment")
public class InformationCommentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int informationCommentNumber;
    private String informationCommentContent;
    private String writerNickname;
    private String writerEmail;
    private String writerProfileImageUrl;
    private String writeDatetime;
    private int userNumber;
    private int informationBoardNumber;


    public InformationCommentEntity (UserEntity userEntity, InformationBoardEntity informationBoardEntity, PostInformationCommentRequestDto dto) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        this.informationCommentContent = dto.getInformationCommentContent();
        this.writerProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.writerNickname = userEntity.getUserNickname();
        this.writerEmail = userEntity.getUserEmail();
        this.writeDatetime = dateFormat.format(now);
        this.userNumber = userEntity.getUserNumber();
        this.informationBoardNumber = informationBoardEntity.getInformationBoardNumber();

    }
}
