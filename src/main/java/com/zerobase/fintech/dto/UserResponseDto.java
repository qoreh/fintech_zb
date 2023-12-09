package com.zerobase.fintech.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UserResponseDto {
    private Object data;
    private String responseCode;
    private String responseMessage;


    @Data
    @Builder
    public static class UserData {
        private String userKey;
    }

    @Data
    @Builder
    public static class privateUserData {
        private String userKey;
        private String userRegistrationNumber;
    }

}
