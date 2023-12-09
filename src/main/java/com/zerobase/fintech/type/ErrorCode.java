package com.zerobase.fintech.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ORGANIZATION_NOT_FOUND("존재하지 않는 기관입니다."),
    PRODUCT_NOT_FOUND("존재하지 않는 상품입니다."),
    ALREADY_REGISTERED_USER("이미 존재하는 사용자입니다.")
    ;

    private final String description;
}
