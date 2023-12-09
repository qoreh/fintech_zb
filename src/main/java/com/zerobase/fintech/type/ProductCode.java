package com.zerobase.fintech.type;

import com.fasterxml.jackson.annotation.JsonValue;
import com.zerobase.fintech.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.zerobase.fintech.type.ErrorCode.PRODUCT_NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ProductCode {
    PRODUCT_ONE("001"),
    PRODUCT_TWO("002"),
    PRODUCT_THREE("003");

    private final String code;
    /*
    public static boolean isExist(String parameter) {
        for (ProductCode productCode : ProductCode.values()) {
            if (productCode.code.equals(parameter)) {
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

    private static final Map<String, ProductCode> BY_CODE =
            Stream.of(values()).collect(Collectors.toMap(ProductCode::getCode, e -> e));

    public static ProductCode find(String dbData) {
        ProductCode productCode = BY_CODE.get(dbData);
        if (ObjectUtils.isEmpty(productCode))
            throw new CustomException(PRODUCT_NOT_FOUND);

        return productCode;
    }
}
