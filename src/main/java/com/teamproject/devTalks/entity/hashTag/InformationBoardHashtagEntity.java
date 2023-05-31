package com.teamproject.devTalks.entity.hashTag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.teamproject.devTalks.entity.primaryKey.information.InformationHashtagPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="information_hashtag")
@Table(name="information_hashtag")
@IdClass(InformationHashtagPk.class)
public class InformationBoardHashtagEntity {
    @Id
    private String boardHashtag;
    @Id
    private int informationBoardNumber;


}
