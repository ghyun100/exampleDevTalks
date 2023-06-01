package com.teamproject.devTalks.repository.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.board.InformationBoardEntity;
import com.teamproject.devTalks.entity.resultSet.InformationBoardListResultSet;

@Repository
public interface InformationBoardRepository extends JpaRepository<InformationBoardEntity, Integer> {
    
    public InformationBoardEntity findByInformationBoardNumber(Integer informationBoardNumber);

    @Query(value = "SELECT " +
        "I.information_board_number AS informationBoardNumber, " +
        "I.writer_profile_image_url AS writerProfileImageUrl, " +
        "I.writer_nickname AS writerNickname, " +
        "I.writer_email AS writerEmail, " +
        "I.information_board_title AS informationBoardTitle, " +
        "I.information_board_content AS informationBoardContent, " +
        "I.information_board_image_url AS informationBoardImageUrl, " +
        "I.view_count AS viewCount, " +
        "I.write_datetime AS writeDatetime, " +
        "count(C.information_comment_number) AS informationCommentCount, " +
        "count(H.user_number) AS informationHeartCount " +
        "FROM information I, information_comment C, information_heart H, information_hashtag T " +
        "WHERE I.information_board_number = C.information_board_number " +
        "AND I.information_board_number = H.information_board_number " +
        "group by informationBoardNumber " +
        "order by writeDatetime ", nativeQuery = true)
    @Transactional
    void deleteByInformationBoardNumber(@Param("informationBoardNumber") Integer informationBoardNumber);

    // 최신순 정보 게시물 목록 조회    
    @Query(value = "SELECT " +
        "I.information_board_number AS informationBoardNumber, " +
        "I.writer_profile_image_url AS writerProfileImageUrl, " +
        "I.writer_nickname AS writerNickname, " +
        "I.writer_email AS writerEmail, " +
        "I.information_board_title AS informationBoardTitle, " +
        "I.view_count AS viewCount, " +
        "I.write_datetime AS writeDatetime, " +
        "count(C.information_comment_number) AS informationCommentCount, " +
        "count(H.user_number) AS informationHeartCount " +
        "FROM information I " +
        "LEFT JOIN information_comment C " +
        "ON I.information_board_number = C.information_board_number " +
        "LEFT JOIN information_heart H " +
        "ON I.information_board_number = H.information_board_number " +
        "GROUP BY I.information_board_number " +
        "ORDER BY I.write_datetime DESC", nativeQuery = true)
    List<InformationBoardListResultSet> getListOrderByWriteDatetime();

    // 좋아요순 게시물 목록 조회
    @Query(value = "SELECT " +
            "I.information_board_number AS informationBoardNumber, " +
            "I.writer_profile_image_url AS writerProfileImageUrl, " +
            "I.writer_nickname AS writerNickname, " +
            "I.writer_email AS writerEmail, " +
            "I.information_board_title AS informationBoardTitle, " +
            "I.view_count AS viewCount, " +
            "I.write_datetime AS writeDatetime, " +
            "count(C.information_comment_number) AS informationCommentCount, " +
            "count(H.user_number) AS informationHeartCount " +
            "FROM information I " +
            "LEFT JOIN information_comment C " +
            "ON I.information_board_number = C.information_board_number " +
            "LEFT JOIN information_heart H " +
            "ON I.information_board_number = H.information_board_number " +
            "GROUP BY I.information_board_number " +
            "ORDER BY informationHeartCount DESC", nativeQuery = true)
        List<InformationBoardListResultSet> getListOrderByHeartCount();

    // 댓글순 게시물 목록 조회
    @Query(value = "SELECT " +
            "I.information_board_number AS informationBoardNumber, " +
            "I.writer_profile_image_url AS writerProfileImageUrl, " +
            "I.writer_nickname AS writerNickname, " +
            "I.writer_email AS writerEmail, " +
            "I.information_board_title AS informationBoardTitle, " +
            "I.view_count AS viewCount, " +
            "I.write_datetime AS writeDatetime, " +
            "count(C.information_comment_number) AS informationCommentCount, " +
            "count(H.user_number) AS informationHeartCount " +
            "FROM information I " +
            "LEFT JOIN information_comment C " +
            "ON I.information_board_number = C.information_board_number " +
            "LEFT JOIN information_heart H " +
            "ON I.information_board_number = H.information_board_number " +
            "GROUP BY I.information_board_number " +
            "ORDER BY informationCommentCount DESC", nativeQuery = true)
        List<InformationBoardListResultSet> getListOrderByCommentCount();

    // 조회순으로 게시물 목록 조회
        @Query(value = "SELECT " +
        "I.information_board_number AS informationBoardNumber, " +
        "I.writer_profile_image_url AS writerProfileImageUrl, " +
        "I.writer_nickname AS writerNickname, " +
        "I.writer_email AS writerEmail, " +
        "I.information_board_title AS informationBoardTitle, " +
        "I.view_count AS viewCount, " +
        "I.write_datetime AS writeDatetime, " +
        "count(C.information_comment_number) AS informationCommentCount, " +
        "count(H.user_number) AS informationHeartCount " +
        "FROM information I " +
        "LEFT JOIN information_comment C " +
        "ON I.information_board_number = C.information_board_number " +
        "LEFT JOIN information_heart H " +
        "ON I.information_board_number = H.information_board_number " +
        "GROUP BY I.information_board_number " +
        "ORDER BY I.view_count DESC", nativeQuery = true)
        List<InformationBoardListResultSet> getListOrderByViewCount();

    // 검색 했을 때 조건 정렬 (제목)
        @Query(value = "SELECT " +
        "I.information_board_number AS informationBoardNumber, " +
        "I.writer_profile_image_url AS writerProfileImageUrl, " +
        "I.writer_nickname AS writerNickname, " +
        "I.writer_email AS writerEmail, " +
        "I.information_board_title AS informationBoardTitle, " +
        "I.view_count AS viewCount, " +
        "I.write_datetime AS writeDatetime, " +
        "count(C.information_comment_number) AS informationCommentCount, " +
        "count(H.user_number) AS informationHeartCount " +
        "FROM information I " +
        "LEFT JOIN information_comment C " +
        "ON I.information_board_number = C.information_board_number " +
        "LEFT JOIN information_heart H " +
        "ON I.information_board_number = H.information_board_number " +
        "WHERE I.information_board_title LIKE ? " +
        "GROUP BY I.information_board_number " +
        "ORDER BY I.write_datetime DESC", nativeQuery = true)
        public List<InformationBoardListResultSet> findByInformationBoardTitleContaining(String searchKeyword);

    // 검색 했을 때 조건 정렬 (닉네임)
        @Query(value = "SELECT " +
        "I.information_board_number AS informationBoardNumber, " +
        "I.writer_profile_image_url AS writerProfileImageUrl, " +
        "I.writer_nickname AS writerNickname, " +
        "I.writer_email AS writerEmail, " +
        "I.information_board_title AS informationBoardTitle, " +
        "I.view_count AS viewCount, " +
        "I.write_datetime AS writeDatetime, " +
        "count(C.information_comment_number) AS informationCommentCount, " +
        "count(H.user_number) AS informationHeartCount " +
        "FROM information I " +
        "LEFT JOIN information_comment C " +
        "ON I.information_board_number = C.information_board_number " +
        "LEFT JOIN information_heart H " +
        "ON I.information_board_number = H.information_board_number " +
        "WHERE I.writer_nickname LIKE ? " +
        "GROUP BY informationBoardNumber " +
        "ORDER BY writeDatetime DESC", nativeQuery = true)
        public List<InformationBoardListResultSet> findByInformationWriterNicknameContaining(String searchKeyword);
}
