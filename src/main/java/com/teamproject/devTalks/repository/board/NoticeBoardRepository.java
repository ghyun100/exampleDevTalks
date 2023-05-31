package com.teamproject.devTalks.repository.board;

import com.teamproject.devTalks.entity.board.NoticeBoardEntity;
import com.teamproject.devTalks.entity.resultSet.NoticeBoardListResultSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoardEntity,Integer> {

    public NoticeBoardEntity findByNoticeBoardNumber(int boardNumber);

    List<NoticeBoardEntity> findAllByOrderByWriteDatetimeDesc();

    public List<NoticeBoardEntity> findByNoticeTitleContaining(String searchKeyword);
    
}
