package com.teamproject.devTalks.service.board;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamproject.devTalks.dto.request.board.information.PatchInformationBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.information.PostInformationBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.information.PatchInformationCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.information.PostInformationCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.information.PostInformationHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.information.GetInformationBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.information.GetInformationBoardResponseDto;

@Service
public interface InformationBoardService {


    public ResponseEntity<ResponseDto> postInformationBoard(String userEmail, PostInformationBoardRequestDto dto);
    public ResponseEntity<ResponseDto> postInformationComment(String userEmail, PostInformationCommentRequestDto dto);
    public ResponseEntity<ResponseDto> postInformationHeart(String userEmail, PostInformationHeartRequestDto dto);

    public ResponseEntity<ResponseDto> patchInformationBoard(String userEmail, PatchInformationBoardRequestDto dto);
    public ResponseEntity<ResponseDto> patchInformationComment(String userEmail, PatchInformationCommentRequestDto dto);

    public ResponseEntity<ResponseDto> deleteInformationBoard(String userEmail, Integer informationBoardNumber);    
    public ResponseEntity<ResponseDto> deleteInformationComment(String userEmail, Integer informationCommentNumber);
    public ResponseEntity<ResponseDto> deleteInformationHeart(String userEmail, Integer informationBoardNumber);

    public ResponseEntity<? super GetInformationBoardResponseDto> getInformationBoard(Integer informationBoardNumber);
    public ResponseEntity<? super GetInformationBoardListResponseDto> getInformationBoardList(String informationSort);
    public ResponseEntity<? super GetInformationBoardListResponseDto> getInformationBoardSearchList(String group, String searchKeyword);

    public ResponseEntity<ResponseDto> deleteAdminInformationBoard(String adminEmail, int informationBoardNumber);
    public ResponseEntity<ResponseDto> deleteAdminInformationComment(String adminEmail, int informationCommentNumber);
}