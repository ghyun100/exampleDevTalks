package com.teamproject.devTalks.entity.primaryKey.recommendation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.io.Serializable;

@Getter
@Setter
public class RecommendationPk implements Serializable {

    @Column(name = "sender_user_number")
    private int senderUserNumber;
    @Column(name = "receiver_user_number")
    private int receiverUserNumber;


}
