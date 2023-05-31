package com.teamproject.devTalks.controller.chat;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamproject.devTalks.dto.request.chat.PostChatRoomDto;
import com.teamproject.devTalks.dto.request.chat.PostUserBlockRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.chat.GetChatMessageListResponseDto;
import com.teamproject.devTalks.dto.response.chat.GetChatRoomListResponseDto;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.service.chat.ChatService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RequestMapping("/chat")
@RestController
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/list/rooms")
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRoomList(
        @AuthenticationPrincipal UserPrinciple userPrinciple) {
        Integer userNumber = userPrinciple.getUserNumber();
        ResponseEntity<? super GetChatRoomListResponseDto> response = 
        chatService.getChatRoomList(userNumber);
        return response;
    }

    @GetMapping("/list/{chat_room_number}")
    public ResponseEntity<? super GetChatMessageListResponseDto> getChatMessageList(
        @PathVariable("chatRoomNumber") String chatRoomNumber,
        @AuthenticationPrincipal UserPrinciple userPrinciple
        ) {
        Integer userNumber = userPrinciple.getUserNumber();
        ResponseEntity<? super GetChatMessageListResponseDto> response = 
        chatService.getChatMessageList(userNumber, chatRoomNumber);
        return response;
    }

    @PostMapping("/room")
    public ResponseEntity<ResponseDto> createChatRoom(
        @Valid @RequestBody PostChatRoomDto RequestBody,
        @AuthenticationPrincipal UserPrinciple userPrinciple
    ) {
        ResponseEntity<ResponseDto> response = chatService.createChatRoom(RequestBody);
        return response;
    }

    @PostMapping("/userBlock")
    public ResponseEntity<ResponseDto> userBlock(
        @Valid @RequestBody PostUserBlockRequestDto RequestBody,
        @AuthenticationPrincipal UserPrinciple userPrinciple
    ) {
        ResponseEntity<ResponseDto> response = 
        chatService.userBlock(RequestBody);
        return response;
    }

    @DeleteMapping("/room/{chat_room_number}")
    public ResponseEntity<ResponseDto> deleteChatRoom(
        @PathVariable("chat_room_number") String chatRoomNumber 
    ) {
        ResponseEntity<ResponseDto> response = 
        chatService.deleteChatRoom(chatRoomNumber);
        return response;
    }

    @DeleteMapping("/message/{chat_message_number}")
    public ResponseEntity<ResponseDto> deleteChatMessage(
        @PathVariable("chat_message_number") Integer chatMessageNumber
    ) {
        ResponseEntity<ResponseDto> response = 
        chatService.deleteChatMessage(chatMessageNumber);
        return response;
    }
    
    }

    
