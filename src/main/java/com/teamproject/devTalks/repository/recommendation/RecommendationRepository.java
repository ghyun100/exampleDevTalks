package com.teamproject.devTalks.repository.recommendation;

import com.teamproject.devTalks.entity.primaryKey.recommendation.RecommendationPk;
import com.teamproject.devTalks.entity.recommendation.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, RecommendationPk> {

   public boolean existsBySenderUserNumberAndReceiverUserNumber(Integer senderId,Integer receiverId);
   public RecommendationEntity findBySenderUserNumberAndReceiverUserNumber(Integer senderId,Integer receiverId);

   public List<RecommendationEntity> findBySenderUserNumber(Integer SenderId);
   public List<RecommendationEntity> findByReceiverUserNumber(Integer receiverId);

}
