package com.teamproject.devTalks.repository.heart;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.teamproject.devTalks.entity.heart.TeacherHeartEntity;
import com.teamproject.devTalks.entity.primaryKey.teacher.TeacherHeartPk;

@Repository
public interface TeacherHeartRepository extends JpaRepository<TeacherHeartEntity, TeacherHeartPk> {
    List<TeacherHeartEntity> findByTeacherBoardNumber(int teacherBoardNumber);
    @Query(value="SELECT user_number FROM teacher_heart WHERE teacher_board_number = ?", nativeQuery=true)
    List<String> findByTeacherBoardNumberToUserNumber(int teacherBoardNumber);

    @Transactional
    void deleteByTeacherBoardNumber(int teacherBoardNumber);

    @Transactional
    void deleteByUserNumberAndTeacherBoardNumber(int userNumber, int teacherBoardNumber);
}
