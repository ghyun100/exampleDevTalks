package com.teamproject.devTalks.controller.user;

import com.teamproject.devTalks.dto.request.user.*;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.user.FindUserEmailResponseDto;
import com.teamproject.devTalks.dto.response.user.GetMyInfoResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserInformationResponseDto;
import com.teamproject.devTalks.dto.response.user.SignInResponseDto;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    ResponseEntity<ResponseDto> userSignUp(
            @Valid @RequestBody UserSignUpRequestDto dto) {
        ResponseEntity<ResponseDto> response = userService.userSignUp(dto);
        return response;
    }

    @PostMapping("/sign-in")
    ResponseEntity<? super SignInResponseDto> userSignIn(
            @Valid @RequestBody UserSignInRequestDto dto) {

        ResponseEntity<? super SignInResponseDto> response = userService.userSignIn(dto);
        return response;
    }

    @GetMapping("me")
    ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(
            @AuthenticationPrincipal UserPrinciple userPrinciple) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<? super GetMyInfoResponseDto> response = userService.getMyInfo(userEmail);
        return response;

    }

    @GetMapping("/{userNumber}")
    ResponseEntity<? super GetUserInformationResponseDto> getUserInfo(
            @PathVariable Integer userNumber) {
        ResponseEntity<? super GetUserInformationResponseDto> response = userService.getUserInformation(userNumber);
        return response;
    }

    @PatchMapping("update")
    ResponseEntity<ResponseDto> updateUser(
            @Valid @RequestBody UpdateUserRequestDto dto,
            @AuthenticationPrincipal UserPrinciple userPrinciple) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = userService.updateUser(userEmail, dto);
        return response;
    }

    @PatchMapping("password")
    ResponseEntity<ResponseDto> updateUserPassword(
            @Valid @RequestBody UpdateUserPasswordRequestDto dto,
            @AuthenticationPrincipal UserPrinciple userPrinciple) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = userService.updateUserPassword(userEmail, dto);

        return response;
    }

    @PatchMapping("delete")
    ResponseEntity<ResponseDto> deleteUser(
            @Valid @RequestBody DeleteUserRequestDto dto,
            @AuthenticationPrincipal UserPrinciple userPrinciple) {

        String userEmail = userPrinciple.getUserEmail();

        ResponseEntity<ResponseDto> response = userService.userDelete(userEmail, dto);
        return response;

    }

    @PostMapping("/find-email")
    ResponseEntity<? super FindUserEmailResponseDto> findUserEmail(
        @Valid @RequestBody FindUserEmailRequestDto dto
    ){
        ResponseEntity<? super FindUserEmailResponseDto> response = userService.findUserEmail(dto);
        return response;
    }

    @PostMapping("/find-password")
    ResponseEntity<ResponseDto> findUserPassword(
        @Valid @RequestBody FindUserPasswordRequestDto dto
    ){
        ResponseEntity<ResponseDto> response = userService.findUserPassword(dto);
        return response;
    }


}
