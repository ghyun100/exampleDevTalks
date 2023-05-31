package com.teamproject.devTalks.dto.response.board.notice;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.board.NoticeBoardEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetNoticeBoardListResponseDto extends ResponseDto {

    private List<NoticeSummary> responeNoticeList;

    public GetNoticeBoardListResponseDto(List<NoticeBoardEntity> noticeBoardEntityList) {

        super("SU", "Success");
        List<NoticeSummary> responeNoticeList = new ArrayList<>();

        for(NoticeBoardEntity noticeBoardEntity : noticeBoardEntityList){

            NoticeSummary noticeSummary = new NoticeSummary(noticeBoardEntity);
            responeNoticeList.add(noticeSummary);

        }
        this.responeNoticeList = responeNoticeList;

    }

}

@Getter
@Setter
@NoArgsConstructor
class NoticeSummary{

    private int noticeBoardNumber;
    private String noticeTitle;
    private String writeDatetime;
    private int viewCount;
    private String writerNickname;
    private String writerEmail;
    private String noticeImageUrl;

    public NoticeSummary(NoticeBoardEntity noticeBoardEntity) {
        this.noticeBoardNumber = noticeBoardEntity.getNoticeBoardNumber();
        this.noticeTitle = noticeBoardEntity.getNoticeTitle();
        this.writeDatetime = noticeBoardEntity.getWriteDatetime();
        this.viewCount = noticeBoardEntity.getViewCount();
        this.writerNickname = noticeBoardEntity.getWriterNickname();
        this.writerEmail = noticeBoardEntity.getWriterEmail();
        this.noticeImageUrl = noticeBoardEntity.getNoticeImageUrl();
    }
}
