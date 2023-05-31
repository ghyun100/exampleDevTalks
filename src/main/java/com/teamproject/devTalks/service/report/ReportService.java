package com.teamproject.devTalks.service.report;

import org.springframework.http.ResponseEntity;

import com.teamproject.devTalks.dto.request.report.ReportUserRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;

public interface ReportService {

    ResponseEntity<ResponseDto> postReport(int reporter, ReportUserRequestDto dto);
    ResponseEntity<ResponseDto> deleteReport(int reporter, int reported);
}
