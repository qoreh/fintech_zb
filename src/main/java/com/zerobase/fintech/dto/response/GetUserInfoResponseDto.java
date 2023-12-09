package com.zerobase.fintech.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserInfoResponseDto {
    private PrivateUserData data;
    @ApiModelProperty(example = "00")
    private String responseCode;
    @ApiModelProperty(example = "success")
    private String responseMessage;
    @Data
    @Builder
    public static class PrivateUserData {
        @ApiModelProperty(example = "833efb9aee7745ceae735f6e5a71d9c9")
        private String userKey;
        @ApiModelProperty(example = "123456-0987654")
        private String userRegistrationNumber;
    }
}
