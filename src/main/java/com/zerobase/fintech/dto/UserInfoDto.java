package com.zerobase.fintech.dto;

import com.zerobase.fintech.domain.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class UserInfoDto {
    @ApiModelProperty(value = "Hidden Variable", hidden = true)
    String userKey;
    @ApiModelProperty(example = "100000")
    Long userIncomeAmount;
    @ApiModelProperty(example = "백엔드")
    String userName;
    @ApiModelProperty(example = "900101-1234567")
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
