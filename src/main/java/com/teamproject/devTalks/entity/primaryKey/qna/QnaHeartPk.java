package com.teamproject.devTalks.entity.primaryKey.qna;

import javax.persistence.Column;
import java.io.Serializable;

public class QnaHeartPk implements Serializable {

    @Column(name = "userNumber")
    private int userNumber;

    @Column(name = "qnaBoardNumber")
    private int qnaBoardNumber;

}
