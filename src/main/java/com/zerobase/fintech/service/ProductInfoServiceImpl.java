package com.zerobase.fintech.service;

import com.zerobase.fintech.domain.ProductInfo;
import com.zerobase.fintech.dto.ProductInfoDto;
import com.zerobase.fintech.repository.ProductInfoRepository;
import com.zerobase.fintech.type.OrganizationCode;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductInfoServiceImpl implements ProductInfoService{
    private final ProductInfoRepository productInfoRepository;


    @Override
    @CacheEvict(value = "PRODUCT", allEntries = true)
    public void checkProductInfo(ProductInfoDto productInfoDto) {
        /*
        productCode가 상품의 고유한 code인지 아니면 하위에 여러 상품을 둔 카테고리 개념의 code인지
        (ex. 정기예금 prodCode: 01, 정기적금 prodCode: 02) 정확하게 알 수가 없어서 해당 프로젝트에서는
        후자와 같은 카테고리 개념으로 인지하고 진행하였습니다.
        requestBody에 id가 포함되어 있지 않아 상품 식별은 productName을 기준으로 잡았습니다.
        따라서 레파지토리에 상품명과 기관정보가 전부 일치하는 데이터가 없을 경우 새로운 상품으로 등록,
        일치 데이터가 존재할 경우 업데이트를 하도록 구현하였습니다.
         */

        Optional<ProductInfo> optionalProductInfo = productInfoRepository
                .findByProductNameAndOrganizationCode(productInfoDto.getProductName(),
                        productInfoDto.getOrganizationCode());

        if (optionalProductInfo.isPresent()) {
            ProductInfo productInfo = optionalProductInfo.get();
            productInfo.setProductCode(productInfoDto.getProductCode());
            productInfo.setProductMaximumInterest(productInfoDto.getProductMaximumInterest());
            productInfo.setProductMinimumInterest(productInfoDto.getProductMinimumInterest());

            productInfoRepository.save(productInfo);

        } else {
            ProductInfo productInfo = productInfoDto.toEntity();
            productInfoRepository.save(productInfo);
        }

    }

    @Override
    @Cacheable(value = "PRODUCT", key = "#organizationCode")
    public List<ProductInfoDto> getProductInformation(OrganizationCode organizationCode) {

        List<ProductInfo> productInfoList;

        if (organizationCode == OrganizationCode.NONE) {
            productInfoList = productInfoRepository.findAll();
        } else {
            productInfoList = productInfoRepository
                    .findAllByOrganizationCode(organizationCode);
        }

        return productInfoList.stream()
                .map(ProductInfoDto::of)
                .collect(Collectors.toList());
    }

}
