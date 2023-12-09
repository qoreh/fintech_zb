package com.zerobase.fintech.service;

import com.zerobase.fintech.dto.ProductInfoDto;
import com.zerobase.fintech.type.OrganizationCode;

import java.util.List;

public interface ProductInfoService {
    void checkProductInfo(ProductInfoDto productInfoDto);


    List<ProductInfoDto> getProductInformation(OrganizationCode organizationCode);
}
