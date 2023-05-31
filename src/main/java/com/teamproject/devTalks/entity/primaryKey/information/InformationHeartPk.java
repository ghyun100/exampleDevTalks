package com.teamproject.devTalks.entity.primaryKey.information;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class InformationHeartPk implements Serializable {
    @Column(name="information_board_number")
    private int informationBoardNumber;
    @Column(name="user_number")
    private int userNumber;
}
