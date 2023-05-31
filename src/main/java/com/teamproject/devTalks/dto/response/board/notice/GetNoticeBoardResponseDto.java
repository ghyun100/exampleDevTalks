package com.teamproject.devTalks.dto.response.board.notice;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.board.NoticeBoardEntity;
import com.teamproject.devTalks.entity.user.AdminEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetNoticeBoardResponseDto extends ResponseDto {

    private int noticeBoardNumber;
    private String writerNickname;
    private String writerProfileImageUrl;
    private String writeDatetime;
    private String noticeTitle;
    private String noticeContent;
    private int viewCount;
    private String noticeImageUrl;

    public GetNoticeBoardResponseDto(
            NoticeBoardEntity noticeBoardEntity,
            AdminEntity adminEntity
    ) {

        super("SU", "Success");

        this.noticeBoardNumber = noticeBoardEntity.getNoticeBoardNumber();
        this.writerNickname = adminEntity.getAdminNickname();
        this.writerProfileImageUrl = adminEntity.getAdminProfileImageUrl();
        this.writeDatetime = noticeBoardEntity.getWriteDatetime();
        this.noticeTitle = noticeBoardEntity.getNoticeTitle();
        this.noticeContent = noticeBoardEntity.getNoticeContent();
        this.viewCount = noticeBoardEntity.getViewCount();
        this.noticeImageUrl = getNoticeImageUrl();

    }

}
