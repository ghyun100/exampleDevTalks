package com.teamproject.devTalks.dto.request.report;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReportUserRequestDto {

    @NotNull
    private int reportedNumber;
    @NotNull
    private String causeReport;

}
