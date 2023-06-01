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

import com.teamproject.devTalks.dto.request.board.recruit.PatchRecruitBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.recruit.PostRecruitBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.recruit.PatchRecruitCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.recruit.PostRecruitCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.recruit.PostRecruitHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.recruit.GetRecruitBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.recruit.GetRecruitBoardResponseDto;
import com.teamproject.devTalks.security.AdminPrinciple;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.service.board.RecruitBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board/recruit")
@RequiredArgsConstructor
public class RecruitBoardController {
    
    private final RecruitBoardService recruitBoardService;

    // 게시물 작성
    @PostMapping("")
    public ResponseEntity<ResponseDto> postRecruitBoard(
        @AuthenticationPrincipal UserPrinciple userPrinciple ,
        @Valid @RequestBody PostRecruitBoardRequestDto requestBody
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = recruitBoardService.postRecruitBoard(userEmail, requestBody);
        return response;
    }

    // 특정 게시물 조회
    @GetMapping("/{recruitBoardNumber}")
    public  ResponseEntity<? super GetRecruitBoardResponseDto> getRecruitBoard(
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        ResponseEntity<? super GetRecruitBoardResponseDto> response = recruitBoardService.getRecruitBoard(recruitBoardNumber);
        return response;
    }

    // 특정 게시물 수정
    @PatchMapping("/{recruitBoardNumber}")
    public ResponseEntity<ResponseDto> patchRecruitBoard(
        @Valid @RequestBody PatchRecruitBoardRequestDto requestBody,
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        recruitBoardService.patchRecruitBoard(userEmail, recruitBoardNumber, requestBody);
        return response;
    }

    // 특정 게시물 삭제
    @DeleteMapping("/{recruitBoardNumber}")
    public ResponseEntity<ResponseDto> deleteRecruitBoard(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
        recruitBoardService.deleteRecruitBoard(userEmail, recruitBoardNumber);
        return response;
    }

    // 댓글 작성
    @PostMapping("/{recruitBoardNumber}/comment")
    public ResponseEntity<ResponseDto> postRecruitComment(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PostRecruitCommentRequestDto requestBody,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
            recruitBoardService.postRecruitComment(userEmail, recruitBoardNumber, requestBody);
        return response;
    }

    // 댓글 수정
    @PatchMapping("/{recruitBoardNumber}/comment/{recruitCommentNumber}")
    public ResponseEntity<ResponseDto> patchRecruitComment(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PatchRecruitCommentRequestDto requestBody,
        @PathVariable ("recruitBoardNumber") Integer recruitBoardNumber,
        @PathVariable("recruitCommentNumber") Integer recruitCommentNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
            recruitBoardService.patchRecruitComment(userEmail, recruitBoardNumber, recruitCommentNumber, requestBody);
        return response;
    }

    // 댓글 삭제
    @DeleteMapping("/{recruitBoardNumber}/comment/{recruitCommentNumber}")
    public ResponseEntity<ResponseDto> deleteRecruitComment(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber,
        @PathVariable("recruitCommentNumber") Integer recruitCommentNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response
            = recruitBoardService.deleteRecruitComment(userEmail, recruitBoardNumber , recruitCommentNumber);
        return response;
    }

    // 좋아요 추가
    @PostMapping("/{recruitBoardNumber}/heart")
    public ResponseEntity<ResponseDto> postRecruitHeart(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PostRecruitHeartRequestDto requestBody,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response 
            = recruitBoardService.postRecruitHeart(userEmail, recruitBoardNumber, requestBody);
        return response;
    }

    // 좋아요 삭제
    @DeleteMapping("/{recruitBoardNumber}/heart")
    public ResponseEntity<ResponseDto> deleteRecruitHeart(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = recruitBoardService.deleteRecruitHeart(userEmail, recruitBoardNumber);
        return response;
    }

    // 관리자가 게시물 삭제
    @DeleteMapping("/admin/{recruitBoardNumber}")
    public ResponseEntity<ResponseDto> deleteAdminRecruitBoard(
        @AuthenticationPrincipal AdminPrinciple adminPrinciple,
        @PathVariable("recruitBoardNumber") int recruitBoardNumber
    ) {
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response = recruitBoardService.deleteRecruitBoard(adminEmail, recruitBoardNumber);
        return response;
    }

    // 관리자가 댓글 삭제
    @DeleteMapping("/admin/{recruitBoardNumber}/comment/{recruitCommentNumber}")
    public ResponseEntity<ResponseDto> deleteAdminRecruitComment (
        @AuthenticationPrincipal AdminPrinciple adminPrinciple,
        @PathVariable("recruitCommentNumber") Integer recruitCommentNumber,
        @PathVariable("recruitBoardNumber") Integer recruitBoardNumber
    ) {
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response = recruitBoardService.deleteRecruitComment(adminEmail, recruitBoardNumber, recruitCommentNumber);
        return response;
    }

    // 전체 게시물 리스트 조회
    @GetMapping("/list/{sort}")
    public ResponseEntity<? super GetRecruitBoardListResponseDto> getRecruitBoardList(
        @PathVariable("sort") String recruitSort
    ) {
    
        ResponseEntity<? super GetRecruitBoardListResponseDto> response = 
            recruitBoardService.getRecruitBoardList(recruitSort);
        return response;
    
    }

    // 검색 기능
    @GetMapping("/list/{group}/{searchKeyword}")
    public ResponseEntity<? super GetRecruitBoardListResponseDto> getRecruitBoardList(
        @PathVariable("group") String group,
        @PathVariable("searchKeyword") String searchKeyword
    ) {
        ResponseEntity<? super GetRecruitBoardListResponseDto> response = recruitBoardService.getRecruitBoardSearchList(group, searchKeyword);
        return response;
    }
}

    
