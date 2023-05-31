package com.teamproject.devTalks.service.implement.board;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.board.qna.PatchQnaBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.qna.PostQnaBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PatchQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.qna.PostQnaCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.qna.PostQnaHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.qna.GetQnaBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.qna.GetQnaBoardResponseDto;
import com.teamproject.devTalks.entity.board.QnaBoardEntity;
import com.teamproject.devTalks.entity.comment.QnaCommentEntity;
import com.teamproject.devTalks.entity.hashTag.QnaBoardHashTagEntity;
import com.teamproject.devTalks.entity.heart.QnaHeartEntity;
import com.teamproject.devTalks.entity.resultSet.QnaBoardListResultSet;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.repository.board.QnaBoardRepository;
import com.teamproject.devTalks.repository.comment.QnaCommentRepository;
import com.teamproject.devTalks.repository.hashTag.QnaBoardHashTagRepository;
import com.teamproject.devTalks.repository.heart.QnaHeartRepository;
import com.teamproject.devTalks.repository.user.AdminRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.board.QnaBoardService;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service // component로 쓰려고
@RequiredArgsConstructor
public class QnaBoardServiceImplement implements QnaBoardService {

    private final UserRepository userRepository;
    private final QnaBoardRepository qnaBoardRepository;
    private final QnaCommentRepository qnaCommentRepository;
    private final QnaBoardHashTagRepository qnaBoardHashTagRepository;
    private final QnaHeartRepository qnaHeartRepository;
    private final AdminRepository adminRepository;

    @Override
    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardList(String qnaSort) {

        GetQnaBoardListResponseDto body = null;
        try {

            List<QnaBoardListResultSet> resultSets = null;
            if (qnaSort.equals("time"))
                resultSets = qnaBoardRepository.getListOrderByWriteDatetime();
            else if (qnaSort.equals("view"))
                resultSets = qnaBoardRepository.getListOrderByViewCount();
            else if (qnaSort.equals("heart"))
                resultSets = qnaBoardRepository.getListOrderByHeartCount();
            else if (qnaSort.equals("comment"))
                resultSets = qnaBoardRepository.getListOrderByCommentCount();
            else
                return CustomResponse.validationFailed();

            body = new GetQnaBoardListResponseDto(resultSets);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // 검색 기능
    @Override
    public ResponseEntity<? super GetQnaBoardListResponseDto> getQnaBoardSearchList(String group,
            String searchKeyword) {

        GetQnaBoardListResponseDto body = null;

        try {

            List<QnaBoardListResultSet> resultSet = new ArrayList<>();

            if (group.equals("nickname"))
                resultSet = qnaBoardRepository.findByWriterNicknameContaining("%" + searchKeyword + "%");
            if (group.equals("title"))
                resultSet = qnaBoardRepository.findByQnaBoardTitleContaining("%" + searchKeyword + "%");

            body = new GetQnaBoardListResponseDto(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetQnaBoardResponseDto> getQnaBoard(int qnaBoardNumber) {

        GetQnaBoardResponseDto body = null;
        try {

            // 존재하지 않는 게시물 반환
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(qnaBoardNumber);
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            int viewCount = qnaBoardEntity.getViewCount();
            qnaBoardEntity.setViewCount(++viewCount);
            qnaBoardRepository.save(qnaBoardEntity);

            String qnaBoardWriterEmail = qnaBoardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByUserEmail(qnaBoardWriterEmail);

            List<QnaCommentEntity> qnaCommentEntities = qnaCommentRepository.findByQnaBoardNumber(qnaBoardNumber);
            List<QnaBoardHashTagEntity> qnaBoardHashTagEntities = qnaBoardHashTagRepository
                    .findByQnaBoardNumber(qnaBoardNumber);

            List<String> hashStrings = new ArrayList<>();
            for (QnaBoardHashTagEntity hashtagList : qnaBoardHashTagEntities) {
                String hashTags = hashtagList.getBoardHashtag();
                hashStrings.add(hashTags);
            }

            List<QnaHeartEntity> qnaHeartEntities = qnaHeartRepository.findByQnaBoardNumber(qnaBoardNumber);
            int qnaHeartCount = qnaHeartEntities.size();

            List<Integer> heartIntegers = new ArrayList<>();
            for (QnaHeartEntity heartList : qnaHeartEntities) {
                int hearts = heartList.getUserNumber();
                heartIntegers.add(hearts);
            }

            body = new GetQnaBoardResponseDto(qnaBoardEntity, userEntity, qnaCommentEntities, hashStrings,
                    qnaHeartCount, heartIntegers);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @Override
    public ResponseEntity<ResponseDto> postQnaBoard(String userEmail, PostQnaBoardRequestDto dto) {

        List<String> hashtagList = dto.getBoardHashtag();
        List<QnaBoardHashTagEntity> qnaHashtagList = new ArrayList<>();

        try {
            // 존재하지 않는 유저(이메일)
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();

            // Date now = new Date();
            // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            // String writerProfileImageUrl = userEntity.getUserProfileImageUrl();
            // String writerNickname = userEntity.getUserNickname();
            // String writerDatetime = dateFormat.format(now);
            // String qnaTitle = dto.getQnaTitle();
            // String qnaContent = dto.getQnaContent();
            // String qnaBoardImageUrl = dto.getQnaBoardImageUrl();
            // QnaBoardEntity qnaBoardEntity = new QnaBoardEntity(0, writerProfileImageUrl,
            // writerNickname, writerDatetime, qnaTitle, qnaContent, qnaBoardImageUrl);

            QnaBoardEntity qnaBoardEntity = new QnaBoardEntity(userEntity, dto);
            qnaBoardRepository.save(qnaBoardEntity); // DB에저장하기 위해서 Repository에 save메서드를 통해서 insert

            for (String hashTag : hashtagList) {
                QnaBoardHashTagEntity qnaBoardHashTagEntity = new QnaBoardHashTagEntity(hashTag,
                        qnaBoardEntity.getQnaBoardNumber());

                // qnaBoardHashTagRepository.save(qnaBoardHashTagEntity);

                qnaHashtagList.add(qnaBoardHashTagEntity); // 하나의 qnaHasgEntity라는 객체를 담을 리스트에 담은거
            }

            qnaBoardHashTagRepository.saveAll(qnaHashtagList);

            // 데이터베이스오류
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        // return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("SU",
        // "Success"));
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postQnaComment(String userEmail, PostQnaCommentRequestDto dto) {
        try {
            // 존재하지 않는 유저(이메일)
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();

            // 존재하지 않는 게시물(게시물번호)
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(dto.getQnaBoardNumber());
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            QnaCommentEntity qnaCommentEntity = new QnaCommentEntity(userEntity, qnaBoardEntity, dto);
            qnaCommentRepository.save(qnaCommentEntity);

            // 데이터베이스 오류
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> postQnaHeart(String userEmail, PostQnaHeartRequestDto dto) {
        try {
            // 존재하지 않는 유저(이메일)
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();
            // 존재하지 않는 게시물(게시물번호)
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(dto.getQnaBoardNumber());
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            QnaHeartEntity qnaHeartEntity = new QnaHeartEntity(userEntity, qnaBoardEntity);
            qnaHeartRepository.save(qnaHeartEntity);

            // 데이터베이스 오류
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchQnaBoard(String userEmail, PatchQnaBoardRequestDto dto) {
        try {

            // 존재하지 않는 유저(이메일)
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();
            // 존재하지 않는 게시물(게시물번호)
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(dto.getQnaBoardNumber());
            // dto 에서 받아왔으니 qnaBoardRepository.findByQnaBoardNumber(dto.getQnaBoardNumber()
            // 이렇게 dto에서 꺼내옴
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            QnaBoardEntity qnaBoardEntityPathch = new QnaBoardEntity(userEntity, dto);
            qnaBoardRepository.save(qnaBoardEntityPathch);

            // 데이터베이스 오류
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchQnaComment(String userEmail, PatchQnaCommentRequestDto dto) {
        try {
            // 존재하지 않는 유저(이메일)
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();
            // 존재하지 않는 게시물(게시물번호)
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(dto.getQnaBoardNumber());
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            // 존재하지 않는 댓글(댓글번호)
            QnaCommentEntity qnaCommentEntity = qnaCommentRepository.findByQnaCommentNumber(dto.getQnaCommentNumber());
            if (qnaCommentEntity == null)
                return CustomResponse.notExistCommentNumber();

            QnaCommentEntity qnaCommentEntityPatch = new QnaCommentEntity(userEntity, qnaBoardEntity, dto);
            qnaCommentRepository.save(qnaCommentEntityPatch);

            // 데이터베이스 오류
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteQnaBoard(String userEmail, int qnaBoardNumber) {

        try {
            // 존재하지 않는 유저(이메일)
            boolean existUser = userRepository.existsByUserEmail(userEmail);
            if (!existUser)
                return CustomResponse.noExistUser();
            // 존재하지 않는 게시물(게시물 번호)
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(qnaBoardNumber);
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            qnaCommentRepository.deleteByQnaBoardNumber(qnaBoardNumber);
            qnaHeartRepository.deleteByQnaBoardNumber(qnaBoardNumber);
            qnaBoardHashTagRepository.deleteByQnaBoardNumber(qnaBoardNumber);
            qnaBoardRepository.delete(qnaBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override // 존재하지 않는 댓글번호같은건 필요없음 삭제를 하면 있든 없든 없애면 되니까
    public ResponseEntity<ResponseDto> deleteQnaComment(String userEmail, int qnaCommentNumber) {
        try {
            // 존재하지 않는 유저(이메일)
            boolean existUser = userRepository.existsByUserEmail(userEmail);
            if (!existUser)
                return CustomResponse.noExistUser();

            qnaCommentRepository.deleteByQnaCommentNumber(qnaCommentNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteQnaHeart(String userEmail, int qnaBoardNumber) {
        try {

            // 존재하지 않는 유저(이메일)
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();
            int userNumber = userEntity.getUserNumber();
            qnaHeartRepository.deleteByUserNumberAndQnaBoardNumber(userNumber, qnaBoardNumber);
            // 뭘로 삭제할거냐? UserNumber란 QnaBoardNumber를 이용해 지울꺼다
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAdminQnaBoard(String adminEmail, int qnaBoardNumber) {

        try {
            // 존재하지 않는 관리자(이메일)
            boolean existAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if (!existAdmin)
                return CustomResponse.noExistAdmin();
            // 존재하지 않는 게시물 번호
            QnaBoardEntity qnaBoardEntity = qnaBoardRepository.findByQnaBoardNumber(qnaBoardNumber);
            if (qnaBoardEntity == null)
                return CustomResponse.notExistBoardNumber();

            qnaCommentRepository.deleteByQnaBoardNumber(qnaBoardNumber);
            qnaHeartRepository.deleteByQnaBoardNumber(qnaBoardNumber);
            qnaBoardHashTagRepository.deleteByQnaBoardNumber(qnaBoardNumber);
            qnaBoardRepository.delete(qnaBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    // 관리자 권한으로 댓글삭제
    @Override
    public ResponseEntity<ResponseDto> deleteAdminQnaComment(String adminEmail, int qnaCommentNumber) {
        try {

            boolean existAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if (!existAdmin)
                return CustomResponse.noExistAdmin();

            qnaCommentRepository.deleteByQnaCommentNumber(qnaCommentNumber);
            // 댓글 번호를 찾아서 지우는 것
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

}
