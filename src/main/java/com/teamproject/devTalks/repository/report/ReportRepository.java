package com.teamproject.devTalks.repository.report;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamproject.devTalks.entity.primaryKey.report.ReportPk;
import com.teamproject.devTalks.entity.report.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, ReportPk> {

    public boolean existsByReporterAndReported(int repoter, int reported);

    public List<ReportEntity> findByReporter(Integer reporter);
    public List<ReportEntity> findByReported(Integer reported);

    ReportEntity findByReporterAndReported(int reporter, int reported);
}
