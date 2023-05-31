package com.teamproject.devTalks.entity.report;

import java.nio.file.attribute.UserPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.teamproject.devTalks.dto.request.report.ReportUserRequestDto;
import com.teamproject.devTalks.entity.primaryKey.report.ReportPk;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.security.UserPrinciple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "report")
@Table(name = "report")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ReportPk.class)
public class ReportEntity {

    @Id
    private int reporter;
    @Id
    private int reported;
    @NotBlank
    private String causeReport;
    @NotBlank
    private String writeDatetime;

    public ReportEntity(UserEntity userEntity, ReportUserRequestDto dto) {

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        this.reporter = userEntity.getUserNumber();
        this.reported = dto.getReportedNumber();
        this.causeReport = dto.getCauseReport();
        this.writeDatetime = dateFormat.format(now);

    }

}
