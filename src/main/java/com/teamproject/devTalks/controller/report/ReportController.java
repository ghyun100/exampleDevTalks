package com.teamproject.devTalks.controller.report;

import java.nio.file.attribute.UserPrincipal;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamproject.devTalks.dto.request.report.ReportUserRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.service.report.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("")
    public ResponseEntity<ResponseDto> postReport(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @Valid @RequestBody ReportUserRequestDto dto) {
        int reporter = userPrinciple.getUserNumber();

        ResponseEntity<ResponseDto> response = reportService.postReport(reporter, dto);

        return response;
    }

    @DeleteMapping("{reportedNumber}")
    public ResponseEntity<ResponseDto> deleteReport(
    @AuthenticationPrincipal UserPrinciple userPrinciple,
    @PathVariable("reportedNumber") int reported
    ){
        int reportNumber = userPrinciple.getUserNumber();
        ResponseEntity<ResponseDto> response = reportService.deleteReport(reportNumber,reported);
        return response;
    }




}
