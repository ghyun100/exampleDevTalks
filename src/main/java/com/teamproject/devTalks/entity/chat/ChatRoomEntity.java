package com.teamproject.devTalks.entity.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.teamproject.devTalks.entity.primaryKey.chat.ChatRoomPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chat_room")
@Table(name = "chat_room")
@IdClass(ChatRoomPk.class)
public class ChatRoomEntity {
    
    @Id
    private String chatRoomNumber;
    @Id
    private int userNumber;
}
