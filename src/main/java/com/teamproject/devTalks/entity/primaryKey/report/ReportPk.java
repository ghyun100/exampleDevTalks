package com.teamproject.devTalks.entity.primaryKey.report;

import java.io.Serializable;

import javax.persistence.Column;

public class ReportPk implements Serializable {

    @Column(name = "reporter")
    private int reporter;

    @Column(name = "reported")
    private int reported;

}
