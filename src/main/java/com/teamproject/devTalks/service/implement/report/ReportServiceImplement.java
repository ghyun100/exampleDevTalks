package com.teamproject.devTalks.service.implement.report;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.report.ReportUserRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.report.ReportEntity;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.repository.report.ReportRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.report.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImplement implements ReportService {

    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Override
    public ResponseEntity<ResponseDto> postReport(int reporter, ReportUserRequestDto dto) {
        try {

            // 존재하지 않는 유저
            UserEntity userEntity1 = userRepository.findByUserNumber(reporter);
            if (userEntity1 == null)
                return CustomResponse.noExistUser();

            // 존재하지 않는 신고당할사용자
            UserEntity userEntity2 = userRepository.findByUserNumber(dto.getReportedNumber());
            if (userEntity2 == null)
                return CustomResponse.noExistUser();

            // 중복신고, 이미신고를 했다
            int reportedUserNumber = dto.getReportedNumber();
            boolean isExistReport = reportRepository.existsByReporterAndReported(reporter, reportedUserNumber);

            if (isExistReport)
                return CustomResponse.alreadyReport();

            // 자기자신 신고
            boolean isSameUser = (reporter == dto.getReportedNumber());
            if (isSameUser)
                return CustomResponse.cannotReportToSelf();

            ReportEntity reportEntity = new ReportEntity(userEntity1, dto);

            reportRepository.save(reportEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteReport(int reporter, int reported) {
        
        try {
            //존재하지 않는 유저
            boolean userReporter = userRepository.existsByUserNumber(reporter);
            if (!userReporter) return CustomResponse.noExistUser();
            // 신고당한사람이 존재하지 않음
            boolean userReported = userRepository.existsByUserNumber(reported);
            if (!userReported) return CustomResponse.noExistUser();
            // 이 정보가 없는경우
            ReportEntity reportEntity = reportRepository.findByReporterAndReported(reporter, reported);
            if (reportEntity == null) return CustomResponse.reportEntityisNull();

            reportEntity = reportRepository.findByReporterAndReported(reporter, reported);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

}
