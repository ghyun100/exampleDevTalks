package com.teamproject.devTalks.entity.primaryKey.qna;

import javax.persistence.Column;
import java.io.Serializable;

public class QnaHashTagPk  implements Serializable {

    @Column(name = "boardHashtag") // DB에 있는 column이름이랑 같아야하는데 아니어서 오류였다
    private String boardHashtag;

    @Column(name = "qnaBoardNumber")
    private int qnaBoardNumber;

}
