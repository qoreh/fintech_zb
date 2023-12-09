package com.zerobase.fintech.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ReceiveUserInfoResponseDto {
    private UserData data;
    @ApiModelProperty(example = "00")
    private String responseCode;
    @ApiModelProperty(example = "success")
    private String responseMessage;


    @Data
    @Builder
    public static class UserData {
        @ApiModelProperty(example = "833efb9aee7745ceae735f6e5a71d9c9")
        private String userKey;
    }


}
