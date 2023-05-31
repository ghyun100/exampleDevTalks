package com.teamproject.devTalks.controller.recommendation;

import com.teamproject.devTalks.dto.response.ResponseDto;
import com.teamproject.devTalks.security.UserPrinciple;
import com.teamproject.devTalks.service.recommendation.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping ("{receiverId}")
    public ResponseEntity<ResponseDto> postRecommendation(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @PathVariable int receiverId
            ){

        int senderId = userPrinciple.getUserNumber();
        ResponseEntity<ResponseDto> response =
                recommendationService.postRecommendation(senderId,receiverId);

        return response;

    }

    @DeleteMapping({"/{receiverId}"})
    public ResponseEntity<ResponseDto> deleteRecommendation(
            @AuthenticationPrincipal UserPrinciple userPrinciple,
            @PathVariable int receiverId
    ){
        int senderId = userPrinciple.getUserNumber();
        ResponseEntity<ResponseDto> response =
                recommendationService.deleteRecommendation(senderId,receiverId);

        return response;

    }

}
