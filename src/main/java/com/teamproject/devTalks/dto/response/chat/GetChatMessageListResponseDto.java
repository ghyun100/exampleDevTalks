package com.teamproject.devTalks.dto.response.chat;

import java.util.ArrayList;
import java.util.List;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.resultSet.chat.ChatMessageListResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetChatMessageListResponseDto extends ResponseDto {

    private List<ChatMessage> ChatMessageList;

    public GetChatMessageListResponseDto(List<ChatMessageListResultSet> resultSetList) {

        super("SU", "SUCCESS");

        ChatMessageList = new ArrayList<>();
        for (ChatMessageListResultSet resultSet : resultSetList) {
            ChatMessage chatMessage = new ChatMessage(resultSet);
            ChatMessageList.add(chatMessage);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class ChatMessage {
        private String chatRoomNumber;
        private Integer chatMessageNumber;
        private String userProfileImageUrl;
        private String userNickname;
        private String sentDatetime;
        private String message;
        private boolean chatStatus;

        public ChatMessage(ChatMessageListResultSet resultSet) {
            this.chatRoomNumber = resultSet.getChatRoomNumber();
            this.chatMessageNumber = resultSet.getChatMessageNumber();
            this.userProfileImageUrl = resultSet.getUserProfileImageUrl();
            this.userNickname = resultSet.getUserNickname();
            this.sentDatetime = resultSet.getSentDatetime();
            this.message = resultSet.getMessage();
            this.chatStatus = resultSet.getChatStatus() == 0;
        }
    }

}
