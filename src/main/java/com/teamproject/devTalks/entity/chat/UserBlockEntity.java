package com.teamproject.devTalks.entity.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.teamproject.devTalks.dto.request.chat.PostUserBlockRequestDto;
import com.teamproject.devTalks.entity.primaryKey.chat.UserBlockPK;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user_block")
@Table(name = "user_block")
@IdClass(UserBlockPK.class)
public class UserBlockEntity {
    
    @Id
    private int senderNumber;
    @Id
    private int receiverNumber;

    public UserBlockEntity(PostUserBlockRequestDto dto) {

        this.senderNumber = dto.getSenderNumber();
        this.receiverNumber = dto.getReceiverNumber();

}
}
