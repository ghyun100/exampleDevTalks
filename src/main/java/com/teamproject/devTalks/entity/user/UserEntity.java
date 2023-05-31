package com.teamproject.devTalks.entity.user;

import com.teamproject.devTalks.dto.request.user.UpdateUserRequestDto;
import com.teamproject.devTalks.dto.request.user.UserSignUpRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "User")
@Table(name ="User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNumber;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userName;
    private String userPhoneNumber;
    private String userIntroduction;
    private String userProfileImageUrl;
    private boolean agreePersonalInformation;
    private boolean chatAcceptance;
    private String createdAt;
    private boolean userStatus;

    public UserEntity(UserSignUpRequestDto dto) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNickname = dto.getUserNickname();
        this.userName = dto.getUserName();
        this.userPhoneNumber = dto.getUserPhoneNumber();
        this.userIntroduction = dto.getUserIntroduction();
        this.userProfileImageUrl = dto.getUserProfileImageUrl();
        this.chatAcceptance = dto.isChatAcceptance();
        this.agreePersonalInformation = dto.isAgreePersonalInformation();
        this.createdAt = now.format(formatter);
        this.userStatus = dto.isUserStatus();

    }

    public UserEntity(UserEntity userEntity, UpdateUserRequestDto dto) {

        this.userNumber = userEntity.getUserNumber();
        this.userEmail = userEntity.getUserEmail();
        this.userPassword = userEntity.getUserPassword();
        this.userNickname = dto.getUserNickname();
        this.userName = userEntity.getUserName();
        this.userPhoneNumber = dto.getUserPhoneNumber();
        this.userIntroduction = dto.getUserIntroduction();
        this.userProfileImageUrl = dto.getUserProfileImageUrl();
        this.agreePersonalInformation = userEntity.isAgreePersonalInformation();
        this.chatAcceptance = dto.isChatAcceptance();

    }

}
