package com.teamproject.devTalks.entity.heart;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import com.teamproject.devTalks.entity.board.TeacherBoardEntity;
import com.teamproject.devTalks.entity.primaryKey.teacher.TeacherHeartPk;
import com.teamproject.devTalks.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "teacherHeart")
@Table(name = "teacherHeart")
@IdClass(TeacherHeartPk.class)
public class TeacherHeartEntity {
    @Id
    private int teacherBoardNumber;
    @Id
    private int userNumber;

    public TeacherHeartEntity(UserEntity userEntity, TeacherBoardEntity teacherBoardEntity) {
        this.teacherBoardNumber = teacherBoardEntity.getTeacherBoardNumber();
        this.userNumber = userEntity.getUserNumber();
    }
}
