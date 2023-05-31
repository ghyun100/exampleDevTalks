package com.teamproject.devTalks.entity.resultSet;


public interface InformationBoardListResultSet {
    public int getInformationBoardNumber();
    public String getWriterProfileImageUrl();
    public String getWriterNickname();
    public String getWriterEmail();
    public String getInformationBoardTitle();
    public Integer getViewCount();
    public String getWriteDatetime();
    public Integer getCommentCount();
    public Integer getHeartCount();
}
