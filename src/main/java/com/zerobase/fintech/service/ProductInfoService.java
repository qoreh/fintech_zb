package com.zerobase.fintech.service;

import com.zerobase.fintech.dto.ProductInfoDto;

import java.util.List;

public interface ProductInfoService {
    void checkProductInfo(ProductInfoDto productInfoDto);


    List<ProductInfoDto> getProductInformation(String organizationCode);
}
