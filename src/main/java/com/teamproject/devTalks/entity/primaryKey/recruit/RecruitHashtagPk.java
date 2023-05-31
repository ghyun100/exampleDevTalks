package com.teamproject.devTalks.entity.primaryKey.recruit;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class RecruitHashtagPk implements Serializable {

    @Column(name="recruit_board_number")
    private int recruitBoardNumber;
    @Column(name="board_hashtag")
    private String boardHashTag;


}
