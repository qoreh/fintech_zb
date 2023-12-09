package com.zerobase.fintech.dto.response;

import com.zerobase.fintech.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseDto {
    private ErrorCode errorCode;
    public String errorMessage;
}
