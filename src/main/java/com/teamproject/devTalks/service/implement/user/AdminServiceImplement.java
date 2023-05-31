package com.teamproject.devTalks.service.implement.user;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.admin.*;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.user.AdminSignInResponseDto;
import com.teamproject.devTalks.dto.response.user.GetAdminInfoResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserForAdminResponseDto;
import com.teamproject.devTalks.dto.response.user.GetUserListForAdminResponseDto;
import com.teamproject.devTalks.entity.hashTag.UserHashtagEntity;
import com.teamproject.devTalks.entity.recommendation.RecommendationEntity;
import com.teamproject.devTalks.entity.resultSet.UserListResultSet;
import com.teamproject.devTalks.entity.user.AdminEntity;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.provider.JwtProvider;
import com.teamproject.devTalks.repository.hashTag.UserHashtagRepository;
import com.teamproject.devTalks.repository.recommendation.RecommendationRepository;
import com.teamproject.devTalks.repository.user.AdminRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.user.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImplement implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final UserHashtagRepository userHashtagRepository;
    private final RecommendationRepository recommendationRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<ResponseDto> adminSignUp(AdminSignUpRequestDto dto) {

        String adminEmail = dto.getAdminEmail();
        String adminNickname = dto.getAdminNickname();
        String adminPhoneNumber = dto.getAdminPhoneNumber();

        String encodedPassword =passwordEncoder.encode(dto.getAdminPassword());
        dto.setAdminPassword(encodedPassword);

        try {

            boolean isExistEmail = adminRepository.existsByAdminEmail(adminEmail);
            if(isExistEmail) return CustomResponse.existEmail();

            boolean isExistNickname = adminRepository.existsByAdminNickname(adminNickname);
            if(isExistNickname) return CustomResponse.existNickname();

            boolean isExistPhoneNumber = adminRepository.existsByAdminPhoneNumber(adminPhoneNumber);
            if(isExistPhoneNumber) return CustomResponse.existPhoneNumber();

            AdminEntity adminEntity = new AdminEntity(dto);
            adminRepository.save(adminEntity);

        } catch (Exception exception){
            exception.printStackTrace();
            CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super AdminSignInResponseDto> adminSignIn(AdminSignInRequestDto dto) {

        AdminSignInResponseDto body = null;
        String adminEmail = dto.getAdminEmail();
        String password = dto.getAdminPassword();
        String ROLE = "ADMIN";

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) return CustomResponse.signInFailed();

            String encodedPassword = adminEntity.getAdminPassword();
            boolean isEqualPassword = passwordEncoder.matches(password,encodedPassword);

            if(!isEqualPassword) return CustomResponse.signInFailed();

            String jwt = jwtProvider.createJwt(adminEmail,ROLE);
            body = new AdminSignInResponseDto(jwt);

        }catch (Exception exception){
            exception.printStackTrace();
            CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<ResponseDto> adminUpdate(
            String adminEmail, UpdateAdminRequestDto dto) {

        String password = dto.getPassword();
        String adminNickname = dto.getAdminNickname();
        String adminPhoneNumber = dto.getAdminPhoneNumber();

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) return CustomResponse.authenticationFailed();

            String encodedPassword = adminEntity.getAdminPassword();
            boolean isEqualPassword = passwordEncoder.matches(password,encodedPassword);

            if(!isEqualPassword) CustomResponse.passwordMismatch();

            boolean isExistNickname =
                    adminRepository.existsByAdminNicknameAndAdminEmailNot(adminNickname,adminEmail);
            if(isExistNickname) return CustomResponse.existNickname();

            boolean isExistPhoneNumber =
                    adminRepository.existsByAdminPhoneNumberAndAdminEmailNot(adminPhoneNumber,adminEmail);
            if(isExistPhoneNumber) return CustomResponse.existPhoneNumber();

            AdminEntity updateAdmin = new AdminEntity(adminEntity,dto);

            adminRepository.save(updateAdmin);

        }catch (Exception exception){
            exception.printStackTrace();
            CustomResponse.databaseError();
        }
        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> updateAdminPassword(
            String adminEmail, UpdateAdminPasswordRequestDto dto
    ) {

        String currentPassword = dto.getCurrentPassword();
        String changePassword = dto.getChangePassword();

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) return CustomResponse.authenticationFailed();

            String encodedCurrentPassword = adminEntity.getAdminPassword();

            boolean isEqualPassword  = passwordEncoder.matches(currentPassword,encodedCurrentPassword);
            if(!isEqualPassword) return CustomResponse.passwordMismatch();

            String encodedChangePassword = passwordEncoder.encode(changePassword);
            adminEntity.setAdminPassword(encodedChangePassword);

            adminRepository.save(adminEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteAdmin(
            String adminEmail, DeleteAdminRequestDto dto) {

        String password = dto.getAdminPassword();


        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) CustomResponse.authenticationFailed();

            String encodedPassword = adminEntity.getAdminPassword();
            boolean isEqualPassword = passwordEncoder.matches(password,encodedPassword);

            if(!isEqualPassword) return CustomResponse.passwordMismatch();
            adminRepository.deleteByAdminEmail(adminEmail);


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetAdminInfoResponseDto> getAdminUpdate(String adminEmail) {

        GetAdminInfoResponseDto body = null;

        try {

            AdminEntity adminEntity = adminRepository.findByAdminEmail(adminEmail);
            if(adminEntity == null) return CustomResponse.authenticationFailed();

            body = new GetAdminInfoResponseDto(adminEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetUserForAdminResponseDto> getUserDetail(Integer userNumber, String adminEmail) {

        if(userNumber == null) return CustomResponse.validationFailed();
        GetUserForAdminResponseDto body = null;
        List<String> hashtagList = new ArrayList<>();

        try {

            UserEntity userEntity = userRepository.findByUserNumber(userNumber);
            if(userEntity == null) return CustomResponse.noExistUser();

            List<UserHashtagEntity> userHashtagEntities =
                    userHashtagRepository.findAllByUserNumber(userNumber);

            for(UserHashtagEntity userHashtagEntity: userHashtagEntities){
                String hashtag = userHashtagEntity.getUserHashtag();
                hashtagList.add(hashtag);
            }

            List<RecommendationEntity> recommendation =
                    recommendationRepository.findByReceiverUserNumber(userNumber);

            int recommendationCount = recommendation.size();

            body = new GetUserForAdminResponseDto(hashtagList,userEntity,recommendationCount);


        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetUserListForAdminResponseDto> getUserList(String adminEmail) {

        GetUserListForAdminResponseDto body = null;

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFailed();

            List<UserListResultSet> resultSets = userRepository.getUserList();
            body = new GetUserListForAdminResponseDto(resultSets);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


}