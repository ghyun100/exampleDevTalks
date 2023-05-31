package com.teamproject.devTalks.entity.heart;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.teamproject.devTalks.entity.board.RecruitBoardEntity;
import com.teamproject.devTalks.entity.primaryKey.recruit.RecruitHeartPk;
import com.teamproject.devTalks.entity.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="RecruitHeart")
@Table(name="RecruitHeart")
@IdClass(RecruitHeartPk.class)

public class RecruitHeartEntity {
    @Id
    private int recruitBoardNumber;
    @Id
    private int userNumber;

    public RecruitHeartEntity (UserEntity userEntity, RecruitBoardEntity recruitBoardEntity) {
        this.recruitBoardNumber = recruitBoardEntity.getRecruitBoardNumber();
        this.userNumber = userEntity.getUserNumber();
    }
}
