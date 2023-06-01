package com.teamproject.devTalks.dto.response.board.qna;

import java.util.ArrayList;
import java.util.List;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.board.QnaBoardEntity;
import com.teamproject.devTalks.entity.comment.QnaCommentEntity;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetQnaBoardResponseDto extends ResponseDto {

    private int qnaBoardNumber;
    private String writerEmail;
    private String qnaTitle;
    private String qnaContent;
    private String qnaBoardImageUrl;
    private String writeDatetime;
    private int viewCount;
    private String writerNickname;
    private String writerProfileImageUrl;
    private List<Comment> commentList;
    private List<String> hashtagList;
    private int qnaHeartCount;
    private List<Integer> heartUserNumberList;
    
    

    public GetQnaBoardResponseDto(
            QnaBoardEntity qnaBoardEntity, UserEntity userEntity, List<QnaCommentEntity> qnaCommentEntities,
            List<String> hashStrings, int qnaHeartCount, List<Integer> heartIntegers) {
        super("SU", "Success");

        this.qnaBoardNumber = qnaBoardEntity.getQnaBoardNumber();
        this.writerEmail = qnaBoardEntity.getWriterEmail();
        this.qnaTitle = qnaBoardEntity.getQnaTitle();
        this.qnaContent = qnaBoardEntity.getQnaContent();
        this.qnaBoardImageUrl = qnaBoardEntity.getQnaBoardImageUrl();
        this.writeDatetime = qnaBoardEntity.getWriteDatetime();
        this.viewCount = qnaBoardEntity.getViewCount();
        this.writerNickname = qnaBoardEntity.getWriterNickname();
        this.writerProfileImageUrl = qnaBoardEntity.getWriterProfileImageUrl();
        this.commentList = createQnaCommentList(qnaCommentEntities);
        this.hashtagList = hashStrings;
        this.qnaHeartCount = qnaHeartCount;
        this.heartUserNumberList = heartIntegers;
        
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Comment {

        private int qnaCommentNumber;
        private int qnaBoardNumber;
        private String commentWriterEmail;
        private String commentContent;
        private String writerNickname;
        private String writerProfileImageUrl;
        private String writeDatetime;

        public Comment(QnaCommentEntity qnaCommentEntity) {

            this.qnaCommentNumber = qnaCommentEntity.getQnaBoardNumber();
            this.qnaBoardNumber = qnaCommentEntity.getQnaBoardNumber();
            this.commentWriterEmail = qnaCommentEntity.getWriterEmail();
            this.commentContent = qnaCommentEntity.getCommentContent();
            this.writerNickname = qnaCommentEntity.getWriterNickname();
            this.writerProfileImageUrl = qnaCommentEntity.getWriterProfileImageUrl();
            this.writeDatetime = qnaCommentEntity.getWriteDatetime();

        }

    }


    private List<Comment> createQnaCommentList(List<QnaCommentEntity> qnaCommentEntities) {

        List<Comment> qnaCommentList = new ArrayList<>();
        for (QnaCommentEntity qnaComments : qnaCommentEntities) {

            Comment qnaComment = new Comment(qnaComments);
            qnaCommentList.add(qnaComment);
        }
        return qnaCommentList;
    }


    //리스트로 반환될때
    // @Data
    // @NoArgsConstructor
    // @AllArgsConstructor
    // class Hashtag {
    //     private String boardHashtag;
    //     public Hashtag(QnaBoardHashTagEntity qnaBoardHashTagEntity){
            
    //         this.boardHashtag = qnaBoardHashTagEntity.getBoardHashtag();
    //     }
    // }

    // private List<Hashtag> createQnaBoardHashtagList(List<QnaBoardHashTagEntity> qnaBoardHashTagEntities) {
        
    //     List<Hashtag> qnaBoardHashtagList = new ArrayList<>();
    //     for (QnaBoardHashTagEntity qnaHashtags : qnaBoardHashTagEntities){
    //         Hashtag qnaHashtag = new Hashtag(qnaHashtags);
    //         qnaBoardHashtagList.add(qnaHashtag);
    //     }
    //     return qnaBoardHashtagList;
    // }
}
