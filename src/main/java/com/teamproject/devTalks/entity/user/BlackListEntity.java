package com.teamproject.devTalks.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "blacklist")
@Table(name = "blacklist")
@Getter
@Setter
@NoArgsConstructor
public class BlackListEntity {

    @Id
    private int userNumber;
    private String userEmail;
    private String userPhoneNumber;
    private String reason;
    private String createdAt;


    public BlackListEntity(UserEntity userEntity, String reason) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.userNumber = userEntity.getUserNumber();
        this.userEmail = userEntity.getUserEmail();
        this.userPhoneNumber = userEntity.getUserPhoneNumber();
        this.reason = reason;
        this.createdAt = now.format(formatter);
    }
}
