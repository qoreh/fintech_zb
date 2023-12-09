package com.zerobase.fintech.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseDto {
    String responseCode;
    String responseMessage;
}
