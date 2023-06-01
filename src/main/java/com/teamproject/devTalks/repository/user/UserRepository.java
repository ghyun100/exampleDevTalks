package com.teamproject.devTalks.repository.user;

import com.teamproject.devTalks.entity.resultSet.UserListResultSet;
import com.teamproject.devTalks.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public boolean existsByUserEmail(String userEmail);
    public boolean existsByUserNickname(String userNickname);
    public boolean existsByUserPhoneNumber(String userPhoneNumber);
    public boolean existsByUserNumber(Integer userNumber);

    public boolean existsByUserPhoneNumberAndUserEmailNot(String phoneNumber,String userEmail);
    public boolean existsByUserNicknameAndUserEmailNot(String userNickname, String userEmail);


    public UserEntity findByUserEmail(String Email);
    public UserEntity findByUserNumber(Integer userNumber);
    public UserEntity findByUserPhoneNumber(String userPhoneNumber);

    @Query(value = "SELECT " +
    "U.user_number AS userNumber " +
    "FROM user U " +
    "WHERE U.user_email = :userEmail"
    , nativeQuery = true)
    public Integer findByUserEmailEquals(@Param("userEmail") String userEmail);
    

    @Transactional
    public void deleteByUserEmail(String userEmail);

    @Query(
            value =
            "SELECT " +
            "U.user_number AS userNumber," +
            "U.user_email AS userEmail," +
            "U.user_nickname AS userNickname," +
            "U.chat_acceptance AS chatAcceptance," +
            "U.created_at AS createdAt," +
            "(SELECT COUNT(*) FROM recommendation R WHERE R.receiver_user_number = U.user_number) AS recommendationCount "+
            "FROM user U",
            nativeQuery = true
    )

    public List<UserListResultSet> getUserList();
}
