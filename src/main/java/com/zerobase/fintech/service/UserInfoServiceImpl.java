package com.zerobase.fintech.service;

import com.zerobase.fintech.domain.UserInfo;
import com.zerobase.fintech.dto.UserInfoDto;
import com.zerobase.fintech.encrypt.EncryptComponent;
import com.zerobase.fintech.exception.CustomException;
import com.zerobase.fintech.repository.UserInfoRepository;
import com.zerobase.fintech.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.zerobase.fintech.type.ErrorCode.ALREADY_REGISTERED_USER;

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
                throw new CustomException(ALREADY_REGISTERED_USER);
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
