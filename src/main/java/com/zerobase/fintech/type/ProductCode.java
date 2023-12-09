package com.zerobase.fintech.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCode {
    PRODUCT_001("001"),
    PRODUCT_002("002"),
    PRODUCT_003("003");

    private final String code;

    public static boolean isExist(String parameter) {
        for (ProductCode productCode : ProductCode.values()) {
            if (productCode.code.equals(parameter)) {
                return true;
            }
        }
        return false;
    }
}
