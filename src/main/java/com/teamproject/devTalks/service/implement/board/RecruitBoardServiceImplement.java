package com.teamproject.devTalks.service.implement.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.board.recruit.PatchRecruitBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.recruit.PostRecruitBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.recruit.PatchRecruitCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.recruit.PostRecruitCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.recruit.PostRecruitHeartRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.board.recruit.GetRecruitBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.recruit.GetRecruitBoardResponseDto;
import com.teamproject.devTalks.entity.board.RecruitBoardEntity;
import com.teamproject.devTalks.entity.comment.RecruitCommentEntity;
import com.teamproject.devTalks.entity.hashTag.RecruitBoardHashTagEntity;
import com.teamproject.devTalks.entity.heart.RecruitHeartEntity;
import com.teamproject.devTalks.entity.resultSet.RecruitBoardListResultSet;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.repository.board.RecruitBoardRepository;
import com.teamproject.devTalks.repository.comment.RecruitCommentRepository;
import com.teamproject.devTalks.repository.hashTag.RecruitBoardHashTagRepository;
import com.teamproject.devTalks.repository.heart.RecruitHeartRepository;
import com.teamproject.devTalks.repository.user.AdminRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.board.RecruitBoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruitBoardServiceImplement implements RecruitBoardService {

    private UserRepository userRepository;
    private RecruitBoardRepository recruitBoardRepository;
    private RecruitCommentRepository recruitCommentRepository;
    private RecruitHeartRepository recruitHeartRepository;
    private RecruitBoardHashTagRepository recruitBoardHashTagRepository;
    private AdminRepository adminRepository;

    @Autowired
    public RecruitBoardServiceImplement(
        UserRepository userRepository,
        AdminRepository adminRepository,
        RecruitBoardRepository recruitBoardRepository,
        RecruitCommentRepository recruitCommentRepository,
        RecruitHeartRepository recruitHeartRepository,
        RecruitBoardHashTagRepository recruitBoardHashTagRepository
    ) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.recruitBoardRepository = recruitBoardRepository;
        this.recruitCommentRepository = recruitCommentRepository;
        this.recruitHeartRepository = recruitHeartRepository;
        this.recruitBoardHashTagRepository = recruitBoardHashTagRepository;
    }

    
    @Override
    public ResponseEntity<? super GetRecruitBoardResponseDto> getRecruitBoard(Integer recruitBoardNumber) {

        GetRecruitBoardResponseDto body = null;

        try {
            if (recruitBoardNumber == null) return CustomResponse.validationFailed();

            RecruitBoardEntity recruitBoardEntity = recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if (recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();

            int viewCount = recruitBoardEntity.getViewCount();
            recruitBoardEntity.setViewCount(++viewCount);
            recruitBoardRepository.save(recruitBoardEntity);

            String writerEmail = recruitBoardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByUserEmail(writerEmail);
            
            List<RecruitCommentEntity> recruitCommentEntities = recruitCommentRepository.findByRecruitBoardNumber(recruitBoardNumber);
            List<RecruitHeartEntity> recruitHeartEntities = recruitHeartRepository.findByRecruitBoardNumber(recruitBoardNumber);
            List<RecruitBoardHashTagEntity> recruitBoardHashTagEntities = recruitBoardHashTagRepository.findAllByRecruitBoardNumber(recruitBoardNumber);

            List<String> boardHashTag = new ArrayList<>();
            for (RecruitBoardHashTagEntity boardHashTagList: recruitBoardHashTagEntities) {
                String hashTags = boardHashTagList.getBoardHashTag();
                boardHashTag.add(hashTags);
            }

            body = new GetRecruitBoardResponseDto(recruitBoardEntity, userEntity, recruitCommentEntities, recruitHeartEntities, boardHashTag);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(body);

    }
    
    @Override
    public ResponseEntity<? super GetRecruitBoardListResponseDto> getRecruitBoardList(String recruitSort) {
        
        GetRecruitBoardListResponseDto body = null;

        try {

            List<RecruitBoardListResultSet> resultSet = null;

            if(recruitSort.equals("time")) 
                resultSet = recruitBoardRepository.getRecruitBoardListOrderByWriteDateTime();
            
            if(recruitSort.equals("heartCount"))
                resultSet = recruitBoardRepository.getRecruitBoardListOrderByHeartCount();
            
            if(recruitSort.equals("commentCount"))
                resultSet = recruitBoardRepository.getRecruitBoardListOrderByCommentCount();
            
            if(recruitSort.equals("viewCount"))
                resultSet = recruitBoardRepository.getRecruitBoardListOrderByViewCount();


            body = new GetRecruitBoardListResponseDto(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    // 전체 리스트 검색
    @Override
    public ResponseEntity<? super GetRecruitBoardListResponseDto> getRecruitBoardSearchList(String group, String searchKeyword) {
        
        GetRecruitBoardListResponseDto body = null;

        try {

            List<RecruitBoardListResultSet> resultSet = new ArrayList<>();

            if (group.equals("title")) resultSet = recruitBoardRepository.findByRecruitBoardTitleContaining("%" + searchKeyword + "%");
            if (group.equals("nickname")) resultSet = recruitBoardRepository.findByWriterNicknameContaining("%" + searchKeyword + "%");

            body = new GetRecruitBoardListResponseDto(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @Override
    public ResponseEntity<ResponseDto> postRecruitBoard(String userEmail, PostRecruitBoardRequestDto dto) {

        try {
            // 존재하지 않는 유저 오류 반환
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) return CustomResponse.noExistUser();

            RecruitBoardEntity recruitBoardEntity = new RecruitBoardEntity(userEntity, dto);
            recruitBoardRepository.save(recruitBoardEntity);

        } catch (Exception exception) {
            // 데이터베이스 오류 반환
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        // 성공 반환
        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> patchRecruitBoard(String userEmail, Integer recruitBoardNumber, PatchRecruitBoardRequestDto dto) {
        
        String recruitBoardTitle = dto.getRecruitBoardTitle();
        String recruitBoardContent = dto.getRecruitBoardContent();
        String recruitBoardImageUrl = dto.getRecruitBoardImageUrl();
        

        try {
            if (recruitBoardNumber == null) return CustomResponse.validationFailed();

            // 존재하지 않는 게시물 번호
            RecruitBoardEntity recruitBoardEntity = recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if (recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();

            // 존재하지 않는 유저 이메일
            boolean existedUserEmail = userRepository.existsByUserEmail(userEmail);
            if (!existedUserEmail) return CustomResponse.noExistUser();

            // 권한 없음
            boolean equalWriter = recruitBoardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            recruitBoardEntity.setRecruitBoardTitle(recruitBoardTitle);
            recruitBoardEntity.setRecruitBoardContent(recruitBoardContent);
            recruitBoardEntity.setRecruitBoardImageUrl(recruitBoardImageUrl);
            recruitBoardEntity.isRecruitmentStatus();

            recruitBoardRepository.save(recruitBoardEntity);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteRecruitBoard(String userEmail, Integer recruitBoardNumber) {
        
        try {
            if (recruitBoardNumber == null) return CustomResponse.validationFailed();

            // 존재하지 않는 게시물 번호 반환
            RecruitBoardEntity recruitBoardEntity = recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if (recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();

            // 존재하지 않는 유저 이메일 반환
            boolean existedUserEmail = userRepository.existsByUserEmail(userEmail);
            if (!existedUserEmail) return CustomResponse.noExistUser();

            // 권한 없음 반환
            boolean equalWriter = recruitBoardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            recruitCommentRepository.deleteByRecruitBoardNumber(recruitBoardNumber);
            recruitHeartRepository.deleteByRecruitBoardNumber(recruitBoardNumber);
            recruitBoardRepository.delete(recruitBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postRecruitComment(String userEmail, Integer recruitBoardNumber, PostRecruitCommentRequestDto dto) {

        try {

            // 존재하지 않는 유저 오류 반환
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return CustomResponse.noExistUser();
            
            //  존재하지 않는 게시물 번호 반환
            RecruitBoardEntity recruitBoardEntity = 
                recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if (recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();

            RecruitCommentEntity recruitCommentEntity = 
                new RecruitCommentEntity(userEntity, recruitBoardEntity, dto);
            recruitCommentRepository.save(recruitCommentEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> patchRecruitComment(String userEmail, Integer recruitBoardNumber, Integer recruitCommentNumber, PatchRecruitCommentRequestDto dto) {
        
        String recruitCommentContent = dto.getRecruitCommentContent();

        try {

            // 존재하지 않는 게시판 번호
            RecruitBoardEntity recruitBoardEntity =
                recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if(recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();

            // 존재하지 않는 댓글 번호
            RecruitCommentEntity recruitCommentEntity = 
                recruitCommentRepository.findByRecruitCommentNumber(recruitCommentNumber);
            if (recruitCommentEntity == null) return CustomResponse.notExistCommentNumber();

             // 권한 없음
            boolean equalWriter = recruitCommentEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            recruitCommentEntity.setRecruitCommentContent(recruitCommentContent);
            recruitCommentRepository.save(recruitCommentEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteRecruitComment(String userEmail, Integer recruitBoardNumber, Integer recruitCommentNumber) {
        
        try {

            if (recruitBoardNumber == null) return CustomResponse.validationFailed();
            if (recruitCommentNumber == null) return CustomResponse.validationFailed();

            // 존재하지 않는 보드 넘버
            RecruitBoardEntity recruitBoardEntity =
            recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if(recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();

            // 존재하지 않는 댓글 번호
            RecruitCommentEntity recruitCommentEntity = 
            recruitCommentRepository.findByRecruitCommentNumber(recruitCommentNumber);
            if(recruitCommentEntity == null) return CustomResponse.notExistCommentNumber();

            // 권한 없음
            boolean equalWriter = recruitCommentEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            recruitCommentRepository.deleteByRecruitCommentNumber(recruitCommentNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        
        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> postRecruitHeart(String userEmail, Integer recruitBoardNumber, PostRecruitHeartRequestDto dto) {
        
        try {

            // 존재하지 않는 유저 반환
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return CustomResponse.noExistUser(); 

            // 존재하지 않는 게시판 번호 반환
            RecruitBoardEntity recruitBoardEntity =
                recruitBoardRepository.findByRecruitBoardNumber(recruitBoardNumber);
            if(recruitBoardEntity == null) return CustomResponse.notExistBoardNumber();
            
            RecruitHeartEntity recruitHeartEntity = new RecruitHeartEntity(userEntity, recruitBoardEntity);
            recruitHeartRepository.save(recruitHeartEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteRecruitHeart(String userEmail, Integer recruitBoardNumber) {
        
        try {

            // 존재하지 않는 유저 이메일
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) return CustomResponse.noExistUser();

            int userNumber = userEntity.getUserNumber();
            recruitHeartRepository.deleteByUserNumberAndRecruitBoardNumber(userNumber, recruitBoardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }


    @Override
    public ResponseEntity<ResponseDto> deleteAdminRecruitBoard(String adminEmail, Integer recruitBoardNumber) {
        
        try {
            boolean existAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if (!existAdmin) return CustomResponse.authenticationFailed();

            recruitBoardRepository.deleteByRecruitBoardNumber(recruitBoardNumber);
            recruitHeartRepository.deleteByRecruitBoardNumber(recruitBoardNumber);
            recruitBoardRepository.deleteByRecruitBoardNumber(recruitBoardNumber);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();

    }


    @Override
    public ResponseEntity<ResponseDto> deleteAdminRecruitComment(String adminEmail, Integer recruitCommentNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAdminRecruitComment'");
    }





    

    
    
    
}
