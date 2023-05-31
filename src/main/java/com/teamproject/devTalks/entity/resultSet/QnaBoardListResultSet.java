package com.teamproject.devTalks.entity.resultSet;

public interface QnaBoardListResultSet {

    public int getQnaBoardNumber();

    public String getQnaTitle();

    public String getQnaContent();

    public String getQnaBoardImageUrl();

    public String getWriteDatetime();

    public int getViewCount();

    public String getWriterEmail();

    public String getWriterNickname();

    public String getWriterProfileImageUrl();

    public int getQnaCommentCount();

    public int getQnaHeartCount();

}
