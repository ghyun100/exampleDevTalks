package com.teamproject.devTalks.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.board.RecruitBoardEntity;
import com.teamproject.devTalks.entity.resultSet.RecruitBoardListResultSet;

@Repository
public interface RecruitBoardRepository extends JpaRepository<RecruitBoardEntity, Integer> {

    public RecruitBoardEntity findByRecruitBoardNumber(Integer recruitBoardNumber);

    @Query(value = "SELECT " +
    "R.recruit_board_number AS recruitBoardNumber, " +
    "R.recruit_board_title AS recruitBoardTitle, " +
    "R.writer_profile_image_url AS writerProfileImageUrl, " +
    "R.writer_nickname AS writerNickname, " +
    "R.writer_email AS writerEmail, " +
    "R.write_datetime AS writeDatetime, " +
    "R.view_count AS viewCount, " +
    "count(C.recruit_comment_number) AS commentCount, " +
    "count(H.user_number) AS heartCount, " +
    "R.recruitment_status AS recruitmentStatus " +
    "FROM recruit R " +
    "LEFT JOIN recruit_comment C " +
    "ON R.recruit_board_number = C.recruit_board_number " +
    "LEFT JOIN recruit_heart H " +
    "ON R.recruit_board_number = H.recruit_board_number " +
    "GROUP BY recruitBoardNumber " +
    "ORDER BY R.write_datetime DESC ", nativeQuery = true
    )
    public List<RecruitBoardListResultSet> getRecruitBoardListOrderByWriteDateTime();
    
    @Query(value = "SELECT " +
    "R.recruit_board_number AS recruitBoardNumber, " +
    "R.recruit_board_title AS recruitBoardTitle, " +
    "R.writer_profile_image_url AS writerProfileImageUrl, " +
    "R.writer_nickname AS writerNickname, " +
    "R.writer_email AS writerEmail, " +
    "R.write_datetime AS writeDatetime, " +
    "R.view_count AS viewCount, " +
    "count(C.recruit_comment_number) AS commentCount, " +
    "count(H.user_number) AS heartCount, " +
    "R.recruitment_status AS recruitmentStatus " +
    "FROM recruit R " +
    "LEFT JOIN recruit_comment C " +
    "ON R.recruit_board_number = C.recruit_board_number " +
    "LEFT JOIN recruit_heart H " +
    "ON R.recruit_board_number = H.recruit_board_number " +
    "GROUP BY recruitBoardNumber " +
    "ORDER BY recruitHeartCount ", nativeQuery = true
    )
    public List<RecruitBoardListResultSet> getRecruitBoardListOrderByHeartCount();

    @Query(value = "SELECT " +
    "R.recruit_board_number AS recruitBoardNumber, " +
    "R.recruit_board_title AS recruitBoardTitle, " +
    "R.writer_profile_image_url AS writerProfileImageUrl, " +
    "R.writer_nickname AS writerNickname, " +
    "R.writer_email AS writerEmail, " +
    "R.write_datetime AS writeDatetime, " +
    "R.view_count AS viewCount, " +
    "count(C.recruit_comment_number) AS commentCount, " +
    "count(H.user_number) AS heartCount, " +
    "R.recruitment_status AS recruitmentStatus " +
    "FROM recruit R " +
    "LEFT JOIN recruit_comment C " +
    "ON R.recruit_board_number = C.recruit_board_number " +
    "LEFT JOIN recruit_heart H " +
    "ON R.recruit_board_number = H.recruit_board_number " +
    "GROUP BY recruitBoardNumber " +
    "ORDER BY recruitCommentCount ", nativeQuery = true
    )
    public List<RecruitBoardListResultSet> getRecruitBoardListOrderByCommentCount();

    @Query(value = "SELECT " +
    "R.recruit_board_number AS recruitBoardNumber, " +
    "R.recruit_board_title AS recruitBoardTitle, " +
    "R.writer_profile_image_url AS writerProfileImageUrl, " +
    "R.writer_nickname AS writerNickname, " +
    "R.writer_email AS writerEmail, " +
    "R.write_datetime AS writeDatetime, " +
    "R.view_count AS viewCount, " +
    "count(C.recruit_comment_number) AS commentCount, " +
    "count(H.user_number) AS heartCount, " +
    "R.recruitment_status AS recruitmentStatus " +
    "FROM recruit R " +
    "LEFT JOIN recruit_comment C " +
    "ON R.recruit_board_number = C.recruit_board_number " +
    "LEFT JOIN recruit_heart H " +
    "ON R.recruit_board_number = H.recruit_board_number " +
    "GROUP BY recruitBoardNumber " +
    "ORDER BY viewCount ", nativeQuery = true
    )
    public List<RecruitBoardListResultSet> getRecruitBoardListOrderByViewCount();


    @Transactional
    public void deleteByRecruitBoardNumber(int recruitBoardNumber);


    @Query(value = "SELECT " +
    "R.recruit_board_number AS recruitBoardNumber, " +
    "R.recruit_board_title AS recruitBoardTitle, " +
    "R.writer_profile_image_url AS writerProfileImageUrl, " +
    "R.writer_nickname AS writerNickname, " +
    "R.writer_email AS writerEmail, " +
    "R.write_datetime AS writeDatetime, " +
    "R.view_count AS viewCount, " +
    "count(C.recruit_comment_number) AS commentCount, " +
    "count(H.user_number) AS heartCount, " +
    "R.recruitment_status AS recruitmentStatus " +
    "FROM recruit R " +
    "LEFT JOIN recruit_comment C " +
    "ON R.recruit_board_number = C.recruit_board_number " +
    "LEFT JOIN recruit_heart H " +
    "ON R.recruit_board_number = H.recruit_board_number " +
    "WHERE R.recruit_board_title LIKE ? " +
    "GROUP BY recruitBoardNumber " +
    "ORDER BY writeDatetime DESC ", nativeQuery = true
    )
    public List<RecruitBoardListResultSet> findByRecruitBoardTitleContaining(String searchKeyword);

    @Query(value = "SELECT " +
    "R.recruit_board_number AS recruitBoardNumber, " +
    "R.recruit_board_title AS recruitBoardTitle, " +
    "R.writer_profile_image_url AS writerProfileImageUrl, " +
    "R.writer_nickname AS writerNickname, " +
    "R.writer_email AS writerEmail, " +
    "R.write_datetime AS writeDatetime, " +
    "R.view_count AS viewCount, " +
    "count(C.recruit_comment_number) AS commentCount, " +
    "count(H.user_number) AS heartCount, " +
    "R.recruitment_status AS recruitmentStatus " +
    "FROM recruit R " +
    "LEFT JOIN recruit_comment C " +
    "ON R.recruit_board_number = C.recruit_board_number " +
    "LEFT JOIN recruit_heart H " +
    "ON R.recruit_board_number = H.recruit_board_number " +
    "WHERE R.writer_nickname LIKE ? " +
    "GROUP BY recruitBoardNumber " +
    "ORDER BY writeDatetime DESC ", nativeQuery = true
    )
    public List<RecruitBoardListResultSet> findByWriterNicknameContaining(String searchKeyword);


}
