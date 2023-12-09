package com.zerobase.fintech.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrganizationCode {
    NONE("none"),
    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002");

    private final String code;

    public static boolean isExist(String parameter) {
        for (OrganizationCode orgCode : OrganizationCode.values()) {
            if (orgCode.getCode().equals(parameter)) {
                return true;
            }
        }
        return false;
    }

}
