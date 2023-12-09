package com.zerobase.fintech.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponseDto {
    @ApiModelProperty(example = "00")
    String responseCode;
    @ApiModelProperty(example = "success")
    String responseMessage;
}
