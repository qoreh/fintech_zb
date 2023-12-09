package com.zerobase.fintech.controller;

import com.zerobase.fintech.dto.UserInfoDto;
import com.zerobase.fintech.dto.UserResponseDto;
import com.zerobase.fintech.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
@RestController
public class UserInformationController {
    private final UserInfoService userInfoService;

    @PostMapping("/information")
    public ResponseEntity<?> receiveInformation(@RequestBody UserInfoDto userInfoDto) {
        String userKey = userInfoService.checkUserInfo(userInfoDto);
        UserResponseDto.UserData userData = UserResponseDto.UserData.builder()
                .userKey(userKey)
                .build();
        UserResponseDto response = UserResponseDto.builder()
                .data(userData)
                .responseCode("00")
                .responseMessage("success")
                .build();

        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<?> getUserInformation(@PathVariable("userKey") String userKey) {
        UserInfoDto userInfoDto = userInfoService.getUserInfo(userKey);
        UserResponseDto response;
        if (ObjectUtils.isEmpty(userInfoDto)) {
            response = UserResponseDto.builder()
                    .responseMessage("유저 정보가 존재하지 않습니다.")
                    .responseCode("02")
                    .build();
        } else {
            UserResponseDto.privateUserData data = UserResponseDto.privateUserData.builder()
                    .userRegistrationNumber(userInfoDto.getUserRegistrationNumber())
                    .userKey(userInfoDto.getUserKey())
                    .build();
            response = UserResponseDto.builder()
                    .data(data)
                    .responseCode("00")
                    .responseMessage("success")
                    .build();
        }

        return ResponseEntity.ok().body(response);
    }


}
