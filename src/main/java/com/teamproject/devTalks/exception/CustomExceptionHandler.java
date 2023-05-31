package com.teamproject.devTalks.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.teamproject.devTalks.common.util.CustomResponse;
import com.teamproject.devTalks.dto.response.ResponseDto;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handlerHttpMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        return CustomResponse.validationFailed();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handlerMethodArgumentNotValidException(
        MethodArgumentNotValidException exception) {
        return CustomResponse.validationFailed();
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ResponseDto> handlerExpiredJwt(
            ExpiredJwtException exception){
        return CustomResponse.expiredJwt();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto> methodArgumentTypeMismatchException(
        MethodArgumentTypeMismatchException exception
    ) {
        return CustomResponse.validationFailed();
    }


}
