package com.teamproject.devTalks.dto.request.chat;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserBlockRequestDto {
    @NotNull
    private Integer senderNumber;
    @NotNull
    private Integer receiverNumber;
}
