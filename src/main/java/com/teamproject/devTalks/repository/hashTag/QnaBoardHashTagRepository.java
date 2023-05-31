package com.teamproject.devTalks.repository.hashTag;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.hashTag.QnaBoardHashTagEntity;
import com.teamproject.devTalks.entity.primaryKey.qna.QnaHashTagPk;

@Repository
public interface QnaBoardHashTagRepository extends JpaRepository<QnaBoardHashTagEntity, QnaHashTagPk>{

    public List<QnaBoardHashTagEntity> findByQnaBoardNumber(int qnaBoardNumber);


    @Transactional
    void deleteByQnaBoardNumber(int qnaBoardNumber);

    @Transactional
    void deleteByBoardHashtagAndQnaBoardNumber(String hashtag, int qnaBoardNumber);

}
