package com.teamproject.devTalks.service.board;

import com.teamproject.devTalks.dto.response.board.teacher.GetTeacherBoardResponseDto;
import com.teamproject.devTalks.dto.request.board.teacher.PatchTeacherBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.teacher.PostTeacherBoardRequestDto;
import com.teamproject.devTalks.dto.request.heart.teacher.PostTeacherHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.teacher.GetTeacherBoardListResponseDto;
import org.springframework.http.ResponseEntity;

public interface TeacherBoardService {
    public ResponseEntity<? super GetTeacherBoardResponseDto> getTeacherBoard(Integer teacherBoardNumber);
    public ResponseEntity<? super GetTeacherBoardListResponseDto> getTeacherBoardList(Integer teacherBoardNumber);
    public ResponseEntity<? super GetTeacherBoardListResponseDto> getTeacherBoardRecruitmentList(String teacherSort, String recruitmentStatus, Integer teacherBoardNumber);
    public ResponseEntity<? super GetTeacherBoardListResponseDto> getTeacherBoardSearchList(String group, String searchKeyword, Integer teacherBoardNumber);

    public ResponseEntity<ResponseDto> postTeacherBoard(String userEmail, PostTeacherBoardRequestDto dto);
    public ResponseEntity<ResponseDto> patchTeacherBoard(String userEmail, PatchTeacherBoardRequestDto dto);
    public ResponseEntity<ResponseDto> deleteTeacherBoard(String userEmail, Integer teacherBoardNumber);

    public ResponseEntity<ResponseDto> postTeacherHeart(String userEmail, PostTeacherHeartRequestDto dto);
    public ResponseEntity<ResponseDto> deleteTeacherHeart(String userEmail, Integer teacherBoardNumber);

    public ResponseEntity<ResponseDto> deleteAdminTeacherBoard(String adminEmail, int teacherBoardNumber);
}
