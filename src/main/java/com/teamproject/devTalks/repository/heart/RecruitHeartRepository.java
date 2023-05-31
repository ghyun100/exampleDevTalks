package com.teamproject.devTalks.repository.heart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.heart.RecruitHeartEntity;
import com.teamproject.devTalks.entity.primaryKey.recruit.RecruitHeartPk;

@Repository
public interface RecruitHeartRepository extends JpaRepository<RecruitHeartEntity, RecruitHeartPk>{
    
    List<RecruitHeartEntity> findByRecruitBoardNumber(int recruitBoardNumber);

    @Transactional
    void deleteByRecruitBoardNumber(int recruitBoardNumber);

    @Transactional
    void deleteByUserNumberAndRecruitBoardNumber(int userNumber, int recruitBoardNumber);
}
