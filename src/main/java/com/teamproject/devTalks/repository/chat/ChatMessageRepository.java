package com.teamproject.devTalks.repository.chat;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mysql.cj.protocol.Message;
import com.teamproject.devTalks.entity.chat.ChatMessageEntity;
import com.teamproject.devTalks.entity.resultSet.chat.ChatMessageListResultSet;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Integer> {


    List<ChatMessageEntity> findByChatMessageNumber(int chatMessageNumber);

    @Query(value = "SELECT DISTINCT M.chat_room_number AS chatRoomNumber, " +
    "M.chat_message_number AS chatMessageNumber, " +
    "U.user_nickname AS userNickname, " +
    "M.sent_datetime AS sentDatetime, " +
    "M.message AS message, " +
    "M.chat_status AS chatStatus " +
    "FROM chat_message M " +
    "LEFT JOIN user U ON M.from_number = U.user_number " +
    "LEFT JOIN chat_room R ON M.chat_room_number = R.chat_room_number " +
    "WHERE M.chat_room_number = :chatRoomNumber " +
    "ORDER BY M.sent_datetime ", nativeQuery = true)
    List<ChatMessageListResultSet> getListOrderBySentDatetime(@Param("chatRoomNumber") String chatRoomNumber);


    // 유저가 조회하는 채팅방 안읽은 메시지 읽음 처리
        @Modifying
        @Query(value = "UPDATE chat_message " +
        "SET chat_status = true " +
        "WHERE from_number <> :userNumber " +
        "AND chat_room_number = :chatRoomNumber ", 
        nativeQuery = true)
         void setChatStatusTrue(@Param("userNumber") int userNumber, @Param("chatRoomNumber") String chatRoomNumber);

    @Transactional
    void deleteAllByChatRoomNumber(String chatRoomNumber);

    @Transactional
    void deleteByChatRoomNumberAndChatMessageNumber(String chatRoomNumber, Integer chatMessageNumber);
    }


