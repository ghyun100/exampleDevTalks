package com.teamproject.devTalks.entity.primaryKey.chat;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ChatRoomPk implements Serializable {
    @Column(name="chat_room_number")
    private String chatRoomNumber;
    @Column(name="user_number")
    private int userNumber;
}
