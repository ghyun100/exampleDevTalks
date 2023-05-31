package com.teamproject.devTalks.repository.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamproject.devTalks.entity.chat.UserBlockEntity;
import com.teamproject.devTalks.entity.primaryKey.chat.UserBlockPK;

@Repository
public interface UserBlockRepository extends JpaRepository<UserBlockEntity, UserBlockPK>{
    
    public boolean existsBySenderNumberAndReceiverNumber(Integer senderNumber, Integer receiverNumber);

    public List<UserBlockEntity> findBySenderNumber(Integer senderNumber);
    public List<UserBlockEntity> findByReceiverNumber(Integer receiverNumber);
}
