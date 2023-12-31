package com.zerobase.fintech.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;
@Getter
@Builder
public class ProductInfoResponseDto {
    List<ProductInfoDto> data;
    @ApiModelProperty(example = "00")
    String responseCode;
    @ApiModelProperty(example = "success")
    String responseMessage;

}
