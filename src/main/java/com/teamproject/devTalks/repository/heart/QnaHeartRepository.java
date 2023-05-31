package com.teamproject.devTalks.repository.heart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.heart.QnaHeartEntity;
import com.teamproject.devTalks.entity.primaryKey.qna.QnaHeartPk;



@Repository
public interface QnaHeartRepository extends JpaRepository<QnaHeartEntity, QnaHeartPk>{
    

    public List<QnaHeartEntity> findByQnaBoardNumber(int qnaBoardNumber);

    @Transactional
    void deleteByQnaBoardNumber(int qnaBoardNumber);

    @Transactional
    void deleteByUserNumberAndQnaBoardNumber(int userNumber, int qnaBoardNumber);

}
