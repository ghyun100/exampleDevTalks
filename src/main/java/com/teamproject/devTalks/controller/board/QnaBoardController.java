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

import com.teamproject.devTalks.dto.request.board.qna.PatchQnaBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.qna.PostQnaBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PatchQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PostQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.qna.PostQnaHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.qna.GetQnaBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.qna.GetQnaBoardResponseDto;
import com.teamproject.devTalks.security.AdminPrinciple;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.service.board.QnaBoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board/qna")
@RequiredArgsConstructor
public class QnaBoardController {

    private final QnaBoardService boardService;

    // @Autowired
    // public QnaBoardController(QnaBoardService qnaBoardService) {
    // this.boardService = boardService;
    // }

    @GetMapping("/list/{sort}")
    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList(
            @PathVariable("sort") String qnaSort) {
        ResponseEntity<? super GetQnaBoardListResponseDto> response = boardService.getQnaBoardList(qnaSort);
        return response;
    }

    // group : 검색조건 선택 (닉네임, 제목 ...)
    @GetMapping("/list/{group}/{searchKeyword}")
    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardSearchList(
            @PathVariable("group") String group,
            @PathVariable("searchKeyword") String searchKeyword
        ) {
        ResponseEntity<? super GetQnaBoardListResponseDto> response = boardService.getQnaBoardSearchList(group, searchKeyword);
        return response;
    }

    @GetMapping("/{qnaBoardNumber}")
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard(
            @PathVariable("qnaBoardNumber") int qnaBoardNumber) {
        ResponseEntity<? super GetQnaBoardResponseDto> response = boardService.getQnaBoard(qnaBoardNumber);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> postQnaBoard(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @Valid @RequestBody PostQnaBoardRequestDto requestBody) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.postQnaBoard(userEmail, requestBody);
        return response;
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchQnaBoard(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @Valid @RequestBody PatchQnaBoardRequestDto requestBody) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.patchQnaBoard(userEmail, requestBody);
        return response;
    }

    @DeleteMapping("/{qnaBoardNumber}")
    public ResponseEntity<ResponseDto> deleteQnaBoard(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @PathVariable("qnaBoardNumber") int qnaBoardNumber) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.deleteQnaBoard(userEmail, qnaBoardNumber);
        return response;
    }

    @PostMapping("/comment")
    public ResponseEntity<ResponseDto> postQnaComment(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @Valid @RequestBody PostQnaCommentRequestDto requestBody) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.postQnaComment(userEmail, requestBody);
        return response;
    }

    @PatchMapping("/comment")
    public ResponseEntity<ResponseDto> patchQnaComment(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @Valid @RequestBody PatchQnaCommentRequestDto requestBody) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.patchQnaComment(userEmail, requestBody);
        return response;
    }

    @DeleteMapping("/comment/{qnaCommentNumber}")
    public ResponseEntity<ResponseDto> deleteQnaComment(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @PathVariable("qnaCommentNumber") int qnaCommentNumber) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.deleteQnaComment(userEmail, qnaCommentNumber);
        return response;
    }

    @PostMapping("/heart")
    public ResponseEntity<ResponseDto> postQnaHeart(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @Valid @RequestBody PostQnaHeartRequestDto requestBody) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.postQnaHeart(userEmail, requestBody);
        return response;
    }

    @DeleteMapping("/heart/{qnaBoardNumber}")
    public ResponseEntity<ResponseDto> deleteQnaHeart(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @PathVariable("qnaBoardNumber") int qnaBoardNumber) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = boardService.deleteQnaHeart(userEmail, qnaBoardNumber);
        return response;
    }

    // 관리자가 게시물을 삭제
    @DeleteMapping("/admin/{qnaBoardNumber}")
    public ResponseEntity<ResponseDto> deleteAdminQnaBoard(
            @AuthenticationPrincipal AdminPrinciple adminPrinciple,
            @PathVariable("qnaBoardNumber") int qnaBoardNumber) {
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response = boardService.deleteAdminQnaBoard(adminEmail, qnaBoardNumber);
        return response;
    }

    // 관리자가 댓글을 삭제
    @DeleteMapping("/admin/comment/{qnaCommentNumber}")
    public ResponseEntity<ResponseDto> deleteAdminQnaComment(
            @AuthenticationPrincipal AdminPrinciple adminPrinciple,
            @PathVariable("qnaCommentNumber") int qnaCommentNumber) {
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response = boardService.deleteAdminQnaComment(adminEmail, qnaCommentNumber);
        return response;
    }

}
