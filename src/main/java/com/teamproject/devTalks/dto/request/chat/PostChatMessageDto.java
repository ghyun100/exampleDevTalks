package com.teamproject.devTalks.dto.request.chat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostChatMessageDto {
    @NotNull
    private Integer fromNumber; 
    @NotBlank
    private String message; 
    @NotBlank
    private String chatRoomNumber;
}
