package com.zerobase.fintech.controller;

import com.zerobase.fintech.dto.ProductInfoDto;
import com.zerobase.fintech.dto.CommonResponseDto;
import com.zerobase.fintech.dto.ProductInfoResponseDto;
import com.zerobase.fintech.service.ProductInfoService;
import com.zerobase.fintech.type.OrganizationCode;
import com.zerobase.fintech.type.ProductCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fintech/v1/product")
public class ProductInformationController {

    private final ProductInfoService productInfoService;

    @PostMapping("/information")
    public ResponseEntity<?> receiveInformation(
            @RequestBody ProductInfoDto productInfoDto) {

        if (!ProductCode.isExist(productInfoDto.getProductCode())) {
            throw new RuntimeException("존재하지 않는 상품코드입니다.");
        }
        if (!OrganizationCode.isExist(productInfoDto.getOrganizationCode())) {
            throw new RuntimeException("존재하지 않는 기관입니다.");
        }

        productInfoService.checkProductInfo(productInfoDto);
        CommonResponseDto response = new CommonResponseDto("00", "success");

        return ResponseEntity.ok().body(response);
    }

    /*
    organizationCode가 "00001", "00002"의 형태로 전달되며
    NONE("none")일때는 모든 기관의 정보를 조회하는 것으로 설정하였습니다.
     */
    @GetMapping("/{organizationCode}")
    public ResponseEntity<?> requestInformation(
            @PathVariable("organizationCode") String organizationCode) {

        if (!OrganizationCode.isExist(organizationCode)) {
            throw new RuntimeException("존재하지 않는 기관입니다.");
        }
        List<ProductInfoDto> productInfoDtoList =
                productInfoService.getProductInformation(organizationCode);


        ProductInfoResponseDto response = ProductInfoResponseDto.builder()
                .data(productInfoDtoList)
                .responseCode("00")
                .responseMessage("success")
                .build();


        return ResponseEntity.ok().body(response);
    }

}
