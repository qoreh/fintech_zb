package com.zerobase.fintech.service;

import com.zerobase.fintech.domain.UserInfo;
import com.zerobase.fintech.dto.UserInfoDto;
import com.zerobase.fintech.dto.UserResponseDto;
import com.zerobase.fintech.encrypt.EncryptComponent;
import com.zerobase.fintech.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService{
    private final EncryptComponent encryptComponent;
    private final UserInfoRepository userInfoRepository;
    @Override
    public String checkUserInfo(UserInfoDto userInfoDto) {

        try {
            String encryptedNumber = encryptComponent
                    .encryptString(userInfoDto.getUserRegistrationNumber());
            if (userInfoRepository.findByUserRegistrationNumber(encryptedNumber).isPresent()) {
                throw new RuntimeException("이미 존재하는 유저 정보입니다.");
            } else {
                userInfoDto.setUserRegistrationNumber(encryptedNumber);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String userKey = generateUserKey();
        userInfoRepository.save(userInfoDto.toEntity(userKey));

        return userKey;
    }

    @Override
    public UserInfoDto getUserInfo(String userKey) {
        // 정보 가져와서 주민번호 복호화
        Optional<UserInfo> optionalUserInfo= userInfoRepository.findByUserKey(userKey);

        if (!optionalUserInfo.isPresent()) {
            return null;
        }

        UserInfo userInfo = optionalUserInfo.get();
        String decryptedNumber;

        try {
            decryptedNumber = encryptComponent
                    .decryptString(userInfo.getUserRegistrationNumber());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return UserInfoDto.of(decryptedNumber, userInfo);
    }

    private String generateUserKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
