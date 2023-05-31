package com.teamproject.devTalks.entity.primaryKey.teacher;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.Data;

@Data
public class TeacherHashTagPk implements Serializable{
    @Column(name = "teacherBoardNumber")
    private int teacherBoardNumber;
    
    @Column(name = "boardHashtag")
    private String boardHashtag;
}
