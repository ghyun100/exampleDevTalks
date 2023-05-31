package com.teamproject.devTalks.controller.board;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamproject.devTalks.dto.request.board.information.PatchInformationBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.information.PostInformationBoardRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.service.board.InformationBoardService;
import com.teamproject.devTalks.dto.response.board.information.GetInformationBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.information.GetInformationBoardResponseDto;
import com.teamproject.devTalks.security.AdminPrinciple;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.dto.request.comment.information.PatchInformationCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.information.PostInformationCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.information.PostInformationHeartRequestDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board/information")
@RequiredArgsConstructor
public class InformationBoardController {
    
    private final InformationBoardService informationBoardService;

    @GetMapping("/list/{sort}")
    public ResponseEntity<? super GetInformationBoardListResponseDto> getInformationBoardList(
        @PathVariable("sort") String informationSort) {
            ResponseEntity<? super GetInformationBoardListResponseDto> response = 
            informationBoardService.getInformationBoardList(informationSort);
        return response;
        }
    
    // group : 검색조건 선택 (닉네임, 제목 ...)
    @GetMapping("/list/{group}/{searchKeyword}")
    public ResponseEntity<? super GetInformationBoardListResponseDto> getInformationBoardList(
        @PathVariable("group") String group,
        @PathVariable("searchKeyword") String searchKeyword
        ) {
            ResponseEntity<? super GetInformationBoardListResponseDto> response = 
            informationBoardService.getInformationBoardSearchList(group, searchKeyword);
        return response;
        }

    @GetMapping("/{informationBoardNumber}") 
    public ResponseEntity<? super GetInformationBoardResponseDto> getInformationBoard(
        @PathVariable("informationBoardNumber") Integer informationBoardNumber
    ) {
        ResponseEntity<? super GetInformationBoardResponseDto> response = 
        informationBoardService.getInformationBoard(informationBoardNumber);
        return response;
        }

    @PostMapping("")
    public ResponseEntity<ResponseDto> PostInformationBoard(
        @Valid @RequestBody PostInformationBoardRequestDto requestBody,
        @AuthenticationPrincipal UserPrinciple userPrinciple
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.postInformationBoard(userEmail, requestBody);
        return response;
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchInformationBoard(
        @Valid @RequestBody PatchInformationBoardRequestDto requestBody,
        @AuthenticationPrincipal UserPrinciple userPrinciple
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.patchInformationBoard(userEmail, requestBody);
        return response;
    }

    @DeleteMapping("/{informationBoardNumber}")
    public ResponseEntity<ResponseDto> deleteInformationBoard(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("informationBoardNumber") Integer informationBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response =
        informationBoardService.deleteInformationBoard(userEmail, informationBoardNumber);
        return response;
    }

    @PostMapping("/comment")
    public ResponseEntity<ResponseDto> postInformationComment(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PostInformationCommentRequestDto requestBody
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.postInformationComment(userEmail, requestBody);
        return response;
    }

    @PatchMapping("/comment")
    public ResponseEntity<ResponseDto> patchInformationComment(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PatchInformationCommentRequestDto requestBody
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.patchInformationComment(userEmail, requestBody);
        return response;
    }

    @DeleteMapping("/comment/{informationCommentNumber}")
    public ResponseEntity<ResponseDto> deleteInformationComment(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("informationCommentNumber") Integer InformationCommentNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.deleteInformationComment(userEmail, InformationCommentNumber);
        return response;
    }

    @PostMapping("/heart")
    public ResponseEntity<ResponseDto> postInformationHeart(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PostInformationHeartRequestDto requestBody
    ) {                
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.postInformationHeart(userEmail, requestBody);
        return response;
    }   

    @DeleteMapping("/heart/{informationBoardNumber}")
    public ResponseEntity<ResponseDto> deleteInformationHeart(
        @PathVariable("informationBoardNumber") Integer informationBoardNumber,
        @AuthenticationPrincipal UserPrinciple userPrinciple
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        informationBoardService.deleteInformationHeart(userEmail, informationBoardNumber);
        return response;
    }

        @DeleteMapping("/admin/{informationBoardNumber}")
        public ResponseEntity<ResponseDto> deleteAdminInformationBoard(
                @AuthenticationPrincipal AdminPrinciple adminPrinciple,
                @PathVariable("informationBoardNumber") int informationBoardNumber) {
            String adminEmail = adminPrinciple.getAdminEmail();
            ResponseEntity<ResponseDto> response = 
            informationBoardService.deleteAdminInformationBoard(adminEmail, informationBoardNumber);
            return response;
        }
    
        @DeleteMapping("/admin/comment/{informationCommentNumber}")
        public ResponseEntity<ResponseDto> deleteAdminInformationComment(
                @AuthenticationPrincipal AdminPrinciple adminPrinciple,
                @PathVariable("informationCommentNumber") int informationCommentNumber) {
            String adminEmail = adminPrinciple.getAdminEmail();
            ResponseEntity<ResponseDto> response = 
            informationBoardService.deleteAdminInformationComment(adminEmail, informationCommentNumber);
            return response;
        }
}
