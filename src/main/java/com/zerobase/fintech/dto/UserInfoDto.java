package com.zerobase.fintech.dto;

import com.zerobase.fintech.domain.UserInfo;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    String userKey;
    Long userIncomeAmount;
    String userName;
    String userRegistrationNumber;

    public UserInfo toEntity(String userKey) {
        return UserInfo.builder()
                .userKey(userKey)
                .userIncomeAmount(userIncomeAmount)
                .userRegistrationNumber(userRegistrationNumber)
                .userName(userName)
                .build();
    }

    public static UserInfoDto of(String userRegistrationNumber, UserInfo userInfo) {
        return UserInfoDto.builder()
                .userKey(userInfo.getUserKey())
                .userIncomeAmount(userInfo.getUserIncomeAmount())
                .userName(userInfo.getUserName())
                .userRegistrationNumber(userRegistrationNumber)
                .build();
    }
}
