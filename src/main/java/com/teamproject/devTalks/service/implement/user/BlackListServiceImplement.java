package com.teamproject.devTalks.service.implement.user;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.request.user.PostBlackListRequestDto;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.dto.response.user.GetBlackListResponseDto;
import com.teamproject.devTalks.entity.user.BlackListEntity;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.repository.user.AdminRepository;
import com.teamproject.devTalks.repository.user.BlackListRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.user.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlackListServiceImplement implements BlackListService {

    private final BlackListRepository blackListRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> postBlackList(String adminEmail, PostBlackListRequestDto dto) {

        int userNumber = dto.getUserNumber();
        String reason = dto.getReason();

        try {

           boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
           if (!isExistAdmin) return CustomResponse.authenticationFailed();

           UserEntity userEntity = userRepository.findByUserNumber(userNumber);
           if(userEntity == null) return CustomResponse.noExistUser();

           boolean isExistBlackUser = blackListRepository.existsByUserNumber(userNumber);
           if(isExistBlackUser) return CustomResponse.alreadyBlacklisted();

           BlackListEntity blackListEntity = new BlackListEntity(userEntity,reason);
           blackListRepository.save(blackListEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBlackList(String adminEmail, Integer userNumber) {

        try {
            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFailed();

            boolean isExistUser = userRepository.existsByUserNumber(userNumber);
            if(!isExistUser) return CustomResponse.noExistUser();

            BlackListEntity blackListEntity = blackListRepository.findByUserNumber(userNumber);
            if(blackListEntity == null) return CustomResponse.noExistUser();

            blackListRepository.delete(blackListEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }

    @Override
    public ResponseEntity<? super GetBlackListResponseDto> getBlackList(String adminEmail) {

        GetBlackListResponseDto body = null;

        try {

            boolean isExistAdmin = adminRepository.existsByAdminEmail(adminEmail);
            if(!isExistAdmin) return CustomResponse.authenticationFailed();

            List<BlackListEntity> blackListEntityList
                    = blackListRepository.findAllByOrderByCreatedAtDesc();

            body = new GetBlackListResponseDto(blackListEntityList);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


}
