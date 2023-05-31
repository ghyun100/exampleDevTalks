package com.teamproject.devTalks.entity.resultSet.chat;

public interface ChatMessageListResultSet {
    public String getChatRoomNumber();
    public Integer getChatMessageNumber();
    public String getUserProfileImageUrl();
    public String getUserNickname();
    public String getSentDatetime();
    public String getMessage();

    //읽음 상태가 Null값으로 넘어와서 지정함
    default int getChatStatus() {
        Integer status = getNullableChatStatus();
        return (status != null) ? status.intValue() : 0;
    }

    Integer getNullableChatStatus();
}

