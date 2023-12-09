package com.zerobase.fintech.exception;

import com.zerobase.fintech.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zerobase.fintech.type.ErrorCode.ORGANIZATION_NOT_FOUND;
import static com.zerobase.fintech.type.ErrorCode.PRODUCT_NOT_FOUND;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException e) {
        ErrorResponseDto response = ErrorResponseDto.builder()
                .errorMessage(e.getErrorMessage())
                .errorCode(e.getErrorCode())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e
    ) {
        ErrorResponseDto response;
        String message = e.getMessage();
        if (message.contains("ProductCode")) {
            response = ErrorResponseDto.builder()
                    .errorCode(PRODUCT_NOT_FOUND)
                    .errorMessage(PRODUCT_NOT_FOUND.getDescription())
                    .build();
        } else if (message.contains("OrganizationCode")) {
            response = ErrorResponseDto.builder()
                    .errorCode(ORGANIZATION_NOT_FOUND)
                    .errorMessage(ORGANIZATION_NOT_FOUND.getDescription())
                    .build();
        } else {
            response = ErrorResponseDto.builder()
                    .errorMessage("HttpMessageNotReadableException")
                    .build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
