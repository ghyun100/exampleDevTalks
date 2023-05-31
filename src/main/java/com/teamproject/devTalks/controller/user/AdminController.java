package com.teamproject.devTalks.controller.user;

import com.teamproject.devTalks.dto.request.admin.*;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.user.AdminSignInResponseDto;
import com.teamproject.devTalks.dto.response.user.GetAdminInfoResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserForAdminResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserListForAdminResponseDto;
import com.teamproject.devTalks.security.AdminPrinciple;
import com.teamproject.devTalks.service.user.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseDto> adminSignUp(

            @Valid @RequestBody AdminSignUpRequestDto dto) {

        ResponseEntity<ResponseDto> response = adminService.adminSignUp(dto);
        return response;

    }

    @PostMapping("sign-in")
    public ResponseEntity<?super AdminSignInResponseDto> adminSignIn(
            @Valid @RequestBody AdminSignInRequestDto dto

    ){
        ResponseEntity<? super AdminSignInResponseDto> response = adminService.adminSignIn(dto);
        return response;

    }

    @GetMapping("me")
    public ResponseEntity<? super GetAdminInfoResponseDto> GetAdminInfo(
            @AuthenticationPrincipal AdminPrinciple adminPrinciple
    ){
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<? super GetAdminInfoResponseDto> response =
                adminService.getAdminUpdate(adminEmail);


        return response;
    }

    @PatchMapping("update")
    public ResponseEntity<ResponseDto> updateAdmin(
            @Valid @RequestBody UpdateAdminRequestDto dto,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple
            ){

        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response =
                adminService.adminUpdate(adminEmail,dto);

        return response;

    }

    @PatchMapping("password")
    public ResponseEntity<ResponseDto> updateAdminPassword(
            @Valid @RequestBody UpdateAdminPasswordRequestDto dto,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple
            ){

        String adminEmail = adminPrinciple.getAdminEmail();

        ResponseEntity<ResponseDto> response =
                adminService.updateAdminPassword(adminEmail,dto);

        return response;
    }

    @PatchMapping("delete")
    public ResponseEntity<ResponseDto> deleteAdmin(
            @Valid @RequestBody DeleteAdminRequestDto dto,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple
    ){
        String adminEmail = adminPrinciple.getAdminEmail();

        ResponseEntity<ResponseDto> response = adminService.deleteAdmin(adminEmail,dto);
        return response;

    }

    @GetMapping("/user/{userNumber}")
    public ResponseEntity<? super GetUserForAdminResponseDto> getUserDetail(
            @PathVariable Integer userNumber,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple

    ){
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<? super GetUserForAdminResponseDto>response =
                adminService.getUserDetail(userNumber,adminEmail);
        return response;

    }

    @GetMapping("/user/list")
    public ResponseEntity<? super GetUserListForAdminResponseDto> getUserList(
        @AuthenticationPrincipal AdminPrinciple adminPrinciple
    ){

        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<? super GetUserListForAdminResponseDto> response =
                adminService.getUserList(adminEmail);
        return response;
    }

}
