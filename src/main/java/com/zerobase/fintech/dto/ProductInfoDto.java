package com.zerobase.fintech.dto;

import com.zerobase.fintech.domain.ProductInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfoDto {
    @ApiModelProperty(example = "00001")
    String organizationCode;
    @ApiModelProperty(example = "001")
    String productCode;
    @ApiModelProperty(example = "9.9")
    Double productMaximumInterest;
    @ApiModelProperty(example = "1.1")
    Double productMinimumInterest;
    @ApiModelProperty(example = "상품 1")
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
