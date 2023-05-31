package com.teamproject.devTalks.repository.user;

import com.teamproject.devTalks.entity.user.BlackListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface BlackListRepository extends JpaRepository <BlackListEntity , Integer> {

    public boolean existsByUserNumber(Integer userNumber);
    public boolean existsByUserEmailOrUserPhoneNumber(String userEmail, String userPhoneNumber);
    public BlackListEntity findByUserNumber(Integer userNumber);
    public List<BlackListEntity> findAllByOrderByCreatedAtDesc();



}
