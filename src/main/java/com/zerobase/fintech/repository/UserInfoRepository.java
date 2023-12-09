package com.zerobase.fintech.repository;

import com.zerobase.fintech.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUserRegistrationNumber(String userRegistrationNumber);
    Optional<UserInfo> findByUserKey(String userKey);
}
