package com.teamproject.devTalks.service.implement.recommendation;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.entity.recommendation.RecommendationEntity;
import com.teamproject.devTalks.entity.user.UserEntity;
import com.teamproject.devTalks.repository.recommendation.RecommendationRepository;
import com.teamproject.devTalks.repository.user.UserRepository;
import com.teamproject.devTalks.service.recommendation.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImplement implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final UserRepository userRepository;
    @Override
        public ResponseEntity<ResponseDto> postRecommendation(int senderId, int receiverId) {


            try {

                UserEntity sendUserEntity = userRepository.findByUserNumber(senderId);
                if(sendUserEntity == null) return CustomResponse.authenticationFailed();

                UserEntity receiveUserEntity = userRepository.findByUserNumber(receiverId);
                if(receiveUserEntity == null) return CustomResponse.noExistUser();

                boolean isSameUser = (senderId == receiverId);
                if(isSameUser) return CustomResponse.cannotRecommendToSelf();

                boolean isExistRecommendation =
                        recommendationRepository.existsBySenderUserNumberAndReceiverUserNumber(senderId,receiverId);

                if(isExistRecommendation) return CustomResponse.alreadyRecommended();

                RecommendationEntity recommendation =
                        new RecommendationEntity(senderId,receiverId);

                recommendationRepository.save(recommendation);

            }catch (Exception exception){
                exception.printStackTrace();
                return CustomResponse.databaseError();
            }

            return CustomResponse.success();
        }

    @Override
    public ResponseEntity<ResponseDto> deleteRecommendation(int senderId, int receiverId) {

        try {

            UserEntity sendUserEntity = userRepository.findByUserNumber(senderId);
            if(sendUserEntity == null) return CustomResponse.authenticationFailed();

            UserEntity receiveUserEntity = userRepository.findByUserNumber(receiverId);
            if(receiveUserEntity == null) return CustomResponse.noExistUser();

            RecommendationEntity recommendationEntity =
                    recommendationRepository.findBySenderUserNumberAndReceiverUserNumber(senderId,receiverId);

            if(recommendationEntity == null) return CustomResponse.noExistRecommendation();

            recommendationRepository.delete(recommendationEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.success();
    }
}
