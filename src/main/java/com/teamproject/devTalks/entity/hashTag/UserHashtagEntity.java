package com.teamproject.devTalks.entity.hashTag;

import com.teamproject.devTalks.entity.primaryKey.user.UserHashTagPk;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Getter
@Setter
@Entity(name = "userHashTag")
@Table(name = "userHashtag")
@IdClass(UserHashTagPk.class)
@NoArgsConstructor
public class UserHashtagEntity {
    @Id
    private int userNumber;
    @Id
    private String userHashtag;

    public UserHashtagEntity(int userNumber, String hashTag) {
        this.userNumber = userNumber;
        this.userHashtag = hashTag;
    }
}
