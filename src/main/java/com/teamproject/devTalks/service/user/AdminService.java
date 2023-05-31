package com.teamproject.devTalks.service.user;

import com.teamproject.devTalks.dto.request.admin.*;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.user.AdminSignInResponseDto;
import com.teamproject.devTalks.dto.response.user.GetAdminInfoResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserForAdminResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserListForAdminResponseDto;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<ResponseDto> adminSignUp(AdminSignUpRequestDto dto);

    ResponseEntity<? super AdminSignInResponseDto> adminSignIn(AdminSignInRequestDto dto);

    ResponseEntity<ResponseDto> adminUpdate(String adminEmail, UpdateAdminRequestDto dto);

    ResponseEntity<ResponseDto> updateAdminPassword(String adminEmail, UpdateAdminPasswordRequestDto dto);

    ResponseEntity<ResponseDto> deleteAdmin(String adminEmail, DeleteAdminRequestDto dto);

    ResponseEntity<? super GetAdminInfoResponseDto> getAdminUpdate(String adminEmail);

    ResponseEntity<? super GetUserForAdminResponseDto> getUserDetail(Integer userNumber, String adminEmail);

    ResponseEntity<? super GetUserListForAdminResponseDto> getUserList(String adminEmail);
}
