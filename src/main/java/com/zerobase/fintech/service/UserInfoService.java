package com.zerobase.fintech.service;

import com.zerobase.fintech.dto.UserInfoDto;

public interface UserInfoService {
    String checkUserInfo(UserInfoDto userInfoDto);

    UserInfoDto getUserInfo(String userKey);
}
