package com.teamproject.devTalks.dto.response.user;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.user.AdminEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAdminInfoResponseDto extends ResponseDto {

    private int adminNumber;
    private String adminEmail;
    private String adminNickname;
    private String adminPhoneNumber;
    private String adminProfileImageUrl;

    public GetAdminInfoResponseDto(AdminEntity adminEntity) {
        super("SU", "Success");

        this.adminNumber = adminEntity.getAdminNumber();
        this.adminEmail = adminEntity.getAdminEmail();
        this.adminNickname = adminEntity.getAdminNickname();
        this.adminPhoneNumber = adminEntity.getAdminPhoneNumber();
        this.adminProfileImageUrl = adminEntity.getAdminProfileImageUrl();
    }
}
