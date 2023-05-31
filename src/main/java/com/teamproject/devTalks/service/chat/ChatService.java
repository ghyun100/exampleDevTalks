package com.teamproject.devTalks.service.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.teamproject.devTalks.dto.response.chat.GetChatRoomListResponseDto;
import com.teamproject.devTalks.dto.response.chat.GetChatMessageListResponseDto;
import com.teamproject.devTalks.dto.request.chat.PostChatMessageDto;
import com.teamproject.devTalks.dto.request.chat.PostChatRoomDto;
import com.teamproject.devTalks.dto.request.chat.PostUserBlockRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;


@Service
public interface ChatService {

    public ResponseEntity<ResponseDto> createChatRoom(PostChatRoomDto dto);
    public ResponseEntity<ResponseDto> userBlock(PostUserBlockRequestDto dto);
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(Integer userNumber);
    public ResponseEntity<? super GetChatMessageListResponseDto> getChatMessageList(Integer userNumber, String chatRoomNumber);
    public ResponseEntity<ResponseDto> deleteChatRoom(String chatRoomNumber);
    public ResponseEntity<ResponseDto> deleteChatMessage(Integer chatMessageNumber);
    
    public boolean postChatMessage(PostChatMessageDto dto);

}
