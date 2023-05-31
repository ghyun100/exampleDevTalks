package com.teamproject.devTalks.entity.hashTag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.teamproject.devTalks.entity.primaryKey.qna.QnaHashTagPk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "qnaHashtag")
@Table(name = "qnaHashtag")
@IdClass(QnaHashTagPk.class)
public class QnaBoardHashTagEntity {

    @Id
    private String boardHashtag;
    @Id
    private int qnaBoardNumber;

}
