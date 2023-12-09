package com.zerobase.fintech.controller;

import com.zerobase.fintech.dto.response.GetUserInfoResponseDto;
import com.zerobase.fintech.dto.UserInfoDto;
import com.zerobase.fintech.dto.response.ReceiveUserInfoResponseDto;
import com.zerobase.fintech.service.UserInfoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user-information-controller", description = "유저 정보 API")
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
@RestController
public class UserInformationController {
    private final UserInfoService userInfoService;


    @ApiOperation(value = "유저 정보 수신 API", notes = "유저 정보를 받는 API")
    @PostMapping("/information")
    public ResponseEntity<ReceiveUserInfoResponseDto> receiveInformation(
            @RequestBody UserInfoDto userInfoDto
    ) {
        String userKey = userInfoService.checkUserInfo(userInfoDto);
        ReceiveUserInfoResponseDto.UserData userData = ReceiveUserInfoResponseDto
                .UserData.builder()
                .userKey(userKey)
                .build();
        ReceiveUserInfoResponseDto response = ReceiveUserInfoResponseDto.builder()
                .data(userData)
                .responseCode("00")
                .responseMessage("success")
                .build();

        return ResponseEntity.ok().body(response);
    }


    @ApiOperation(value = "유저 정보 조회")
    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<GetUserInfoResponseDto> getUserInformation(
            @PathVariable("userKey") String userKey
    ) {
        UserInfoDto userInfoDto = userInfoService.getUserInfo(userKey);
        GetUserInfoResponseDto response;
        if (ObjectUtils.isEmpty(userInfoDto)) {
            response = GetUserInfoResponseDto.builder()
                    .responseMessage("유저 정보가 존재하지 않습니다.")
                    .responseCode("02")
                    .build();
        } else {
            GetUserInfoResponseDto.PrivateUserData data = GetUserInfoResponseDto
                    .PrivateUserData.builder()
                    .userRegistrationNumber(userInfoDto.getUserRegistrationNumber())
                    .userKey(userInfoDto.getUserKey())
                    .build();
            response = GetUserInfoResponseDto.builder()
                    .data(data)
                    .responseCode("00")
                    .responseMessage("success")
                    .build();
        }

        return ResponseEntity.ok().body(response);
    }


}
