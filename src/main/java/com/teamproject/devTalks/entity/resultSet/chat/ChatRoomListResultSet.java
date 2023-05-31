package com.teamproject.devTalks.entity.resultSet.chat;

public interface ChatRoomListResultSet {
    public String getChatRoomNumber();
    public int getUserStatus();
    public String getUserNickname();
    public String getLastMessage();
    public String getSentDateTime();
    public Integer getUnreadMessageCount();
}
