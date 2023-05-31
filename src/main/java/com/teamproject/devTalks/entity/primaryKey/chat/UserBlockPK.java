package com.teamproject.devTalks.entity.primaryKey.chat;

import java.io.Serializable;

import javax.persistence.Column;

public class UserBlockPK implements Serializable {
    @Column(name="sender_number")
    private int senderNumber;
    @Column(name="receiver_number")
    private int receiverNumber;
}
