package com.zerobase.fintech.dto;

import lombok.*;

import java.util.List;
@Getter
@Builder
public class ProductInfoResponseDto {
    List<ProductInfoDto> data;
    String responseCode;
    String responseMessage;

}
