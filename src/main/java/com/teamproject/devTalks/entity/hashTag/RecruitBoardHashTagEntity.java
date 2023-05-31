package com.teamproject.devTalks.entity.hashTag;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.teamproject.devTalks.entity.primaryKey.recruit.RecruitHashtagPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="RecruitHashtag")
@Table(name="RecruitHashtag")
@IdClass(RecruitHashtagPk.class)
public class RecruitBoardHashTagEntity {
    @Id
    private int recruitBoardNumber;
    @Id
    private String boardHashTag;

}
