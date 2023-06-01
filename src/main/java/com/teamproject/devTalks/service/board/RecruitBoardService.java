package com.teamproject.devTalks.service.board;

import org.springframework.http.ResponseEntity;

import com.teamproject.devTalks.dto.request.board.recruit.PatchRecruitBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.recruit.PostRecruitBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.recruit.PatchRecruitCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.recruit.PostRecruitCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.recruit.PostRecruitHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.recruit.GetRecruitBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.recruit.GetRecruitBoardResponseDto;


public interface RecruitBoardService {

    public ResponseEntity<? super GetRecruitBoardResponseDto> getRecruitBoard(Integer recruitBoardNumber);
    public ResponseEntity<? super GetRecruitBoardListResponseDto> getRecruitBoardList(String recruitSort);
    public ResponseEntity<? super GetRecruitBoardListResponseDto> getRecruitBoardSearchList(String group, String searchKeyword);
    
    public ResponseEntity<ResponseDto> postRecruitBoard(String userEmail, PostRecruitBoardRequestDto dto);
    public ResponseEntity<ResponseDto> patchRecruitBoard(String userEmail, Integer recruitBoardNumber, PatchRecruitBoardRequestDto dto);
    public ResponseEntity<ResponseDto> deleteRecruitBoard(String userEmail, Integer recruitBoardNumber);

    public ResponseEntity<ResponseDto> postRecruitComment(String userEmail, Integer recruitBoardNumber, PostRecruitCommentRequestDto dto);
    public ResponseEntity<ResponseDto> patchRecruitComment(String userEmail, Integer recruitBoardNumber, Integer recruitCommentNumber, PatchRecruitCommentRequestDto dto);
    public ResponseEntity<ResponseDto> deleteRecruitComment(String userEmail, Integer recruitBoardNumber, Integer recruitCommentNumber);

    public ResponseEntity<ResponseDto> postRecruitHeart(String userEmail, Integer recruitBoardNumber, PostRecruitHeartRequestDto dto);
    public ResponseEntity<ResponseDto> deleteRecruitHeart(String userEmail, Integer recruitBoardNumber);

    public ResponseEntity<ResponseDto> deleteAdminRecruitBoard(String adminEmail, Integer recruitBoardNumber);
    public ResponseEntity<ResponseDto> deleteAdminRecruitComment(String adminEmail, Integer recruitCommentNumber);


    
}
