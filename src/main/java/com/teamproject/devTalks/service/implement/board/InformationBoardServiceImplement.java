package com.teamproject.devTalks.service.implement.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.repository.board.InformationBoardRepository;
import com.teamproject.devTalks.repository.comment.InformationCommentRepository;
import com.teamproject.devTalks.repository.hashTag.InformationBoardHashtagRepository;
import com.teamproject.devTalks.repository.heart.InformationHeartRepository;
import com.teamproject.devTalks.repository.user.AdminRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.dto.response.board.information.GetInformationBoardListResponseDto;
import com.teamproject.devTalks.dto.response.board.information.GetInformationBoardResponseDto;
import com.teamproject.devTalks.entity.board.InformationBoardEntity;
import com.teamproject.devTalks.entity.comment.InformationCommentEntity;
import com.teamproject.devTalks.entity.hashTag.InformationBoardHashtagEntity;
import com.teamproject.devTalks.entity.heart.InformationHeartEntity;
import com.teamproject.devTalks.entity.resultSet.InformationBoardListResultSet;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.service.board.InformationBoardService;

import lombok.RequiredArgsConstructor;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.board.information.PatchInformationBoardRequestDto;
import com.teamproject.devTalks.dto.request.board.information.PostInformationBoardRequestDto;
import com.teamproject.devTalks.dto.request.comment.information.PatchInformationCommentRequestDto;
import com.teamproject.devTalks.dto.request.comment.information.PostInformationCommentRequestDto;
import com.teamproject.devTalks.dto.request.heart.information.PostInformationHeartRequestDto;

@Service
@RequiredArgsConstructor
public class InformationBoardServiceImplement implements InformationBoardService {
    
    private final UserRepository userRepository;
    private final InformationBoardRepository informationBoardRepository;
    private final InformationCommentRepository informationCommentRepository;
    private final InformationHeartRepository informationHeartRepository;
    private final InformationBoardHashtagRepository informationBoardHashtagRepository;
    private final AdminRepository adminRepository;

    @Override
    public ResponseEntity<? super GetInformationBoardListResponseDto> getInformationBoardList(String informationSort) {
        
        GetInformationBoardListResponseDto body = null;
        if (!informationSort.equals("latest") && !informationSort.equals("heartcount") && 
        !informationSort.equals("commentcount") && !informationSort.equals("viewcount")) {
            return CustomResponse.validationFailed();
        }
        try {        
        List<InformationBoardListResultSet> resultSets = null;
            // 최신순으로 조회
            if (informationSort.equals("latest")){
            resultSets = informationBoardRepository.getListOrderByWriteDatetime();}
            // 좋아요순으로 조회
            else if (informationSort.equals("heartcount")){
            resultSets = informationBoardRepository.getListOrderByHeartCount();}
            // 댓글순으로 조회
            else if (informationSort.equals("commentcount")){
            resultSets = informationBoardRepository.getListOrderByCommentCount();}
            // 조회순으로 조회
            else if (informationSort.equals("viewcount")){
            resultSets = informationBoardRepository.getListOrderByViewCount();}

            body = new GetInformationBoardListResponseDto(resultSets);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // 검색기능
    @Override
        public ResponseEntity<? super GetInformationBoardListResponseDto> getInformationBoardSearchList(String group,
                String searchKeyword) {
                    
            GetInformationBoardListResponseDto body = null;
            
            try {

                List<InformationBoardListResultSet> resultSet = new ArrayList<>();

                if (group.equals("title")) resultSet = informationBoardRepository.findByInformationBoardTitleContaining("%" + searchKeyword + "%");
                if (group.equals("nickname")) resultSet = informationBoardRepository.findByInformationWriterNicknameContaining("%" + searchKeyword + "%");
            
                body = new GetInformationBoardListResponseDto(resultSet);

            } catch (Exception exception) {
                exception.printStackTrace();
                return CustomResponse.databaseError();
            }

            return ResponseEntity.status(HttpStatus.OK).body(body);

        }

    @Override
    public ResponseEntity<? super GetInformationBoardResponseDto> getInformationBoard(Integer informationBoardNumber) {
                                                                            // Integer는 참조형으로 null을 값으로 가질 수 있음
        GetInformationBoardResponseDto body = null;
        if (informationBoardNumber == null) return CustomResponse.validationFailed();

        try {
            InformationBoardEntity informationBoardEntity = 
            informationBoardRepository.findByInformationBoardNumber(informationBoardNumber);
            if(informationBoardEntity == null) return CustomResponse.notExistBoardNumber();

            int viewCount = informationBoardEntity.getViewCount();
            informationBoardEntity.setViewCount(++viewCount);
            informationBoardRepository.save(informationBoardEntity);

            String writerEmail = informationBoardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByUserEmail(writerEmail);

            List<InformationCommentEntity> informationCommentEntities = 
            informationCommentRepository.findByInformationBoardNumber(informationBoardNumber);
            List<InformationHeartEntity> informationHeartEntities = 
            informationHeartRepository.findByInformationBoardNumber(informationBoardNumber);
            List<String> heartList = informationHeartRepository.findByInformationBoardNumberToUserNumber(informationBoardNumber);
            List<InformationBoardHashtagEntity> informationBoardHashTagEntities = 
            informationBoardHashtagRepository.findByInformationBoardNumber(informationBoardNumber);        
            List<String> hashtagStrings = new ArrayList<>();
            for(InformationBoardHashtagEntity hashtagList: informationBoardHashTagEntities) {
                String hashtags = hashtagList.getBoardHashtag();
                hashtagStrings.add(hashtags);
            }
            body = new GetInformationBoardResponseDto(informationBoardEntity, userEntity, informationCommentEntities, informationHeartEntities, heartList, hashtagStrings);

        } catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
    @Override
    public ResponseEntity<ResponseDto> postInformationBoard(String userEmail, PostInformationBoardRequestDto dto) {
        List<String> hashtagList = dto.getBoardHashtag();
        List<InformationBoardHashtagEntity> informationHashtagList = new ArrayList<>();

        try {
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return CustomResponse.authenticationFailed();
        
            InformationBoardEntity informationBoardEntity = new InformationBoardEntity(userEntity, dto);
            informationBoardRepository.save(informationBoardEntity);

            for (String boardHashtag : hashtagList) {
                InformationBoardHashtagEntity informationBoardHashTagEntity = 
                new InformationBoardHashtagEntity(boardHashtag, informationBoardEntity.getInformationBoardNumber());
                informationHashtagList.add(informationBoardHashTagEntity); 
                }

            informationBoardHashtagRepository.saveAll(informationHashtagList);
    
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }
    
    @Override
    public ResponseEntity<ResponseDto> patchInformationBoard(String userEmail, PatchInformationBoardRequestDto dto) {
        int informationBoardNumber = dto.getInformationBoardNumber();
        String informationBoardTitle = dto.getInformationBoardTitle();
        String informationBoardContent = dto.getInformationBoardContent();
        String informationBoardImageUrl = dto.getInformationBoardImageUrl();

        try {
            InformationBoardEntity informationBoardEntity = 
            informationBoardRepository.findByInformationBoardNumber(informationBoardNumber);
            if (informationBoardEntity == null) return CustomResponse.notExistBoardNumber();

            boolean equalWriter = informationBoardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            informationBoardEntity.setInformationBoardTitle(informationBoardTitle);
            informationBoardEntity.setInformationBoardContent(informationBoardContent);
            informationBoardEntity.setInformationBoardImageUrl(informationBoardImageUrl);

            informationBoardRepository.save(informationBoardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> postInformationComment(String userEmail, PostInformationCommentRequestDto dto) {
    
        try {
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return CustomResponse.authenticationFailed();

            InformationBoardEntity informationBoardEntity = 
            informationBoardRepository.findByInformationBoardNumber(dto.getInformationBoardNumber());
            if (informationBoardEntity == null)
            return CustomResponse.notExistBoardNumber();

            InformationCommentEntity informationCommentEntity = 
            new InformationCommentEntity(userEntity, informationBoardEntity, dto);
            informationCommentRepository.save(informationCommentEntity);    
            
            return CustomResponse.success();

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
    }
    
    @Override
    public ResponseEntity<ResponseDto> postInformationHeart(String userEmail, PostInformationHeartRequestDto dto) {

        try {
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return CustomResponse.authenticationFailed();

            InformationBoardEntity informationBoardEntity = 
            informationBoardRepository.findByInformationBoardNumber(dto.getInformationBoardNumber());
            if (informationBoardEntity == null) return CustomResponse.notExistBoardNumber();

            InformationHeartEntity informationHeartEntity = new InformationHeartEntity(userEntity, informationBoardEntity);
            informationHeartRepository.save(informationHeartEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }



    @Override
    public ResponseEntity<ResponseDto> patchInformationComment(String userEmail,
            PatchInformationCommentRequestDto dto) {

            Integer informationCommentNumber = dto.getInformationCommentNumber();
            String informationCommentContent = dto.getInformationCommentContent();
    
            try {
                InformationCommentEntity informationCommentEntity = 
                informationCommentRepository.findByInformationCommentNumber(informationCommentNumber);
                if (informationCommentEntity == null) return CustomResponse.notExistCommentNumber();
    
                boolean equalWriter = informationCommentEntity.getWriterEmail().equals(userEmail);
                if (!equalWriter) return CustomResponse.noPermission();
    
                informationCommentEntity.setInformationCommentContent(informationCommentContent); 
                informationCommentRepository.save(informationCommentEntity);
    
            } catch (Exception exception) {
                exception.printStackTrace();
                return CustomResponse.databaseError();
            }
    
            return CustomResponse.success();
            }

    @Override
    public ResponseEntity<ResponseDto> deleteInformationBoard(String userEmail, Integer informationBoardNumber) {

        try {
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity == null) return CustomResponse.authenticationFailed();
            
            if (informationBoardNumber == null) return CustomResponse.validationFailed();

            InformationBoardEntity informationBoardEntity = 
            informationBoardRepository.findByInformationBoardNumber(informationBoardNumber);
            if (informationBoardEntity == null) return CustomResponse.notExistBoardNumber();

            boolean equalWriter = informationBoardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            informationCommentRepository.deleteByInformationBoardNumber(informationBoardNumber);
            informationHeartRepository.deleteByInformationBoardNumber(informationBoardNumber);            
            informationBoardHashtagRepository.deleteByInformationBoardNumber(informationBoardNumber);
            informationBoardRepository.deleteByInformationBoardNumber(informationBoardNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteInformationComment(String userEmail, Integer informationCommentNumber) {

        try {
            if (informationCommentNumber == null) return CustomResponse.validationFailed();

            InformationCommentEntity informationCommentEntity = 
            informationCommentRepository.findByInformationCommentNumber(informationCommentNumber);
            if (informationCommentEntity == null) return CustomResponse.notExistCommentNumber();

            boolean equalWriter = informationCommentEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermission();

            informationCommentRepository.deleteByInformationCommentNumber(informationCommentNumber);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteInformationHeart(String userEmail, Integer informationBoardNumber) {

        try {
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null) return CustomResponse.noExistUser();
            int userNumber = userEntity.getUserNumber();
            informationHeartRepository.deleteByUserNumberAndInformationBoardNumber(userNumber, informationBoardNumber);

    
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
    
        return CustomResponse.success();
    
    }

        @Override
        public ResponseEntity<ResponseDto> deleteAdminInformationComment(String adminEmail, int informationCommentNumber) {
            try {
                boolean existAdmin = adminRepository.existsByAdminEmail(adminEmail);
                if (!existAdmin) return CustomResponse.authenticationFailed();
    
                informationCommentRepository.deleteByInformationCommentNumber(informationCommentNumber);

            } catch (Exception exception) {
                exception.printStackTrace();
                return CustomResponse.databaseError();
            }
            return CustomResponse.success();
        }

        @Override
        public ResponseEntity<ResponseDto> deleteAdminInformationBoard(String adminEmail, int informationBoardNumber) {
            try {
                boolean existAdmin = adminRepository.existsByAdminEmail(adminEmail);
                if (!existAdmin) return CustomResponse.authenticationFailed();

                informationBoardRepository.deleteByInformationBoardNumber(informationBoardNumber);
                informationHeartRepository.deleteByInformationBoardNumber(informationBoardNumber);
                informationBoardRepository.deleteByInformationBoardNumber(informationBoardNumber);
                
            } catch (Exception exception) {
                exception.printStackTrace();
                return CustomResponse.databaseError();
            }
            return CustomResponse.success();
        }

        
}


    
    