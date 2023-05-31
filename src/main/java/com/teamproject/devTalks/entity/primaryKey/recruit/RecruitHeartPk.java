package com.teamproject.devTalks.entity.primaryKey.recruit;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class RecruitHeartPk implements Serializable{

    @Column(name="recruit_board_number")
    private int recruitBoardNumber;
    @Column(name="user_number")
    private int userNumber;
}
