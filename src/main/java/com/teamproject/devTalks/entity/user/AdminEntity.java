package com.teamproject.devTalks.entity.user;


import javax.persistence.*;

import com.teamproject.devTalks.dto.request.admin.AdminSignUpRequestDto;
import com.teamproject.devTalks.dto.request.admin.UpdateAdminRequestDto;
import lombok.*;

@Entity(name = "admin")
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminNumber;
    private String adminEmail;
    private String adminPassword;
    private String adminNickname;
    private String adminPhoneNumber;
    private String adminName;
    private String adminProfileImageUrl;
    private boolean agreePersonalInformation;

    public AdminEntity(AdminSignUpRequestDto dto) {

        this.adminEmail = dto.getAdminEmail();
        this.adminPassword = dto.getAdminPassword();
        this.adminNickname = dto.getAdminNickname();
        this.adminPhoneNumber = dto.getAdminPhoneNumber();
        this.adminName = dto.getAdminName();
        this.agreePersonalInformation = dto.isAgreePersonalInformation();

    }

    public AdminEntity(AdminEntity adminEntity, UpdateAdminRequestDto dto) {
        this.adminNumber = adminEntity.getAdminNumber();
        this.adminEmail = adminEntity.getAdminEmail();
        this.adminPassword = adminEntity.getAdminPassword();
        this.adminNickname = dto.getAdminNickname();
        this.adminPhoneNumber = dto.getAdminPhoneNumber();
        this.adminName = adminEntity.getAdminName();
        this.adminProfileImageUrl = dto.getAdminProfileImageUrl();
        this.agreePersonalInformation = adminEntity.isAgreePersonalInformation();

    }
}
