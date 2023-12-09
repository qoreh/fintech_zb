package com.zerobase.fintech.exception;

import com.zerobase.fintech.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private ErrorCode errorCode;
    private String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }
}
