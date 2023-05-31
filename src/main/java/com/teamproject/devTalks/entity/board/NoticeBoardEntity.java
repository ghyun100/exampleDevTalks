package com.teamproject.devTalks.entity.board;

import com.teamproject.devTalks.dto.request.board.notice.PatchNoticeBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.notice.PostNoticeBoardRequestDto;
import com.teamproject.devTalks.entity.user.AdminEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity(name = "notice")
@Table(name = "notice")
@NoArgsConstructor
public class NoticeBoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeBoardNumber;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImageUrl;
    private int viewCount;
    private String noticeTitle;
    private String noticeContent;
    private String noticeImageUrl;
    private String writeDatetime;

    public NoticeBoardEntity(PostNoticeBoardRequestDto dto, AdminEntity adminEntity) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.writerEmail = adminEntity.getAdminEmail();
        this.writerNickname = adminEntity.getAdminNickname();
        this.writerProfileImageUrl = adminEntity.getAdminProfileImageUrl();
        this.viewCount = 0;
        this.noticeTitle = dto.getNoticeTitle();
        this.noticeContent = dto.getNoticeContent();
        this.noticeImageUrl = dto.getNoticeImageUrl();
        this.writeDatetime = now.format(formatter);
    }

    public NoticeBoardEntity(NoticeBoardEntity noticeBoardEntity, PatchNoticeBoardRequestDto dto) {
        this.noticeBoardNumber = noticeBoardEntity.getNoticeBoardNumber();
        this.writerEmail = noticeBoardEntity.getWriterEmail();
        this.writerNickname = noticeBoardEntity.getWriterNickname();
        this.writerProfileImageUrl = noticeBoardEntity.getWriterProfileImageUrl();
        this.viewCount = noticeBoardEntity.getViewCount();
        this.noticeTitle = dto.getNoticeTitle();
        this.noticeContent = dto.getNoticeContent();
        this.noticeImageUrl = dto.getBoardImageUrl();
        this.writeDatetime = noticeBoardEntity.getWriteDatetime();



    }
}
