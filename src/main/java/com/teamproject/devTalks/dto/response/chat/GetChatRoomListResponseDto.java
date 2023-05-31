package com.teamproject.devTalks.dto.response.chat;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.resultSet.chat.ChatRoomListResultSet;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GetChatRoomListResponseDto extends ResponseDto {
    
    private List<ChatSummary> ChatRoomList;

    public GetChatRoomListResponseDto(List<ChatRoomListResultSet> resultSetList) {
        super("SU", "SUCCESS");
        
        ChatRoomList = new ArrayList<>();
        for (ChatRoomListResultSet resultSet : resultSetList) {
            ChatSummary chatSummary = new ChatSummary(resultSet);
            ChatRoomList.add(chatSummary);
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    class ChatSummary {
        private String chatRoomNumber;
        private String userProfileImageUrl;
        private boolean userStatus;
        private String userNickname;
        private String lastMessage;
        private String sentDateTime;
        private Integer unreadMessageCount;


        public ChatSummary(ChatRoomListResultSet resultSet) {
            this.chatRoomNumber = resultSet.getChatRoomNumber();
            this.userStatus = resultSet.getUserStatus() == 0;
            this.userNickname = resultSet.getUserNickname();
            this.lastMessage = resultSet.getLastMessage();
            this.sentDateTime = resultSet.getSentDateTime();
            this.unreadMessageCount = resultSet.getUnreadMessageCount() != null ? resultSet.getUnreadMessageCount() : 0;
        }
    }
}
