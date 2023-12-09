package com.zerobase.fintech.service;

import com.zerobase.fintech.dto.UserInfoDto;
import com.zerobase.fintech.dto.UserResponseDto;

public interface UserInfoService {
    String checkUserInfo(UserInfoDto userInfoDto);

    UserInfoDto getUserInfo(String userKey);
}
