package com.teamproject.devTalks.repository.hashTag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamproject.devTalks.entity.hashTag.InformationBoardHashtagEntity;
import com.teamproject.devTalks.entity.primaryKey.information.InformationHashtagPk;

@Repository
public interface InformationBoardHashtagRepository extends JpaRepository<InformationBoardHashtagEntity, InformationHashtagPk> {

    public List<InformationBoardHashtagEntity> findByInformationBoardNumber(Integer informationBoardNumber);
    
    @Transactional
    void deleteByInformationBoardNumber(Integer informationBoardNumber);

    @Transactional
    void deleteByBoardHashtagAndInformationBoardNumber(String boardHashtag, Integer informationBoardNumber);
}
