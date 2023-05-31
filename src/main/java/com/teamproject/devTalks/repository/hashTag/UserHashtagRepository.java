package com.teamproject.devTalks.repository.hashTag;

import com.teamproject.devTalks.entity.hashTag.UserHashtagEntity;
import com.teamproject.devTalks.entity.primaryKey.user.UserHashTagPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHashtagRepository extends JpaRepository<UserHashtagEntity, UserHashTagPk> {

    List<UserHashtagEntity> findAllByUserNumber(int userNumber);
}
