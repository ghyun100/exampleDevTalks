package com.teamproject.devTalks.controller.board;


import com.teamproject.devTalks.dto.request.board.notice.PatchNoticeBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.notice.PostNoticeBoardRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.notice.GetNoticeBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.notice.GetNoticeBoardResponseDto;
import com.teamproject.devTalks.security.AdminPrinciple;
import com.teamproject.devTalks.service.board.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> postNotice (
            @Valid @RequestBody PostNoticeBoardRequestDto dto,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple

    ) {

        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response = noticeBoardService.postNotice(adminEmail,dto);

        return response;

    }

    @GetMapping("/{noticeBoardNumber}")
    public ResponseEntity<? super GetNoticeBoardResponseDto> getNoticeBoard(
            @PathVariable("noticeBoardNumber") Integer boardNumber
    ){
        ResponseEntity<? super GetNoticeBoardResponseDto> response =
                noticeBoardService.getBoard(boardNumber);

        return response;

    }

    @GetMapping("/list")
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeList(){

        ResponseEntity<? super GetNoticeBoardListResponseDto> response
                = noticeBoardService.getNoticeList();
        return response;

    }

    // group : 검색조건 선택 (닉네임, 제목 ...)
    @GetMapping("/list/{group}/{searchKeyword}")
    public ResponseEntity<? super GetNoticeBoardListResponseDto> getNoticeSearchList(
        @PathVariable("group") String group,
        @PathVariable("searchKeyword") String searchKeyword
    ){

        ResponseEntity<? super GetNoticeBoardListResponseDto> response
                = noticeBoardService.getNoticeSearchList(group, searchKeyword);
        return response;

    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> updateNotice(
            @Valid @RequestBody PatchNoticeBoardRequestDto dto,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple

    ){
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response
                =noticeBoardService.updateNotice(adminEmail,dto);

        return response;

    }

    @DeleteMapping("/{noticeBoardNumber}")
    public ResponseEntity<ResponseDto> deleteNotice(
            @PathVariable Integer noticeBoardNumber,
            @AuthenticationPrincipal AdminPrinciple adminPrinciple
    ){
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response =
                noticeBoardService.deleteNotice(noticeBoardNumber, adminEmail);

        return response;

    }


}
