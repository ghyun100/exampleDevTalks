package com.teamproject.devTalks.repository.hashTag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.hashTag.RecruitBoardHashTagEntity;
import com.teamproject.devTalks.entity.primaryKey.recruit.RecruitHashtagPk;

@Repository
public interface RecruitBoardHashTagRepository extends JpaRepository<RecruitBoardHashTagEntity, RecruitHashtagPk> {
    
    List<RecruitBoardHashTagEntity> findAllByRecruitBoardNumber(int recruitBoardNumber);
    
    @Transactional
    void deleteByRecruitBoardNumber(int recruitBoardNumber);

}
