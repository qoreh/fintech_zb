package com.zerobase.fintech.domain;

import com.zerobase.fintech.converter.OrganizationConverter;
import com.zerobase.fintech.converter.ProductCodeConverter;
import com.zerobase.fintech.type.OrganizationCode;
import com.zerobase.fintech.type.ProductCode;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT_INFO")
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = OrganizationConverter.class)
    private OrganizationCode organizationCode;
    @Convert(converter = ProductCodeConverter.class)
    private ProductCode productCode;
    private String productName;
    private Double productMaximumInterest;
    private Double productMinimumInterest;


}
