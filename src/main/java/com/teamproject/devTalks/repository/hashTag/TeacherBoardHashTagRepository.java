package com.teamproject.devTalks.repository.hashTag;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.teamproject.devTalks.entity.hashTag.TeacherBoardHashTagEntity;
import com.teamproject.devTalks.entity.primaryKey.teacher.TeacherHashTagPk;

@Repository
public interface TeacherBoardHashTagRepository extends JpaRepository<TeacherBoardHashTagEntity, TeacherHashTagPk> {
    List<TeacherBoardHashTagEntity> findAllByTeacherBoardNumber(int teacherBoardNumber);
    
    @Transactional
    void deleteByTeacherBoardNumber(int teacherBoardNumber);

    @Transactional
    void deleteByBoardHashtagAndTeacherBoardNumber(String hashtag, int teacherBoardNumber);
}
