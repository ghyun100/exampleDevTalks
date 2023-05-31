package com.teamproject.devTalks.service.recommendation;

import com.teamproject.devTalks.dto.response.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface RecommendationService {
    ResponseEntity<ResponseDto> postRecommendation(int senderId, int receiverId);

    ResponseEntity<ResponseDto> deleteRecommendation(int senderId, int receiverId);


}
