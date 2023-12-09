package com.zerobase.fintech.dto;

import com.zerobase.fintech.domain.ProductInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfoDto {

    String organizationCode;
    String productCode;
    Double productMaximumInterest;
    Double productMinimumInterest;
    String productName;

    public ProductInfo toEntity() {
        return ProductInfo.builder()
                .organizationCode(organizationCode)
                .productCode(productCode)
                .productName(productName)
                .productMaximumInterest(productMaximumInterest)
                .productMinimumInterest(productMinimumInterest)
                .build();
    }

    public static ProductInfoDto of(ProductInfo productInfo) {
        return ProductInfoDto.builder()
                .organizationCode(productInfo.getOrganizationCode())
                .productCode(productInfo.getProductCode())
                .productName(productInfo.getProductName())
                .productMaximumInterest(productInfo.getProductMaximumInterest())
                .productMinimumInterest(productInfo.getProductMinimumInterest())
                .build();
    }

}
