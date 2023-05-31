package com.teamproject.devTalks.dto.request.chat;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostChatRoomDto {
    @NotNull
    private Integer fromNumber;
    @NotNull
    private Integer toNumber;
    
}
