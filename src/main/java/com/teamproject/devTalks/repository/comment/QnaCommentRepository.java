package com.teamproject.devTalks.repository.comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.teamproject.devTalks.entity.comment.QnaCommentEntity;




@Repository
public interface QnaCommentRepository extends JpaRepository<QnaCommentEntity, Integer> {

    public QnaCommentEntity findByQnaCommentNumber(int qnaCommentNumber);

    public List<QnaCommentEntity> findByQnaBoardNumber(int qnaBoardNumber);

    
    @Transactional // 삭제하려면 달아야함(게시물에 달린 댓글전체삭제) 
    void deleteByQnaBoardNumber(int qnaBoardNumber);

    @Transactional // 댓글하나삭제, 댓글을 지울 때 리스트에서 찾아서 지워야하나
    void deleteByQnaCommentNumber(int qnaCommentNumber);


}
