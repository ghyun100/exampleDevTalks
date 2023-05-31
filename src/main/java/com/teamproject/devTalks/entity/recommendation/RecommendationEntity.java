package com.teamproject.devTalks.entity.recommendation;

import com.teamproject.devTalks.entity.primaryKey.recommendation.RecommendationPk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name ="recommendation")
@Table(name = "recommendation")
@IdClass(RecommendationPk.class)
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationEntity {

    @Id
    private int senderUserNumber;
    @Id
    private int receiverUserNumber;

}
