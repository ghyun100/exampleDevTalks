package com.teamproject.devTalks.service.board;

import org.springframework.http.ResponseEntity;

import com.teamproject.devTalks.dto.request.board.qna.PatchQnaBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.qna.PostQnaBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PatchQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PostQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.qna.PostQnaHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.qna.GetQnaBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.qna.GetQnaBoardResponseDto;

public interface QnaBoardService {

    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList(String qnaSort);

    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard(int qnaBoardNumber);

    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardSearchList(String group, String searchKeyword);

    public ResponseEntity<ResponseDto> postQnaBoard(String userEmail, PostQnaBoardRequestDto dto);

    public ResponseEntity<ResponseDto> patchQnaBoard(String userEmail, PatchQnaBoardRequestDto dto);

    public ResponseEntity<ResponseDto> deleteQnaBoard(String userEmail, int qnaBoardNumber);

    public ResponseEntity<ResponseDto> postQnaComment(String userEmail, PostQnaCommentRequestDto dto);

    public ResponseEntity<ResponseDto> patchQnaComment(String userEmail, PatchQnaCommentRequestDto dto);

    public ResponseEntity<ResponseDto> deleteQnaComment(String userEmail, int qnaCommentNumber);

    public ResponseEntity<ResponseDto> postQnaHeart(String userEmail, PostQnaHeartRequestDto dto);

    public ResponseEntity<ResponseDto> deleteQnaHeart(String userEmail, int qnaBoardNumber);

    public ResponseEntity<ResponseDto> deleteAdminQnaBoard(String adminEmail, int qnaBoardNumber);

    public ResponseEntity<ResponseDto> deleteAdminQnaComment(String adminEmail, int qnaCommentNumber);

}
