package com.zerobase.fintech.domain;

import javax.persistence.*;

@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Encrypt로 암호화 복호화 AOP 처리
    private String userKey;
    //@Encrypt로 암호화 복호화 AOP 처리
    private String userRegistrationNumber;

    private String userName;
    private Long userIncomeAmount;


}
