package com.teamproject.devTalks.controller.board;

import com.teamproject.devTalks.dto.response.board.teacher.GetTeacherBoardResponseDto;
import com.teamproject.devTalks.security.AdminPrinciple;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.dto.request.board.teacher.PatchTeacherBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.teacher.PostTeacherBoardRequestDto;
import com.teamproject.devTalks.dto.request.heart.teacher.PostTeacherHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.teacher.GetTeacherBoardListResponseDto;
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
import com.teamproject.devTalks.service.board.TeacherBoardService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board/teacher")
@RequiredArgsConstructor
public class TeacherBoardController {
    
    private final TeacherBoardService teacherBoardService;

    @GetMapping("{teacherBoardNumber}")
    public ResponseEntity<? super GetTeacherBoardResponseDto> getTeacherBoard(
        @Valid @PathVariable("teacherBoardNumber") Integer teacherBoardNumber
    ){
        ResponseEntity<? super GetTeacherBoardResponseDto> response =
            teacherBoardService.getTeacherBoard(teacherBoardNumber);
        return response;
    }

    //전체 조회
    @GetMapping("/list")
    public ResponseEntity<? super GetTeacherBoardListResponseDto> getTeacherBoardList(
        @Valid @PathVariable("teacherBoardNumber") Integer teacherBoardNumber
    ){
        ResponseEntity<? super GetTeacherBoardListResponseDto> response = 
            teacherBoardService.getTeacherBoardList(teacherBoardNumber);
        return response;
    }

    //정렬 : sort(시간, 좋아요, 조회수) / recruitmentStatus(모집 상태. 모집중:0, 모집완료:1)
    @GetMapping("/list/{sort}/{recruitmentStatus}")
    public ResponseEntity<? super GetTeacherBoardListResponseDto> getTeacherBoardRecruitmentList(
        @PathVariable("sort") String teacherSort, 
        @PathVariable("recruitmentStatus") String recruitmentStatus,
        @Valid @PathVariable("teacherBoardNumber") Integer teacherBoardNumber
    ) {
        ResponseEntity<? super GetTeacherBoardListResponseDto> response =
            teacherBoardService.getTeacherBoardRecruitmentList(teacherSort, recruitmentStatus, teacherBoardNumber);
        return response;
    }

    //group : 검색조건 선택 (닉네임, 제목 ...)
    @GetMapping("/list/{group}/{searchKeyword}")
    public ResponseEntity<? super GetTeacherBoardListResponseDto> getTeacherBoardSearchList(
        @PathVariable("group") String group, 
        @PathVariable("searchKeyword") String searchKeyword,
        @Valid @PathVariable("teacherBoardNumber") Integer teacherBoardNumber
    ) {
        ResponseEntity<? super GetTeacherBoardListResponseDto> response =
            teacherBoardService.getTeacherBoardSearchList(group, searchKeyword, teacherBoardNumber);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> postTeacherBoard(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PostTeacherBoardRequestDto requestBody
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
            teacherBoardService.postTeacherBoard(userEmail, requestBody);
        return response;
    }

    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchTeacherBoard(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PatchTeacherBoardRequestDto requestBody
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
            teacherBoardService.patchTeacherBoard(userEmail, requestBody);
        return response;
    }

    @DeleteMapping("/{teacherBoardNumber}")
    public ResponseEntity<ResponseDto> deleteTeacherBoard(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("teacherBoardNumber") Integer teacherBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response =
            teacherBoardService.deleteTeacherBoard(userEmail, teacherBoardNumber);
        return response;
    }

    @PostMapping("/heart")
    public ResponseEntity<ResponseDto> postTeacherHeart(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @Valid @RequestBody PostTeacherHeartRequestDto requestBody
    ) {                
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
            teacherBoardService.postTeacherHeart(userEmail, requestBody);
        return response;
    }
        
    @DeleteMapping("/heart/{teacherBoardNumber}")
    public ResponseEntity<ResponseDto> deleteTeacherHeart(
        @AuthenticationPrincipal UserPrinciple userPrinciple,
        @PathVariable("teacherBoardNumber") Integer teacherBoardNumber
    ) {
        String userEmail = userPrinciple.getUserEmail();
        ResponseEntity<ResponseDto> response = 
            teacherBoardService.deleteTeacherHeart(userEmail, teacherBoardNumber);
        return response;
    }

    @DeleteMapping("/admin/{teacherBoardNumber}")
    public ResponseEntity<ResponseDto> deleteAdminTeacherBoard(
        @AuthenticationPrincipal AdminPrinciple adminPrinciple,
        @PathVariable("teacherBoardNumber") int teacherBoardNumber
    ) {
        String adminEmail = adminPrinciple.getAdminEmail();
        ResponseEntity<ResponseDto> response = 
            teacherBoardService.deleteTeacherBoard(adminEmail, teacherBoardNumber);
        return response;
    }

}
