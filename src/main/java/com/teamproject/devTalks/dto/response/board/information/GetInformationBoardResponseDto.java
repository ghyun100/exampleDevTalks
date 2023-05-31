package com.teamproject.devTalks.dto.response.board.information;

import java.util.ArrayList;
import java.util.List;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.board.InformationBoardEntity;
import com.teamproject.devTalks.entity.comment.InformationCommentEntity;
import com.teamproject.devTalks.entity.heart.InformationHeartEntity;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetInformationBoardResponseDto extends ResponseDto {
    private int informationBoardNumber;
    private String writerProfileImageUrl;
    private String writerNickname;
    private String writerEmail;
    private String informationBoardTitle;
    private String informationBoardContent;
    private String informationBoardImageUrl;
    private String contentSource;
    private int viewCount;
    private String writeDatetime;
    private List<Comment> commentList;
    private int heartCount;
    private List<String> heartList;
    private List<String> hashtagList;

    public GetInformationBoardResponseDto(
        InformationBoardEntity informationBoardEntity, UserEntity userEntity,
        List<InformationCommentEntity> informationCommentEntities, List<InformationHeartEntity> informationHeartEntities,
        List<String> heartList, List<String> hashtagList
        ) {   

        super("SU", "Success");

        this.informationBoardNumber = informationBoardEntity.getInformationBoardNumber();
        this.writerProfileImageUrl = userEntity.getUserProfileImageUrl();
        this.writerNickname = userEntity.getUserNickname();
        this.writerEmail = userEntity.getUserEmail();
        this.informationBoardTitle = informationBoardEntity.getInformationBoardTitle();
        this.informationBoardContent = informationBoardEntity.getInformationBoardContent();
        this.informationBoardImageUrl = informationBoardEntity.getInformationBoardImageUrl();
        this.contentSource = informationBoardEntity.getContentSource();
        this.viewCount = informationBoardEntity.getViewCount();
        this.writeDatetime = informationBoardEntity.getWriteDatetime();
        this.commentList = createCommentList(informationCommentEntities);
        this.heartCount = getHeartCount(informationHeartEntities);
        this.heartList = heartList;
   
    }

    public int getHeartCount(List<InformationHeartEntity> informationHeartEntities) {
        return informationHeartEntities.size();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Comment {
        private int informationCommentNumber;
        private String informationCommentContent;
        private String writerNickname;
        private String writerEmail;
        private String writerProfileImageUrl;
        private String writeDatetime;
        private int informationBoardNumber;
    
        public Comment(InformationCommentEntity informationCommentEntity) {
            this.informationCommentNumber = informationCommentEntity.getInformationCommentNumber();
            this.informationCommentContent = informationCommentEntity.getInformationCommentContent();
            this.writerNickname = informationCommentEntity.getWriterNickname();
            this.writerEmail = informationCommentEntity.getWriterEmail();
            this.writerProfileImageUrl = informationCommentEntity.getWriterProfileImageUrl();
            this.writeDatetime = informationCommentEntity.getWriteDatetime();
            this.informationBoardNumber = informationCommentEntity.getInformationBoardNumber();
        }
    }
    
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InformationHeart {
        private int informationBoardNumber;
        private int userNumber;
    
        public InformationHeart(InformationHeartEntity informationHeartEntity) {
            this.informationBoardNumber = informationHeartEntity.getInformationBoardNumber();
            this.userNumber = informationHeartEntity.getUserNumber();
        }
    }
    
    private List<Comment> createCommentList(List<InformationCommentEntity> informationCommentEntities) {
        List<Comment> commentList = new ArrayList<>();
        for (InformationCommentEntity informationCommentEntity : informationCommentEntities) {
            Comment comment = new Comment(informationCommentEntity);
            commentList.add(comment);
        }
        return commentList;
    }
    
}    
