package com.zerobase.fintech.type;

import com.fasterxml.jackson.annotation.JsonValue;
import com.zerobase.fintech.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zerobase.fintech.type.ErrorCode.ORGANIZATION_NOT_FOUND;

@AllArgsConstructor
@Getter
public enum OrganizationCode {
    NONE("none"),
    ORGANIZATION_ONE("00001"),
    ORGANIZATION_TWO("00002");

    private final String code;

    /*
    public static boolean isExist(String parameter) {
        for (OrganizationCode orgCode : OrganizationCode.values()) {
            if (orgCode.getCode().equals(parameter)) {
                return true;
            }
        }
        return false;
    }
     */
    @JsonValue
    public String getCode(){
        return code;
    }

    private static final Map<String, OrganizationCode> BY_CODE =
            Stream.of(values()).collect(Collectors.toMap(OrganizationCode::getCode, e -> e));

    public static OrganizationCode find(String dbData) {
        OrganizationCode organizationCode = BY_CODE.get(dbData);
        if (ObjectUtils.isEmpty(organizationCode))
            throw new CustomException(ORGANIZATION_NOT_FOUND);

        return organizationCode;
    }

}
