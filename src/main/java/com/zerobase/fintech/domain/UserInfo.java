package com.zerobase.fintech.domain;

import com.zerobase.fintech.aop.Encrypt;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userKey;
    @Encrypt
    private String userRegistrationNumber;
    private String userName;
    private Long userIncomeAmount;
}
