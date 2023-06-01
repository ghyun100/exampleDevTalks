package com.teamproject.devTalks.repository.chat;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.teamproject.devTalks.entity.chat.ChatRoomEntity;
import com.teamproject.devTalks.entity.primaryKey.chat.ChatRoomPk;
import com.teamproject.devTalks.entity.resultSet.chat.ChatRoomListResultSet;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, ChatRoomPk> {

    boolean existsByChatRoomNumber(String chatRoomNumber);

    @Query (value = "SELECT R.chat_room_number AS chatRoomNumber, " +
    "U.user_status AS userStatus, " +
    "U.user_nickname AS userNickname, " +
    "M.message AS lastMessage, " +
    "M.sent_datetime AS sentDatetime, " + 
    "count(MS.chat_message_number) AS unreadMessageCount " +
    "FROM chat_room R " +
    "LEFT JOIN user U ON R.user_number = U.user_number " +
    "LEFT JOIN chat_message M ON R.chat_room_number = M.chat_room_number AND M.sent_datetime = " +
    "(SELECT sent_datetime FROM chat_message WHERE chat_room_number = R.chat_room_number ORDER BY sent_datetime DESC LIMIT 1) " +
    "LEFT JOIN (SELECT * FROM chat_message WHERE NOT chat_status AND from_number <> :userNumber) MS ON R.chat_room_number = MS.chat_room_number " +
    "WHERE R.chat_room_number IN " +
    "(SELECT chat_room_number FROM chat_room " +
    "WHERE user_number = :userNumber) " +
    "AND U.user_number <> :userNumber " +
    "GROUP BY R.chat_room_number " +
    "ORDER BY M.sent_datetime DESC ", nativeQuery = true)
    List<ChatRoomListResultSet> findAllByOrderBySentDatetimeDesc(@Param("userNumber") Integer userNumber);
    
    List<ChatRoomEntity> findByChatRoomNumber(String chatRoomNumber);

    //  중복 제거 
    @Query(value = "SELECT * FROM chat_room " +
    "WHERE chat_room_number IN  " +
    "(SELECT chat_room_number FROM chat_room WHERE user_number = :fromNumber) " +
    "AND user_number = :toNumber ", nativeQuery = true)
    public ChatRoomEntity findExistChatRoomCountByUserNumber(@Param("fromNumber") Integer fromNumber, @Param("toNumber") Integer toNumber);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM chat_room WHERE chat_room_number = :chatRoomNumber", nativeQuery = true)
    void deleteByChatRoomNumber(@Param("chatRoomNumber")String chatRoomNumber);

    }
