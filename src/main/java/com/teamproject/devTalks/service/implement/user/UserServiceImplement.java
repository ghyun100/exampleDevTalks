package com.teamproject.devTalks.service.implement.user;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.user.*;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.user.FindUserEmailResponseDto;
import com.teamproject.devTalks.dto.response.user.GetMyInfoResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserInformationResponseDto;
import com.teamproject.devTalks.dto.response.user.SignInResponseDto;
import com.teamproject.devTalks.entity.hashTag.UserHashtagEntity;
import com.teamproject.devTalks.entity.recommendation.RecommendationEntity;
import com.teamproject.devTalks.entity.report.ReportEntity;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.provider.JwtProvider;
import com.teamproject.devTalks.provider.MailProvider;
import com.teamproject.devTalks.repository.hashTag.UserHashtagRepository;
import com.teamproject.devTalks.repository.recommendation.RecommendationRepository;
import com.teamproject.devTalks.repository.report.ReportRepository;
import com.teamproject.devTalks.repository.user.BlackListRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.user.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final UserHashtagRepository userHashtagRepository;
    private final BlackListRepository blackListRepository;
    private final ReportRepository reportRepository;
    private final RecommendationRepository recommendationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final MailProvider mailProvider;

    @Override
    public ResponseEntity<ResponseDto> userSignUp(UserSignUpRequestDto dto) {

        String userEmail = dto.getUserEmail();
        String userNickname = dto.getUserNickname();
        String userPhoneNumber = dto.getUserPhoneNumber();

        List<String> hashtagList = dto.getUserHashtag();
        List<UserHashtagEntity> userHashtagList = new ArrayList<>();

        String encodedUserPassword = passwordEncoder.encode(dto.getUserPassword());
        dto.setUserPassword(encodedUserPassword);

        try {

            boolean isBlackListed =
                    blackListRepository.existsByUserEmailOrUserPhoneNumber(userEmail,userPhoneNumber);

            if(isBlackListed) return CustomResponse.alreadyBlacklisted();

            boolean isExistEmail = userRepository.existsByUserEmail(userEmail);
            if (isExistEmail)
                return CustomResponse.existEmail();

            boolean isExistNickname = userRepository.existsByUserNickname(userNickname);
            if (isExistNickname)
                return CustomResponse.existNickname();

            boolean isExistPhoneNumber = userRepository.existsByUserPhoneNumber(userPhoneNumber);
            if (isExistPhoneNumber)
                return CustomResponse.existPhoneNumber();

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

            int userNumber = userEntity.getUserNumber();

            for (String hashtag : hashtagList) {

                UserHashtagEntity userHashTagEntity = new UserHashtagEntity(userNumber, hashtag);

                userHashtagList.add(userHashTagEntity);

            }

            userHashtagRepository.saveAll(userHashtagList);

        } catch (Exception exception) {
            exception.printStackTrace();
            CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> userSignIn(UserSignInRequestDto dto) {

        SignInResponseDto body = null;
        String userEmail = dto.getUserEmail();
        String password = dto.getUserPassword();
        String ROLE = "USER";

        try {

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.signInFailed();

            String encodedPassword = userEntity.getUserPassword();
            boolean isEqualPassword = passwordEncoder.matches(password, encodedPassword);
            if (!isEqualPassword)
                return CustomResponse.signInFailed();

            String jwt = jwtProvider.createJwt(userEmail, ROLE);
            body = new SignInResponseDto(jwt);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();

        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetMyInfoResponseDto> getMyInfo(String userEmail) {

        GetMyInfoResponseDto body = null;
        List<String> hashtagList = new ArrayList<>();

        try {

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();

            int userNumber = userEntity.getUserNumber();
            List<UserHashtagEntity> userHashtagEntities = userHashtagRepository.findAllByUserNumber(userNumber);

            for (UserHashtagEntity userHashTagEntity : userHashtagEntities) {
                String hashtag = userHashTagEntity.getUserHashtag();
                hashtagList.add(hashtag);
            }

            List<RecommendationEntity> recommendation = recommendationRepository.findByReceiverUserNumber(userNumber);

            int recommendationCount = recommendation.size();

            body = new GetMyInfoResponseDto(hashtagList, userEntity, recommendationCount);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();

        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetUserInformationResponseDto> getUserInformation(Integer userNumber) {

        if (userNumber == null)
            return CustomResponse.validationFailed();

        GetUserInformationResponseDto body = null;
        List<String> hashtagList = new ArrayList<>();

        try {

            UserEntity userEntity = userRepository.findByUserNumber(userNumber);
            if (userEntity == null)
                return CustomResponse.noExistUser();

            List<UserHashtagEntity> userHashtagEntities = userHashtagRepository.findAllByUserNumber(userNumber);

            for (UserHashtagEntity userHashtagEntity : userHashtagEntities) {
                String hashtag = userHashtagEntity.getUserHashtag();
                hashtagList.add(hashtag);
            }

            List<RecommendationEntity> recommendation = recommendationRepository.findByReceiverUserNumber(userNumber);

            int recommendationCount = recommendation.size();

            body = new GetUserInformationResponseDto(hashtagList, userEntity, recommendationCount);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> updateUser(String userEmail, UpdateUserRequestDto dto) {

        String password = dto.getPassword();
        String userNickname = dto.getUserNickname();
        String userPhoneNumber = dto.getUserPhoneNumber();

        List<String> hashtagList = dto.getUserHashtag();
        List<UserHashtagEntity> userHashtagList = new ArrayList<>();

        try {

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                CustomResponse.authenticationFailed();

            String encodedCurrentPassword = userEntity.getUserPassword();

            boolean isEqualPassword = passwordEncoder.matches(password, encodedCurrentPassword);
            if (!isEqualPassword)
                return CustomResponse.passwordMismatch();

            boolean isExistUserNickname = userRepository.existsByUserNicknameAndUserEmailNot(userNickname, userEmail);
            if (isExistUserNickname)
                return CustomResponse.existNickname();

            boolean isExistPhoneNumber = userRepository.existsByUserPhoneNumberAndUserEmailNot(userPhoneNumber,
                    userEmail);
            if (isExistPhoneNumber)
                return CustomResponse.existPhoneNumber();

            UserEntity updateUser = new UserEntity(userEntity, dto);

            userRepository.save(updateUser);

            int userNumber = userEntity.getUserNumber();

            List<UserHashtagEntity> currentUserHashTagEntities = userHashtagRepository.findAllByUserNumber(userNumber);

            userHashtagRepository.deleteAll(currentUserHashTagEntities);

            for (String hashtag : hashtagList) {

                UserHashtagEntity userHashtagEntity = new UserHashtagEntity(userNumber, hashtag);
                userHashtagList.add(userHashtagEntity);

            }

            userHashtagRepository.saveAll(userHashtagList);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> updateUserPassword(
            String userEmail, UpdateUserPasswordRequestDto dto) {
        String currentPassword = dto.getCurrentPassword();
        String changePassword = dto.getChangePassword();

        UserEntity userEntity = userRepository.findByUserEmail(userEmail);
        if (userEntity == null)
            CustomResponse.noExistUser();

        String encodedCurrentPassword = userEntity.getUserPassword();
        boolean isEqualPassword = passwordEncoder.matches(currentPassword, encodedCurrentPassword);

        if (!isEqualPassword)
            return CustomResponse.passwordMismatch();
        String encodedChangePassword = passwordEncoder.encode(changePassword);

        userEntity.setUserPassword(encodedChangePassword);
        userRepository.save(userEntity);

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> userDelete(String userEmail, DeleteUserRequestDto dto) {

        String password = dto.getUserPassword();

        try {

            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
            if (userEntity == null)
                return CustomResponse.noExistUser();

            String encodedPassword = userEntity.getUserPassword();
            boolean isEqualPassword = passwordEncoder.matches(password, encodedPassword);

            if (!isEqualPassword)
                return CustomResponse.passwordMismatch();

            int userNumber = userEntity.getUserNumber();

            List<UserHashtagEntity> userHashtagEntities =
                    userHashtagRepository.findAllByUserNumber(userNumber);

            if (userHashtagEntities != null)
                userHashtagRepository.deleteAll(userHashtagEntities);

            List<RecommendationEntity> sendRecommendations =
                    recommendationRepository.findBySenderUserNumber(userNumber);

            List<RecommendationEntity> receiveRecommendations =
                    recommendationRepository.findByReceiverUserNumber(userNumber);

            if (sendRecommendations != null)
                recommendationRepository.deleteAll(sendRecommendations);
            if (receiveRecommendations != null)
                recommendationRepository.deleteAll(receiveRecommendations);

            List<ReportEntity> sendReportList =
                    reportRepository.findByReporter(userNumber);

            List<ReportEntity> receiveReportList =
                    reportRepository.findByReported(userNumber);

            if(sendReportList != null)
                reportRepository.deleteAll(sendReportList);
            if(receiveReportList != null)
                reportRepository.deleteAll(receiveReportList);

            userRepository.deleteByUserEmail(userEmail);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super FindUserEmailResponseDto> findUserEmail(FindUserEmailRequestDto dto) {

        FindUserEmailResponseDto body = null;

        try {

            // 존재하지 않는 핸드폰 번호
            UserEntity userEntity = userRepository.findByUserPhoneNumber(dto.getUserPhoneNumber());
            if (userEntity == null)
                return CustomResponse.notExistUserPhoneNumber();

            body = new FindUserEmailResponseDto(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    // 비밀번호찾기
    @Override
    public ResponseEntity<ResponseDto> findUserPassword(FindUserPasswordRequestDto dto) {
        
        String userEmail = dto.getUserEmail();

        try {
            // 존재하지 않는 유저
            boolean existsUser = userRepository.existsByUserEmail(userEmail);    
            if (!existsUser) return CustomResponse.noExistUser();
            String text = UUID.randomUUID().toString();
            // 메일 보내기
            mailProvider.sendMail(userEmail, "임시비밀번호", text);
            
            // 현재비밀번호를 임시비밀번호로 바꾸기
            UserEntity userEntity = userRepository.findByUserEmail(userEmail);
        
            String temporaryPassword = text;
            String encodedTemporaryPassword = passwordEncoder.encode(temporaryPassword);
            userEntity.setUserPassword(encodedTemporaryPassword);
            userRepository.save(userEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public boolean isExistedUser(String userEmail) {
        
        boolean checkExistedUser = false;

        try {
            checkExistedUser = userRepository.existsByUserEmail(userEmail);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        return checkExistedUser;

    }

    @Override
    public Integer findByUserEmailEquals(String userEmail) {

        try { 
            Integer userNumber = userRepository.findByUserEmailEquals(userEmail);
            if (userNumber != null) return userNumber;

         } catch (Exception exception) {
            exception.printStackTrace();
            return null;
         }         
         return null;

    }

}